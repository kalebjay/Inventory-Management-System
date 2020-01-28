package c482task1.kaleb.chatland;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kaleb Chatland
 */
public class ModifyProductController implements Initializable
{
    Stage stage;
    Parent scene;
    
    @FXML
    private TableView<Part> topTableview;

    @FXML
    private TableColumn<Part, Integer> topPartID;

    @FXML
    private TableColumn<Part, String> topPartName;

    @FXML
    private TableColumn<Part, Integer> topInventoryLv;

    @FXML
    private TableColumn<Part, Double> topPricePerUnit;

    @FXML
    private TableView<Part> bottomTalbleview;

    @FXML
    private TableColumn<Part, Integer> bPartID;

    @FXML
    private TableColumn<Part, String> bPartName;

    @FXML
    private TableColumn<Part, Integer> bInventoryLv;

    @FXML
    private TableColumn<Part, Integer> bPricePerUnit;

    @FXML
    private TextField searchTxt;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;
    
    @FXML
    public ObservableList<Part> tempPart=FXCollections.observableArrayList();
    private ObservableList<Part> currentPart=FXCollections.observableArrayList();
    int productIndex = MainMenuController.modifyIndex();
    private Product selectedProduct;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        topTableview.setItems(Inventory.getAllParts());
        
        topPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        topPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        topInventoryLv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        topPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        bPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        bPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        bInventoryLv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }   
    
    @FXML
    void onActionDeletePart(ActionEvent event) 
    {
        Part part = bottomTalbleview.getSelectionModel().getSelectedItem();
        currentPart.remove(part);
        bottomTalbleview.setItems(currentPart); 
        MainMenuController.deleteConfirmation();
    }

    @FXML
    void onActionGoToMainMenu(ActionEvent event) throws IOException 
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddPart(ActionEvent event) 
    {
        Part part = topTableview.getSelectionModel().getSelectedItem();
        currentPart.add(part);
        bottomTalbleview.setItems(currentPart);
    }

    @FXML
    void onActionUpdateProduct(ActionEvent event) throws IOException 
    {
        int itemID = Integer.parseInt(idTxt.getText());
        String name = nameTxt.getText();
        int stock = Integer.parseInt(invTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int min = Integer.parseInt(maxTxt.getText());
        int max = Integer.parseInt(minTxt.getText());
        ArrayList<Part> parts = new ArrayList<>();
        
        Product newProduct = new Product(itemID, name, stock, price, min, max, parts);
        
        parts.addAll(bottomTalbleview.getItems());
        newProduct.setAssociatedParts(parts);
        Inventory.updateProduct(productIndex, newProduct);
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void setProduct(Product product) 
        {   
            selectedProduct = product;
            currentPart = selectedProduct.getAllAssociatedParts();
            bottomTalbleview.setItems(currentPart);
            
            idTxt.setText(Integer.toString(product.getProductID()));
            nameTxt.setText(product.getName());
            invTxt.setText(Integer.toString(product.getStock()));
            priceTxt.setText(Double.toString(product.getPrice()));
            minTxt.setText(Integer.toString(product.getMin()));
            maxTxt.setText(Integer.toString(product.getMax()));
        }
    
    @FXML
    void onActionSearchParts(ActionEvent event) 
    {
        String searchItem=searchTxt.getText();
        if (searchItem.equals("")){
                topTableview.setItems(Inventory.getAllParts());
        } else
        {
            boolean found=false;
            try
            {
                int itemNumber=Integer.parseInt(searchItem);
                Part p = Inventory.lookupPart(itemNumber);
                    if(p != null)
                    {
                        System.out.println("This is part "+p.getPartID());
                        infoMessage(itemNumber);                       
                        found=true;
                        tempPart.clear();
                        tempPart.add(p);
                        topTableview.setItems(tempPart);
                    } 
                    if (found==false)
                    {
                        topTableview.setItems(Inventory.getAllParts());
                        errorMessage();                
                    }
            }
            //catches strings used instead of numbers 
            catch(NumberFormatException e)
            {
                String itemName = searchItem;
                Part n = Inventory.lookupPart(itemName);
                    if(n != null)
                    {
                        infoMessage(itemName);
                        found=true;
                        tempPart.clear();
                        tempPart.add(n);
                        topTableview.setItems(tempPart);
                    }
            
                    if (found==false)
                    {
                        topTableview.setItems(Inventory.getAllParts());
                        errorMessage();           
                    }
            }
        }
    
    }
    
    void errorMessage()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Item not found");
        alert.setContentText("Please search by item ID# or exact Name");
        alert.showAndWait();
    }
    
    void infoMessage(int item)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Item ID:"+item+" Successfully Found");
        alert.setContentText("Item is displayed in table");
        alert.showAndWait();
    }
    
    void infoMessage(String item)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Item \""+item+"\" successfully found");
        alert.setContentText("Item is displayed in table");
        alert.showAndWait();
    }
}
