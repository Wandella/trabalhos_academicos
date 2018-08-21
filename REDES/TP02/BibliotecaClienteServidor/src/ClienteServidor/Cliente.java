package ClienteServidor;

import utilitarios.ChecaTempo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    private Socket socket;
    private final int porta;
    private final String host;
    private PrintStream out; //Saída da comunicação
    private BufferedReader in; //Buffer que armazena a mensagem enviada pelo Servidor
    

    public Cliente(String host, int porta) {
        this.porta = porta;
        this.host = host;
    }

    public int getPorta() {
        return porta;
    }

    public String getHost() {
        return host;
    }

    public boolean pedeConexao() {
        ChecaTempo timer = new ChecaTempo();

        timer.conexaoRealizada(1); //timer para estabelecer tempo máximo de espera
        while (timer.checaConexao()) {

            try {
                //Cria socket e faz o bind
                this.socket = new Socket(host, porta);
                return true;

            } catch (IOException e) {
                System.err.println("Erro ao tentar conectar (porta:" + this.porta + ")");
                return false;
            }
        }
        
        System.err.println("Impossivel conectar");
        return false;
    }

    private boolean estabeleceComunicacao() { //liga as entradas e saidas do cliente com o servidor
        try {
            InputStream input = this.socket.getInputStream();
            OutputStream output = this.socket.getOutputStream();
            this.in = new BufferedReader(new InputStreamReader(input));
            this.out = new PrintStream(output);
            return true;

        } catch (IOException e) {
            System.err.println("Erro ao estabelecer conexão");
            return false;
        }

    }

    private boolean enviarMensagem(String mensagem) { //envia mensagem ao servidor
        try {
            out.println(" ;" + mensagem);
            return true;
            
        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem");
            return false;
            
        }
    }

    private String recebeMensagem() { //recebe mensagem do servidor
        try {
            String mensagem = in.readLine();
            return mensagem;
            
        } catch (IOException e) {
            System.err.println("Erro ao receber mensagem");
            return null;
            
        }
    }

    public String fazComunicacao(String mensagemEnviar, int maxEspera) { //envia e recebe mensagem do servidor
        String mensagem;
        ChecaTempo timer = new ChecaTempo();
        timer.conexaoRealizada(maxEspera);

        while (timer.checaConexao()) { //tenta fazer a comunicação em um máximo de tempo
            try {
                this.estabeleceComunicacao();
                if (!this.enviarMensagem(mensagemEnviar)) return null;
                    
                mensagem = this.recebeMensagem();

                if (!mensagem.equals("0")) {
                    return mensagem;
                }

            } catch (Exception e) {
                System.err.println("Impossível realizar comunicação");
                return null;
                
            }
        }
        System.err.println("Impossível realizar comunicação. Tempo de espera esgotado!");
        return null;
    }

    public void encerraCliente() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Erro ao encerrar conexao");
        }
    }

}
