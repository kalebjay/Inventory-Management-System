package c482task1.kaleb.chatland;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Kaleb Chatland
 */
public class Product
{   
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty stock;
    private final DoubleProperty price;
    private final IntegerProperty min;
    private final IntegerProperty max;
    private ArrayList<Part> associatedParts;
    
    //Product(id : int, name : String, price : double, stock : int, min : int, max : int)
    public Product(int id, String name, int stock, double price, int min, int max, ArrayList<Part> associatedPart)    
    {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.stock = new SimpleIntegerProperty(stock);
        this.price = new SimpleDoubleProperty(price);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.associatedParts = new ArrayList<>(associatedPart);
    }
    
    public Product()    
    {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0);
        this.stock = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.associatedParts = new ArrayList<>();
    }
    //setId(id:int):void
    public void setID(int id)
    {
        this.id.set(id);
    }
    
    //setName(name:String):void
    public void setName(String name)
    {
        this.name.set(name);
    }
    
    //setPrice(price:double):void
    public void setPrice(double price)
    {
        this.price.set(price);
    }
    
    //setStock(stock:int):void
    public void setStock(int stock)
    {
        this.stock.set(stock);
    }
    
    //setMin(min:int):void
    public void setMin(int min)
    {
        this.min.set(min);
    }
    
    //setMax(max:int):void
    public void setMax(int max)
    {
        this.max.set(max);
    }
   
    //not sure why this is needed, the pdf doesn't show a getter to match
    //setPrice(max:int):void
    public void setPrice(int max)
    {
        this.price.set(max);
    }
    
    public void setAssociatedParts(ArrayList<Part> associatedPart) {
        this.associatedParts = associatedPart;
    }
    
    public ArrayList<Part> getAssociatedParts() {
        return this.associatedParts;
    }
    
    //getId():int
    public int getProductID()
    {
        return this.id.get();
    }
    
    //getName():String
    public String getName()
    {
        return this.name.get();
    }
    
    //getPrice():double
    public double getPrice()
    {
        return this.price.get();
    }
    
    //getStock():int
    public int getStock()
    {
        return this.stock.get();
    }
    
    //getMin():int
    public int getMin()
    {
        return min.get();
    }
    
    //getMax():int
    public int getMax()
    {
        return this.max.get();
    }
    
    public IntegerProperty productIDProperty() 
    {
        return id;
    }
    
    public StringProperty productNameProperty() 
    {
        return name;
    }
    
    public DoubleProperty productPriceProperty() 
    {
        return price;
    }
    
    public IntegerProperty productInStockProperty() 
    {
        return stock;
    }
    
    public IntegerProperty productMinProperty() 
    {
        return min;
    }
    
    public IntegerProperty productMaxProperty() 
    {
        return max;
    }
      
    //addAssociatedPart(part:Part):void
    public void addAssociatedPart(Part associatedPart)
    {
        associatedParts.add(associatedPart);
    }
        
    //deleteAssociatedPart(selectedAspart:Part):boolean
    public boolean deleteAssociatedPart(Part associatedPart)
    {
        associatedParts.remove(associatedPart);
        return false;
    }
    
    //getAllAssociatedParts():ObservableList<Part>
    public ObservableList<Part> getAllAssociatedParts()
    {
        ObservableList<Part> parts = FXCollections.observableArrayList(this.associatedParts);
        return parts;
    }
    
}
