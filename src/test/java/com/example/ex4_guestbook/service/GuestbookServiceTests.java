package com.example.ex4_guestbook.service;

import com.example.ex4_guestbook.dto.GuestbookDTO;
import com.example.ex4_guestbook.dto.PageRequestDTO;
import com.example.ex4_guestbook.dto.PageResultDTO;
import com.example.ex4_guestbook.entity.Guestbook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService service;

    /*DTO -> ENTITY -> DB*/
    @Test
    public void testRegister() {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));

    }


    /*ENTITY -> DTO*/
    @Test
    public void testList2() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

    }

    /*목록 데이터 페이지 처리*/
    @Test
    @DisplayName("g")
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);


        System.out.println("PREV: "+resultDTO.isPrev());
        assertFalse(resultDTO.isPrev()); /*현재 페이지는 1페이지임으로 prev 는 false*/

        System.out.println("NEXT: "+resultDTO.isNext());
        assertTrue(resultDTO.isNext()); /*다음 페이지로 가는 링크는 true*/

        System.out.println("TOTAL: "+resultDTO.getTotalPage());


        System.out.println("-----------------------------------------------");
        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

        /*화면에 출력될 페이지 번호*/
        System.out.println("===============================================");
        resultDTO.getPageList().forEach(System.out::println);
    }

    @Test
    @DisplayName("방명록을 조회한다")
    public void testRead() {

        Long gno = 10L;

        GuestbookDTO guestbookDTO = service.read(gno);

        System.out.println(guestbookDTO);

    }

}
