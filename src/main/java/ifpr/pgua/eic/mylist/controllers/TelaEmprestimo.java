package ifpr.pgua.eic.mylist.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.mylist.controllers.viewmodels.EmprestimoRow;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaEmprestimoViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<EmprestimoRow, String> tbcDataDevolucao;

    @FXML
    private TableView<EmprestimoRow> tbEmprestimos;

    
    private TelaEmprestimoViewModel viewModel;

    public TelaEmprestimo(TelaEmprestimoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tbcFuncionario.setCellValueFactory(new PropertyValueFactory<>("Funcionario"));
        tbcFerramenta.setCellValueFactory(new PropertyValueFactory<>("Ferramenta"));
        tbcQuantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
        tbcDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("Data Emprestimo"));
        tbcDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("Data Devolucao"));

        tbEmprestimos.setItems(viewModel.getEmprestimos());

        viewModel.selecionadoProperty().bind(tbEmprestimos.getSelectionModel().selectedItemProperty());

        tfFuncionario.textProperty().bindBidirectional(viewModel.funcionarioProperty());

        tfFerramenta.textProperty().bindBidirectional(viewModel.ferramentaProperty());

        tfQuantidade.textProperty().bindBidirectional(viewModel.quantidadeProperty());
        
    }

    @FXML
    private void cadastrar() {
        viewModel.cadastrar();
    }
    
}
