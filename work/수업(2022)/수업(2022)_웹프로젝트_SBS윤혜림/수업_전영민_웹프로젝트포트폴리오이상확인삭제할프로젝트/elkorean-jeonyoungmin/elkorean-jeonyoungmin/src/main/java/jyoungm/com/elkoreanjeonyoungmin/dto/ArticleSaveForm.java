package jyoungm.com.elkoreanjeonyoungmin.dto;

import jyoungm.com.elkoreanjeonyoungmin.domain.Article;

public interface ArticleSaveForm {

        Article article = Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );

        article.setMember(member);

        articleRepository.save(article);
    }
}
