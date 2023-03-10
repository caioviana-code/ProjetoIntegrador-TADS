package ifpr.pgua.eic.mylist.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.mylist.controllers.viewmodels.EmprestimoRow;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaEmprestimoViewModel;
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

public class TelaEmprestimo extends BaseController implements Initializable{

    @FXML 
    private TextField tfFuncionario;

    @FXML 
    private TextField tfFerramenta;

    @FXML
    private TextField tfQuantidade;


    @FXML
    private Button btEmprestar;

    @FXML
    private Button btLimpar;

    @FXML 
    private Button btDevolver;


    @FXML
    private TableColumn<EmprestimoRow, String> tbcFuncionario;

    @FXML 
    private TableColumn<EmprestimoRow, String> tbcFerramenta;

    @FXML
    private TableColumn<EmprestimoRow, String> tbcQuantidade;

    @FXML
    private TableColumn<EmprestimoRow, String> tbcDataEmprestimo;

    @FXML
    private TableView<EmprestimoRow> tbEmprestimos;

    
    private TelaEmprestimoViewModel viewModel;

    public TelaEmprestimo(TelaEmprestimoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tbcFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        tbcFerramenta.setCellValueFactory(new PropertyValueFactory<>("ferramenta"));
        tbcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tbcDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));

        tbEmprestimos.setItems(viewModel.getEmprestimos());

        viewModel.selecionadoProperty().bind(tbEmprestimos.getSelectionModel().selectedItemProperty());

        viewModel.alertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });

        tfFuncionario.textProperty().bindBidirectional(viewModel.funcionarioProperty());

        tfFerramenta.textProperty().bindBidirectional(viewModel.ferramentaProperty());

        tfQuantidade.textProperty().bindBidirectional(viewModel.quantidadeProperty());

        btDevolver.disableProperty().bind(viewModel.desativadoProperty());
        
        viewModel.updateList();
    }

    @FXML
    private void cadastrar() {
        viewModel.cadastrar();
    }

    @FXML
    private void atualizar(MouseEvent event) {
        if (event.getClickCount() == 2) {
            viewModel.atualizar();
        }
    }

    @FXML
    private void devolver() {
        viewModel.devolver();
    }
    
    @FXML
    private void limpar() {
        viewModel.limpar();
    }
}
