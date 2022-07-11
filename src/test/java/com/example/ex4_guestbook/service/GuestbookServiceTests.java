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
    @DisplayName("방명록을 조회")
    public void testRead() {

        Long gno = 10L;

        GuestbookDTO guestbookDTO = service.read(gno);

        System.out.println(guestbookDTO);

    }

    @Test
    @DisplayName("방명록을 삭제한다")
    public void testRemove() {

        long gno = 10L;

        service.remove(gno);

    }

    @Test
    @DisplayName("방명록을 수정한다")
    public void testModify() {

        long gno = 12L;

        GuestbookDTO dto = service.read(gno);
        dto.setTitle("방명록 제목 수정 테스트");
        dto.setContent("방명록 내용 수정 테스트");

        service.modify(dto);

    }

    @Test
    @DisplayName("검색 처리 1- 서버측 검색 처리 - 서비스")
    public void testSearch() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc") // 검색 조건 t, c, w, tc, tcw..
                .keyword("안ㄴ여하세요") // 검색 키워드
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("-----------------------------------------------------");
        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

        System.out.println("=====================================================");
        resultDTO.getPageList().forEach(System.out::println);
    }

}
