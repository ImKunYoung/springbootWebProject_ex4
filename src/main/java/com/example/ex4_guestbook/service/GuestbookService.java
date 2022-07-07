package com.example.ex4_guestbook.service;

import com.example.ex4_guestbook.dto.GuestbookDTO;
import com.example.ex4_guestbook.entity.Guestbook;

public interface GuestbookService {
    /*방명록을 등록한다*/
    Long register(GuestbookDTO dto);

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
}
