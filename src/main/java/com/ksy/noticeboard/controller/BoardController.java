package com.ksy.noticeboard.controller;

import com.ksy.noticeboard.dto.Board;
import com.ksy.noticeboard.service.BoardService;
import com.ksy.noticeboard.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    @GetMapping("/main")
    public String home() {
        LOGGER.info("메인 페이지 : home() 메서드 호출");
        return "home";
    }

    @GetMapping
    public String list(Model model, @RequestParam(value="p", required = false) Integer p) {
        LOGGER.info("리스트 페이지 : list() 메서드 호출");

        // 페이지네이션
        int curPageNo = (p != null)? p : 1;// 현재 페이지 번호
        int perPageRows = 4; // 한 페이지에 나타날 행의 개수
        int perPagination =  5; // 한 화면에 나타날 페이지 번호 개수
        int totalRows = boardService.getBoardTotalNum(); // 행의 총 개수
        Pagination pagination = new Pagination(curPageNo, perPageRows, perPagination, totalRows);
        pagination.setFirstRow(pagination.getFirstRow() - 1);

        model.addAttribute("pagination", pagination);
        model.addAttribute("boardList", boardService.getBoardList(pagination));
        return "boards/list";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable int id) {
        LOGGER.info("상세 페이지 : detail() 메서드 호출");
        model.addAttribute("board", boardService.getBoard(id));
        return "boards/detail";
    }

    @GetMapping("/create")
    public String createForm() {
        LOGGER.info("새 글 작성 페이지 : createForm() 메서드 호출");
        return "boards/create";
    }

    @PostMapping
    public String create(@Valid Board board, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        LOGGER.info("글 생성 : create() 메서드 호출");

        if(bindingResult.hasErrors()) return "boards/create";

        int user_id = 1; // 현재 로그인 한 사람 <-- 임시
        board.setWriterId(user_id);
        Timestamp current_time = new Timestamp(System.currentTimeMillis());
        board.setWriteTime(String.valueOf(current_time));
        int id = boardService.createBoard(board);

        redirectAttributes.addAttribute("id", id);
        return "redirect:/board/{id}";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable int id, Model model) {
        LOGGER.info("글 수정 페이지 : updateForm() 메서드 호출");
        model.addAttribute("board", boardService.getBoard(id));
        return "boards/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @Valid Board board, BindingResult bindingResult) {
        LOGGER.info("글 수정 : update() 메서드 호출");
        if(bindingResult.hasErrors()) return "boards/edit";
        boardService.updateBoard(board);
        return "redirect:/board/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable int id) {
        LOGGER.info("글 삭제 : delete() 메서드 호출");
        boardService.deleteBoard(id);
        return "redirect:/board";
    }
}
