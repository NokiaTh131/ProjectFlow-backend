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

@Data
@Entity
@Builder
@Table(name = "features")
@AllArgsConstructor
@NoArgsConstructor
public class Feature {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "feature",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> tasks;

}
