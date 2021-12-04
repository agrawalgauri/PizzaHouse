package com.gauri.pizzahouse.service;

import com.gauri.pizzahouse.model.Order;
import com.gauri.pizzahouse.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepository.saveWithId(order);
    }
    public Order updateOrder(Order order){
        Order dbOrder = orderRepository.findById(order.getId()).orElse(null);
       if(dbOrder!=null)
        {
            if(order.getName()!=null){
                dbOrder.setName(order.getName());
            }
            if(order.getIngredients()!=null){
                dbOrder.setIngredients(order.getIngredients());
            }
            orderRepository.save(dbOrder);
        }
       return dbOrder;
    }
    public void deleteOrder(Integer id){
        Order dbOrder = orderRepository.findById(id).orElse(null);
        if(dbOrder!=null){
            orderRepository.deleteById(id);
        }
    }

}
