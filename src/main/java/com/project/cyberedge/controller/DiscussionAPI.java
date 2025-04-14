package com.project.cyberedge.controller;


import com.project.cyberedge.dto.ApiResponse;
import com.project.cyberedge.dto.CourseDTO;
import com.project.cyberedge.dto.DiscussionDTO;
import com.project.cyberedge.service.DiscussionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor/discussion")
public class DiscussionAPI {

    private final DiscussionService discussionService;

    @PostMapping("create")
    public ResponseEntity<ApiResponse<Void>> createDiscussion(@RequestBody DiscussionDTO.DiscussionRequest discussionRequest) {
        discussionService.create(discussionRequest);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .status(200)
                .message("Discussion Created Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<ApiResponse<Void>> updateDiscussion(@RequestBody DiscussionDTO.DiscussionRequest discussionRequest,@PathVariable Integer id) {
        discussionService.update(discussionRequest,id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .status(200)
                .message("Discussion Updated Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDiscussion(@PathVariable Integer id) {
        discussionService.delete(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .status(200)
                .message("Discussion Deleted Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
