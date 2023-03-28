package com.rd.productservice.repository;

import com.rd.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created at 23.03.2023
 *
 * @author Rıdvan DOĞAN
 */
@Repository
public interface ProductRepository  extends JpaRepository<Product, String> {
}
