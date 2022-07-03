package com.gameLove.domain.repository;

import com.gameLove.domain.entity.GameEntity;
import com.gameLove.domain.entity.GameRankEntity;
import com.gameLove.domain.entity.UserEntity;

import java.util.List;

public interface GameRepository {
    List<GameRankEntity> getTopLikedGames(Integer topCount);
    List<GameEntity> getGamesByUserId(String userId);
    boolean isGameIdExist(String gameId);
}
