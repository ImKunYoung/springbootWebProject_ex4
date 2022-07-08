package com.example.ex4_guestbook.service;

import com.example.ex4_guestbook.dto.GuestbookDTO;
import com.example.ex4_guestbook.dto.PageRequestDTO;
import com.example.ex4_guestbook.dto.PageResultDTO;
import com.example.ex4_guestbook.entity.Guestbook;
import com.example.ex4_guestbook.repository.GuestbookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;

@SpringBootTest
public class PracticeTests {

    @Autowired
    GuestbookRepository repository;
    @Autowired
    GuestbookServiceImpl guestbookServiceImpl;

    /*Practice - 엔티티 객체가 DTO 객체들로 반환이 되는지*/
    @Test
    public void testList() {

        PageRequestDTO requestDTO = PageRequestDTO.builder().page(1).size(10).build();

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<Guestbook> result = repository.findAll(pageable);

        Function<Guestbook, GuestbookDTO> fn = (entity -> guestbookServiceImpl.entityToDto(entity));

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = new PageResultDTO<>(result, fn);

        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

    }
    /*Practice - select * from guestbook;*/
    @Test
    public void testGetGuestbook() {
        List<Guestbook> guestbook = repository.findAll();

        for(Guestbook guestbook1 : guestbook) {
            System.out.println(guestbook1);
        }

    }
    /*Practice - SELECT * FROM guestbook LIMIT 10;*/
    @Test
    public void testGuestBookPageable() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Guestbook> guestbooks = repository.findAll(pageable);

        guestbooks.get().forEach(guestbook -> System.out.println(guestbook));
    }

    /*Practice - SELECT * FROM guestbook ORDER BY guestbook.gno DESC LIMIT 10;*/
    @Test
    public void testGuestBookPageableSort() {
        Sort sort = Sort.by("gno").descending();

        Pageable pageable = PageRequest.of(0, 10, sort);

        Page<Guestbook> guestbooks = repository.findAll(pageable);

        guestbooks.get().forEach(guestbook -> System.out.println(guestbook));

    }
}
