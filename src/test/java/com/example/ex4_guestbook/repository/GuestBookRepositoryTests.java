package com.example.ex4_guestbook.repository;

import com.example.ex4_guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.ex4_guestbook.entity.Guestbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    /*엔티티 테스트*/
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

    /*게시판 제목, 내용, 업데이트 시간, 수정 테스트*/
    @Test
    public void updateTest() {

        Optional<Guestbook> result = guestbookRepository.findById(300L);

        if(result.isPresent()) {

            Guestbook guestbook = result.get();

            guestbook.changeTitle("Change Title....");
            guestbook.changeContent("Change Content....");

            guestbookRepository.save(guestbook);

        }
    }

    /*Querydsl 테스트1 - 단일 항목 검색 테스트 - 제목에 1이라는 글자가 있는 엔티티 검색*/
    @Test
    public void testQuery1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook; /*q 도메인 클래스를 활용하면 엔티티 클래스에 선언된 필드를 (조건문에 활용할) 변수로 활용할 수 있음*/

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder(); /*where 문에 들어가는 조건들을 넣어주는 컨테이너*/

        BooleanExpression expression = qGuestbook.title.contains(keyword); /*조건과 필드 값을 결합*/

        builder.and(expression); /*만들어진 조건을 where 문의 and나 or 같은 키워드와 결합시킴*/

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable); /*BooleanBuilder 의 GuestbookRepository 에 추가된 QuerydslPredicateExecutor 인터페이스의 findAll() 사용*/

        result.stream().forEach(System.out::println);

    }


}
