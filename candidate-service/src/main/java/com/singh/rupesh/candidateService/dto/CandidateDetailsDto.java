package com.singh.rupesh.candidateService.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CandidateDetailsDto extends CandidateDto{

    private List<JobDto> recommendedJobs;

}
