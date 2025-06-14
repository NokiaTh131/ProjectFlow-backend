package com.pjmj.pjmanage.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ProjectsDTO {
    private Long id;
    private String name;
    private Date deadLine;
    private String finished;
}
