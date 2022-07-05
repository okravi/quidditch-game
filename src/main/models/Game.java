package src.main.models;

import java.util.HashMap;

public class Game {

    private HashMap<Team, Integer> scoreboard;
    private static int gameCount;

    public Game(Team home, Team away){
        gameCount++;
        this.scoreboard = new HashMap<Team, Integer>();
        scoreboard.put(new Team(home), 0);
        scoreboard.put(new Team (away), 0);
    }

    public Integer getScore(Team team) {
        return this.scoreboard.get(team);
    }

    public void setScore(Team team, Integer score){
        scoreboard.put(team, score);
    }

    public Team getTeam(String teamName) {
        
        return this.scoreboard.keySet().stream()
        .filter((key) -> key.getHouse().equals(teamName))
        .findFirst()
        .orElse(null);
    }

    public static int getGameCount() {
        return gameCount;
    }

}
