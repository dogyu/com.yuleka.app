package com.yuleka.app.web;

import com.yuleka.app.service.PostsService;
import com.yuleka.app.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {

        List<PostsListResponseDto> postsListResponseDtoList = postsService.findAllDesc(pageNum);
        Integer[] pageList = postsService.getPageList(pageNum);

        model.addAttribute("posts", postsListResponseDtoList);
        model.addAttribute("pageList", pageList);

        //log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}");
        return "index";
    }

    @PostMapping("/fetch_data")
    public String index2(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        model.addAttribute("posts", postsService.findAllDesc(pageNum));
        log.info("/fetch_data");
        return "fetch_data";
    }

    @GetMapping("/touch")
    public String index(Model model) {
        return "touch";
    }
}
