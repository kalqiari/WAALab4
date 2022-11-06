--- users

INSERT INTO users (id, name)
VALUES (1,  'user1');

INSERT INTO users (id, name)
VALUES (2,  'user2');

INSERT INTO users (id, name)
VALUES (3,  'user3');

-- posts

INSERT INTO posts (id, title, content, author, user_id)
VALUES (1, 'title1', 'content1', 'author1', 1);

INSERT INTO posts (id, title, content, author, user_id)
VALUES (4, 'title1', 'content4', 'author4', 1);

INSERT INTO posts (id, title, content, author, user_id)
VALUES (2, 'title2', 'content2', 'author2', 1);

INSERT INTO posts (id, title, content, author, user_id)
VALUES (3, 'title3', 'content3', 'author3', 2);

INSERT INTO comments (id, name, post_id)
VALUES (1,  'comment1', 1);

INSERT INTO comments (id, name, post_id)
VALUES (2,  'comment2', 1);

INSERT INTO comments (id, name, post_id)
VALUES (3,  'comment3', 2);

