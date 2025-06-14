package com.pjmj.pjmanage.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeatureResponseDTO {
    private Long id;
    private String name;
    private List<TaskResponseDTO> tasks;
}