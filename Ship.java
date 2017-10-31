import java.util.*;
/**
 * Abstract class Ship - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Ship
{
    // instance variables - replace the example below with your own
    private int length;
    private String name;
    private int id;

   
    
    public Ship(int length,String name,int id)
    {
        this.length=length;
        this.id=id;

        this.name=name;

     
    }

    public int getId()
    {return id;
    }

    public String getName()
    {return name;
    }

    public int getLength()
    {
        return length;  
    }
}
