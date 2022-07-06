package com.example.ex4_guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.ex4_guestbook.entity.Guestbook;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    /*entity test*/
    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 300).forEach(i -> {

            Guestbook guestbook = Guestbook.builder()
                    .title("Title...." +i)
                    .content("Content..." +i)
                    .writer("user"+(i % 10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }
}
