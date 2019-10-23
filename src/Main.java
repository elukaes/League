import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList <Team> listOfTeams = new ArrayList<>();
        for (int i=0; i<20; i++) {
            listOfTeams.add(new Team("Team "+(char)(i+65)+(char)(i+65)));
        }
        Schedule schedule = new Schedule();
        schedule.draw(listOfTeams);
        schedule.readMatch(1,1, listOfTeams.size(), listOfTeams);

        Match match = new Match();
        match.result(schedule.readMatch(1,1,listOfTeams.size(),listOfTeams));
        System.out.println(listOfTeams.get(0).getPoints());
        System.out.println(listOfTeams.get(19).getPoints());
    }


}
