package com.ksy.noticeboard.controller;

import com.google.gson.Gson;
import com.ksy.noticeboard.dto.Board;
import com.ksy.noticeboard.service.BoardServiceImpl;
import com.ksy.noticeboard.util.Pagination;
import com.ksy.noticeboard.vo.BoardState;
import com.ksy.noticeboard.vo.BoardVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BoardServiceImpl boardService;

    @Mock
    private BindingResult mockBindingResult;

    @BeforeEach
    void beforeEach() {
        System.out.println("테스트 시작");
    }

    @AfterEach
    void afterEach() {
        System.out.println("테스트 끝");
    }

    @Test
    @DisplayName("메인 페이지 테스트")
    public void homeTest() throws Exception {
        // given -- 없음

        // when, then
        mockMvc.perform(
                        get("/board/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andDo(print());
    }

    @Test
    @DisplayName("게시판 리스트 페이지 테스트")
    public void listTest() throws Exception {
        // given
        BoardVO givenBoard = BoardVO.builder()
                .id(1)
                .title("테스트")
                .content("테스트 내용")
                .writeTime("2022-07-28 18:26:39")
                .viewCount(0)
                .state(1)
                .writerId(2)
                .boardState(BoardState.see)
                .build();
        List<BoardVO> boardList = new ArrayList<>();
        boardList.add(givenBoard);
        given(boardService.getBoardTotalNum()).willReturn(10);
        Pagination pagination = new Pagination(1, 4, 5, 10);
        pagination.setFirstRow(pagination.getFirstRow() - 1);
        given(boardService.getBoardList(pagination)).willReturn(boardList);

        // when, then
        mockMvc.perform(
                        get("/board"))
                .andExpect(status().isOk())
                .andExpect(view().name("boards/list"))
                .andDo(print());

        verify(boardService).getBoardTotalNum();
        verify(boardService).getBoardList(refEq(pagination));
    }

    @Test
    @DisplayName("게시판 상세 페이지 테스트")
    public void detailTest() throws Exception {
        // given
        BoardVO givenBoard = BoardVO.builder()
                .id(1)
                .title("테스트")
                .content("테스트 내용")
                .writeTime("2022-07-28 18:26:39")
                .viewCount(0)
                .state(1)
                .writerId(2)
                .boardState(BoardState.see)
                .build();
        int boardId = 1;
        given(boardService.getBoard(boardId)).willReturn(givenBoard);

        // when, then
        mockMvc.perform(
            get("/board/" + boardId))
                .andExpect(status().isOk())
                .andExpect(view().name("boards/detail"))
                .andDo(print());

        verify(boardService).getBoard(boardId);
    }

    @Test
    @DisplayName("게시글 생성 폼 테스트")
    public void createFormTest() throws Exception {
        // given -- 없음

        // when, then
        mockMvc.perform(
                        get("/board/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("boards/create"))
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 생성 로직 테스트")
    public void createTest() throws Exception {
        // given
        Board givenBoard = Board.builder()
                .id(1)
                .title("테스트")
                .content("테스트 내용")
                .writeTime("2022-07-28 18:26:39")
                .viewCount(0)
                .state(1)
                .writerId(2)
                .build();
        given(boardService.createBoard(givenBoard)).willReturn(1);
        Gson gson = new Gson();
        String content = gson.toJson(givenBoard);

        // when, then
        mockMvc.perform(
                        post("/board")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is3xxRedirection())
                .andDo(print());

        verify(boardService).createBoard(givenBoard);
    }

    @Test
    @DisplayName("게시글 수정 폼 테스트")
    public void updateFormTest() throws Exception {
        // given
        int boardId = 1;
        BoardVO givenBoard = BoardVO.builder()
                .id(1)
                .title("테스트")
                .content("테스트 내용")
                .writeTime("2022-07-28 18:26:39")
                .viewCount(0)
                .state(1)
                .writerId(2)
                .boardState(BoardState.see)
                .build();
        given(boardService.getBoard(boardId)).willReturn(givenBoard);

        // when, then
        mockMvc.perform(
                        get("/board/" + boardId + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("boards/edit"))
                .andDo(print());

        verify(boardService).getBoard(boardId);
    }

    @Test
    @DisplayName("게시글 수정 로직 테스트")
    public void updateTest() throws Exception {
        // given
        int boardId = 1;
        Board givenBoard = Board.builder()
                .id(1)
                .title("테스트")
                .content("테스트 내용")
                .writeTime("2022-07-28 18:26:39")
                .viewCount(0)
                .state(1)
                .writerId(2)
                .build();
        given(boardService.updateBoard(givenBoard)).willReturn(1);
        Gson gson = new Gson();
        String content = gson.toJson(givenBoard);

        // when, then
        mockMvc.perform(
                        put("/board/" + boardId)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(view().name("redirect:/board/{id}"))
                .andDo(print());

        verify(boardService).updateBoard(givenBoard);
    }

    @Test
    @DisplayName("게시글 삭제 로직 테스트")
    public void deleteTest() throws Exception {
        // given
        int boardId = 1;
        given(boardService.deleteBoard(boardId)).willReturn(1);

        // when, then
        mockMvc.perform(
                        delete("/board/" + boardId))
                .andExpect(redirectedUrl("/board"))
                .andDo(print());

        verify(boardService).deleteBoard(boardId);
    }
}
