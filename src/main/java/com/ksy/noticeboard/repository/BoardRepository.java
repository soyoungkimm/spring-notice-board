package com.ksy.noticeboard.repository;

import com.ksy.noticeboard.dto.Board;
import com.ksy.noticeboard.util.Pagination;
import com.ksy.noticeboard.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {
    List<BoardVO> findAll();
    List<BoardVO> findAllByPagination(Pagination pagination);
    void create(Board board);
    void update(Board board);
    void delete(int id);
    int countAll();
    BoardVO findById(int id);
}
