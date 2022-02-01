package com.jym.ekorean.controller;

import com.jym.ekorean.domain.Article;
import com.jym.ekorean.domain.Member;
import com.jym.ekorean.dto.article.ArticleDTO;
import com.jym.ekorean.dto.article.ArticleModifyForm;
import com.jym.ekorean.dto.article.ArticleSaveForm;
import com.jym.ekorean.service.ArticleService;
import com.jym.ekorean.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/articles/write")
    public String showArticleWrite(Model model) {

        model.addAttribute("articleSaveForm", new ArticleSaveForm());

        return "usr/article/write";

    }

    @PostMapping("/articles/write")
    public String doWrite(@Validated ArticleSaveForm articleSaveForm, BindingResult bindingResult, Model model, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "usr/article/write";
        }

        try {

            Member findMember = memberService.findByLoginId(principal.getName());

            articleService.save(
                    articleSaveForm,
                    findMember
            );

        } catch (IllegalStateException e) {

            model.addAttribute("err_msg", e.getMessage());

            return "usr/article/write";

        }

        return "redirect:/";

    }

    @GetMapping("/articles/modify/{id}")
    public String showModify(@PathVariable(name = "id") Long id, Model model){

        try {
            Article article = articleService.getById(id);
            model.addAttribute("articleModifyForm", new ArticleModifyForm(
                    article.getTitle(),
                    article.getBody()
            ));
            return "usr/article/modify";
        }catch (Exception e){
            return "redirect:/";
        }
    }

    @PostMapping("/articles/modify/{id}")
    public String doModify(@PathVariable(name = "id") Long id, ArticleModifyForm articleModifyForm){

        try{
            articleService.modifyArticle(articleModifyForm, id);
            return "redirect:/article/"+ id;
        }catch (Exception e){
            return "usr/article/modify";
        }
    }

    @GetMapping("/articles/")
    public String showList(Model model) {

        List<ArticleDTO> articleList = articleService.getList();

        model.addAttribute("articleList", articleList);

        return "usr/article/list";

    }

    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable(name = "id") Long id, Principal principal){

        try {
            ArticleDTO article = articleService.getArticle(id);

            if(article.getAuthorName() != principal.getName()){
                return "redirect:/";
            }
            articleService.delete(id);
            return "redirect:/";

        }catch (Exception e){
            return "redirect:/";
        }

    }
    // 2022년 1월 26일
    @GetMapping("/articles/{id}")
    public String showDetail(@PathVariable(name = "id") Long id, Model model){

        try {
            ArticleDTO findArticle = articleService.getArticle(id);
            model.addAttribute("article", findArticle);

            return "usr/article/detail";
        }catch (Exception e){
            return "redirect:/"; // id가 없으면 이전 페이지 redirect로 보내줌
        }


    }

}