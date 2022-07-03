package com.gameLove.application;

import com.gameLove.domain.entity.GameEntity;
import com.gameLove.domain.entity.UserEntity;
import com.gameLove.domain.repository.GameRepository;
import com.gameLove.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final ValidateService validateService;

    @Autowired
    public UserService(UserRepository userRepository,
                       GameRepository gameRepository,
                       ValidateService validateService) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.validateService = validateService;
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.getAllUsers();
    }

    /**
     * like Game
     * @param userId userId
     * @param gameId gameId
     */
    public  void likeGame(String userId,String gameId){
        validateService.checkIdsExist(userId,gameId);
        userRepository.likeGame(userId,gameId);
    }

    /**
     * unlike Game
     * @param userId userId
     * @param gameId gameId
     */
    public  void unlikeGame(String userId,String gameId) {
        validateService.checkIdsExist(userId,gameId);
        userRepository.unlikeGame(userId,gameId);
    }

    /**
     * get All the games that a user likes
     * @param userId userId
     */
    public  List<GameEntity> getUserLikeGamesAll(String userId){
        validateService.checkIfUserIdExists(userId);
        return gameRepository.getGamesByUserId(userId);
    }
}
