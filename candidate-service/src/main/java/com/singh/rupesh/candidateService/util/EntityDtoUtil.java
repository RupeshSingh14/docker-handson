package com.singh.rupesh.candidateService.util;

import com.singh.rupesh.candidateService.dto.CandidateDetailsDto;
import com.singh.rupesh.candidateService.dto.CandidateDto;
import com.singh.rupesh.candidateService.entity.Candidate;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static CandidateDto toDto(Candidate candidate){
        CandidateDto dto = new CandidateDto();
        BeanUtils.copyProperties(candidate, dto);
        dto.setHostname(AppUtil.getHostname());
        return dto;
    }

    public static CandidateDetailsDto toDetailsDto(Candidate candidate){
        CandidateDetailsDto dto = new CandidateDetailsDto();
        BeanUtils.copyProperties(candidate, dto);
        dto.setHostname(AppUtil.getHostname());
        return dto;
    }

    public static Candidate toEntity(CandidateDto dto){
        Candidate entity = new Candidate();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
