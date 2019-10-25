import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList <Team> listOfTeams = new ArrayList<>(); // create list of teams
        for (int i=0; i<20; i++) {
            listOfTeams.add(new Team("Team "+(char)(i+65)+(char)(i+65)));
        }
        Schedule schedule = new Schedule(); // rand of schedule(now always is the same)
        schedule.draw(listOfTeams);

        schedule.result(schedule.readMatch(1,1,listOfTeams.size(),listOfTeams)); // play match
        System.out.println(listOfTeams.get(0).getPoints());
        System.out.println(listOfTeams.get(19).getPoints());
    }
}
