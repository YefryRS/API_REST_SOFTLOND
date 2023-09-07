package com.api.rest.softlond.repository;

import com.api.rest.softlond.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {

    @Query(value = "SELECT * FROM sale WHERE date = :date",nativeQuery = true)
    List<Sale> findByDate(@Param("date") LocalDate date);

    @Query(value = "SELECT * FROM sale WHERE cliente_id = :id_client",nativeQuery = true)
    List<Sale> findByClient(@Param("id_client") Long idClient);

    @Query(value = "SELECT * FROM sale WHERE cliente_id = :id_client AND date BETWEEN :datefirst and :dateEnd",nativeQuery = true)
    List<Sale> findByClientAndDate(@Param("id_client") Long idClient,@Param("datefirst") LocalDate dateFirst,@Param("dateEnd") LocalDate dateEnd);

    @Query(value = "SELECT SUM(totalSale) FROM sale WHERE cliente_id = :id_client AND date BETWEEN :datefirst and :dateEnd",nativeQuery = true)
    Double findByDiscount(@Param("id_client") Long idClient, @Param("datefirst") LocalDate dateFirst, @Param("dateEnd") LocalDate dateEnd);

}
