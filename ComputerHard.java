import java.util.*;

public class ComputerHard implements Player
{
    
    private int[][] grid;
    private boolean destroy;
    private int[] lastshot;
    private int[] lasthit;
    private HashSet<int[]> pot;
    
    
    
    
    
    public ComputerHard()
    { 
        lasthit=new int[2];
        lastshot=new int[2];
        destroy=false;
        
        grid=new int[10][10];
    
   
        
    }
    
  
    
    public void isHit(boolean b)
    {   
        destroy=b;
        if(b)
        {grid[lastshot[0]][lastshot[1]]=1;}
        else
        {grid[lastshot[0]][lastshot[1]]=-1;}
        
        
       
    }
    
    
    
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
    
    
    private int orient(int x, int y)
    {
        
         
        if(cell(x-1, y)==1||cell(x+1,y)==1)
        {return 1;}
        else if(cell(x, y-1)==1||cell(x,y+1)==1)
        {return 2;}
        else
        {return 0;}
       
            
        
        
        
        
        
        }
        
    
    
    
   
    
    private int cell(int x, int y)
    {
        if ((x < 0) || (y < 0) || (x  >= grid.length) || (y >= grid.length)) {
            return (-1);
        }
        else {
            return (grid[x][y]);
        }
    }
    
    public int[] shoot()
    {int[] p= new int[2];
       int x=rndm();
       int y=rndm();
       int z=x+y;
       
     
       
       if(destroy)
        
       {Random r=new Random();
        if(orient(lasthit[0],lasthit[1])==0)
        {
         p[0]=lasthit[0]+1-2*r.nextInt(1);
         p[1]=lasthit[1]+1-2*r.nextInt(1);}
        else if(orient(lasthit[0],lasthit[1])==1)
        {p[0]=lasthit[0];
         p[1]=lasthit[1]+1-2*r.nextInt(1);}
        else if(orient(lasthit[0],lasthit[1])==2)
        {p[0]=lasthit[0]+1-2*r.nextInt(1);
         p[1]=lasthit[1];}
         
        if(pot.contains(p))
        {destroy=false;}
        lastshot=p.clone();
        
    
        }
       else
       {while(z%2==1)
       {Random r1=new Random();  
        x = r1.nextInt(10);
        
        y = r1.nextInt(10);
    
        
        p[0]=x;p[1]=y;
        lastshot=p.clone();}
    }
        
        
           
       
    
    
       
  
       return p;
    }
    
    
    
    
}
