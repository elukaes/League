import java.util.List;

public class Table {

    public void sortTable(List listOfTeams) {
        int nbChanges = 0;
        Team[] temp = new Team[2];
        do {
            nbChanges = 0;
            for (int i = 0; i < listOfTeams.size()-1; i++) {
                temp[0] = (Team) listOfTeams.get(i);
                temp[1] = (Team) listOfTeams.get(i+1);
                if (temp[0].getPoints() < temp[1].getPoints()) {
                    listOfTeams.set(i, temp[1]);
                    listOfTeams.set(i + 1, temp[0]);
                    nbChanges++;
                }
            }
        } while (nbChanges!=0);
    }
}
