import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {
    private File file = new File("results.txt"); //file to write results
    private BufferedWriter writeResult = new BufferedWriter(new FileWriter("results.txt"));

    //constructor
    public Match() throws IOException {
        try {
            FileChannel readFile = new FileInputStream("schedule.txt").getChannel();
            FileChannel writeFile = new FileOutputStream("results.txt").getChannel();
            file.createNewFile();
            try {
                writeFile.transferFrom(readFile, 0, readFile.size());
            }
            finally {
                if (readFile != null && writeFile != null) {
                    readFile.close();
                    writeFile.close();
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    // read teams which will play match
    private Team[] readTeams(int nbFixture, int nbMatch, int nbTeams, List list) throws IOException {
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
        Team []teams = new Team[2];
        for (int i=0; i<list.size();i++) {
            if (list.get(i).toString().equals(teamHome.getName()))
                teams[0] = (Team)list.get(i);
            if (list.get(i).toString().equals(teamAway.getName()))
                teams[1] = (Team)list.get(i);
        }
        return teams;
    }

    //0 - home, 1 -away
    public void result(int nbFixture, int nbMatch, int nbTeams, List list) throws IOException {
        Team []matchTeams = new Team[2];
        matchTeams = readTeams(nbFixture, nbMatch, nbTeams, list);
        Random randResult = new Random();
        int goalsOfHome, goalsOfAway;
        // rand of result
        goalsOfHome = randResult.nextInt(8)+1;
        goalsOfAway = randResult.nextInt(8)+1;
        // write result to file
        String text = Files.lines(Paths.get("results.txt")).skip((nbFixture - 1) * (nbTeams / 2 + 3) + nbMatch + 1).findFirst().get();
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("results.txt")));
        for (int i=0; i<fileContent.size(); i++) {
            if (fileContent.get(i).equals(text)) {
                fileContent.set(i, text+" "+goalsOfHome+" - "+goalsOfAway);
                break;
            }
        }
        Files.write(Paths.get("results.txt"), fileContent);

        // check who win
        if (goalsOfHome > goalsOfAway){
            matchTeams[0].addWin(goalsOfHome, goalsOfAway);
            matchTeams[1].addLoss(goalsOfAway, goalsOfHome);
        }
        else if (goalsOfHome == goalsOfAway) {
            matchTeams[0].addDraw(goalsOfHome, goalsOfAway);
            matchTeams[1].addDraw(goalsOfAway, goalsOfHome);
        }
        else {
            matchTeams[0].addLoss(goalsOfHome, goalsOfAway);
            matchTeams[1].addWin(goalsOfAway, goalsOfHome);
        }
    }
}