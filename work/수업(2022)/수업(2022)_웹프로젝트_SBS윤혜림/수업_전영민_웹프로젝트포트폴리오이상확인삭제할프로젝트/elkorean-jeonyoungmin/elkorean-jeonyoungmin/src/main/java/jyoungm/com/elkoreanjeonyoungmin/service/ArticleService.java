package jyoungm.com.elkoreanjeonyoungmin.service;

import jyoungm.com.elkoreanjeonyoungmin.dao.ArticleRepository;
import jyoungm.com.elkoreanjeonyoungmin.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class ArticleService {

    private final ArticleRepository articleRepository;

    public void save(ArticleSaveForm articleSAveForm, Member member) {


    }

}
