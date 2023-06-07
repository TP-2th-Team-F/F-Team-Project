package com.jojoldu.book.springboot.service.NumBall;

import com.jojoldu.book.springboot.domain.numballs.Numballs;

public interface NumberService {
    Numballs saveNumber(String myGuess);
    Numballs getNumberById(Long id);
}
