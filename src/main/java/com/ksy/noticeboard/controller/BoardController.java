package com.ksy.noticeboard.controller;

import com.ksy.noticeboard.dto.Board;
import com.ksy.noticeboard.service.BoardService;
import com.ksy.noticeboard.util.CRUDFailException;
import com.ksy.noticeboard.util.Pagination;
import com.ksy.noticeboard.vo.BoardVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    @ApiOperation(value = "메인", notes = "메인 페이지입니다.")
    @GetMapping("/main")
    public String home() {
        LOGGER.info("메인 페이지 : home() 메서드 호출");
        return "home";
    }

    @ApiOperation(value = "게시판 리스트", notes = "게시판 리스트 페이지입니다.")
    @GetMapping
    public String list(Model model,
                       @ApiParam(value = "현재 페이지", example = "1")
                       @RequestParam(value="p", required = false) Integer p) {
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

    @ApiOperation(value = "상세 페이지", notes = "게시글 눌렀을 때 나오는 상세 페이지입니다.")
    @GetMapping("/{id}")
    public String detail(Model model,
                         @ApiParam(value = "글 id", required = true, example = "1")
                         @PathVariable int id) {
        LOGGER.info("상세 페이지 : detail() 메서드 호출");
        BoardVO boardVO =  boardService.getBoard(id);

        if (boardVO.getId() == 0) {
            throw new CRUDFailException("글을 불러오지 못했습니다.");
        }
        else {
            model.addAttribute("board",boardVO);
            return "boards/detail";
        }
    }

    @ApiOperation(value = "새 글 작성 페이지", notes = "새 게시글 작성하는 페이지입니다.")
    @GetMapping("/create")
    public String createForm() {
        LOGGER.info("새 글 작성 페이지 : createForm() 메서드 호출");
        return "boards/create";
    }

    @ApiOperation(value = "글 생성 로직", notes = "글을 생성하는 로직입니다. 새 글 작성 페이지에서 저장 버튼 누르면 호출됩니다.")
    @PostMapping
    public String create(@Valid @RequestBody Board board, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        LOGGER.info("글 생성 : create() 메서드 호출");

        if(bindingResult.hasErrors()) return "boards/create";

        int user_id = 1; // 현재 로그인 한 사람 <-- 임시
        board.setWriterId(user_id);
        int result = boardService.createBoard(board);

        if (result == 1) {
            redirectAttributes.addAttribute("id", board.getId());
            return "redirect:/board/{id}";
        }
        else {
            throw new CRUDFailException("글을 생성하지 못했습니다.");
        }
    }

    @ApiOperation(value = "게시글 수정 페이지", notes = "사용자가 게시글을 수정하는 페이지 입니다.")
    @GetMapping("/{id}/edit")
    public String updateForm(@ApiParam(value = "글 id", required = true, example = "1") @PathVariable int id, Model model) {
        LOGGER.info("글 수정 페이지 : updateForm() 메서드 호출");
        model.addAttribute("board", boardService.getBoard(id));
        return "boards/edit";
    }

    @ApiOperation(value = "글 수정 로직", notes = "글을 수정하는 로직입니다.")
    @PutMapping("/{id}")
    public String update(@Valid @RequestBody Board board, BindingResult bindingResult) {
        LOGGER.info("글 수정 : update() 메서드 호출");
        if(bindingResult.hasErrors()) return "boards/edit";

        int result = boardService.updateBoard(board);

        if (result == 1) {
            return "redirect:/board/{id}";
        }
        else {
            throw new CRUDFailException("글 수정에 실패했습니다.");
        }
    }

    @ApiOperation(value = "글 삭제 로직", notes = "글을 삭제하는 로직입니다.")
    @DeleteMapping("/{id}")
    public String delete(@ApiParam(value = "글 id", required = true, example = "1") @PathVariable int id) {
        LOGGER.info("글 삭제 : delete() 메서드 호출");
        int result = boardService.deleteBoard(id);

        if (result == 1) {
            return "redirect:/board";
        }
        else {
            throw new CRUDFailException("글 삭제에 실패했습니다.");
        }
    }
}
