import java.io.PrintStream;
import java.util.*;

public class Board
{
    private ArrayList<Ship> shipsIn;
    private ArrayList<Ship> shipsOut;

    private int[][] grid;

    public Board()
    {
        Ship d=new Destroyer();
        Ship b =new BattleShip();
        Ship s=new Submarine();
        Ship a=new AircraftCarrier();
        Ship p=new PatrolBoat();
        grid = new int[10][10];
        shipsIn = new ArrayList<Ship>();
        shipsOut = new ArrayList<Ship>();
        shipsOut.add(d);
        shipsOut.add(b);
        shipsOut.add(s);
        shipsOut.add(a);
        shipsOut.add(p);

    }

    public ArrayList<Ship> getShipsIn()
    {return shipsIn;
    }

    public ArrayList<Ship> getShipsOut()
    {return shipsOut;
    }

    public void takeShot(int x, int y) throws ShotException
    {
        if((x < 0) || (y < 0) || (x >= grid.length) || (y >= grid.length))
        {throw new ShotException();

        }

        else if(grid[x][y]>1)
        {int t=grid[x][y];
            set(x,y,-t);

        }

        else if(grid[x][y]==0)
        {set(x,y,-1);
        }
        else
        {throw new ShotException();
        }

    }

    public int[][] getBoard()
    {return grid;
    }

    public int[][] attackBoard()
    {
        int[][] a  = new int[grid.length][];
        for(int i = 0; i < grid.length; i++)
            a[i] = grid[i].clone();

        for(int ii = 0; ii < grid.length; ii++){
            for(int hh = 0; hh < grid.length; hh++){
                if((grid[ii][hh]>1)||(grid[ii][hh]==0))
                {a[ii][hh]=0;
                }
                else if(grid[ii][hh]<-1)
                {a[ii][hh]=1;

                }
            }

        }

        return a;

    }

    public void placeShip(int x, int y, boolean isVertical,Ship ship) throws PlacementException
    {
        int len=ship.getLength();
        int id=ship.getId();

        if(isVertical){

            if((x+len<=grid.length )&&(x >= 0) && (y >= 0)&&(y < grid.length))
            {
                for (int a=0; a<len; a++){
                    if(shipSpacing(x+a,y,isVertical,ship))
                    {}

                    else{throw new PlacementException();}
                }
                for (int a=0; a<len; a++){

                    grid[x+a][y]=id;

                }
            }
            else{
                throw new PlacementException();

            }
        }

        else {

            if((y+len<=grid.length)&&(x >= 0) && (y >= 0)&& (x < grid.length)){
                for (int a=0; a<len; a++){
                    if(shipSpacing(x,y+a,isVertical,ship))
                    {}
                    else
                    {throw new PlacementException();}
                }
                for(int a=0; a<len; a++)
                {grid[x][y+a]=id;}
            }
            else{
                throw new PlacementException();

            }
        }
        shipsOut.remove(ship);

        shipsIn.add(ship);
    }

    public void sinkShips()

    {
        ArrayList<Ship> list=new ArrayList<Ship>(shipsIn);

        for(Ship ship:list)
            if(offBoard(ship))
            {shipsIn.remove(ship);
              if(!shipsOut.contains(ship))
              {shipsOut.add(ship);}
              

               
              
        }

    }
    public void initShips()
    {ArrayList<Ship> list=new ArrayList<Ship>(shipsOut);
        for(Ship ship:list)
            if(!offBoard(ship))
            {
                if(!shipsIn.contains(ship))
                {shipsIn.add(ship);}

             shipsOut.remove(ship);
        }
    }

    public Boolean offBoard(Ship ship)
    {

        for (int x=0; x<grid.length; x++)
            for (int y=0; y<grid.length; y++)
                if (grid[x][y]==ship.getId())
                    return false;
        return true;

    }

    public int contents(int x,int y)
    {int t;
        t= grid[x][y];
        return t;

    }

    public void set(int x, int y, int set)
    {
        grid[x][y]= set;
    }

    private Boolean adjSpace(int x, int y)
    {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!isClear(x + i, y + j)) {
                    return (false);
                }
            }
        }
        return (true);
    }

    private Boolean shipSpacing(int x, int y, boolean isVertical,Ship ship)
    {
        if (isVertical) {
            for (int i = 0; i < ship.getLength(); i++) {
                if (!adjSpace(x, y + i).booleanValue()) {
                    return (false);
                }
            }
        } 
        else {
            for (int i = 0; i < ship.getLength(); i++) {
                if (!adjSpace(x + i, y).booleanValue()) {
                    return (false);
                }
            }
        }
        return (true);
    }

    private Boolean isClear(int x, int y)
    {
        if ((x < 0) || (y < 0) || (x  >= grid.length) || (y >= grid.length)) {
            return (true);
        }
        if(grid[x][y]==0) {
            return (true);
        }
        return (false);
    }

}
