package com.project.cyberedge.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment extends AbstractEntity {

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_active")
    private Boolean isActive;
}
