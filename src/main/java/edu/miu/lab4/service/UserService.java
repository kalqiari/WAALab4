package edu.miu.lab4.service;

import edu.miu.lab4.entity.dto.UserDto;

import java.util.List;


public interface UserService {
    List<UserDto> findAll();

    UserDto findById(long id);

    void deleteById(long id);

    void save(UserDto p);

    void update(long userId, UserDto u);

    List<UserDto> findUsersWithMoreThanOnePost(int n);
    List<UserDto> findUsersWithPostTitle(String title);

    List<UserDto> findUsersByNumberOfPostsAndTitle(Integer numberOfPosts, String postTitle);
}
