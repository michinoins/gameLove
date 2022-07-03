package com.gameLove.infrastructure;

import com.gameLove.domain.entity.GameEntity;
import com.gameLove.domain.entity.GameRankEntity;
import com.gameLove.domain.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepositoryImpl implements GameRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * get all games
     */
    public List<GameEntity> getAllGames() {
        String query = "SELECT * from game order by id";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(GameEntity.class));
    }

    /**
     * get games by userId
     */
    public List<GameEntity> getGamesByUserId(String userId) {
        String query = "select game.id,game.name,game.created_at,game.updated_at"+
        " from game inner join user_like_game on game.id = user_like_game.game_id where user_like_game.user_id = '"+userId+"' order by game.id";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(GameEntity.class));
    }

    /**
     * get top liked games
     *
     * @param topCount count from the top
     */
    public List<GameRankEntity> getTopLikedGames(Integer topCount) {
        String query = "select game.id, game.name, count(user_like_game.game_id) as favorite_count " +
                "from user_like_game inner join game on game.id = user_like_game.game_id  " +
                " group by user_like_game.game_id  order by favorite_count desc limit " + topCount;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(GameRankEntity.class));
    }

    /**
     * check if userId Exist
     */
    public boolean isGameIdExist(String gameId) {
        String query = "select exists ( select * from game where id = '" + gameId + "') as game_exists";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(query, boolean.class));
    }
}
