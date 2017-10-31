import java.util.*;
public class ComputerEasy implements Player
{
    public ComputerEasy()
    {

        
    }

    public void isHit(boolean b)
    {}

    
    private int rndm()
    { Random r = new Random();
        return r.nextInt(10);

    }
    public ShipPlacer place() 
    {
        int x = rndm();
        int y = rndm();
        boolean isVertical= true;
        double s = 2*Math.random();

        if(s<1)
        {isVertical=true;}
        else if(s>=1)
        {isVertical=false; }

        ShipPlacer p= new ShipPlacer(x,y,isVertical);

        return p;

    }
    public int[] shoot()
    {
        int x = rndm();
        int y = rndm();
        int[] p={x,y};

       
      
        return p;
    }

}