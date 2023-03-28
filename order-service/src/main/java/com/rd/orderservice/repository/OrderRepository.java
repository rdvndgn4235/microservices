package com.rd.orderservice.repository;

import com.rd.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created at 24.03.2023
 *
 * @author Rıdvan DOĞAN
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
