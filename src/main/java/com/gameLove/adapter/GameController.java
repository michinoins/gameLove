package com.gameLove.adapter;

import com.gameLove.application.GameService;
import com.gameLove.domain.entity.GameEntity;
import com.gameLove.domain.entity.GameRankEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * get all games
     */
    @GetMapping("")
    public List<GameEntity> getAllGames(){
        return gameService.getAllGames();
    }

    /**
     * get top n loved games
     */
    @GetMapping("ranking/{topCount}")
    public List<GameRankEntity> getTopLikedGames(
            @PathVariable Integer topCount
    ) {
        return gameService.getTopLikedGames(topCount);
    }
}

