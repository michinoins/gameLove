package com.gameLove.application;

import com.gameLove.domain.exception.NotFoundException;
import com.gameLove.domain.repository.GameRepository;
import com.gameLove.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Validation Class
 */
@Service
public class ValidateService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Autowired
    public ValidateService(UserRepository userRepository,
                       GameRepository gameRepository){
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    /**
     * check if ids exist
     * @param userId userId
     */
    public void checkIdsExist(String userId,String gameId) {
        checkIfUserIdExists(userId);
        checkIfGameIdExists(gameId);
    }

    /**
     * check if userId exists
     * @param userId userId
     */
    public void checkIfUserIdExists(String userId) throws RuntimeException {
        if(!userRepository.isUserIdExist(userId)){
            throw new NotFoundException("user id not found");
        }
    }

    /**
     * check if gameId exists
     * @param gameId userId
     */
    public void checkIfGameIdExists(String gameId) throws RuntimeException {
        if(!gameRepository.isGameIdExist(gameId)){
            throw new NotFoundException("game id not found");
        }
    }
}
