package ifpr.pgua.eic.mylist;

import ifpr.pgua.eic.mylist.controllers.TelaCadastro;
import ifpr.pgua.eic.mylist.controllers.TelaEmprestimo;
import ifpr.pgua.eic.mylist.controllers.TelaFerramenta;
import ifpr.pgua.eic.mylist.controllers.TelaFuncionario;
import ifpr.pgua.eic.mylist.controllers.TelaLogin;
import ifpr.pgua.eic.mylist.controllers.TelaPrincipal;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaCadastroViewModel;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaEmprestimoViewModel;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaFerramentaViewModel;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaFuncionarioViewModel;
import ifpr.pgua.eic.mylist.controllers.viewmodels.TelaLoginViewModel;
import ifpr.pgua.eic.mylist.models.FabricaConexoes;
import ifpr.pgua.eic.mylist.models.daos.EmprestimoDAO;
import ifpr.pgua.eic.mylist.models.daos.FerramentaDAO;
import ifpr.pgua.eic.mylist.models.daos.FuncionarioDAO;
import ifpr.pgua.eic.mylist.models.daos.JDBCEmprestimoDAO;
import ifpr.pgua.eic.mylist.models.daos.JDBCFerramentaDAO;
import ifpr.pgua.eic.mylist.models.daos.JDBCFuncionarioDAO;
import ifpr.pgua.eic.mylist.models.daos.JDBCUsuarioDAO;
import ifpr.pgua.eic.mylist.models.daos.UsuarioDAO;
import ifpr.pgua.eic.mylist.models.repositories.EmprestimoRepository;
import ifpr.pgua.eic.mylist.models.repositories.FerramentaRepository;
import ifpr.pgua.eic.mylist.models.repositories.FuncionarioRepository;
import ifpr.pgua.eic.mylist.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.mylist.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.mylist.utils.Navigator.ScreenRegistryFXML;

public class App extends BaseAppNavigator{

    private FerramentaDAO ferramentaDAO;
    private FuncionarioDAO funcionarioDAO;
    private EmprestimoDAO emprestimoDAO;
    private UsuarioDAO usuarioDAO;
    private FerramentaRepository ferramentaRepository;
    private FuncionarioRepository funcionarioRepository;
    private EmprestimoRepository emprestimoRepository;
    private UsuarioRepository usuarioRepository;

    @Override
    public void init() throws Exception {

        ferramentaDAO = new JDBCFerramentaDAO(FabricaConexoes.getInstance());
        funcionarioDAO = new JDBCFuncionarioDAO(FabricaConexoes.getInstance());
        emprestimoDAO = new JDBCEmprestimoDAO(FabricaConexoes.getInstance());
        usuarioDAO = new JDBCUsuarioDAO(FabricaConexoes.getInstance());
        ferramentaRepository = new FerramentaRepository(ferramentaDAO);
        funcionarioRepository = new FuncionarioRepository(funcionarioDAO);
        emprestimoRepository = new EmprestimoRepository(emprestimoDAO, funcionarioDAO, ferramentaDAO);
        usuarioRepository = new UsuarioRepository(usuarioDAO);

        ferramentaRepository.getFerramentas();
        funcionarioRepository.getFuncionarios();
        emprestimoRepository.getEmprestimos();
        usuarioRepository.getUsuarios();

        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public String getHome() {
        return "LOGIN";
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
        registraTela("LOGIN", new ScreenRegistryFXML(getClass(), "fxml/telaLogin.fxml", (o)-> new TelaLogin(new TelaLoginViewModel(usuarioRepository))));
        registraTela("CADASTRO", new ScreenRegistryFXML(getClass(), "fxml/telaCadastro.fxml", (o)-> new TelaCadastro(new TelaCadastroViewModel(usuarioRepository))));
    }   
}