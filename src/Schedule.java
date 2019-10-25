import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Schedule {
    private File file = new File("schedule.txt"); //file to write schedule and results
    private FileWriter writeSchedule = new FileWriter("schedule.txt");
    //constructor
    public Schedule() throws IOException {
        file.createNewFile();
    }
    // read teams which will play match
    public Team[] readMatch(int nbFixture, int nbMatch, int nbTeams, ArrayList list) throws IOException {
        // read from file pointing match
        String text = Files.lines(Paths.get("schedule.txt")).skip((nbFixture - 1) * (nbTeams / 2 + 3) + nbMatch + 1).findFirst().get();
        int dashAtPlace = 0;
        do { // divide line of text on 2 areas(first for home team, second for away team)
            if (text.charAt(dashAtPlace) == '-')
                break;
            else dashAtPlace++;
        } while (true);
        Team teamHome = new Team(text.substring(0, dashAtPlace - 1));
        Team teamAway = new Team(text.substring(dashAtPlace + 2));
        // search for team ID and return them
        Team []team = new Team[2];
        for (int i=0; i<list.size();i++) {
            if (list.get(i).toString().equals(teamHome.getName()))
                team[0] = (Team)list.get(i);
            if (list.get(i).toString().equals(teamAway.getName()))
                team[1] = (Team)list.get(i);
        }
        return team;
    }

    //0 - home, 1 -away
    public void result(Team []team) {
        Random randResult = new Random();
        int goalsOfHome, goalsOfAway;
        // rand of result
        goalsOfHome = randResult.nextInt(8)+1;
        goalsOfAway = randResult.nextInt(8)+1;
        // write result to file(to make)

        System.out.println(goalsOfHome+" : "+goalsOfAway);
        // check who win
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
    // draw the schedule; now always the same
    public void draw(ArrayList listOfTeams) throws IOException {
        short number = (short)listOfTeams.size();
        short[][][] pairs = new short[number-1][number/2][2];
        short w;
        for (short i=1;i<number;i++)
        {
            if(i<=number/2)
            {
                pairs[2*i-2][0][0] = i;
                pairs[2*i-2][0][1] = number;
                w = (short)(2*i-2);
            }
            else
            {
                pairs[2*i-1-number][0][1] = i;
                pairs[2*i-1-number][0][0] = number;
                w = (short)(2*i-1-number);
            }
            short j = (short)(i+1);
            for (int k=1;k<=number-2;k++)
            {
                if(j >= number)
                    j = 1;
                if(k <= (number-2)/2)
                    pairs[w][k][0] = j;
                else
                    pairs[w][number-1-k][1] = j;
                j++;
            }
        }
        try { //write to file; 1st and 2nd legs
            for (short i = 1; i < number; i++) {
                writeSchedule.write((i) + " fixture\n\n");
                for (short j = 0; j < number / 2; j++) {
                    writeSchedule.write(listOfTeams.get(pairs[i-1][j][0]-1).toString() + " - " + listOfTeams.get(pairs[i-1][j][1]-1).toString() + "\n");
                }
                writeSchedule.write("\n");
            }
            for (short i = 1; i < number; i++) {
                writeSchedule.write((i+number-1) + " fixture\n\n");
                for (short j = 0; j < number / 2; j++) {
                    writeSchedule.write(listOfTeams.get(pairs[i-1][j][1]-1).toString() + " - " + listOfTeams.get(pairs[i-1][j][0]-1).toString() + "\n");
                }
                writeSchedule.write("\n");
            }
        }
        finally {
            if (writeSchedule != null)
                writeSchedule.close();
        }
    }


}
