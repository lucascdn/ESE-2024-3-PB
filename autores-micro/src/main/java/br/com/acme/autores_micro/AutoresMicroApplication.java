package br.com.acme.autores_micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AutoresMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoresMicroApplication.class, args);
    }

}
