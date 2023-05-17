package com.singh.rupesh.jobService.service;

import com.singh.rupesh.jobService.dto.JobDto;
import com.singh.rupesh.jobService.repository.JobRepository;
import com.singh.rupesh.jobService.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    public Flux<JobDto> allJobs(){
        return this.repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Flux<JobDto> jobsBySkills(Set<String> skills){
        return this.repository.findBySkillsIn(skills)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<JobDto> save(Mono<JobDto> mono){
        return mono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }

}
