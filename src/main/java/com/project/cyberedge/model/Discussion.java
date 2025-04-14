package com.project.cyberedge.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "discussion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discussion extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "body")
    private String body;

    @Column(name = "is_active")
    private Boolean isActive;

}
