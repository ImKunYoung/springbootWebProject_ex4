package com.example.ex4_guestbook.service;

import com.example.ex4_guestbook.dto.GuestbookDTO;
import com.example.ex4_guestbook.entity.Guestbook;
import com.example.ex4_guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service /*스프링에서 빈으로 처리하기 위함*/
@Log4j2
@RequiredArgsConstructor /*의존성 자동 주입*/
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository; /*반드시 final 로 선언*/

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("DTO-------------------------------------");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getGno();
    }

}
