package com.gameLove.domain.repository;

import com.gameLove.domain.entity.GameEntity;
import com.gameLove.domain.entity.GameRankEntity;

import java.util.List;

public interface GameRepository {
    List<GameRankEntity> getTopLikedGames(Integer topCount);
    List<GameEntity> getGamesByUserId(String userId);
    List<GameEntity> getAllGames();
    boolean isGameIdExist(String gameId);
}
