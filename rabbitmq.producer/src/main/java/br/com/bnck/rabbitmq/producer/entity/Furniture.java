package br.com.bnck.rabbitmq.producer.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Furniture {

    private String color;
    private String material;
    private String name;
    private int price;
}
