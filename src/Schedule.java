import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private File file = new File("schedule.txt"); //file to write schedule
    private FileWriter writeSchedule = new FileWriter("schedule.txt");
    //constructor
    public Schedule() throws IOException {
        file.createNewFile();
    }

    // draw the schedule; now always the same
    public void draw(List listOfTeams) throws IOException {
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
