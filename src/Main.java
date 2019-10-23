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
    }
}
