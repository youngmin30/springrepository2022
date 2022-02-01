package jyoungm.com.elkoreanjeonyoungmin.controller;

import jyoungm.com.elkoreanjeonyoungmin.dto.ArticleSaveForm;
import jyoungm.com.elkoreanjeonyoungmin.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsContructor

public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles/write")
    public String showWrite(Model model) {

        model.addAttribute(attributeName: "articleSaveForm", new ArticleSaveForm());

        return "usr/article/write";

    }

    @PostMapping("/articles/write")
    public String doWrite(@Validated ArticleSaveForm, @BindingREsult BindingResult, Model model, Principal principal) {

        if( bindingREsult.hasErros() ){
            return "redirect:/";
    }

}
