
public class GameEngine
{

    public int randomNumber;
    public int upperLimit;
    public int turns;

//    public GameEngine newGame;
    public GameEngine()
    {
        
        upperLimit = 10; 

        randomNumber = (int)((Math.random() * upperLimit)+1);
    }

    public void setLimit(int y)
    {
        
        upperLimit = y;
        System.out.println("System intialized" );
        randomNumber = (int)((Math.random() * upperLimit)+1);

    }

    public int getrandomNumber()

    {return randomNumber;}
}
