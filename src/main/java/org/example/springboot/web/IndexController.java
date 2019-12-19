package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.posts.Posts;
import org.example.springboot.service.posts.PostsService;
import org.example.springboot.web.dto.PostsListResponseDto;
import org.example.springboot.web.dto.PostsResponseDto;
import org.example.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @PageableDefault(page=0, size=5, sort={"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        Page<PostsListResponseDto> posts = postsService.findAllPaging(pageable);
        int maxSize = posts.getSize()/5;
        int prev = pageable.getPageNumber() - 1 < 0 ? 0 : pageable.getPageNumber() - 1;
        int next = pageable.getPageNumber() + 1 > maxSize ? maxSize : pageable.getPageNumber() + 1;

        model.addAttribute("posts", posts);
        model.addAttribute("prev", prev);
        model.addAttribute("next", next);
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }



    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto requestDto = postsService.findById(id);
        model.addAttribute("post", requestDto);

        return "posts-update";
    }
}
