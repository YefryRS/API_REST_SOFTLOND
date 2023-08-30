package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Client;
import com.api.rest.softlond.entity.Sale;
import com.api.rest.softlond.repository.SaleRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService implements ISaleService{

    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return saleRepository.findById(id);
    }

    @Override
    public Sale saveOrUpdate(Sale sale) {
        Sale savedSale = saleRepository.save(sale);
        applyDiscount(savedSale);

        return savedSale;
    }

    @Override
    public void delete(Long id) {
        saleRepository.deleteById(id);
    }

    @Override
    public List<Sale> findByDate(LocalDate date) {
        return saleRepository.findByDate(date);
    }

    @Override
    public List<Sale> findByClient(Long id) {
        return saleRepository.findByClient(id);
    }

    @Override
    public List<Sale> findByClientAndDate(Long id, LocalDate dateFirst, LocalDate dateEnd) {
        return saleRepository.findByClientAndDate(id,dateFirst,dateEnd);
    }

    //Aplicar descuento
    @Override
    public void applyDiscount(Sale sale) {

        Client client = sale.getClient();
        LocalDate today = sale.getDate();

        List<Sale> saleClient = client.getSales();
        double totalVentas = 0;

        for (Sale saleDate : saleClient) {
            LocalDate lastSale = saleDate.getDate();
            LocalDate lastSaleLimit = lastSale.plusDays(31);

            if (lastSaleLimit.isAfter(today) || lastSale.isEqual(today)) {
                totalVentas += saleDate.getTotalSale();
            }
        }


        if(totalVentas > 1000000) {
            double descuento = 0.10; // 10% ya que no especifican
            double totalConDescuento = sale.getTotalSale() *  descuento;
            double saleTotal = sale.getTotalSale() - totalConDescuento;
            sale.setTotalSale(saleTotal);
        }

    }

    /*@Override
    public void applyDiscount(Sale sale) {

        Client client = sale.getClient();

        //Fecha actual
        LocalDate today = sale.getDate();
        //LocalDate todayLimit = today.minusDays(31);

        //Fecha de la ultima venta
        List<Sale> saleClient = client.getSales();
        LocalDate lastSale;
        LocalDate lastSaleSum;

        int dias = 0;

        // Sumar el total de ventas
        double totalVentas = 0;

        for (Sale saleDate : saleClient) {
            lastSale = saleDate.getDate();
            lastSaleSum = lastSale.plusDays(31);
            if (lastSaleSum.isAfter(today) || lastSale.isEqual(today)) {
                totalVentas += saleDate.getTotalSale();
            }
        }


        if(totalVentas > 1000000) {
            double descuento = 0.10; // 10% ya que no especifican
            double totalConDescuento = sale.getTotalSale() *  descuento;
            double saleTotal = sale.getTotalSale() - totalConDescuento;
            sale.setTotalSale(saleTotal);
        }

    } */

    /*@Override
    public void applyDiscount(Sale sale) {

        if(sale.getTotalSale() > 1000000) {
            double descuento = 0.10; // 10% ya que no especifican
            double totalConDescuento = sale.getTotalSale() *  descuento;
            double saleTotal = sale.getTotalSale() - totalConDescuento;
            sale.setTotalSale(saleTotal);
            System.out.println("descuento aplicado");
        }

    }*/


}
