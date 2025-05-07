import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexao {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/brunesco";
        String usuario = "root";
        String senha = "";
        try {
            // Estabelecer conexão
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão bem-sucedida!");

            // Fechar conexão
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados:" + e.toString());
        }
    }
}
