
import java.util.*;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;

public class Game
{
    // instance variables - replace the example below with your own

    private int turn=0;
    private Player winner;
    private Player one;
    private Player two;
    private Board board1;
    private Board board2;

    public static void main(String[] args)
    {   
        Scanner scanner=new Scanner(System.in);
        System.out.println("Choose Game(Saved/New):");
        String s = scanner.nextLine();
        System.out.println("Choose Game Setting(Human=1/Computer=2/Test=anykey):");

        int i=scanner.nextInt();
        Game g= new Game(i,s);
    }

    public Game(int setting, String s)
    {

        try{
            if(setting==1){
                boolean i=true;
                while(i){
                    Scanner input = new Scanner(System.in);
                    System.out.println("#Human's playing:");
                    int x = input.nextInt();
                    if(x==1)
                    {one=new Human();
                     two=new ComputerEasy();i=false;}

                    else if(x==2)
                    {one= new Human();
                        one=new Human();i=false;}

                    else
                    {System.out.println("Choose valid number of humans for this setting");}

                } 

            }
            else if(setting==2)
            {one=new ComputerHard();
                two=one=new ComputerHard();}

            else
            { one=new ComputerEasy();
                two=new ComputerEasy();}

            board1 = new Board();
            board2 = new Board();
           
            if(s.equals("Saved"))
            {reader();
             board1.sinkShips();board2.sinkShips();board1.initShips();board2.initShips();
            }


          

            placer(one,board1);
            placer(two,board2);
            turn=0;
            System.out.println("Key[unknown:~, hit:0 , miss:X]");

            while((!board1.getShipsIn().isEmpty())||(!board2.getShipsIn().isEmpty()))
            {
                printattack(board2);

                shooter(one,board2);
                System.out.println("Ship sunk:" + sunk(board2));
                board2.sinkShips();

                if(board2.getShipsIn().isEmpty())
                {winner=one;break;}

                printattack(board1);

                shooter(two,board1);
                System.out.println("Ship sunk:" + sunk(board1));
                board1.sinkShips();

                if(board1.getShipsIn().isEmpty())
                {printattack(board1);winner=two;break;}

            }

            int t1=2-turn%2;
            int t2=(turn+(t1%2))/2;
            System.out.println("player " + t1 + " wins in " +t2 + " turns");

        }
        catch(SaveException e)
        {if(e.getSave()==true)
            {saver();} 

            else
            {}

        }
    }

    public int getturns()
    {int t1=2-turn%2;
        int t2=(turn+(t1%2))/2;
        return t2;

    }

    private String sunk(Board b)
    { 

        for(Ship ship: b.getShipsIn())
        {if(b.offBoard(ship))
            {return ship.getName();}

        } 
        return "None";

    } 

    private void placer(Player p, Board b) throws SaveException
    {
        toplay();  

        showBoard(b);

        while(!b.getShipsOut().isEmpty())
        {
            Ship s=b.getShipsOut().get(0);
            System.out.println("Place: " + s.getName());
            System.out.println("Length: " +s.getLength());
            System.out.println("ID: " +s.getId());
            boolean promptUser=true;
            while(promptUser){
                try{ShipPlacer sh=p.place();
                    int x=sh.getx();
                    int y=sh.gety();
                    boolean v=sh.getVertical();

                    b.placeShip(x,y,v,s);
                    showBoard(b);

                    promptUser=false;
                }
                catch(PlacementException e)
                {System.out.println("Invalid placement, try again.");}
            }

        }
        turn++;

    }

    private void shooter(Player p, Board b) throws SaveException
    {
        toplay();

        boolean promptUser = true;

        while(promptUser){
            try{
                int[] t = p.shoot();
                int x=t[0];
                int y=t[1];

                b.takeShot(x,y);
                if(b.contents(x,y)<-1)
                {p.isHit(true);}
                else
                {p.isHit(false);}

                promptUser=false;}
            catch(ShotException e)
            {System.out.println("Invalid shot, try again.");
            }

        }
        turn++;
    }

    private void toplay()
    {
        int t=turn%2 +1;
        System.out.println("player " + t + " to play" );

    }

    private void clearScreen()
    {
        System.out.print('\u000C');
    }

    public void printattack(Board b)

    {

        int [][] a  = b.attackBoard(); 

        System.out.println();

        for(int ii = 0; ii<=a.length;ii++)
        { System.out.print(ii + "   ");

        }
        System.out.println();

        for(int ii = 0; ii < a.length; ii++){
            System.out.print(toLetter(ii+1)+"   ");
            for(int hh = 0; hh < a.length; hh++){
                if(a[ii][hh]==0)
                {System.out.print("~" + "   ");}
                else if(a[ii][hh]==1)
                {System.out.print("0" + "   ");}
                else if(a[ii][hh]==-1)
                {System.out.print("X" + "   ");}

            }
            System.out.println();

        }
    }

    public void showBoard(Board b)
    {
        int[][] a = b.getBoard();

        System.out.println();
        for(int ii = 0; ii<=a.length;ii++)
        { System.out.print(ii + "   ");

        }
        System.out.println();

        for(int ii = 0; ii < a.length; ii++){
            System.out.print(toLetter(ii+1)+"   ");
            for(int hh = 0; hh < a.length; hh++){

                System.out.print(a[ii][hh] + "   ");
            }
            System.out.println();

        }
    }

    private String toLetter(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 'A' - 1)) : null;
    }

    public void reader()
    {
        boolean b=true;
        while(b){
            try{
                Scanner input = new Scanner(System.in);
                System.out.println("Enter filename:");
                String filename=input.nextLine();
                InputStream inputStream = new FileInputStream(filename);
                Reader      reader      = new InputStreamReader(inputStream);
                String t="";

                int data=reader.read();

                while(data != -1){
                    for(int ii = 0; ii < board1.getBoard().length; ii++){
                        for(int hh = 0; hh < board1.getBoard().length; hh++)
                        {board1.set(hh,ii,Character.getNumericValue(reader.read()));data=reader.read();}

                    }
                    for(int ii = 0; ii < board2.getBoard().length; ii++){
                        for(int hh = 0; hh < board2.getBoard().length; hh++)
                        {board2.set(hh,ii,Character.getNumericValue(reader.read()));data=reader.read();}

                    }
                    
                    while(data != -1){

                    t=t + (int)reader.read();
                    data=reader.read();}
                    
                }

                reader.close();  
                
                b=false;

            }
            catch(IOException e)
            {System.out.println("file not found:new game initialized");}

        }
    }

    private void saver() 
    { 
        try{Scanner input = new Scanner(System.in);
            System.out.println("Enter filename:");
            String filename=input.nextLine()+".txt";
            String s= "";
            for(int ii = 0; ii < board1.getBoard().length; ii++){
                for(int hh = 0; hh < board1.getBoard().length; hh++){
                    s=s+board1.contents(ii,hh);

                }

            }

            for(int ii = 0; ii <  board2.getBoard().length; ii++){
                for(int hh = 0; hh < board2.getBoard().length; hh++){
                    s=s+board2.contents(ii,hh);

                }

            }

            s=s+turn;

            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println(s);
            writer.close();
        }
        catch(Exception e)
        {}
    }
}
