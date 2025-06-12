package com.pjmj.pjmanage.dto;


import com.pjmj.pjmanage.models.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureTaskDTO {
    private Long id;
    private List<TaskStatusDTO> tasks;
}
