package edu.miu.lab4.controller;

import edu.miu.lab4.entity.dto.CommentDto;
import edu.miu.lab4.entity.dto.PostDto;
import edu.miu.lab4.service.CommentService;
import edu.miu.lab4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("api/v1/posts")
@RestController
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("")
    public List<PostDto> allPost(@RequestParam(value = "author" ,required = false) String author, @RequestParam(value = "title" ,required = false) String title) {
        return author == null && title == null  ? postService.findAll() : postService.findByAuthorAndTitle(author, title);
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable long id, HttpServletResponse res) {
        PostDto p = postService.findById(id);
        if(p == null){
            res.setStatus(404);
            return null;
        }
        else return p;

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        postService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody PostDto p) {
        postService.save(p);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long postId, @RequestBody PostDto p) {
        postService.update(postId,p);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/comments")
    public void saveComment(@PathVariable("id") long postId, @RequestBody CommentDto c) {
        commentService.save(postId, c);
    }

}
