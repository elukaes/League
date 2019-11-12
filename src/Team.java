public class Team {
    private String name;
    private int points, scoredGoals, lostGoals;
    private byte matches, wins, draws, losses;
    //2 constructors
    public Team () {name = "Team"; matches=0; points=0; wins=0; draws=0; losses=0; scoredGoals=0; lostGoals=0;};

    public Team (String name) {this.name = name; matches=0; points=0; wins=0; draws=0; losses=0; scoredGoals=0; lostGoals=0;}
//sG = scoredGoals, lG = lostGoals
    public void addWin(int sG, int lG) { matches++; wins++; points+=3; addGoals(sG, lG); }

    public void addDraw(int sG, int lG) { matches++; draws++; points++; addGoals(sG, lG); }

    public void addLoss(int sG, int lG) { matches++; losses++;  addGoals(sG, lG); }

    public void addGoals(int scoredGoals, int lostGoals) { this.scoredGoals=scoredGoals; this.lostGoals=lostGoals; }

    public int differenceOfGoals() { return scoredGoals - lostGoals; }

    public byte getMatches() { return matches; }

    public int getPoints() { return points; }

    public byte getWins() { return wins; }

    public byte getDraws() { return draws; }

    public byte getLosses() { return losses; }

    public int getScoredGoals() { return scoredGoals; }

    public int getLostGoals() { return lostGoals; }

    public String getName() { return name; }

    @Override
    public String toString() {
        return name;
    }
}