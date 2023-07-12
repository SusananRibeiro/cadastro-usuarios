package cadastro.usuario.controller;
import cadastro.usuario.model.Cadastros;
import cadastro.usuario.model.Usuarios;
import cadastro.usuario.service.CadastroService;
import cadastro.usuario.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/main.fxml") // para lincar com o arquivo "main.fxml"
public class UsuarioController {
    @FXML
    private TextField senha;
    @FXML
    private TextField nomeUsuario;
    @FXML
    private TextField idUsuario;
    @FXML
    private TextField nome;
    @FXML
    private TextField cpf;
    @FXML
    private TextField endereco;
    @FXML
    private TextField telefone;

    private int index = -1;

    // USUARIO
    public void executarEntrar() {
        Usuarios user = new Usuarios();
        user.nomeUsuario = nomeUsuario.getText();
        user.senha = senha.getText();

        try {
            Alert alertaObrig = new Alert(Alert.AlertType.ERROR);
            alertaObrig.setTitle("Campo obrigatório");
            Alert alertaInval = new Alert(Alert.AlertType.ERROR);
            alertaInval.setTitle("Erro");
            // Colocar na classe controller para não aceitar letras no campo documento
            if (nomeUsuario.getText().isEmpty()) {
                alertaObrig.setHeaderText("É obrigatório informar o usuário!");
                alertaObrig.show(); // precisa para mostrar a tela do alerta
            } else if (senha.getText().isEmpty()) {
                alertaObrig.setHeaderText("É obrigatório informar a senha!");
                alertaObrig.show(); // precisa para mostrar a tela do alerta
            } else if (index < 0) {
                if (UsuarioService.buscarUsuarioByDocumento(user.nomeUsuario)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alerta");
                    alert.setHeaderText("Usuário " + nomeUsuario.getText() + " já existe na base.");
                    alert.show(); // precisa para mostrar a tela do alerta
                } else {
                    UsuarioService.inserirUsuario(user);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        this.limparCamposUsuario();

    }
    public void limparCamposUsuario() {
        nomeUsuario.setText(""); // zera o campo
        senha.setText("");
    }

    // CADASTRO
    public void executarSalvar() {
            Cadastros cadastro = new Cadastros();
            cadastro.idUsuario = idUsuario.getText();
            cadastro.nome = nome.getText();
            cadastro.cpf = cpf.getText();
            cadastro.endereco = endereco.getText();
            cadastro.telefone = telefone.getText();
            try {
                Alert alertaObrig = new Alert(Alert.AlertType.ERROR);
                alertaObrig.setTitle("Campo obrigatório");
                Alert alertaInval = new Alert(Alert.AlertType.ERROR);
                alertaInval.setTitle("Erro");

                if (nome.getText().isEmpty()) {
                    alertaObrig.setHeaderText("É obrigatório informar o nome!");
                    alertaObrig.show(); // precisa para mostrar a tela do alerta
                } else if (!cadastro.cpf.matches("[0-9]*")) { // expressão regular: [0-9]*, ela só permite números de 0 a 9
                    alertaInval.setHeaderText("CPF inválido, somente números.");
                    alertaInval.show();
                }  else if (cpf.getText().isEmpty()) {
                    alertaObrig.setHeaderText("É obrigatório informar o CPF!");
                    alertaObrig.show();
                } else if (!cadastro.telefone.matches("[0-9]*")) { // expressão regular: [0-9]*, ela só permite números de 0 a 9
                    alertaInval.setHeaderText("Telefone inválido, somente números.");
                    alertaInval.show();
                } else if (index < 0) {
                    CadastroService.inserirCadastro(cadastro);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            this.limparCamposCadastro();
    }
    public void limparCamposCadastro() {
        idUsuario.setText(""); // zera o campo
        nome.setText("");
        cpf.setText("");
        endereco.setText("");
        telefone.setText("");
    }

}
