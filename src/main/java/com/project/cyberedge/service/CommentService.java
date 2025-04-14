package com.project.cyberedge.service;

import com.project.cyberedge.dto.CommentDTO;
import com.project.cyberedge.model.Comment;
import com.project.cyberedge.model.Discussion;
import com.project.cyberedge.model.DiscussionComment;
import com.project.cyberedge.model.User;
import com.project.cyberedge.repository.CommentRepository;
import com.project.cyberedge.repository.DiscussionCommentRepository;
import com.project.cyberedge.repository.DiscussionRepository;
import com.project.cyberedge.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService  {
   private final DiscussionCommentRepository discussionCommentRepository;
   private final DiscussionRepository discussionRepository;
   private final CommentRepository commentRepository;
   private final UserRepository userRepository;

   @Transactional
   public void createComment(CommentDTO.CommentRequest commentRequest){
       Comment comment = new Comment();
       comment.setComment(commentRequest.getComment());
       comment.setIsActive(true);

       commentRepository.save(comment);

       User user = userRepository.findById(commentRequest.getUserId()).orElseThrow(()-> new RuntimeException("User not found"));
       Discussion discussion = discussionRepository.findById(commentRequest.getDiscussionId()).orElseThrow(()-> new RuntimeException("Discussion not found"));

       DiscussionComment discussionComment = new DiscussionComment();
       discussionComment.setDiscussion(discussion);
       discussionComment.setComment(comment);
       discussionComment.setUser(user);

       discussionCommentRepository.save(discussionComment);
   }
}
