package com.gauri.pizzahouse.controller;

import com.gauri.pizzahouse.model.Order;
import com.gauri.pizzahouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RestController
public class OrderController {
    private final OrderService orderService;

Consumer<Integer> consumer=((x)-> System.out.println(x));
Supplier<Integer> supplier=()->{return 1;};

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> getOrders(@RequestParam Integer id) {
        return orderService.getOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable Integer id) {
        order.setId(id);
        return orderService.updateOrder(order);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }
}


import java.io.*;
        import java.util.*;

public class DiscountValidator {

    /// <summary>
    /// Determines whether or not a list of assignment discounts are valid
    /// </summary>
    /// <param name="assignedDiscounts">collection of DiscountAssignments that contain a mapping between DiscountId and CustomerId</param>
    /// <param name="customers">A collection of customers of the super store</param>
    /// <param name="discounts">A collection of discounts that are available to offer to customers of the super store</param>
    /// <returns><c>Boolean</c> Determines whether or not it is a valid assignment of discounts to customers</returns>
    /// <remarks>
    public boolean validateDiscounts(List<DiscountAssignment> assignedDiscounts, List<Customer> customers, List<Discount> discounts) {
        // solution goes here
        Set<Integer> setDiscount=new HashSet<>();
        for(Discount d:discounts){
            setDiscount.add(d.getDiscountId());
        }


        Map<Integer,List<Integer>> map=new HashMap<>();
        createMap(map,assignedDiscounts,setDiscount);

        if(!validate3Discounts(map)){
            return false;
        }
        if(!validateDiscountAss(setDiscount)){
            return false;
        }
        if(!validateYearlySpend(map,customers)){
            return false;
        }
        if(!validateOtherCust(map,customers)){
            return false;
        }
        return true;
    }
    private boolean validateOtherCust(Map<Integer,List<Integer>> map,List<Customer> customers){
        customers.sort((x,y)->(int)(x.getYearlySpend()-y.getYearlySpend()));
        int prev=0;
        for(Customer customer:customers){
            List<Integer> discountList=map.get(customer.getCustomerId());

            int totalDiscount=0;
            for(Integer discount:discountList){
                totalDiscount+=discount;
            }
            if(prev>totalDiscount){
                return false;
            }
            prev=totalDiscount;
        }
        return true;
    }
    private boolean validateYearlySpend(Map<Integer,List<Integer>> map,List<Customer> customers){

        for(Customer c:customers){
            List<Integer> discountList=map.get(c.getCustomerId());
            float yearlySpend=c.getYearlySpend();
            int totalDiscount=0;
            for(Integer discount:discountList){
                totalDiscount+=discount;
            }
            float val=(20*yearlySpend)/100;
            if(totalDiscount>val){
                return false;
            }
        }
        return true;
    }
    private boolean validateDiscountAss(Set<Integer> set){
        if(set.isEmpty()){
            return true;
        }
        return false;
    }
    private boolean validate3Discounts(Map<Integer,List<Integer>> map){

        for(Integer key:map.keySet()){
            if(map.get(key).size()>3){
                return false;
            }
        }
        return true;
    }
    private void createMap(Map<Integer,List<Integer>> map,List<DiscountAssignment> assignedDiscounts, Set<Integer> set){

        for(DiscountAssignment da:assignedDiscounts){
            int key=da.getCustomerId();
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(da.getDiscountId());
            set.remove(da.getDiscountId());
        }
    }
}


public class Customer {
    private int customerId;
    private float yearlySpend;

    public Customer(int cutomerId, float yearlySpend) {
        this.customerId = cutomerId;
        this.yearlySpend = yearlySpend;
    }

    public int getCustomerId() {
        return customerId;
    }

    public float getYearlySpend() {
        return yearlySpend;
    }

}

public class Discount {
    private int discountId;
    private int productId;
    private float dollarValue;

    public Discount(int discountId, int productId, float value) {
        this.discountId = discountId;
        this.productId = productId;
        this.dollarValue = value;
    }

    public int getDiscountId() {
        return discountId;
    }

    public int getProductId() {
        return productId;
    }

    public float getDollarValue() {
        return dollarValue;
    }
}

public class DiscountAssignment {
    private int discountId;
    private int customerId;

    public DiscountAssignment(int discountId, int customerId) {
        this.discountId = discountId;
        this.customerId = customerId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public int getCustomerId() {
        return customerId;
    }
}




