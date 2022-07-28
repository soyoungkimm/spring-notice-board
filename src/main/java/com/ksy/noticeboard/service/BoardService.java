package com.ksy.noticeboard.service;

import com.ksy.noticeboard.dto.Board;
import com.ksy.noticeboard.util.Pagination;
import com.ksy.noticeboard.vo.BoardVO;

import java.util.List;

public interface BoardService {
    List<BoardVO> getBoardList();
    List<BoardVO> getBoardList(Pagination pagination);
    int getBoardTotalNum();
    BoardVO getBoard(int id);
    int createBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(int id);
}
