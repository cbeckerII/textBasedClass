import java.util.Set;
import java.util.Iterator;
/**
 * Write a description of class Objects here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Objects
{
    private String name;
    private String description;
    
    public Objects(String name, String description)
    {
        this.name = name;
        this.description = description;        
    }
    
    //Sets the name of the object when called
    public void setName(String name){
        this.name = name;
    }
    
    //Returns the name of the object
    public String getName()
    {
        return name;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getDescription()
    {
        return description;
    }
}
