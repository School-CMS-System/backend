package com.project.cyberedge.controller;


import com.project.cyberedge.dto.ApiResponse;
import com.project.cyberedge.dto.CommentDTO;
import com.project.cyberedge.dto.CourseDTO;
import com.project.cyberedge.dto.DiscussionDTO;
import com.project.cyberedge.model.Discussion;
import com.project.cyberedge.service.CommentService;
import com.project.cyberedge.service.DiscussionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discussion")
public class CommentAPI {

    private final CommentService commentService;
    private final DiscussionService discussionService;

    @PostMapping("comment/create")
    public ResponseEntity<ApiResponse<Void>> createComment(@RequestBody CommentDTO.CommentRequest commentRequest) {
        commentService.createComment(commentRequest);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .status(200)
                .message("Comment Created Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("all")
    public ResponseEntity<ApiResponse<List<Discussion>>> getAllDiscussions() {
        ApiResponse<List<Discussion>> response = ApiResponse.<List<Discussion>>builder()
                .success(true)
                .status(200)
                .data(discussionService.getAllDiscussions())
                .message("Discussion Retrieved Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("all/{id}")
    public ResponseEntity<ApiResponse<DiscussionDTO.DiscussionResponse>> getAllDiscussionComments(@PathVariable Integer id) {
        ApiResponse<DiscussionDTO.DiscussionResponse> response = ApiResponse.<DiscussionDTO.DiscussionResponse>builder()
                .success(true)
                .status(200)
                .data(discussionService.getDiscussionById(id))
                .message("Discussion Retrieved Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
