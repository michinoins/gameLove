package com.gameLove.domain.repository;

import com.gameLove.domain.entity.GameEntity;
import com.gameLove.domain.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    List<UserEntity> getUser();
    void likeGame(String userId,String gameId);
    void unlikeGame(String userId,String gameId);
    boolean isUserIdExist(String userId);
}