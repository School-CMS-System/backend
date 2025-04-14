package com.project.cyberedge.service;


import com.project.cyberedge.dto.CommentDTO;
import com.project.cyberedge.dto.DiscussionDTO;
import com.project.cyberedge.dto.UserDTO;
import com.project.cyberedge.model.Discussion;
import com.project.cyberedge.model.DiscussionComment;
import com.project.cyberedge.repository.CommentRepository;
import com.project.cyberedge.repository.DiscussionCommentRepository;
import com.project.cyberedge.repository.DiscussionRepository;
import com.project.cyberedge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final DiscussionCommentRepository discussionCommentRepository;

    public void create(DiscussionDTO.DiscussionRequest request) {
        Discussion discussion = new Discussion();
        discussion.setBody(request.getBody());
        discussion.setName(request.getName());
        discussion.setIsActive(true);
        discussionRepository.save(discussion);
    }

    public void update(DiscussionDTO.DiscussionRequest request,Integer id) {
        Discussion discussion = discussionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Discussion not found"));

        discussion.setBody(request.getBody());
        discussion.setName(request.getName());
        discussionRepository.save(discussion);
    }

    public void delete(Integer id) {
        Discussion discussion = discussionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Discussion not found"));

        discussion.setIsActive(false);
        discussionRepository.save(discussion);
    }

    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll().stream()
                .filter(Discussion::getIsActive).collect(Collectors.toList());
    }

    public DiscussionDTO.DiscussionResponse getDiscussionById(Integer id) {

        Discussion discussion = discussionRepository.findDiscussionByIdAndIsActive(id,true).orElseThrow(()-> new RuntimeException("Discussion not found"));
        // Fetching the discussions along with the comments and users
        List<DiscussionComment> discussionComments = discussionCommentRepository.findAllByDiscussion(discussion);

        // Grouping the discussion comments by discussion
        Map<Discussion, List<DiscussionComment>> discussionMap = discussionComments.stream()
                .collect(Collectors.groupingBy(DiscussionComment::getDiscussion));

        // Creating a DiscussionDTO
        DiscussionDTO.DiscussionResponse discussionDTO = new DiscussionDTO.DiscussionResponse();
        discussionDTO.setId(discussion.getId());
        discussionDTO.setName(discussion.getName());
        discussionDTO.setBody(discussion.getBody());

        // Converting discussions to DTOs
        for (Map.Entry<Discussion, List<DiscussionComment>> entry : discussionMap.entrySet()) {
            List<DiscussionComment> comments = entry.getValue();
            // Creating CommentDTOs
            List<CommentDTO.CommentResponse> commentDTOs = new ArrayList<>();
            for (DiscussionComment discussionComment : comments) {
                CommentDTO.CommentResponse commentDTO = new CommentDTO.CommentResponse();
                commentDTO.setId(discussionComment.getComment().getId());
                commentDTO.setComment(discussionComment.getComment().getComment());
                // Creating UserDTO for each comment
                UserDTO.UserResponseDTO userDTO = new UserDTO.UserResponseDTO();
                userDTO.setId(discussionComment.getUser().getId());
                userDTO.setEmail(discussionComment.getUser().getEmail());
                userDTO.setFirstName(discussionComment.getUser().getFirstName());
                userDTO.setLastName(discussionComment.getUser().getLastName());

                commentDTO.setUser(userDTO);
                commentDTOs.add(commentDTO);
            }
           discussionDTO.setComment(commentDTOs);
        }
        return discussionDTO;
    }


}
