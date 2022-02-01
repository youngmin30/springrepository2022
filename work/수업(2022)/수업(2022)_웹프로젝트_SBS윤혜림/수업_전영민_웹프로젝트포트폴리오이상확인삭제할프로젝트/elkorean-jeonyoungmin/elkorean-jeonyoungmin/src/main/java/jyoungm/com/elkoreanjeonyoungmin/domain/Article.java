package jyoungm.com.elkoreanjeonyoungmin.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // entity는 데이터베이스에 있는 테이블을 말함
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별하게 생기는 클래스(?)를 막음
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    // Member 클래스 추가하기
    @ManyToOne(fetch = FetchType.LAZY) // manytoone fetch를 직접 찾아 보세요. 멤버 한 명이 여러 게시글을 쓸 수 있다는 것을 의미함
    @JoinColumn(name = "member_id")
    private Member member;

    
    // 생성 메소드
    public static Article createArticle(String title, String body){

        Article article = new Article();

        article.title = title;
        article.body = body;

        return article;
    }

    // 연관 관계 메소드(집에서 찾아 보세요)
    public void setMember(Member member) {
        this.member = member;
        member.get // getArticles가 나와야 함 member.getArticles().add(this);

    }
}
