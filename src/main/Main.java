package src.main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import src.main.models.Game;
import src.main.models.Team;

public class Main {

    public static Game game;
    static final String TEAMS_FILE = "src/main/teams.txt";
    static final String PLAYS_FILE = "src/main/plays.txt";

    public static void main(String[] args) {
     
        try {
            String[][] data = getData();

            game = new Game(
            new Team(data[0][0], data[0][1], data[0][2], 
            new String[] {data[0][3], data[0][4], data[0][5]}),
            new Team(data[1][0], data[1][1], data[1][2], 
            new String[] {data[1][3], data[1][4], data[1][5]})
            );

            startGame();
            printResult();

        } catch (IOException e) {
            System.out.println("Files can not be read");
        }
  
    }
    
    public static String[][] getData() throws IOException {

        String fileString = Files.readString((Paths.get(TEAMS_FILE)));
        String[] lines = fileString.split("\n");
        return new String[][]{
            lines[0].split(","), lines[1].split(",")
        };
    }
    
    public static void startGame() throws IOException {

        String fileString = Files.readString((Paths.get(PLAYS_FILE)));
        String[] lines = fileString.split("\n");

        for (String play : lines) {
            System.out.println("\n" + game.simulate(play) + "\n");
            wait(3);
        }
    }
    
    public static void printResult() {
        int scoreSly = game.getScore(game.getTeam("SLYTHERIN"));
        int scoreHry = game.getScore(game.getTeam("GRYFFINDOR"));

        System.out.println("\nGRYFFINDOR: " + scoreHry 
        + " SLYTHERIN: " + scoreSly);

        System.out.println("The winner is: " + (scoreSly>scoreHry ? "SLYTHERIN" : "GRYFFINDOR"));
    }

    public static void wait(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  }
