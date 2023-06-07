package com.jojoldu.book.springboot.domain.posts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Numballs extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=100, nullable = false)
    private String myGuess;

    private String answer;

    @Column(length=100, nullable = false)
    private String player;

//    @Column(length=100, nullable = false)
//    private String myAnswer;


    // --- 수정 요망. player 이름 확인하는 알고리즘 추가 후 player 내용 삭제
    @Builder
    public Numballs(String myGuess, String answer, String player){
        this.myGuess=myGuess;
        this.answer=answer;
        this.player=player;
    }

}
