package com.ksy.noticeboard.controller;

import com.ksy.noticeboard.dto.Board;
import com.ksy.noticeboard.service.BoardService;
import com.ksy.noticeboard.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="p", required = false) Integer p) {
        // 페이지네이션
        int curPageNo = (p != null)? p : 1;// 현재 페이지 번호
        int perPageRows = 4; // 한 페이지에 나타날 행의 개수
        int perPagination = 5; // 한 화면에 나타날 페이지 번호 개수
        int totalRows = boardService.getBoardTotalNum(); // 행의 총 개수
        Pagination pagination = new Pagination(curPageNo, perPageRows, perPagination, totalRows);
        pagination.setFirstRow(pagination.getFirstRow() - 1);

        model.addAttribute("pagination", pagination);
        model.addAttribute("boardList", boardService.getBoardList(pagination));
        return "boards/list";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable int id) {
        model.addAttribute("board", boardService.getBoard(id));
        return "boards/detail";
    }

    @GetMapping("/create-form")
    public String createForm() {
        return "boards/create";
    }

    @PostMapping("/create")
    public String create(@Valid Board board, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "boards/create";

        int user_id = 1; // 현재 로그인 한 사람 <-- 임시
        board.setWriter_id(user_id);
        Timestamp current_time = new Timestamp(System.currentTimeMillis());
        board.setWrite_time(String.valueOf(current_time));
        int id = boardService.createBoard(board);

        redirectAttributes.addAttribute("id", id);
        return "redirect:/board/{id}";
    }
}
