package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.NumBall.NumBallService;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.NumballsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final NumBallService numBallService;

    // 어느 컨트롤러든지 LoginUser만 사용하면 세션 정보를 가져올 수 있음
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        if(user!= null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

//   수정 api
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/numball/refresh")
    public String gameStart(Model model){
        return"game-start";
    }


//    @PostMapping("/numball/refresh")
//    public String gameOn(){
//        return"game-on";
//    }
    @GetMapping("/numball/log/{id}")
    public String gameLog(@PathVariable Long id, Model model){
        NumballsResponseDto dto = numBallService.findById(id);
        model.addAttribute("numball", dto);
        return"game-log";
    }
    @GetMapping("/numball/{id}")
    public String GameOn(@PathVariable Long id, Model model){
        NumballsResponseDto dto = numBallService.findById(id);
        model.addAttribute("numball", dto);

        return "game-on";
    }
//    @GetMapping("/numball/log/{id}")
//    public String GamePage(@PathVariable Long id){
//        NumballsResponseDto dto = numBallService.findById(id);
//
//        return "game-log";
//    }

//    @PostMapping("/numball/{id}")
//    public String gameOn(@PathVariable Long id, Model model){
//        NumballsResponseDto dto = numBallService.findById(id);
//
//        model.addAttribute("numball", dto);
//
//        return "game-on";
//    }

}
