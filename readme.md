## Game Love Service

## Overview
This is Game Love Service. You can get user,game information and user's favorite games.

[https://raw.github.com/wiki/michinoins/gameLove/images/Untitled.gif]
## Features
Api Path and Description

 | Method | Path                                 | Description                         |  Parameter | 
 |--------------------------------------|-------------------------------------|-------------|------------|
 | GET| /users                               | get all users                       | - |
 | GET| /users/{userId}/like/game            | fetch all games a player have loved | - |
 | POST| /users/{userId}/like/game/{gameId}   | like a game                         | userId: a user who like a game / gameId: a game that is liked |
 | DELETE| /users/{userId}/unlike/game/{gameId} | unlike a game                       | userId: a user who like a game / gameId: a game that is liked |
 | GET| /games                               | get all games                       | - |
 | GET| /games/ranking/{topCount} | fetch the most loved games    | topCount:  count from the top |

# Usage

```bash
git clone https://github.com/michinoins/gameLove.git
cd gameLove

## start application
mvn spring-boot:run
```

# Environment
- Java11
- mvn3.8.6
- spring-framework 2.5.4
 