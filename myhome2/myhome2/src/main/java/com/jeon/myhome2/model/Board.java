package com.jeon.myhome2.model;


import lombok.Data;
import org.hibernate.annotations.BatchSize;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; // 자동으로 나오지 않아서 직접 입력함.
import javax.validation.constraints.NotNull; // notnull은 종류가 둘이었는데, validation에서 가져왔음
import javax.validation.constraints.Size; // size도 validation에서 가져옴.


@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 이곳에서 시퀀스, 데이터베이스에서도 시퀀스 만들어야 하지만 편의상 identity 사용함
    private Long id;
    @NotNull
    @Size(min=2, max=30, message = "제목은 2자 이상 30자 이하여야 합니다.") // 게시판 form에서 게시글 제목 크기와 오류 메시지
    private String title;
    private String content;

}
