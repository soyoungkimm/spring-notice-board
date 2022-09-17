package com.ksy.noticeboard.repository;

import com.ksy.noticeboard.dto.Board;
import com.ksy.noticeboard.vo.BoardVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

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

    @Test
    void createTest() {
        // when
        int result = boardRepository.create(givenBoard);

        // then
        Assertions.assertEquals(result, 1);
    }

    @Test
    void updateTest() {
        // when
        int result = boardRepository.update(givenBoard);

        // then
        Assertions.assertEquals(result, 1);
    }

    @Test
    void findByIdTest() {
        // when
        BoardVO board = boardRepository.findById(givenBoard.getId());

        // then
        Assertions.assertEquals(givenBoard.getId(), board.getId());
        Assertions.assertEquals(givenBoard.getTitle(), board.getTitle());
        Assertions.assertEquals(givenBoard.getContent(), board.getContent());
        Assertions.assertEquals(givenBoard.getWriteTime(), board.getWriteTime());
        Assertions.assertEquals(givenBoard.getViewCount(), board.getViewCount());
        Assertions.assertEquals(givenBoard.getState(), board.getState());
        Assertions.assertEquals(givenBoard.getWriterId(), board.getWriterId());
    }

    @Test
    void deleteTest() {
        // when
        int result = boardRepository.delete(givenBoard.getId());

        // then
        Assertions.assertEquals(result, 1);
    }
}
