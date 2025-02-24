package com.example.filiera_francoletti_belardinelli_raiola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.example.filiera_francoletti_belardinelli_raiola")

public class FilieraFrancolettiBelardinelliRaiolaApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilieraFrancolettiBelardinelliRaiolaApplication.class, args);
        System.out.println("Ciao");
        System.out.println("Miao");
        System.out.println("prova");
    }

}
