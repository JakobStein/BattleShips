import java.util.*;
import java.io.*;
public class Human implements Player
{
    private boolean hit;

    public Human()
    {

        
    }

    public void isHit(boolean b)
    {System.out.println("A Hit!");
    }

    public ShipPlacer place() throws SaveException
    {

        Scanner input = new Scanner(System.in);
        boolean user=true;
        int x=0;
        int y=0;
        boolean isVertical=true;
        do{
            try{

        
                System.out.print("Row: ");
                String s1=input.nextLine();
                x="ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(s1.toUpperCase());
                if(s1.equals("exit"))
                {throw new StopException();}

        
                System.out.print("Column: ");
                String s2=input.nextLine();
                y= Integer.parseInt(s2);
                y--;
                
                if(s2.equals("exit"))
                {throw new StopException();}

                System.out.print("Orientation (Vertical=1,Horizontal=2): ");
                String s3=input.nextLine();
                if(s3.equals("exit"))
                {throw new StopException();}
                int t=Integer.parseInt(s3);

                user=false;

                if(t==1)
                {isVertical=true;}
                else if(t==2)
                {isVertical=false; }

            }
            catch(StopException e)
            {System.out.println("Would you like to quit? (yes/no)");
                String s=input.nextLine();
                if(s.equals("yes"))
                {System.out.println("Would you like to save? (yes/no)");
                    String t=input.nextLine();
                    if(s.equals("yes"))
                    {throw new SaveException(true);}
                    else
                    {throw new SaveException(false);}
                }
            }
            catch(NumberFormatException e)
            {
            }

        }while(user);

        ShipPlacer p= new ShipPlacer(x,y,isVertical);
        return p;
        
    }
    
    public int[] shoot() throws SaveException 
    {
        Scanner input = new Scanner(System.in);
        boolean user=true;
        int x=0;
        int y=0;
        int[] p = new int[2];
        while(user){
            try{

        
                System.out.print("Row: ");

                x= "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(input.nextLine().toUpperCase());
                if(x<0)
                {throw new Exception();}

        
                System.out.print("Column: ");
                y= Integer.parseInt(input.next());
                y--;

       
                p[0]=x;
                p[1]=y;
                user= false;

      
        
            }
            catch(Exception e)

            {System.out.println("Would you like to quit? (yes/no)");
                String s=input.nextLine();
                if(s.equals("yes"))
                {System.out.println("Would you like to save? (yes/no)");
                    String t=input.nextLine();
                    if(s.equals("yes"))
                    {throw new SaveException(true);}
                    else
                    {throw new SaveException(false);}
                }

            }
        }   

        return p;
    }

}