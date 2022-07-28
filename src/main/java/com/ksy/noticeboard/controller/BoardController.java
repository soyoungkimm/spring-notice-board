package com.ksy.noticeboard.controller;

import com.ksy.noticeboard.service.BoardService;
import com.ksy.noticeboard.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
