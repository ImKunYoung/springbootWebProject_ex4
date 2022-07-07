package com.example.ex4_guestbook.service;

import com.example.ex4_guestbook.dto.GuestbookDTO;
import com.example.ex4_guestbook.entity.Guestbook;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service /*스프링에서 빈으로 처리하기 위함*/
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("DTO-------------------------------------");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);

        log.info(entity);

        return null;
    }


}
