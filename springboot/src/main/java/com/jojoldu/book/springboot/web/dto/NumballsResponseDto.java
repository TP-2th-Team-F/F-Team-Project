package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.numballs.Numballs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NumballsResponseDto<T> {
    private Long id;
    private List<String> myGuess;
    private List<String> result;
    private String answer;

    private String error;
    private List<T> data;

    public NumballsResponseDto(Numballs entity){
        this.id = entity.getId();
        this.myGuess=entity.getMyGuess();
        this.result=entity.getResult();
        this.answer=entity.getAnswer();
    }

}
