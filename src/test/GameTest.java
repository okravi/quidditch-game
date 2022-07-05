package src.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.main.models.Game;
import src.main.models.Team;


public class GameTest {

    Game testGame;

    @Before

    public void setup() {
        
        testGame = new Game(new Team("GRYFFINDOR", "Oliver", "Harry", 
        new String[] {"Angelina", "Ginny", "Katie"}),
        new Team("SLYTHERIN", "Vincent",  "Draco", 
        new String[] {"Bridget", "Harper", "Malcolm"}));
    }

    @Test
    public void getPlaceholderTest() {
        
        assertEquals("chaser", 
        testGame.getPlaceHolder("<chaser> gets the next pass"));
    }

    @Test
    public void replacePlaceholderTest() {
        
        assertEquals("Katie gets the next pass", 
        testGame.replacePlaceHolder("<chaser> gets the next pass", "chaser", "Katie"));
    }

    @Test
    public void quaffleScoreTest() {

        Team team = testGame.getTeam("GRYFFINDOR");
        testGame.quaffleScore(team);
        testGame.quaffleScore(team);

        assertEquals(Game.getQuafflePoints()*2, testGame.getScore(team));
    }

    @Test
    public void catchSnitchTest() {
        
        Team team = testGame.getTeam("SLYTHERIN");
        testGame.catchSnitch(team);

        assertEquals(150, testGame.getScore(team));
    }








}
