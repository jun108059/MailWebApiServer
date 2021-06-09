package org.dev.hufs.controller;

import lombok.extern.slf4j.Slf4j;
import org.dev.hufs.dto.SampleDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController

@Controller
@RequestMapping("/sample")
@Slf4j
public class SampleController {

    @GetMapping({"/hello"})
    public void hello() {
        log.info("###");
    }

    @GetMapping({"/dto"})
    public void testModel(Model model) {
        log.info("###2");
        List<SampleDto> list = IntStream.rangeClosed(1, 20)
                .asLongStream().mapToObj(i -> {
                    return SampleDto.builder()
                            .sno(i)
                            .first("First.." + i)
                            .last("Last.." + i)
                            .regTime(LocalDateTime.now())
                            .build();
                }).collect(Collectors.toList());

        model.addAttribute("list", list);
    }
}
