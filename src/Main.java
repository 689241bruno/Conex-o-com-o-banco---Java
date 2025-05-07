import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        System.out.println("Banco Brunesco!");
        Scanner sc = new Scanner(System.in);
        System.out.println("________________________________");
        System.out.println(" 1 - Entrar");
        System.out.println(" 2 - Cadastrar");
        System.out.println("________________________________");
        System.out.print("Sua opção: ");
        int resp = sc.nextInt();
        if(resp == 1)
        {
            System.out.println("________________________________");
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Senha: ");
            String senha = sc.next();
            Usuario usuario = new Usuario();
            System.out.println("________________________________");
            if(!(usuario.verificarUsuario(senha, email) == null)){
                double saldo = Double.parseDouble(usuario.verificarUsuario(senha, email));
                NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                System.out.println("Seja bem vindo "+ usuario.getNome());
                System.out.println("Saldo atual: "+ formatoMoeda.format(saldo));
            } else {
                System.out.println("error ao entrar");
            }
            System.out.println(" 1 - Despositar");
            System.out.println(" 2 - Sacar ");
            System.out.print("Sua resposta: ");
            resp = sc.nextInt();
            if (resp == 1){
                System.out.println("deposito");
            } else if (resp == 2){
                System.out.println("saque");
                System.out.print("Quanto deseja sacar?: ");
                double saque = sc.nextDouble();
                usuario.Sacar(email, saque);
            } else {
                System.out.println("opção invalida!");
            }
        }
        else if(resp == 2)
        {
            System.out.println("________________________________");
            System.out.print("Usuario: ");
            String nome = sc.next();
            System.out.print("Senha: ");
            String senha = sc.next();
            System.out.print("Email: ");
            String email = sc.next();
            Usuario usuario = new Usuario();
            usuario.cadastrarUsuario(nome, email, senha);
            System.out.println("Para entrar em sua conta reinicie o programa");
            System.out.println("________________________________");

        }
        else {
            System.out.println("opção invalida!");
        }
    }
}