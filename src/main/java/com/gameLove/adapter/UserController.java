package com.gameLove.adapter;

import com.gameLove.application.UserService;
import com.gameLove.domain.entity.GameEntity;
import com.gameLove.domain.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * get users
     */
    @GetMapping("")
    public List<UserEntity> getUser(){
        return userService.getUser();
    }


    /**
     * fetch all games a player have loved.
     */
    @GetMapping("/{userId}/like/game")
    public List<GameEntity> getUserLikeGamesAll(
            @PathVariable String userId
    )
    {
        return userService.getUserLikeGamesAll(userId);
    }

    /**
     * add game that a user like
     */
    @PostMapping("/{userId}/like/game/{gameId}")
    public HttpStatus addUserLikeGame(
            @PathVariable String userId,
            @PathVariable String gameId)
    {
        userService.likeGame(userId,gameId);
        return HttpStatus.OK;
    }

    /**
     * delete user's game favorite
     */
    @DeleteMapping("/{userId}/unlike/game/{gameId}")
    public HttpStatus deleteUserLikeGame(
            @PathVariable String userId,
            @PathVariable String gameId) {
        userService.unlikeGame(userId,gameId);
        return HttpStatus.OK;
    }
}
