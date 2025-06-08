package com.pjmj.pjmanage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskDescriptionDTO {
    private Long taskId;
    private String description;
}
