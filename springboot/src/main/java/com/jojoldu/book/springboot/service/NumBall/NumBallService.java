package com.jojoldu.book.springboot.service.NumBall;

import com.jojoldu.book.springboot.domain.posts.Numballs;
import com.jojoldu.book.springboot.domain.posts.NumballsRepository;
import com.jojoldu.book.springboot.web.dto.NumballsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class NumBallService {
    private final NumballsRepository numballsRepository;

    @Transactional
    public Long save(NumballsRequestDto requestDto){
        return numballsRepository.save(requestDto.toEntity()).getId();
    }

}
