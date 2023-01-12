package ifpr.pgua.eic.mylist.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.mylist.controllers.viewmodels.CadastroFerramentaViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class TelaCadastroFerramenta extends BaseController implements Initializable {

    @FXML
    private TextField tfNome;

    @FXML 
    private TextField tfEstoque;

    private CadastroFerramentaViewModel viewModel;

    public TelaCadastroFerramenta(CadastroFerramentaViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tfNome.textProperty().bindBidirectional(viewModel.nomeProperty());
        tfEstoque.textProperty().bindBidirectional(viewModel.estoqueProperty());
    }

    @FXML
    private void cadastrar() {
        viewModel.cadastrar();
    }

    @FXML 
    private void limpar() {
        viewModel.limpar();
    }
}
