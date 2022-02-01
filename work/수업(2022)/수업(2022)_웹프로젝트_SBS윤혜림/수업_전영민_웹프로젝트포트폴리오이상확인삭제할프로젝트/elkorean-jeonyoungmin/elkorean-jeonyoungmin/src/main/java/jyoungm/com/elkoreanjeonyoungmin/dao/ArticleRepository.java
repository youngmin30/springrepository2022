package jyoungm.com.elkoreanjeonyoungmin.dao;

import jyoungm.com.elkoreanjeonyoungmin.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
