package edu.miu.lab4.controller;


import edu.miu.lab4.aspect.annotation.ExecutionTime;
import edu.miu.lab4.entity.dto.CommentDto;
import edu.miu.lab4.entity.dto.PostDto;
import edu.miu.lab4.entity.dto.UserDto;
import edu.miu.lab4.service.CommentService;
import edu.miu.lab4.service.PostService;
import edu.miu.lab4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("api/v1/users")
@RestController
public class UserController {
    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
    @Autowired
    public UserController(UserService userService, PostService postService, CommentService commentService) {
        this.userService = userService;
        this.postService =postService;
        this.commentService = commentService;
    }




    @GetMapping
    public List<UserDto> allUsers(@RequestParam(value = "number_of_posts", required = false) Integer numberofPosts, @RequestParam(value = "title") String title) {
        return numberofPosts == null && title == null ? userService.findAll() : userService.findUsersByNumberOfPostsAndTitle(numberofPosts, title);
    }


    @GetMapping("/{id}")
    @ExecutionTime
    public UserDto getById(@PathVariable long id, HttpServletResponse res) {
        UserDto u = userService.findById(id);
        if(u == null){
            res.setStatus(404);
            return null;
        }
        else return u;

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody UserDto p) {
        userService.save(p);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long userId, @RequestBody UserDto u) {
        userService.update(userId, u);
    }

    @GetMapping("/{id}/posts")
    public List<PostDto> findUserPosts(@PathVariable("id") long userId) {
        return postService.findByUserId(userId);
    }

    @GetMapping("/{id}/posts/{postId}")
    public PostDto findUserPosts(@PathVariable("id") long userId, @PathVariable("postId") long postId, HttpServletResponse res) {
        PostDto p =  postService.findByUserIdAndId(userId,postId);
        if(p == null){
            res.setStatus(404);
            return null;
        }
        else return p;
    }

    @GetMapping("/{id}/posts/{postId}/comments")
    public List<CommentDto> getPostComments(@PathVariable("id") long userId,@PathVariable("postId") long postId) {
        return commentService.findAllByPostIDAndUserId(postId, userId);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/posts/{postId}/comments")
    public void savePostComment(@PathVariable("id") long userId, @PathVariable("postId") long postId, @RequestBody CommentDto c) {
        postService.addComment(userId, postId, c);
    }

    @GetMapping("/{id}/posts/{postId}/comments/{commentId}")
    public CommentDto getPostComment(@PathVariable("id") long userId,@PathVariable("postId") long postId, @PathVariable("commentId") long commentId, HttpServletResponse res) {
        CommentDto c = commentService.findByIdAndPostIDAndUserId(commentId, postId, userId);
        if(c == null){
            res.setStatus(404);
            return null;
        }
        else return c;
    }
}
