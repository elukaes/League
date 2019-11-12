import java.util.List;

public class Table {

    public List sortTable(List listOfTeams) {
        int nbChanges = 0;
        Team[] temp = new Team[2];
        do {
            nbChanges = 0;
            for (int i = 0; i < listOfTeams.size()-1; i++) {
                temp[0] = (Team) listOfTeams.get(i);
                temp[1] = (Team) listOfTeams.get(i+1);
                if ((temp[0].getPoints() < temp[1].getPoints()) ||
                        ((temp[0].getPoints() == temp[1].getPoints() && temp[0].differenceOfGoals() < temp[1].differenceOfGoals()) ||
                                (temp[0].differenceOfGoals() == temp[1].differenceOfGoals() && temp[0].getScoredGoals() < temp[1].getScoredGoals()))) {
                    listOfTeams.set(i, temp[1]);
                    listOfTeams.set(i + 1, temp[0]);
                    nbChanges++;
                }
            }
        } while (nbChanges!=0);
        return listOfTeams;
    }

    public void displayTable(List listOfTeams) {
        Team t;
        for (int i=1; i<=listOfTeams.size(); i++) {
            t = (Team) listOfTeams.get(i-1);
            System.out.println(i+ " " + t.getName() + " "+ t.getMatches()+" " + t.getWins() + " " + t.getDraws() + " " +
                    t.getLosses() + " " + t.getScoredGoals() + "-" + t.getLostGoals() + " " + t.getPoints());
        }
    }
}
