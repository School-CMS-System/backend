package com.project.cyberedge.repository;


import com.project.cyberedge.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Integer> {
    Optional<Discussion> findDiscussionByIdAndIsActive(Integer id, Boolean isActive);
}
