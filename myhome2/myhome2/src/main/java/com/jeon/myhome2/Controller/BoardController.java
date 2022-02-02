package com.jeon.myhome2.Controller;

import com.jeon.myhome2.model.Board;
import com.jeon.myhome2.repository.BoardRepository;
import com.jeon.myhome2.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    // BoardValidator.java를 작성한 후 사용하기 위해 이곳에 선언한다.
    @Autowired
    private BoardValidator boardValidator; // spring dependency injection 이용하기 위해 autowired 주석 붙임

    // list.html과 연결
    @GetMapping("/list")
    public String list(Model model) { // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference 참고
        List<Board> boards = boardRepository.findAll();
        model.addAttribute( "boards", boards); // "boards"라는 키 값에, boards라는 데이터 주기
        return "board/list";
    }

    // form.html 연결
    // form.html 작성 후 BoardController.java로 와서 아래 내용 입력하기
    // 홈 화면에서 form 화면으로 이동하게 함
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id)  { // Model model 옆에 파라미터 추가함
        if(id == null) {
            model.addAttribute("board", new Board()); //"board"로 수정함
        } else {
            Board board = boardRepository.findById(id).orElse(null); // 조회된 값이 없으면 null주기
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    //
    // Handling Form Submission 참고(https://spring.io/guides/gs/handling-form-submission/)
    @PostMapping("/form")
    // public String greetingSubmit(@ModelAttribute를 Board board) { // 이 줄을 아래 줄로 변경함
    public String greetingSubmit(@Valid Board board, BindingResult bindingResult) { // @ModelAttribute를 @Valid로 바꾸기
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        boardRepository.save(board); // boardRepository를 이용하여 저장함
        return "redirect:/board/list"; //list로 redirect되면서 다시 화면으로 이동됨
    }
}
