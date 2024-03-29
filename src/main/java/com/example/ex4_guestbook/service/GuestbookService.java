package com.example.ex4_guestbook.service;

import com.example.ex4_guestbook.dto.GuestbookDTO;
import com.example.ex4_guestbook.dto.PageRequestDTO;
import com.example.ex4_guestbook.dto.PageResultDTO;
import com.example.ex4_guestbook.entity.Guestbook;

public interface GuestbookService {

    /*방명록을 등록한다*/
    Long register(GuestbookDTO dto);

    /*방명록을 불러온다*/
    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    /*방명록을 조회한다*/
    GuestbookDTO read(Long gno);

    /*방명록을 삭제한다*/
    void remove(Long gno);

    /*방명록을 수정한다*/
    void modify(GuestbookDTO dto);

    /*등록과 DTO 를 엔티티로 변환하기*/
    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    /*엔티티를 DTO 로 반환*/
    default GuestbookDTO entityToDto(Guestbook entity) {
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
