package ifpr.pgua.eic.mylist.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaCadastroViewModel;
import ifpr.pgua.eic.mylist.models.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaCadastro extends BaseController implements Initializable {
    
    @FXML 
    private TextField tfLogin;

    @FXML
    private PasswordField tfSenha;

    @FXML
    private TextField tfEmail;


    @FXML
    private Button btCadastro;

    @FXML 
    private Button btVoltar;

    private TelaCadastroViewModel viewModel;

    public TelaCadastro(TelaCadastroViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        viewModel.alertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });

        tfLogin.textProperty().bindBidirectional(viewModel.loginProperty());

        tfSenha.textProperty().bindBidirectional(viewModel.senhaProperty());

        tfEmail.textProperty().bindBidirectional(viewModel.emailProperty());
    }

    @FXML void cadastro() {
        viewModel.cadastro();
    }

    @FXML void voltar() {
        viewModel.voltar();
    }
    
}
