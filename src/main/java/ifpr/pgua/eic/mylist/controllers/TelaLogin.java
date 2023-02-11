package ifpr.pgua.eic.mylist.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaLoginViewModel;
import ifpr.pgua.eic.mylist.models.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLogin extends BaseController implements Initializable{

    @FXML 
    private TextField tfLogin;

    @FXML
    private PasswordField tfSenha;


    @FXML 
    private Button btEntrar;

    @FXML
    private Button btCadastro;


    private TelaLoginViewModel viewModel;

    public TelaLogin(TelaLoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        viewModel.alertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });

        tfLogin.textProperty().bindBidirectional(viewModel.loginProperty());

        tfSenha.textProperty().bindBidirectional(viewModel.senhaProperty());
    }

    @FXML void entrar() {
        viewModel.entrar();
    }

    @FXML void cadastro() {
        viewModel.cadastro();
    }
    
}
