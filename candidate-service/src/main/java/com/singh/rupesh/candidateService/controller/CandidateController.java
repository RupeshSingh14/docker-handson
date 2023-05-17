package com.singh.rupesh.candidateService.controller;

import com.singh.rupesh.candidateService.dto.CandidateDetailsDto;
import com.singh.rupesh.candidateService.dto.CandidateDto;
import com.singh.rupesh.candidateService.service.CandidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("candidate")
public class CandidateController {

    private static final Logger LOG = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    private CandidateService service;

    @GetMapping("all")
    public Flux<CandidateDto> allCandidates(){
        LOG.info("getting all the candidates info");
        return this.service.all();
    }

    @GetMapping("{id}")
    public Mono<CandidateDetailsDto> getById(@PathVariable String id){
        LOG.info("getting candidate info for id: {}", id);
        return this.service.getById(id);
    }

    @PostMapping
    public Mono<CandidateDto> save(@RequestBody Mono<CandidateDto> mono){
        LOG.info("saving candidate info: {}", mono);
        return this.service.save(mono);
    }
}