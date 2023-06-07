package com.jojoldu.book.springboot.service.NumBall;

import com.jojoldu.book.springboot.domain.numballs.Numballs;
import com.jojoldu.book.springboot.domain.numballs.NumballsRepository;
import com.jojoldu.book.springboot.web.dto.NumballsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;


@RequiredArgsConstructor
@Service
public class NumBallService implements NumberService{
    private final NumballsRepository numballsRepository;
    @Override
    public Numballs saveNumber(String myGuess) {
        Numballs numball = new Numballs();
        numball.setMyGuess(myGuess);
        numball.setAnswer(getResult(answer, myGuess));
        return numballsRepository.save(numball);
    }

    @Override
    public Numballs getNumberById(Long id) {
        return numballsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Number not found"));
    }


    @Transactional
    public Long save(NumballsRequestDto requestDto){
        return numballsRepository.save(requestDto.toEntity()).getId();
    }


    //게임 진행 메서드

    //임의 결과
    static String answer="1234";
    public static boolean getNumball(String guess){
        if(answer.equals(guess)){
            //결과 맞춤. end
            System.out.println(" you are right! ");
            return true;
        } else{
            //game 다시 하기
            System.out.println(" Try again! ");
            System.out.println(getResult(answer, guess));
            return false;
        }
    }

    // 입력값 결과 판정
    public static String getResult(String answer, String guess){
        int strikeNum=0;
        int ballNum=0;

        String[] answerArray = answer.split("");
        String[] guessArray = guess.split("");
        for(int i=0;i<answerArray.length;i++){
            // 스트라이크 판정
            if(answerArray[i].equals(guessArray[i])){
                strikeNum++;
                continue;
            }
            for(int j=0; j<answerArray.length;j++){
                if(i!=j){
                    // 볼 판정
                    if(answerArray[i].equals(guessArray[j])){
                        ballNum++;
                        continue;
                    }
                }
            }
        }

        return "strike: "+strikeNum+" ball: "+ballNum;
    }

    public static void RunGame(){
        Scanner scan = new Scanner(System.in);
        String guess ;
        while(true){
            // 게임 진행. 입력값 받기
            System.out.println("입력값 >> ");
            guess = scan.nextLine();

            if(getNumball(guess)){
                break;
            }
        }
    }
}
