
public class GameManager
{
   
    private double average;

  
    public GameManager()
    {
        int score=0;
        int games=0;
        while(games<500)
        {Game g= new Game(2,"");
            score=score+g.getturns();
            games++;
         
            
            
        }
        
        average=score/games;
    }

  
    
}
