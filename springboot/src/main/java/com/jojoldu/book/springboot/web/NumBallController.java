package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.domain.numballs.NumballsRepository;
import com.jojoldu.book.springboot.service.NumBall.NumBallService;
import com.jojoldu.book.springboot.web.dto.NumballsRequestDto;
import com.jojoldu.book.springboot.web.dto.NumballsResponseDto;
import com.jojoldu.book.springboot.web.dto.NumballsSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class NumBallController {
    @Autowired
    private final NumBallService numBallService;
//
//    @GetMapping("/")
//    public String gameStart(){
//        return "game-start";
//    }

    //    게임 시작 -> Player 와 랜덤 값 answer 저장
    @PostMapping("/numball/refresh")
    public Long save(@RequestBody NumballsSaveRequestDto requestDto){
        return numBallService.save(requestDto);
    }

    public NumBallController(NumBallService numBallService,
                             NumballsRepository numballsRepository){
        this.numBallService = numBallService;
    }

    //게임 로그 보여주기
//    @GetMapping("/numball/{id}")
//    public NumballsResponseDto findById(@PathVariable Long id){
//        return numBallService.findById(id);
//    }


    // 게임 진행, 값 추정하기
    @PostMapping("/numball/{id}")
    public Long Playing(@PathVariable Long id, @RequestBody  NumballsRequestDto requestDto){
        return numBallService.saveNumber(id, requestDto);
    }
}
