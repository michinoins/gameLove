package com.gameLove.infrastructure;

import com.gameLove.domain.entity.UserEntity;
import com.gameLove.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * get all users
     */
    public List<UserEntity> getAllUsers() {
        String query = "SELECT * from `user` order by created_at";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(UserEntity.class));
    }


    /**
     * like game
     */
    public void likeGame(String userId, String gameId) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        String query =
                "INSERT INTO  user_like_game ( id, user_id, game_id,created_at,updated_at)" +
                        " VALUES (" +
                        "'" + uuid + "'," +
                        "'" + userId + "'," +
                        "'" + gameId + "'," +
                        "'" + now + "'," +
                        "'" + now + "')";
        jdbcTemplate.execute(query);
    }


    /**
     * unlike game
     */
    public void unlikeGame(String userId, String gameId) {
        String query = "delete from user_like_game where user_id ='" + userId + "'and game_id = '" + gameId + "';";
        jdbcTemplate.execute(query);
    }

    /**
     * check if userId Exist
     */
    public boolean isUserIdExist(String userId) {
        String query = "select exists ( select * from `user` where id = '" + userId + "') as user_exists";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(query, boolean.class));
    }
}
