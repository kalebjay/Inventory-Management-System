package c482task1.kaleb.chatland;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kaleb Chatland
 */
public class AddPartController implements Initializable
{
    Stage stage;
    Parent scene;

    @FXML
    private ToggleGroup InOutTG;
    
    @FXML
    private RadioButton inHouseTogBTN;
    
    @FXML
    private RadioButton outsourcedTogBTN;
    
    @FXML
    private Label companyMachineLabel;
    
    @FXML
    private TextField idTxt;
    
    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceCostTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField machineCompanyTxt;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        InOutTG = new ToggleGroup();
        this.inHouseTogBTN.setToggleGroup(InOutTG);
        this.outsourcedTogBTN.setToggleGroup(InOutTG);
        
    }    
    
    public void onActoinRadioBTN()
    {
         if (this.InOutTG.getSelectedToggle().equals(this.inHouseTogBTN))
         {
            companyMachineLabel.setText("Machine ID");
            machineCompanyTxt.setPromptText("Machine ID");
         }
         if (this.InOutTG.getSelectedToggle().equals(this.outsourcedTogBTN))
         {
            companyMachineLabel.setText("Company Name");
            machineCompanyTxt.setPromptText("Company Name");
         }
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
    void onActionSaveData(ActionEvent event) throws IOException 
    {
            //Grabs text from the textfields and saves them to new objects that aren't strings
            int itemID = Integer.parseInt(idTxt.getText());
            String name = nameTxt.getText();
            int stock = Integer.parseInt(invTxt.getText());
            double price = Double.parseDouble(priceCostTxt.getText());
            int min = Integer.parseInt(maxTxt.getText());
            int max = Integer.parseInt(minTxt.getText());
            
            if ((this.InOutTG.getSelectedToggle().equals(this.inHouseTogBTN))) 
            {
                int machineID = Integer.parseInt(machineCompanyTxt.getText());
                Inventory.addPart(new InhousePart(itemID, name, price, stock, min, max, machineID));
            }else
                {
                    String companyName = machineCompanyTxt.getText();
                    Inventory.addPart(new OutsourcedPart(itemID, name, price, stock, min, max, companyName));
                }
          
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
    }
}
