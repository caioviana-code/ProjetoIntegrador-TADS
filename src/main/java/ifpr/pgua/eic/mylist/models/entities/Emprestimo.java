package ifpr.pgua.eic.mylist.models.entities;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Emprestimo {
    
    private Integer id;
    private Funcionario funcionario;
    private String cpfFuncionario;
    private List<ItemEmprestimo> itens;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private Integer status;
    
    public Emprestimo(Integer id, Funcionario funcionario, String cpfFuncionario, List<ItemEmprestimo> itens, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, Integer status) {
        this.id = id;
        this.funcionario = funcionario;
        this.cpfFuncionario = cpfFuncionario;
        this.itens = itens;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public Emprestimo(Integer id, String cpfFuncionario, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, Integer status) {
        this.id = id;
        this.cpfFuncionario = cpfFuncionario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public Emprestimo(Funcionario funcionario, String cpfFuncionario, List<ItemEmprestimo> itens, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, Integer status) {
        this.funcionario = funcionario;
        this.cpfFuncionario = cpfFuncionario;
        this.itens = itens;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public void adicionarFerramenta(Ferramenta ferramenta, Integer quantidade) {
        Optional<ItemEmprestimo> item = itens.stream().filter((it)->it.getFerramenta().getId()==ferramenta.getId()).findFirst();

        if (item.isPresent()) {
            ItemEmprestimo i = item.get();
            i.setQuantidade(i.getQuantidade() + quantidade);
        } else {
            ItemEmprestimo i = new ItemEmprestimo();
            i.setFerramenta(ferramenta);
            i.setQuantidade(quantidade);
            itens.add(i);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemEmprestimo> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void setItens(List<ItemEmprestimo> itens) {
        this.itens = itens;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Emprestimo [id=" + id + ", funcionario=" + funcionario + ", cpfFuncionario=" + cpfFuncionario
                + ", itens=" + itens + ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao
                + ", status=" + status + "]";
    }

}