package c482task1.kaleb.chatland;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Kaleb Chatland
 */
public abstract class Part
{
    protected IntegerProperty id;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty stock;
    protected IntegerProperty min;
    protected IntegerProperty max;
    
    /*Why is this not needed? 
    //Part(id : int, name : String, price : double, stock : int, min : int, max : int)
    public Part(int id, String name, double price, int stock, int min, int max)
    {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(id);
        this.min = new SimpleIntegerProperty(id);
        this.max = new SimpleIntegerProperty(id);
    }
    */  
    
    //setId(id:int):void
    public void setId(int id)
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
    
    //getPartId():int
    public int getPartID()
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
        return this.min.get();
    }
    
    //getMax():int
    public int getMax()
    {
        return this.max.get();
    }
    
    public IntegerProperty partIDProperty() 
    {
        return id;
    }
    
    public StringProperty nameProperty() 
    {
        return name;
    }
    
    public DoubleProperty priceProperty() 
    {
        return price;
    }
    
    public IntegerProperty inStockProperty() 
    {
        return stock;
    }
    
    public IntegerProperty minProperty() 
    {
        return min;
    }
    
    public IntegerProperty maxProperty() 
    {
        return max;
    }
    
}
