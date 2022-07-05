package src.main.models;

import java.util.HashMap;


public class Game {

    private HashMap<Team, Integer> scoreboard;
    private static int gameCount;
    private static final int QUAFFLE_POINTS = 10;
    private static final int SNITCH_POINTS = 150;

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
    public static int getQuafflePoints() {
        return QUAFFLE_POINTS;
    }

    public String getPlaceHolder(String play) {

        return play.substring(play.indexOf("<") + 1, 
        play.indexOf(">"));
    }

    public String replacePlaceHolder(String string, 
    String pHolder, String name) {
        return string.replace("<" + pHolder + ">", name);
    }

    public void quaffleScore(Team team) {
        this.setScore(team, this.getScore(team) + QUAFFLE_POINTS);
    }

    public void catchSnitch(Team team) {
        this.setScore(team, this.getScore(team) + SNITCH_POINTS);
    }

    public String simulate(String play) {

        String pHolder = this.getPlaceHolder(play);

        Team team = getRandomTeam();

        if (pHolder.equals(Team.getPositionChaser())){
            quaffleScore(team);
            String chaser = team.getChasers()[random(team.getChasers().length)];
            return replacePlaceHolder(play, pHolder, chaser);
        }else if (pHolder.equals(Team.getPositionSeeker())) {
            catchSnitch(team);
            return replacePlaceHolder(play, pHolder, team.getSeeker());
        }else if (pHolder.equals(Team.getPositionKeeper())) {
            return replacePlaceHolder(play, pHolder, team.getKeeper());
        }else{
            return "";
        }
    }

    public Team getRandomTeam(){
        Team[] tArr = (Team[]) this.scoreboard.keySet().toArray();
        return tArr[random(tArr.length)];
    }

    public int random(int range){
        return (int)Math.random()*range;
    }

}
