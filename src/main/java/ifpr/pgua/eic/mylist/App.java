package ifpr.pgua.eic.mylist;

import ifpr.pgua.eic.mylist.controllers.TelaEmprestimo;
import ifpr.pgua.eic.mylist.controllers.TelaFerramenta;
import ifpr.pgua.eic.mylist.controllers.TelaFuncionario;
import ifpr.pgua.eic.mylist.controllers.TelaPrincipal;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaEmprestimoViewModel;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaFerramentaViewModel;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaFuncionarioViewModel;
import ifpr.pgua.eic.mylist.models.FabricaConexoes;
import ifpr.pgua.eic.mylist.models.daos.EmprestimoDAO;
import ifpr.pgua.eic.mylist.models.daos.FerramentaDAO;
import ifpr.pgua.eic.mylist.models.daos.FuncionarioDAO;
import ifpr.pgua.eic.mylist.models.daos.JDBCEmprestimoDAO;
import ifpr.pgua.eic.mylist.models.daos.JDBCFerramentaDAO;
import ifpr.pgua.eic.mylist.models.daos.JDBCFuncionarioDAO;
import ifpr.pgua.eic.mylist.models.repositories.EmprestimoRepository;
import ifpr.pgua.eic.mylist.models.repositories.FerramentaRepository;
import ifpr.pgua.eic.mylist.models.repositories.FuncionarioRepository;
import ifpr.pgua.eic.mylist.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.mylist.utils.Navigator.ScreenRegistryFXML;

public class App extends BaseAppNavigator{

    private FerramentaDAO ferramentaDAO;
    private FuncionarioDAO funcionarioDAO;
    private EmprestimoDAO emprestimoDAO;
    private FerramentaRepository ferramentaRepository;
    private FuncionarioRepository funcionarioRepository;
    private EmprestimoRepository emprestimoRepository;

    @Override
    public void init() throws Exception {

        ferramentaDAO = new JDBCFerramentaDAO(FabricaConexoes.getInstance());
        funcionarioDAO = new JDBCFuncionarioDAO(FabricaConexoes.getInstance());
        emprestimoDAO = new JDBCEmprestimoDAO(FabricaConexoes.getInstance());
        ferramentaRepository = new FerramentaRepository(ferramentaDAO);
        funcionarioRepository = new FuncionarioRepository(funcionarioDAO);
        emprestimoRepository = new EmprestimoRepository(emprestimoDAO, funcionarioDAO, ferramentaDAO);

        ferramentaRepository.getFerramentas();
        funcionarioRepository.getFuncionarios();
        emprestimoRepository.getEmprestimos();

        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public String getHome() {
        return "PRINCIPAL";
    }

    @Override
    public String getAppTitle() {
        return "MyList";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(getClass(), "fxml/principal.fxml", (o)->new TelaPrincipal()));
        registraTela("FERRAMENTA", new ScreenRegistryFXML(getClass(), "fxml/telaFerramenta.fxml", (o)-> new TelaFerramenta(new TelaFerramentaViewModel(ferramentaRepository))));
        registraTela("FUNCIONARIO", new ScreenRegistryFXML(getClass(), "fxml/telaFuncionario.fxml", (o)-> new TelaFuncionario(new TelaFuncionarioViewModel(funcionarioRepository))));
        registraTela("EMPRESTIMO", new ScreenRegistryFXML(getClass(), "fxml/telaEmprestimo.fxml", (o)-> new TelaEmprestimo(new TelaEmprestimoViewModel(emprestimoRepository, funcionarioRepository, ferramentaRepository))));
    }   
}