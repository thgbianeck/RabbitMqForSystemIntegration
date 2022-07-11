package br.com.bnck.rabbitmq.consumer.entity;

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
