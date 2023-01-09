package ifpr.pgua.eic.mylist.controllers;

import ifpr.pgua.eic.mylist.models.results.Result;
import ifpr.pgua.eic.mylist.models.results.SuccessResult;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BaseController {
    
    public void showMessage(Result msg){
        AlertType tipo = msg instanceof SuccessResult? AlertType.INFORMATION:AlertType.ERROR;
        
        Alert alert = new Alert(tipo, msg.getMsg());
        alert.showAndWait();
    }
}