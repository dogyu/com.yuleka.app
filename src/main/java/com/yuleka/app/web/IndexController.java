package com.yuleka.app.web;

import com.yuleka.app.service.PostsService;
import com.yuleka.app.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        Integer pageCount = postsService.getPageCount();
        log.info(">>>>>> pageCount : " + pageCount);

        model.addAttribute("posts", postsListResponseDtoList);
        model.addAttribute("pageCount", pageCount);

        //return "index";
        return "posts/posts_list.html";
    }

    @PostMapping("/fetch_data")
    public String index2(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        model.addAttribute("posts", postsService.findAllDesc(pageNum));
        log.info(">>>>>> pageNum : " + pageNum);
        //return "fetch_data";
        return "posts/fetch_data.html";
    }

    @GetMapping("/touch")
    public String index(Model model) {
        return "touch";
    }
}
