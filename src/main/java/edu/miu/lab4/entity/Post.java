package edu.miu.lab4.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     long id;
     String title;
     String content;
     String author;
     @ManyToOne
     User user;

     @OneToMany(cascade = CascadeType.ALL)
     @JoinColumn(name = "post_id")
     List<Comment> comments;
}

