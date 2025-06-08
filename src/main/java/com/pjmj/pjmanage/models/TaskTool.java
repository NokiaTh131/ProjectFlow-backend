package com.pjmj.pjmanage.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_tool")
@Builder
public class TaskTool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task tasks;

    @NotNull
    private String name;

    @URL
    private String iconUrl;

    @Column(columnDefinition = "TEXT")
    private String note;

    @URL
    private String link;
}
