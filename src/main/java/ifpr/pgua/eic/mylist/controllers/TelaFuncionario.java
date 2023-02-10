package ifpr.pgua.eic.mylist.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.mylist.controllers.viewmodels.FuncionarioRow;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaFuncionarioViewModel;
import ifpr.pgua.eic.mylist.models.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TelaFuncionario extends BaseController implements Initializable {

    @FXML
    private TextField tfNome;
    
    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfTelefone;


    @FXML
    private Button btCadastrar;

    @FXML
    private Button btLimpar;

    @FXML
    private Button btExcluir;

    @FXML
    private TableColumn<FuncionarioRow,String> tbcNome;

    @FXML
    private TableColumn<FuncionarioRow,String> tbcCpf;

    @FXML
    private TableColumn<FuncionarioRow,String> tbcTelefone;

    @FXML
    private TableView<FuncionarioRow> tbFuncionarios;

    
    private TelaFuncionarioViewModel viewModel;

    public TelaFuncionario(TelaFuncionarioViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tbcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tbFuncionarios.setItems(viewModel.getFuncionarios());

        viewModel.selecionadoProperty().bind(tbFuncionarios.getSelectionModel().selectedItemProperty());

        viewModel.alertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });

        tfNome.textProperty().bindBidirectional(viewModel.nomeProperty());
        tfNome.editableProperty().bind(viewModel.podeEditarProperty());

        tfCpf.textProperty().bindBidirectional(viewModel.cpfProperty());
        tfCpf.editableProperty().bind(viewModel.podeEditarProperty());

        tfTelefone.textProperty().bindBidirectional(viewModel.telefoneProperty());

        btCadastrar.textProperty().bind(viewModel.operacaoProperty());

        btExcluir.disableProperty().bind(viewModel.desativadoProperty());

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

    @FXML
    private void atualizar(MouseEvent event) {
        if (event.getClickCount() == 2) {
            viewModel.atualizar();
        }
    }

    @FXML
    private void excluir() {
        viewModel.excluir();
    }
}