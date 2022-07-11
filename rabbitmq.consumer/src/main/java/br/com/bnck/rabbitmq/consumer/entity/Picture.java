package br.com.bnck.rabbitmq.consumer.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Picture {

    private String name;
    private String type;
    private String source;
    private long size;
}
