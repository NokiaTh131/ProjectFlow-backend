package com.pjmj.pjmanage.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "tasks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String status = "not-started";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id")
    @JsonBackReference
    private Feature feature;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tasks",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TaskDescription> descriptions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tasks",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TaskTool> taskTools;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tasks",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TaskMessage> taskMessages;
}
