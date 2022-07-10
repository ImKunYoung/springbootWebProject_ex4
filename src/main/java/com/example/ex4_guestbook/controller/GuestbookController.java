package com.example.ex4_guestbook.controller;

import com.example.ex4_guestbook.dto.GuestbookDTO;
import com.example.ex4_guestbook.dto.PageRequestDTO;
import com.example.ex4_guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 Annotation
public class GuestbookController {

    private final GuestbookService service;

    @GetMapping({"/"})
    public String list() {

        log.info("list............");

        return "redirect:/guestbook/list"; // /guestbook/list 로 매핑

    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list............"+pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));

    }

    /*등록 페이지와 등록 처리 - 등록 페이지*/
    @GetMapping("/register")
    public void register() {
        log.info("register get...");
    }
    /*등록 페이지와 등록 처리 - 등록 처리*/
    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes) {

        log.info("dto..."+dto);

        // 새로 추가된 엔티티의 번호
        Long gno = service.register(dto);

        // 단 한 번만 데이터를 전달하는 용도
        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

}
