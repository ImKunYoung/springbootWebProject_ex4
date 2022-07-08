package com.example.ex4_guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {

    /*DTO 리스트*/
    private List<DTO> dtoList;

    /*총 페이지 개수*/
    private int totalPage;

    /*현재 페이지 번호*/
    private int page;

    /*목록 사이즈*/
    private int size;

    /*시작 페이지 번호, 끝 페이지 번호*/
    private int start, end;

    /*이전, 다음*/
    private boolean prev, next;

    /*페이지 번호 목록*/
    private List<Integer> pageList;


    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    /*목록 데이터 페이지 처리*/
    private void makePageList(Pageable pageable) {

        this.page = pageable.getPageNumber() + 1; // 0부터 시작하므로 1 추가
        this.size = pageable.getPageSize();

        // temp end page
        int tempEnd = (int) (Math.ceil(page/10.0)) * 10; // 1페이지의 경우 page=0.1, 10페이지의 경우 page=1, 11페이지의 경우 page=1.1 ,, Math.ceil() -> 올림

        start = tempEnd - 9;

        prev = start > 1;

        end = Math.min(totalPage, tempEnd); // end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }
}
