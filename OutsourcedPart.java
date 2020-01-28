package c482task1.kaleb.chatland;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author Kaleb Chatland
 */
public class OutsourcedPart extends Part
{
    private final StringProperty companyName;
    
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) 
    {
        
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.companyName = new SimpleStringProperty(companyName);
    }
    
    public OutsourcedPart() 
    {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0);
        this.stock = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.companyName = new SimpleStringProperty("");
        
    }

    public String getCompanyName() 
    {
        return this.companyName.get();
    }

    public void setCompanyName(String companyName) 
    {
        this.companyName.set(companyName);
    }
    
    public StringProperty companyNameProperty() 
    {
            return companyName;
    }
}
