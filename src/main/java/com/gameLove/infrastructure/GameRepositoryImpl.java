package com.gameLove.infrastructure;

import com.gameLove.domain.entity.GameEntity;
import com.gameLove.domain.entity.GameRankEntity;
import com.gameLove.domain.entity.UserEntity;
import com.gameLove.domain.repository.GameRepository;
import com.gameLove.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class GameRepositoryImpl implements GameRepository {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String URL = "jdbc:h2:mem:testdb";
    static final String USER = "sa";
    static final String PASS = "";

    /**
     * get games by userId
     */
    public List<GameEntity> getGamesByUserId(String userId){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select * from user_like_game where user_id ='"+userId+"'"+"";
            ResultSet rs = stmt.executeQuery(sql);
            List<GameEntity> gameList = new ArrayList<>() ;
            while(rs.next()) {
                GameEntity gameEntity = new GameEntity();
                gameEntity.setId(rs.getString("id"));
                gameEntity.setName(rs.getString("user_id"));
                gameEntity.setName(rs.getString("game_id"));
                gameEntity.setCreatedAt(rs.getObject("created_at",LocalDateTime.class));
                gameEntity.setUpdatedAt(rs.getObject("updated_at",LocalDateTime.class));
                gameList.add(gameEntity);
            }
            return gameList;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * get top liked games
     * @param topCount count from the top
     */
    public List<GameRankEntity> getTopLikedGames(Integer topCount){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select game.id, game.name, count(user_like_game.game_id) as favorite_count " +
                    "from user_like_game inner join game on game.id = user_like_game.game_id  " +
                    " group by user_like_game.game_id limit "+topCount;

            ResultSet rs = stmt.executeQuery(sql);
            List<GameRankEntity> gameRankEntityList = new ArrayList<>() ;
            while(rs.next()) {
                GameRankEntity gameRankEntity = new GameRankEntity();
                gameRankEntity.setId(rs.getString("id"));
                gameRankEntity.setName(rs.getString("name"));
                gameRankEntity.setFavoriteCount(rs.getInt("favorite_count"));
                gameRankEntityList.add(gameRankEntity);
            }
            return gameRankEntityList;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * check if userId Exist
     */
    public boolean isGameIdExist(String gameId){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select exists ( select * from game where id = '"+gameId+"') as game_exist" ;
            ResultSet res = stmt.executeQuery(sql);

            res.next();
            return res.getBoolean("game_exists");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
