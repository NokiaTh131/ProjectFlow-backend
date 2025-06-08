package com.pjmj.pjmanage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.Date;
import java.util.List;

@Builder
@Data
@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @URL(message = "please provide valid url")
    private String githubUrl;
    private Date deadLine;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Feature> features;
}
