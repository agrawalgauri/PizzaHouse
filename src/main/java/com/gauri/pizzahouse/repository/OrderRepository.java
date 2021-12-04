package com.gauri.pizzahouse.repository;

import com.gauri.pizzahouse.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer> {
    default Order saveWithId(Order order) {
        order.setId((int) (count() + 1));
        return save(order);
    }
}
