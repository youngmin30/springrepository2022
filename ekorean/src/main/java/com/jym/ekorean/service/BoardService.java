package com.jym.ekorean.service;

import com.jym.ekorean.dao.BoardRepository;
import com.jym.ekorean.domain.Article;
import com.jym.ekorean.domain.Board;
import com.jym.ekorean.dto.article.ArticleListDTO;
import com.jym.ekorean.dto.board.BoardDTO;
import com.jym.ekorean.dto.board.BoardModifyForm;
import com.jym.ekorean.dto.board.BoardSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(BoardSaveForm boardSaveForm){

        Board board = Board.createBoard(
                boardSaveForm.getName(),
                boardSaveForm.getDetail()
        );

        boardRepository.save(board);

    }

    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    public Optional<Board> findById(Long id){
        return boardRepository.findById(id);
    }

    public BoardDTO getBoardDetail(Long id) {

        Optional<Board> boardOptional = findById(id);

        boardOptional.orElseThrow(
                () -> new NoSuchElementException("해당 게시판은 존재하지 않습니다.")
        );

        Board findBoard = boardOptional.get();

        List<ArticleListDTO> articleList = new ArrayList<>();
        List<Article> articles = findBoard.getArticles();

        for(Article article : articles){
            ArticleListDTO articleListDTO = new ArticleListDTO(article);
            articleList.add(articleListDTO);
        }

        return new BoardDTO(findBoard, articleList);

    }


    @Transactional
    public Long modify(BoardModifyForm boardModifyForm) throws NoSuchElementException{

        Optional<Board> boardOptional = boardRepository.findByName(boardModifyForm.getName());

        boardOptional.orElseThrow(
                () -> new NoSuchElementException("해당 게시판은 존재하지 않습니다.")
        );

        Board board = boardOptional.get();

        board.modifyBoard(
                boardModifyForm.getName(),
                boardModifyForm.getDetail()
        );

        return board.getId();

    }

    @Transactional
    public void delete(Long id) {

        Optional<Board> boardOptional = findById(id);

        boardOptional.orElseThrow(
                () -> new NoSuchElementException("해당 게시판은 존재하지 않습니다.")
        );

        Board findBoard = boardOptional.get();

        boardRepository.delete(findBoard);
    }

}

