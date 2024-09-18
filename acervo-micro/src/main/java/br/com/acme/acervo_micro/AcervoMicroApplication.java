package br.com.acme.acervo_micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AcervoMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcervoMicroApplication.class, args);
    }

}
