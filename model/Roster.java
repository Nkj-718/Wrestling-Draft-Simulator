import java.util.ArrayList;

class Roster {
    private ArrayList<Superstar> draftPicks = new ArrayList<>();

    public void addToRoster(Superstar superstar){
        draftPicks.add(superstar);
    }

    public int spotsLeft(Division division){
        int count=0;
        
        for(Superstar s: draftPicks){
            if(s.getDivision()==division){
                count++;
            }
        }

        return division.getQuota()-count;
    }

    private boolean hasRemainingSpots(Division division){
        return spotsLeft(division)>0;
    }

    public boolean isFull(){
        for(Division d : Division.values()){
            if(hasRemainingSpots(d)){
                return false;
            }
        }
        return true;
    }
}
