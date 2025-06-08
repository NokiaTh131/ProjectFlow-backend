package com.pjmj.pjmanage.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_message")
@Builder
public class TaskMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task tasks;

    @NotNull
    private String name;

    @Column(columnDefinition = "TEXT")
    private String code;

    private String language;
}
