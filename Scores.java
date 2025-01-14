import java.util.HashMap;

public class Scores  
{
    //static map to track multiple attempts and their scores
    public static HashMap<Integer, Integer> scores = new HashMap<>();
    public static HashMap<Integer, Integer> bonusFish = new HashMap<>();
    
    public static void addScore(int score) {
        //Move exisiting scores down by one rank for each new score added
        for(int i = 6; i > 1; i--) {
            scores.put(i, scores.get(i - 1));
        }
        
        //Add new score at the top (rank 1)
        scores.put(1, score);
    }
    
    public static void addFish(int fish) {
        //Move existing fish score down one rank with scores
        for(int i = 6; i > 1; i--) {
            bonusFish.put(i, bonusFish.get(i - 1));
        }
        
        //Add new fish count with score
        bonusFish.put(1, fish);
    }
    
    public static Integer returnScoreValue(int attempt) {
        return scores.get(attempt);
    }
    
    public static Integer returnFishValue(int attempt) {
        return bonusFish.get(attempt);
    }
}
