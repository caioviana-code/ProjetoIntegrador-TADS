package ifpr.pgua.eic.mylist.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.mylist.controllers.viewmodels.FerramentaRow;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaFerramentaViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaFerramenta extends BaseController implements Initializable {

    @FXML
    private TextField tfNome;

    @FXML 
    private TextField tfEstoque;


    @FXML
    private Button btCadastrar;

    @FXML
    private Button btLimpar;


    @FXML
    private TableColumn<FerramentaRow,String> tbcNome;

    @FXML
    private TableColumn<FerramentaRow,String> tbcEstoque;

    @FXML
    private TableView<FerramentaRow> tbFerramentas;


    private TelaFerramentaViewModel viewModel;

    public TelaFerramenta(TelaFerramentaViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));

        tbFerramentas.setItems(viewModel.getFerramentas());

        tfNome.textProperty().bindBidirectional(viewModel.nomeProperty());
        tfEstoque.textProperty().bindBidirectional(viewModel.estoqueProperty());

        viewModel.updateList();
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