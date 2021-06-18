package org.dev.hufs.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.hufs.dto.MailSaveResponseDto;
import org.dev.hufs.entity.Mail;
import org.dev.hufs.service.MailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;

    @GetMapping
    public String main() {

        return "basic";
    }

    @GetMapping("mail")
    public String getAllMails(Model model,
                              @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Mail> result = mailService.getMails(pageable);
        model.addAttribute("mails", result.getContent());
        model.addAttribute("totalCount", result.getTotalElements());
        model.addAttribute("totalPage", result.getTotalPages());
        model.addAttribute("boards", result);
        return "basic";
    }

    @GetMapping("mail/ad")
    public String getAdMails(Model model,
                              @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Mail> result = mailService.getCategoryMails("광고", pageable);
        model.addAttribute("mails", result.getContent());
        model.addAttribute("totalCount", result.getTotalElements());
        model.addAttribute("totalPage", result.getTotalPages());
        model.addAttribute("boards", result);
        return "/basic";
    }

    @GetMapping("mail/payment")
    public String getPaymentMails(Model model,
                              @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Mail> result = mailService.getCategoryMails("결제", pageable);
        model.addAttribute("mails", result.getContent());
        model.addAttribute("totalCount", result.getTotalElements());
        model.addAttribute("totalPage", result.getTotalPages());
        model.addAttribute("boards", result);
        return "/basic";
    }

    @GetMapping("mail/login")
    public String getLoginMails(Model model,
                              @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Mail> result = mailService.getCategoryMails("로그인", pageable);
        model.addAttribute("mails", result.getContent());
        model.addAttribute("totalCount", result.getTotalElements());
        model.addAttribute("totalPage", result.getTotalPages());
        model.addAttribute("boards", result);
        return "/basic";
    }

    @GetMapping("mail/edu")
    public String getEduMails(Model model,
                              @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Mail> result = mailService.getCategoryMails("교육", pageable);
        model.addAttribute("mails", result.getContent());
        model.addAttribute("totalCount", result.getTotalElements());
        model.addAttribute("totalPage", result.getTotalPages());
        model.addAttribute("boards", result);
        return "basic";
    }

    @PostMapping("mail")
    public MailSaveResponseDto createMailData() {
        List<Mail> result = mailService.saveMails();
        return MailSaveResponseDto.builder()
                .totalCount(result.size())
                .page(result.size() / 10)
                .build();
    }


}
