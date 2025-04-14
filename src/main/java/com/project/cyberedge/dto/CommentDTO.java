package com.project.cyberedge.dto;

import com.project.cyberedge.model.Comment;
import com.project.cyberedge.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommentDTO {

    @Data
    public static class CommentRequest{
        private Integer discussionId;
        private String comment;
        private Integer userId;

        public CommentRequest(Integer discussionId, String comment, Integer userId) {
            this.discussionId = discussionId;
            this.comment = comment;
            this.userId = userId;
        }
    }

    @Data
    public static class CommentResponse{
        private Integer id;
        private String comment;
        private UserDTO.UserResponseDTO user;

        public CommentResponse from(Comment comment, User user) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setComment(comment.getComment());
            commentResponse.setUser(UserDTO.UserResponseDTO.from(user));
            return commentResponse;
        }
    }
}
