public class Team {
    private String name;
    private short points;
    private byte wins, draws, losses;

    public Team () {name = "Team"; points = 0; wins = 0; draws = 0; losses = 0;};

    public Team (String name) {this.name = name; points = 0; wins = 0; draws = 0; losses = 0;}

    public byte getWins() { return wins; }

    public void addWin() { wins++; points+=3; }

    public byte getDraws() { return draws; }

    public void addDraw() { draws++; points++; }

    public byte getLosses() { return losses; }

    public void addLoss() { losses++; }

    public short getPoints() { return points; }

    public String getName() { return name; }

    @Override
    public String toString() {
        return name;
    }
}