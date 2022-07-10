package com.example.ex4_guestbook.service;

import com.example.ex4_guestbook.dto.GuestbookDTO;
import com.example.ex4_guestbook.dto.PageRequestDTO;
import com.example.ex4_guestbook.dto.PageResultDTO;
import com.example.ex4_guestbook.entity.Guestbook;
import com.example.ex4_guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;


@Service /*스프링에서 빈으로 처리하기 위함*/
@Log4j2
@RequiredArgsConstructor /*의존성 자동 주입*/
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository; /*반드시 final 로 선언*/

    /*방명록을 등록한다*/
    @Override
    public Long register(GuestbookDTO dto) {
        log.info("DTO-------------------------------------");
        log.info(dto);

        /*DTO -> ENTITY*/
        Guestbook entity = dtoToEntity(dto);

        log.info(entity);

        /*DB INSERT*/
        repository.save(entity);

        return entity.getGno();
    }

    /*방명록을 불러온다*/
    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        /*Pageable*/
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        /*DB SELECT (IN ENTITY)*/
        Page<Guestbook> result = repository.findAll(pageable);
        /*ENTITY -> DTO*/
        Function<Guestbook, GuestbookDTO> fn = (this::entityToDto);
        /*RETURN ENTITY DTO LIST*/
        return new PageResultDTO<>(result, fn);

    }

    /*방명록을 조회한다*/
    @Override
    public GuestbookDTO read(Long gno) {

        Optional<Guestbook> result = repository.findById(gno);

        return result.isPresent()? entityToDto(result.get()): null;
    }

    /*방명록을 삭제한다*/
    @Override
    public void remove(Long gno) {

        repository.deleteById(gno);

    }

    /*방명록을 수정한다*/
    @Override
    public void modify(GuestbookDTO dto) {

        /*업데이트 항목은 'title', 'content'*/

        Optional<Guestbook> result = repository.findById(dto.getGno());

        if(result.isPresent()) {

            Guestbook entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repository.save(entity);

        }

    }


}
