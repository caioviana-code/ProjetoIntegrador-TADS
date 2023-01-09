package ifpr.pgua.eic.mylist.models.results;

public class FailResult extends Result {

    /**
     * Cria um objeto que representa um resultado falho de uma
     * operação.
     * 
     * @param msg Mensagem de falha.
     */
    public FailResult(String msg) {
        super(msg);
    }
    
}