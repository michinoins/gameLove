package com.gameLove.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class GameRankEntity {
    @Id
    private String id;
    private String name;
    private Integer favoriteCount;
}