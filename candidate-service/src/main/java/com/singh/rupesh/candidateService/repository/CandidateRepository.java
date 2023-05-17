package com.singh.rupesh.candidateService.repository;

import com.singh.rupesh.candidateService.entity.Candidate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CandidateRepository extends ReactiveCrudRepository<Candidate, String> {

}
