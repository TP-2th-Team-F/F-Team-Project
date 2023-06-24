package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.numballs.Numballs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class NumballsRequestDto {
    private String myGuess;

    @Builder
    public NumballsRequestDto(String myGuess){
        this.myGuess = myGuess;
    }

    public Numballs toEntity(){
        return Numballs.builder()
                .myGuess(myGuess)
                .build();
    }
}
