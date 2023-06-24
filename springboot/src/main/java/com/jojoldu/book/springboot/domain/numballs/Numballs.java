package com.jojoldu.book.springboot.domain.numballs;


import com.jojoldu.book.springboot.domain.posts.BaseTimeEntity;
import com.jojoldu.book.springboot.service.NumBall.ListToJsonConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Numballs extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = ListToJsonConverter.class)
    @Column(length = 100)
    private List<String> myGuess= new ArrayList<>();;

    @Convert(converter = ListToJsonConverter.class)
    @Column(length = 100)
    private List<String> result= new ArrayList<>();;

    @Column(nullable = false)
    private String answer;
    @Column(nullable = false)
    private String player;


    // --- 수정 요망. player 이름 확인하는 알고리즘 추가 후 player 내용 삭제
    @Builder
    public Numballs(String player, String answer, String myGuess){
        this.player=player;
        this.answer=answer;

        int temp = (int) (Math.random() * 1000);
        this.answer = String.valueOf(temp);
    }
    public void setMyGuess(String myGuess){
        this.myGuess.add(myGuess);
    }

    public void checkResult(String result) {
        this.result.add(result);
    }
}
