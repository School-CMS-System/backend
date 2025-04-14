package com.project.cyberedge.repository;

import com.project.cyberedge.model.Discussion;
import com.project.cyberedge.model.DiscussionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionCommentRepository extends JpaRepository<DiscussionComment, Integer> {
    List<DiscussionComment> findAllByDiscussion(Discussion discussion);
}
