package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Discount;
import com.api.rest.softlond.entity.Sale;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;
import com.api.rest.softlond.repository.DiscountRepository;
import com.api.rest.softlond.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SaleService implements ISaleService{

    private final SaleRepository saleRepository;
    private final DiscountRepository discountRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, DiscountRepository discountRepository) {
        this.saleRepository = saleRepository;
        this.discountRepository = discountRepository;
    }

    @Override
    public List<Sale> findAll() throws LocalNotFoundException, LocalInternalServerErrorException {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> findById(Long id) throws LocalNotFoundException {
        return saleRepository.findById(id);
    }

    @Override
    public Sale save(Sale sale) {

        // Descuento del millon
        Long clientId = sale.getClient().getId();
        LocalDate today = sale.getDate();
        LocalDate daysAgo = today.minusDays(31);


        double totalVentas = saleRepository.findByDiscount(clientId,daysAgo,today);


        Long id = 1L;
        Optional<Discount> discountId = discountRepository.findById(id);

        if(totalVentas > 1000000) {

            Double discount = discountId.get().getPurchaseOverMillion();
            Double discountPurchaseOverMillion = discount / 100;

            double descuento = discountPurchaseOverMillion;
            double totalConDescuento = sale.getTotalSale() *  descuento;
            double saleTotal = sale.getTotalSale() - totalConDescuento;
            sale.setTotalSale(saleTotal);
        }

        //Descuento por el sorteo
        boolean sorteo = true;
        int counter = 0;
        while (sorteo) {

            int numberRandom = new Random().nextInt(3)+1; // devuelve aleatorio 1-3
            System.out.println("NUMBER RANDOM: " + numberRandom);

            switch (numberRandom) {
                case 1:
                    Double discount = discountId.get().getFullPurchase();
                    Double discountFullPurchase = discount / 100;

                    double descuento = discountFullPurchase;
                    double totalConDescuento = sale.getTotalSale() *  descuento;
                    double saleTotal = sale.getTotalSale() - totalConDescuento;
                    sale.setTotalSale(saleTotal);
                    sorteo = false;
                    break;
                case 2:
                    counter++;
                    break;
                case 3:
                    sorteo = false;
                    break;
            }

            if (counter == 3) {
                sorteo = false;
            }

        }

        return saleRepository.save(sale);
    }

    @Override
    public Sale update(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public void delete(Long id) throws LocalNotFoundException {
        saleRepository.deleteById(id);
    }

    @Override
    public List<Sale> findByDate(LocalDate date) throws LocalNotFoundException{
        return saleRepository.findByDate(date);
    }

    @Override
    public List<Sale> findByClient(Long id) throws LocalNotFoundException {
        return saleRepository.findByClient(id);
    }

    @Override
    public List<Sale> findByClientAndDate (Long id, LocalDate dateFirst, LocalDate dateEnd) throws LocalNotFoundException {
        return saleRepository.findByClientAndDate(id,dateFirst,dateEnd);
    }

}
