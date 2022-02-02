package com.jeon.myhome2.validator;

import com.jeon.myhome2.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Board b = (Board) obj;
        if(StringUtils.isEmpty(b.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요."); // 키가 없을 때 세 번째 파라미터 넣기
        }
    }

}
