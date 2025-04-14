package com.project.cyberedge.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class DiscussionDTO {

    @Data
    public static class DiscussionRequest {
        private String name;
        private String body;

        public DiscussionRequest(String name, String body) {
            this.name = name;
            this.body = body;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DiscussionResponse {
        private Integer id;
        private String name;
        private String body;
        private List<CommentDTO.CommentResponse> comment;
    }
}
