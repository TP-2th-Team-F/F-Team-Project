package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.numballs.Numballs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NumballsSaveRequestDto {
    private String player;

    @Builder
    public NumballsSaveRequestDto(String player){
        this.player = player;
    }

    public Numballs toEntity(){
        return Numballs.builder()
                .player(player)
                .build();
    }
}
