create table `user` (
id        VARCHAR ,
name      VARCHAR,
created_at  TIMESTAMP,
updated_at  TIMESTAMP,
primary key (id)
);

create table game (
id        VARCHAR,
name      VARCHAR,
created_at  TIMESTAMP,
updated_at  TIMESTAMP,
primary key (id)

);

create table user_like_game (
id        VARCHAR,
user_id      VARCHAR,
game_id      VARCHAR,
created_at  TIMESTAMP,
updated_at  TIMESTAMP,
primary key (id),
FOREIGN KEY (user_id) REFERENCES `user`(id),
FOREIGN KEY (game_id) REFERENCES game(id)
);