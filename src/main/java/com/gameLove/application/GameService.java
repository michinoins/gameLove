package com.gameLove.application;

import com.gameLove.domain.entity.GameEntity;
import com.gameLove.domain.entity.GameRankEntity;
import com.gameLove.domain.entity.UserEntity;
import com.gameLove.domain.repository.GameRepository;
import com.gameLove.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameRankEntity> getTopLikedGames(Integer topCount) {
        return gameRepository.getTopLikedGames(topCount);
    }
}