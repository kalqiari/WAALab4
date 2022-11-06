package edu.miu.lab4.service.imp;

import edu.miu.lab4.entity.Comment;
import edu.miu.lab4.entity.Post;
import edu.miu.lab4.entity.dto.CommentDto;
import edu.miu.lab4.entity.dto.PostDto;
import edu.miu.lab4.repo.CommentRepo;
import edu.miu.lab4.repo.PostRepo;
import edu.miu.lab4.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<PostDto> findAll() {
        var posts = postRepo.findAll();
        return posts.stream().map( e-> modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto findById(long id) {
        var post = postRepo.findById(id).orElse(null);
        return post == null ? null :  modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> findByTitle(String title) {
        var posts = postRepo.findByTitle(title);
        return posts.stream().map( e-> modelMapper.map(e, PostDto.class)).collect(Collectors.toList());

    }

    @Override
    public void deleteById(long id) {
        postRepo.deleteById(id);
    }

    @Override
    public void save(PostDto p) {
        postRepo.save(modelMapper.map(p, Post.class));
    }

    @Override
    public void update(long postId, PostDto p) {
        p.setId(postId);
        postRepo.save(modelMapper.map(p, Post.class));
    }

    @Override
    public List<PostDto> findByAuthor(String author) {
        return postRepo.findByAuthor(author).stream().map( e-> modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findByUserId(long userId) {
        return postRepo.findByUserId(userId).stream().map( e-> modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto findByUserIdAndId(long userId, long postId) {
        var post = postRepo.findByUserIdAndId(userId, postId);
        return post == null ? null :  modelMapper.map(post, PostDto.class);
    }

    @Override
    public void addComment(long userId, long postId, CommentDto commentDto) {
        var post = postRepo.findByUserIdAndId(userId, postId);

        if( post != null )
        {
            Comment c = modelMapper.map(commentDto, Comment.class);
            c.setPost(post);
            commentRepo.save(c);
        }
    }

    @Override
    public List<PostDto> findByAuthorAndTitle(String author, String title) {
        if(author != null && title != null)
            return postRepo.findByAuthorAndTitle(author, title).stream().map(e -> modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
        else if(author != null)
            return findByAuthor(author);
        else
            return findByTitle(title);
    }
}
