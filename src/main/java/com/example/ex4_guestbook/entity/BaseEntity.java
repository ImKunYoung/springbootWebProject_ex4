package com.example.ex4_guestbook.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass /*해당 어노테이션이 적용된 클래스는 테이블로 생성되지 않고 이 클래스를 상속받은 엔티티 클래스로 데이터베이스 테이블이 생성됨*/
@EntityListeners(value = {AuditingEntityListener.class}) /*엔티티 객체가 생성/변경되는 것을 감지 - AuditingEntityListener*/
@Getter
abstract class BaseEntity {

    @CreatedDate /*엔티티 생성 시간 처리*/
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate /*최종 수정 시간 처리*/
    @Column(name = "moddate")
    private LocalDateTime modDate;

}
