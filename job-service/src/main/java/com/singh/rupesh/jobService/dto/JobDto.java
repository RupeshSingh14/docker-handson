package com.singh.rupesh.jobService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class JobDto {

    private String description;
    private String company;
    private Set<String> skills;
    private Integer salary;
    private Boolean isRemote;
    private String hostname;

}
