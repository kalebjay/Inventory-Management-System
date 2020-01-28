package c482task1.kaleb.chatland;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kaleb Chatland
 */
public class Inventory
{   
    //observable list backed by arraylist
    //allParts:ObservableList<Part>;
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    //allProducts:ObservableList<Product>;
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
      
    //addPart(newPart:Part):void
    public static void addPart(Part part)
    {
        allParts.add(part);
    }
    
    //addProduct(newProduct:Product):void
    public static void addProduct(Product product)
    {
        allProducts.add(product);
    }
    
    //lookupPart(partId:int):Part
    public static Part lookupPart(int partID)
    {
        for(Part p: getAllParts())
        {
            if(p.getPartID()==partID)
            {
                return p;                
            }
        }
       return null;
    }

    //lookupProduct(productId:int):Product
    public static Product lookupProduct(int productID)
    {
        for(Product p: getAllProducts())
        {
            if(p.getProductID()==productID)
            {
                return p;                
            }
        }
       return null;
    }
    
    //lookupPart(partName:String):ObservableList<Part>
    public static Part lookupPart(String partName)
    {
        for(Part p: getAllParts())
        {
            if(p.getName().equals(partName))
            {
               return p; 
            }      
        }
       return null; 
    }
    
    //lookupProduct(productName:String):ObservableList<Product>
    public static Product lookupProduct(String productName)
    {
        for(Product p: getAllProducts())
        {
            if(p.getName().equals(productName))
            {
               return p; 
            }      
        }
        return null;
    }
    
    //updatePart(index:int, selectedPart:Part):void
    public static void updatePart(int index, Part part)
    {
        allParts.set(index, part);
    }
    
    //updateProduct(index:int, newProduct:Product):void
    public static void updateProduct(int index, Product product)
    {
        allProducts.set(index, product);
    }
    
    //deletePart(selectedPart:Part):boolean
    public static boolean deletePart(Part part)
    {
        allParts.remove(part);
        return true;
    }
    
    //deleteProduct(selectedProduct:Product):boolean
    public static boolean deleteProduct(Product product)
    {
        allProducts.remove(product);
        return true;
    }
     
    //getAllParts():ObservableList<Part>
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    
    //getAllProducts():ObservableList<Product>
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }
}
