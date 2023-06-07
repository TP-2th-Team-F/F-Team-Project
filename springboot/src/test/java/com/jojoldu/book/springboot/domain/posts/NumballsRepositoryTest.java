package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.numballs.Numballs;
import com.jojoldu.book.springboot.domain.numballs.NumballsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumballsRepositoryTest {

    @Autowired
    NumballsRepository numballsRepository;

    @After
    public void cleanup(){
        numballsRepository.deleteAll();
    }
    @Test
    public void 게임내역_불러오기(){
        //given
        String myGuess = "123";
        String answer = "1Ball-1Strike";
        String player = "aristo";

        numballsRepository.save(Numballs.builder()
                        .myGuess(myGuess)
                        .answer(answer)
                        .player(player)
                        .build());

        //when
        List<Numballs> numballsList = numballsRepository.findAll();

        //then
        Numballs numballs = numballsList.get(0);
        assertThat(numballs.getAnswer()).isEqualTo(answer);
        assertThat(numballs.getPlayer()).isEqualTo(player);
        assertThat(numballs.getMyGuess()).isEqualTo(myGuess);


    }

}