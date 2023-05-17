package com.singh.rupesh.candidateService.service;

import com.singh.rupesh.candidateService.client.JobClient;
import com.singh.rupesh.candidateService.controller.CandidateController;
import com.singh.rupesh.candidateService.dto.CandidateDetailsDto;
import com.singh.rupesh.candidateService.dto.CandidateDto;
import com.singh.rupesh.candidateService.repository.CandidateRepository;
import com.singh.rupesh.candidateService.util.EntityDtoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CandidateService {

    private static final Logger LOG = LoggerFactory.getLogger(CandidateService.class);

    @Autowired
    private JobClient client;

    @Autowired
    private CandidateRepository repository;

    public Flux<CandidateDto> all(){
        return this.repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CandidateDetailsDto> getById(String id){
        LOG.info("candidate service called to get info");
        return this.repository.findById(id)
                .map(EntityDtoUtil::toDetailsDto)
                .flatMap(this::addRecommendedJobs);
    }

    private Mono<CandidateDetailsDto> addRecommendedJobs(CandidateDetailsDto dto){
        LOG.info("getting recommendations for info: {}", dto);
        return this.client.getRecommendedJobs(dto.getSkills())
                .doOnNext(dto::setRecommendedJobs)
                .thenReturn(dto);
    }

    public Mono<CandidateDto> save(Mono<CandidateDto> mono){
        return mono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }
}