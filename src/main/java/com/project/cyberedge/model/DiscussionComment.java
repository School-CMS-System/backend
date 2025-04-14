package com.project.cyberedge.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "discussion_comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionComment extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "discussion_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_discussion", value = ConstraintMode.CONSTRAINT))
    private Discussion discussion;

    @ManyToOne
    @JoinColumn(name = "comment_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_comment", value = ConstraintMode.CONSTRAINT))
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user", value = ConstraintMode.CONSTRAINT))
    private User user;
}
