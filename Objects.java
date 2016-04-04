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
    private String scent;
    private String taste;
    private String use;
    private String hit;
    
    public Objects(String name, String description, String scent, String taste, String use, String hit)
    {
        this.name = name;
        this.description = description;
        this.scent = scent;
        this.taste = taste;
        this.use = use;
        this.hit = hit;
    }
    
    //Returns the name of the object
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }

    public String getScent()
    {
        return scent;
    }
    
    public String getTaste()
    {
        return taste;
    }
    
    public void setUse(String use)
    {
        this.use = use;
    }
    
    public String getUse()
    {
        return use;
    }
    
    public String getHit()
    {
        return hit;
    }
}
