package ai;

import java.util.ArrayList;
import java.util.Random;

import model.*;

class DraftAI {
    private Random random=new Random();
    private double needWeight;
    private double scarcityWeight;
    private double noiseWeight;

    public DraftAI(){
        needWeight=3;
        scarcityWeight=0.4;
        noiseWeight=1;
    }
    
    //Calculate Next Best Superstar's Rating based on the current condition.
    private int nextBestSuperstar(Superstar s, ArrayList<Superstar> characterPool){
        int best=-1;

        for(Superstar other : characterPool){
            if(other!=s && other.getDivision()==s.getDivision() && other.getRating()<=s.getRating()){
                best=Math.max(best, other.getRating());
            }
        }

        return best;
    }

    //Calculate Character score based on the current condition and GM personality.
    private double calculateScore(Superstar s, ArrayList<Superstar> characterPool, Roster roster){
        double need=(double)roster.spotsLeft(s.getDivision())/s.getDivision().getQuota();
        
        int nextBest=nextBestSuperstar(s, pool);

        double scarcity=(nextBest<0) ? 0 : s.getRating()-nextBest();

        return s.getRating() + (need*needWeight) + (scarcity*scarcityWeight) + (random.nextDouble()*noiseWeight);
    }

    //Computer Character picking logic
    public Superstar pickSuperstar(ArrayList<Superstar> characterPool, Roster roster){
        Superstar best=null;
        double bestScore=-1;
        
        for(Superstar s : characterPool){
            if(!roster.hasRemainingSpots(s.getDivision())) continue;

            double score=calculateScore(s, characterPool, roster);
            if(score>bestScore){
                bestScore=score;
                best=s;
            }
        }

        return best;
    }
}
