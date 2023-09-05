package com.api.rest.softlond.controller;

import com.api.rest.softlond.entity.Sale;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;
import com.api.rest.softlond.service.ISaleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    private final ISaleService saleService;
    Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    public SaleController(ISaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> findAll() throws LocalNotFoundException, LocalInternalServerErrorException {
        List<Sale> sales = saleService.findAll();

        if(sales.isEmpty()) {
            logger.info("Aun no hay ventas en la base de datos");
            throw new LocalNotFoundException("No hay ninguna venta en la base de datos");
        }
        else if (!sales.isEmpty()) {
            return ResponseEntity.ok().body(sales);
        }
        logger.error("Error interno del servidor");
        throw new LocalInternalServerErrorException("Error interno");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sale>> findAll(@PathVariable Long id) throws LocalNotFoundException {
        Optional<Sale> sale = saleService.findById(id);

        if(sale.isEmpty()) {
            logger.warn("El id enviado no existe, por tanto la venta no fue encontrado");
            throw new LocalNotFoundException("La venta no fue encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sale);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Sale>> findByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws LocalNotFoundException {
        List<Sale> sale = saleService.findByDate(date);

        if(sale.isEmpty()) {
            logger.warn("No hay ventas con la fecha buscada");
            throw new LocalNotFoundException("Las ventas con la fecha de busqueda no fue encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sale);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Sale>> findByClient(@PathVariable Long id) throws LocalNotFoundException {
        List<Sale> sale = saleService.findByClient(id);

        if(sale.isEmpty()) {
            logger.warn("No hay ventas relacionadas al cliente buscaado");
            throw new LocalNotFoundException("Las ventas con el cliente buscado no fue encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sale);
    }

    @GetMapping("/date/client/{id}/{datefirst}/{dateend}")
    public ResponseEntity<List<Sale>> findByClientAndDate(
            @PathVariable Long id,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate datefirst,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateend) throws LocalNotFoundException {

        List<Sale> sale = saleService.findByClientAndDate(id,datefirst,dateend);

        if(sale.isEmpty()) {
            logger.warn("No se encontraron ventas relacionadas al cliente buscado y / o las fechas buscadas");
            throw new LocalNotFoundException("Las ventas con el cliente buscado y / o fechas no fueron encontradas");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sale);
    }

    @PostMapping
    public ResponseEntity<Sale> save(@Valid @RequestBody Sale sale) {
        Sale saleSave = saleService.saveOrUpdate(sale);
        return ResponseEntity.status(HttpStatus.CREATED).body(saleSave);
    }

    @PutMapping("{id}")
    public ResponseEntity<Sale> save(@RequestBody Sale sale, @PathVariable Long id) throws LocalNotFoundException {

        Optional<Sale> saleId = saleService.findById(id);

        if(saleId.isEmpty()) {
            logger.warn("El id no fue encontrado, por tanto no es posible llevar a cabo la actualizacion");
            throw new LocalNotFoundException("No se encontro la venta");
        }
        sale.setId(id);
        saleService.saveOrUpdate(sale);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws LocalNotFoundException {

        Optional<Sale> saleId = saleService.findById(id);

        if(saleId.isEmpty()) {
            logger.warn("El id no fue encontrado, por tanto no es posible eliminar la venta");
            throw new LocalNotFoundException("No se encontro la venta");
        }

        saleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
