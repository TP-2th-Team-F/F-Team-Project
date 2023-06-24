package com.jojoldu.book.springboot.service.NumBall;

import com.jojoldu.book.springboot.domain.numballs.Numballs;
import com.jojoldu.book.springboot.web.dto.NumballsRequestDto;

public interface NumberService {
    Long saveNumber(Long id, NumballsRequestDto requestDto);
    Numballs getNumberById(Long id);
}
