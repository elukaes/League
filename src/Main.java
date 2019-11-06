import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Team> listOfTeams = new ArrayList<>(); // create list of teams
        for (int i=0; i<20; i++) {
            listOfTeams.add(new Team("Team "+(char)(i+65)+(char)(i+65)));
        }
        Schedule schedule = new Schedule(); // rand of schedule(now always is the same)
        schedule.draw(listOfTeams);

        Match match = new Match();
        for(int i = 1; i <= 10; i++)
            match.result(1,i,listOfTeams.size(),listOfTeams); // play match
        Table table = new Table();
        table.sortTable(listOfTeams);
    }
}
