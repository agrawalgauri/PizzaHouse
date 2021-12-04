package com.gauri.pizzahouse.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection ="order")
public class Order {
    @Id
    private Integer id;
    private String name;
    private List<String> ingredients;
}
