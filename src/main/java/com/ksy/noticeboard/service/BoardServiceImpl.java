package com.ksy.noticeboard.service;

import com.ksy.noticeboard.dto.Board;
import com.ksy.noticeboard.repository.BoardRepository;
import com.ksy.noticeboard.util.Pagination;
import com.ksy.noticeboard.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public List<BoardVO> getBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public List<BoardVO> getBoardList(Pagination pagination) {
        return boardRepository.findAllByPagination(pagination);
    }

    @Override
    public int getBoardTotalNum() {
        return boardRepository.countAll();
    }

    @Override
    public BoardVO getBoard(int id) {
        return boardRepository.findById(id);
    }

    @Override
    public int createBoard(Board board) {
        boardRepository.create(board);
        return board.getId();
    }

    @Override
    public void updateBoard(Board board) {
        boardRepository.update(board);
    }

    @Override
    public void deleteBoard(int id) {
        boardRepository.delete(id);
    }
}
