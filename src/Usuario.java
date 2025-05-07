import java.sql.*;

public class Usuario {
    // Atributos
    private String nome;
    private String email;
    private String senha;
    private Double saldo;

    // Construtor com argumentos
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
    }

    public Usuario() {

    }

    // Getters e Setters
    public Double getSaldo(){
        return saldo;
    }

    public void setSaldo(Double saldo){
        this.saldo = saldo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Senha: "+ senha);
        System.out.println("Email: " + email);
    }

    private String URL = "jdbc:mysql://localhost:3306/brunesco";
    private String USUARIO = "root";
    private String SENHA = "";


    public void cadastrarUsuario(String nome, String email, String senha) {
        String sqlVerificar = "SELECT * FROM usuario WHERE email = ?";
        String sqlInserir = "INSERT INTO usuario (nome, email, senha, saldo) VALUES (?, ?, ?, 0.0)";

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            // Verifica se o email já está cadastrado
            try (PreparedStatement stmtVerificar = conexao.prepareStatement(sqlVerificar)) {
                stmtVerificar.setString(1, email);
                try (ResultSet rs = stmtVerificar.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Erro: Usuário com o email '" + email + "' já está cadastrado!");
                        return;
                    }
                }
            }

            // Insere o novo usuário no banco
            try (PreparedStatement stmtInserir = conexao.prepareStatement(sqlInserir)) {
                stmtInserir.setString(1, nome);
                stmtInserir.setString(2, email);
                stmtInserir.setString(3, senha);
                stmtInserir.executeUpdate();

                System.out.println("Usuário cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados:");
            e.printStackTrace();
        }
    }

    public String verificarUsuario(String senha, String email) {
        String sqlVerificar = "SELECT nome, senha, saldo FROM usuario WHERE email = ?";

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            // Verifica se o email já está cadastrado
            try (PreparedStatement stmt = conexao.prepareStatement(sqlVerificar)) {
                stmt.setString(1, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) { // Se encontrar o usuário
                        String senhaUser = rs.getString("senha");
                        String nomeUser = rs.getString("nome");
                        String saldoUser = rs.getString("saldo");
                        this.nome = nomeUser;
                        if(senhaUser.equals(senha)){
                            return saldoUser;
                        }

                    } else {
                        System.out.println("usuario não encontrado");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados:"+e.toString());

        }
        return null;
    }
    public double Sacar(String email, double saque){
        String sql = "SELECT saldo FROM usuario WHERE email = ?";
        String sqlUp = "UPDATE usuario SET saldo = ? WHERE email = ?";
        try{
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            double saldoUser = rs.getDouble("saldo");
            if(saldoUser > saque){
                double saldoAtual = saldoUser - saque;
                PreparedStatement stmtUp = conexao.prepareStatement(sqlUp);
                stmtUp.setDouble(1, saldoAtual);
                stmtUp.setString(2, email);
                System.out.println("Saque efetuado");
                return saldoAtual;
            } else {
                System.out.println("Você não pode sacar essa quantia!");
            }


        }catch (SQLException e){
            System.out.println("erro aqui"+e.toString());
        }

        return 0;
    }

}
