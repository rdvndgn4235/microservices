package com.rd.productservice.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created at 23.03.2023
 *
 * @author Rıdvan DOĞAN
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "product", catalog = "productDB")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String price;
}
