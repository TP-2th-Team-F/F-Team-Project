package com.jojoldu.book.springboot.service.NumBall;

import com.jojoldu.book.springboot.domain.numballs.Numballs;
import com.jojoldu.book.springboot.domain.numballs.NumballsRepository;
import com.jojoldu.book.springboot.web.dto.NumballsRequestDto;
import com.jojoldu.book.springboot.web.dto.NumballsResponseDto;
import com.jojoldu.book.springboot.web.dto.NumballsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NumBallService implements NumberService{
    private final NumballsRepository numballsRepository;

    @Transactional(readOnly = true)
    public List<NumballsResponseDto> findAllDesc(){
        return numballsRepository.findAllDesc().stream()
                .map(NumballsResponseDto::new)
                .collect(Collectors.toList());
    }

    //player와 랜덤 answer db에 저장
    @Transactional
    public Long save(NumballsSaveRequestDto requestDto){
        return numballsRepository.save(requestDto.toEntity()).getId();
    }


    //값 추정하기 - update 원리
    @Transactional
    public Long saveNumber(Long id, NumballsRequestDto requestDto) {
        Numballs numball = numballsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임이 없습니다. "+ id));
        String result="collect!";
        if(!getNumball(numball.getAnswer(), requestDto.getMyGuess())){//반환값 true(성공) or flase(실패)
            // 실패할 시 판정 결과 도출
            result = getResult(numball.getAnswer(),  requestDto.getMyGuess());
        }
        numball.setMyGuess(requestDto.getMyGuess());
        numball.checkResult(result);
        return id;
    }

    @Override
    public Numballs getNumberById(Long id) {
        return numballsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Number not found"));
    }

    // 게임 로그 보여주기
    @Transactional
    public NumballsResponseDto findById(Long id){
        Numballs entity = numballsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임이 없습니다."+id));
        return new NumballsResponseDto(entity);
    }




    //게임 진행 메서드
    // Random answer


    // 채점
    @Transactional
    public boolean getNumball(String answer, String myGuess){
        if(answer.equals(myGuess)){
            //결과 맞춤. end
            return true;
        } else{
            //game 다시 하기
            return false;
        }
    }

    // getResult 입력값 결과 판정 ->  return String
    @Transactional(readOnly = true)
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
}
