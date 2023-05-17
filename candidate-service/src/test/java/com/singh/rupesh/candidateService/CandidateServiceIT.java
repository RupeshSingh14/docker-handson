package com.singh.rupesh.candidateService;

import com.singh.rupesh.candidateService.dto.CandidateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
class CandidateServiceIT extends BaseTest{

    @Autowired
    private WebTestClient client;

    @Test
    void allCandidatesTest(){
        this.client
                .get()
                .uri("/candidate/all")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void getCandidateByIdTest(){
        this.client
                .get()
                .uri("/candidate/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$..name").isEqualTo("Sam");
    }

    @Test
    void postCandidateTest(){
        var dto= CandidateDto.create(null, "test", Set.of("K8s"), null);
        this.client.post()
                .uri("/candidate")
                .bodyValue(dto)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("test");
    }
}
