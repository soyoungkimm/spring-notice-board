package com.ksy.noticeboard.service;

import com.ksy.noticeboard.dto.Board;
import com.ksy.noticeboard.repository.BoardRepository;
import com.ksy.noticeboard.util.Pagination;
import com.ksy.noticeboard.vo.BoardState;
import com.ksy.noticeboard.vo.BoardVO;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class BoardServiceTest {

    private BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private BoardServiceImpl boardService;

    @BeforeEach
    public void setUpTest() {
        boardService = new BoardServiceImpl(boardRepository);
        System.out.println("테스트 시작");
    }

    @AfterEach
    void afterEach() {
        System.out.println("테스트 끝");
    }

    @Test
    @DisplayName("게시글 리스트 반환 테스트")
    public void getBoardListTest() {
        // given
        Pagination pagination = new Pagination(1, 4, 5, 10);
        BoardVO boardVO = new BoardVO();
        boardVO.setState(1); // 공개
        List<BoardVO> boardList = new ArrayList<>();
        boardList.add(boardVO);
        for (BoardVO board : boardList) {
            board.setBoardState(BoardState.values()[board.getState()]);
        }

        Mockito.when(boardRepository.findAllByPagination(pagination))
                .thenReturn(boardList);

        // when
        List<BoardVO> resultList = boardService.getBoardList(pagination);

        // then
        Assertions.assertEquals(resultList, boardList);
        for (BoardVO board : boardList) {
            Assertions.assertEquals(board.getBoardState(), BoardState.see);
        }
    }

    @Test
    @DisplayName("게시글 총 개수 반환 테스트")
    public void getBoardTotalNumTest() {
        // given
        Mockito.when(boardRepository.countAll())
                .thenReturn(10);

        // when
        int count = boardService.getBoardTotalNum();

        // then
        Assertions.assertEquals(count, 10);
    }

    @Test
    @DisplayName("게시글 찾기 테스트")
    public void getBoardTest() {
        //given
        BoardVO givenBoard = BoardVO.builder()
                .id(1)
                .title("테스트")
                .content("테스트 내용")
                .writeTime("2022-07-28 18:26:39")
                .viewCount(0)
                .state(1)
                .writerId(2)
                .build();

        Mockito.when(boardRepository.findById(1))
                .thenReturn(givenBoard);

        // when
        BoardVO boardVO = boardService.getBoard(1);

        // then
        Assertions.assertEquals(boardVO.getId(), givenBoard.getId());
        Assertions.assertEquals(boardVO.getTitle(), givenBoard.getTitle());
        Assertions.assertEquals(boardVO.getContent(), givenBoard.getContent());
        Assertions.assertEquals(boardVO.getWriteTime(), givenBoard.getWriteTime());
        Assertions.assertEquals(boardVO.getViewCount(), givenBoard.getViewCount());
        Assertions.assertEquals(boardVO.getState(), givenBoard.getState());
        Assertions.assertEquals(boardVO.getWriterId(), givenBoard.getWriterId());

        verify(boardRepository).findById(1);
    }

    @Test
    @DisplayName("게시글 생성 테스트")
    public void createBoardTest() {
        //given
        Board givenBoard = Board.builder()
                .id(1)
                .title("테스트")
                .content("테스트 내용")
                .writeTime("2022-07-28 18:26:39")
                .viewCount(0)
                .state(1)
                .writerId(2)
                .build();

        Mockito.when(boardRepository.create(any(Board.class)))
                .thenReturn(1);

        // when
        int result = boardService.createBoard(givenBoard);

        // then
        Assertions.assertEquals(result, 1);

        verify(boardRepository).create(any(Board.class));
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    public void updateBoardTest() {
        //given
        Board givenBoard = Board.builder()
                .id(1)
                .title("테스트")
                .content("테스트 내용")
                .writeTime("2022-07-28 18:26:39")
                .viewCount(0)
                .state(1)
                .writerId(2)
                .build();

        Mockito.when(boardRepository.update(any(Board.class)))
                .thenReturn(1);

        // when
        int result = boardService.updateBoard(givenBoard);

        // then
        Assertions.assertEquals(result, 1);

        verify(boardRepository).update(any(Board.class));
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    public void deleteBoardTest() {
        //given
        Mockito.when(boardRepository.delete(1))
                .thenReturn(1);

        // when
        int result = boardService.deleteBoard(1);

        // then
        Assertions.assertEquals(result, 1);

        verify(boardRepository).delete(1);
    }
}
