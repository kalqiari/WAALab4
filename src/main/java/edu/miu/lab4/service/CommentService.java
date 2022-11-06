package edu.miu.lab4.service;

import edu.miu.lab4.entity.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void save(long postId, CommentDto commentDto);

    List<CommentDto> findAllByPostIDAndUserId(long postId, long userId);

   CommentDto findByIdAndPostIDAndUserId(long commentId, long postId, long userId);
}
