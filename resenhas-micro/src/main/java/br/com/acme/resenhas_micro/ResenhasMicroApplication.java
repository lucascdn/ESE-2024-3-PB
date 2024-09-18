package br.com.acme.resenhas_micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ResenhasMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResenhasMicroApplication.class, args);
    }

}
