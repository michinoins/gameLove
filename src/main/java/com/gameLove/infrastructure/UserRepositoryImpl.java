package com.gameLove.infrastructure;

import com.gameLove.domain.entity.GameEntity;
import com.gameLove.domain.entity.UserEntity;
import com.gameLove.domain.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public class UserRepositoryImpl implements UserRepository {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String URL = "jdbc:h2:mem:testdb";
    static final String USER = "sa";
    static final String PASS = "";

    /**
     * get all users
     */
    public  List<UserEntity> getUser(){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from `user`");
            List<UserEntity> userList = new ArrayList<>() ;
            while(rs.next()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(rs.getString("id"));
                userEntity.setName(rs.getString("name"));
                userEntity.setCreatedAt(rs.getObject("created_at",LocalDateTime.class));
                userEntity.setUpdatedAt(rs.getObject("updated_at",LocalDateTime.class));
                userList.add(userEntity);
            }
            return userList;
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
     * like game
     */
    public void likeGame(String userId,String gameId){
        Connection conn = null;
        Statement stmt = null;
        UUID uuid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            boolean rs = stmt.execute(
                    "INSERT INTO  user_like_game ( id, user_id, game_id,created_at,updated_at)" +
                            " VALUES ("+
                            "'"+uuid+"',"+
                            "'"+userId+"',"+
                            "'"+gameId+"',"+
                            "'"+now+"',"+
                            "'"+now+"')"
            );

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
    }



    /**
     * unlike game
     */
    public void unlikeGame(String userId,String gameId){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            boolean rs = stmt.execute(
                    "delete from user_like_game where user_id ='"+ userId+"'and game_id = '"+gameId+"';"
            );
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
    }

    /**
     * check if userId Exist
     */
    public boolean isUserIdExist(String userId){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select exists ( select * from `user` where id = '"+userId+"') as user_exists" ;
            ResultSet res = stmt.executeQuery(sql);

            res.next();
            return res.getBoolean("user_exists");
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
