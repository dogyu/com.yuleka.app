package com.yuleka.app.web.domain.heritage;


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
public class HeritageRepositoryTest {

    @Autowired
    HeritageRepository heritageRepository;

    @After
    public void cleanup() {
        heritageRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        String title = "테스크 게시글";
        String contnet = "테스트 본문";

        heritageRepository.save(Heritage.builder()
                                    .title(title)
                                    .content(contnet)
                                    .author("hello@gmail.com")
                                    .build()
        );

        List<Heritage> heritageList = heritageRepository.findAll();

        Heritage heritage = heritageList.get(0);
        assertThat(heritage.getTitle()).isEqualTo(title);
        assertThat(heritage.getContent()).isEqualTo(contnet);
    }
}
