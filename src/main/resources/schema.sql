create table `user` (
id        VARCHAR ,
name      VARCHAR,
created_at  TIMESTAMP,
updated_at  TIMESTAMP
);

create table game (
id        VARCHAR,
name      VARCHAR,
created_at  TIMESTAMP,
updated_at  TIMESTAMP
);

create table user_like_game (
id        VARCHAR,
user_id      VARCHAR,
game_id      VARCHAR,
created_at  TIMESTAMP,
updated_at  TIMESTAMP
);