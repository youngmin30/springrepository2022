package com.jym.ekorean.controller;

import com.jym.ekorean.domain.Board;
import com.jym.ekorean.dto.board.BoardDTO;
import com.jym.ekorean.dto.board.BoardModifyForm;
import com.jym.ekorean.dto.board.BoardSaveForm;
import com.jym.ekorean.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/adm")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards/add")
    public String showAddBoard(Model model) {

        model.addAttribute("boardSaveForm", new BoardSaveForm());

        return "adm/board/add";

    }

    @PostMapping("/boards/add")
    public String doAddBoard(BoardSaveForm boardSaveForm) {

        boardService.save(boardSaveForm);

        return "redirect:/adm/boards";
    }

    // 게시판 리스트
    @GetMapping("/boards")
    public String showBoardList(Model model) {

        List<Board> boardList = boardService.findAll();

        model.addAttribute("boardList", boardList);

        return "adm/board/list";

    }

    // 디테일
    @GetMapping("/boards/{id}")
    public String showBoardDetail(@PathVariable(name = "id") Long id, Model model) {

        try {

            BoardDTO boardDetail = boardService.getBoardDetail(id);
            model.addAttribute("board", boardDetail);

        } catch (Exception e) {
            return "redirect:/";
        }

        return "adm/board/detail";

    }

    @GetMapping("/boards/modify")
    public String showModifyBoard(Model model) {

        model.addAttribute("boardModifyForm", new BoardModifyForm());

        return "adm/board/modify";
    }

    // 수정
    @PostMapping("/boards/modify")
    public String doModifyBoard(BoardModifyForm boardModifyForm) {

        try {
            boardService.modify(boardModifyForm);
        } catch (Exception e) {
            return "adm/board/modify";
        }

        return "redirect:/";
    }

    // 삭제
    @GetMapping("/boards/delete/{id}")
    public String doDeleteBoard(@PathVariable(name = "id") Long id) {

        try {
            boardService.delete(id);
            return "adm/board/list";
        } catch (Exception e) {
            return "adm/board/list";
        }

    }


}

