
/**
 * Write a description of class SaveException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SaveException extends Exception
{
    private boolean save;
    
    SaveException(boolean b)
    {save=b;}
    
   
    
    public boolean getSave()
    {return save;
    }
}
