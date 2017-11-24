
public class TheGame

{
    public GameEngine gamee;
    public int score;
    public int finalScore;
    public int gamesPlayed;
    public int runningScore;
    
    
    

    
   

    public TheGame()
    {
      gamee = new GameEngine();
        
      score=0;
        
      System.out.println("New Game");
        

    }

    public void setNewLimit(int x)
    {
        gamee.setLimit(x);
    }

    public void haveaGuess(int x)
    {

        if

        (x<gamee.getrandomNumber())

        {System.out.println("too low");
            score=score+1;
        }

        else{

            if(x>gamee.getrandomNumber())

            {System.out.println("too high");
                score=score+1;
            }

            else
            {System.out.println("well done");
                finalScore=score+1;
            gamesPlayed=gamesPlayed+1;
            
            
            }

        }
    }


    public int getScore()
    {return score;}

    public int getfinalScore()
    {return finalScore;}
    
    public int getgamesPlayed()
    
    {return gamesPlayed;}
    
    
    
    
    

}
    
    
  
   
    
    
