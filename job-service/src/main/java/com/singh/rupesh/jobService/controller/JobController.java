package com.singh.rupesh.jobService.controller;

import com.singh.rupesh.jobService.dto.JobDto;
import com.singh.rupesh.jobService.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("all")
    public Flux<JobDto> all(){
        return this.service.allJobs();
    }

    @GetMapping("search")
    public Flux<JobDto> search(@RequestParam Set<String> skills){
        return this.service.jobsBySkills(skills);
    }

    @PostMapping
    public Mono<JobDto> save(@RequestBody Mono<JobDto> mono){
        return this.service.save(mono);
    }
}
