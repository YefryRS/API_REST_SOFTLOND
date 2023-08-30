package com.api.rest.softlond.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriyProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_category")
    private String nameCategory;

    @OneToMany(mappedBy = "categoriyProduct", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

}