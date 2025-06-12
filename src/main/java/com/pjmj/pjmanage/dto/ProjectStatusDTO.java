package com.pjmj.pjmanage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ProjectStatusDTO {
    private Long id;
    private String status;
}