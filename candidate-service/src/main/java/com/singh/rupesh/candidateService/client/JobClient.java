package com.singh.rupesh.candidateService.client;

import com.singh.rupesh.candidateService.dto.JobDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class JobClient {

    private final WebClient client;

    //job/search?skills=
    //http://localhost:1080/job/search?skills=java&skills=spring
    public JobClient(@Value("${job.service.url}") String baseUrl){
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<List<JobDto>> getRecommendedJobs(Set<String> skills){
        return this.client.get()
                .uri(u -> u.path("search").queryParam("skills", skills).build())
                .retrieve()
                .bodyToFlux(JobDto.class)
                .log("client call is successful")
                .collectList()
                .onErrorReturn(Collections.emptyList());
    }
}
