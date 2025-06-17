package com.hamitmizrak.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Deneme {

    private String name;

    public static void main(String[] args) {
        Deneme deneme= new Deneme();
        deneme.setName("Hamit Mızrak");
        System.out.println(deneme.getName());
        System.out.println("Merhabalar");
    }
}
