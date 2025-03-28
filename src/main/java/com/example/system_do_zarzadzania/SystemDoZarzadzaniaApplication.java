package com.example.system_do_zarzadzania;

import com.example.system_do_zarzadzania.Entity.*;
import com.example.system_do_zarzadzania.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemDoZarzadzaniaApplication{
    public static void main(String[] args) {
        SpringApplication.run(SystemDoZarzadzaniaApplication.class, args);
    }

}
