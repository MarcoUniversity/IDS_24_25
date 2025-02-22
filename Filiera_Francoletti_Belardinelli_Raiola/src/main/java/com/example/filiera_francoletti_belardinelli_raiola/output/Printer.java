package com.example.filiera_francoletti_belardinelli_raiola.output;

import org.springframework.stereotype.Component;

@Component
public class Printer {

    public void printMessage(String message) {
        System.out.println(message);
    }
    //iniettare il bean con @Autowired
}
