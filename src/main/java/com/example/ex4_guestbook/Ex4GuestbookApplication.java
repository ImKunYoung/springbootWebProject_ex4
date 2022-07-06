package com.example.ex4_guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing /*AuditingEntityListener 활성화를 위함*/
public class Ex4GuestbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ex4GuestbookApplication.class, args);
    }

}
