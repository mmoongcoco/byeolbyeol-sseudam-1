package com.app.byeolbyeolsseudam.controller.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.service.community.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/comment/*", "/comment"})
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{boardId}")
    public List<CommentDTO> getCommentList(@PathVariable Long boardId){
        return commentService.getCommentList(boardId);
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> saveComment(@RequestBody CommentDTO commentDTO) throws UnsupportedEncodingException {
        commentService.saveComment(commentDTO);
        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
    }


}