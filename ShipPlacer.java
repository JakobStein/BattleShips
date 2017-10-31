import java.awt.Point;

public class ShipPlacer
{
    private int x;
    private int y;
    private boolean isVertical;
    
    public ShipPlacer(int x,int y,boolean isVertical)
    {this.x=x;
     this.y=y;
     this.isVertical=isVertical;
        
    }
    
    public int getx()
    {return x;
    }
    
    public int gety()
    {return y;
    }
    
    public boolean getVertical()
    
    {return isVertical;
    }
    
  
}
