import java.util.Random;

public class Match {

    //0 - home, 1 -away
    public void result(Team []team) {
        Random randResult = new Random();
        int goalsOfHome, goalsOfAway;
        goalsOfHome = randResult.nextInt(8)+1;
        goalsOfAway = randResult.nextInt(8)+1;
        System.out.println(goalsOfHome+" : "+goalsOfAway);
        if (goalsOfHome > goalsOfAway){
            team[0].addWin();
            team[1].addLoss();
        }
        else if (goalsOfHome == goalsOfAway) {
            team[0].addDraw();
            team[1].addDraw();
        }
        else {
            team[0].addLoss();
            team[1].addWin();
        }
    }
}
