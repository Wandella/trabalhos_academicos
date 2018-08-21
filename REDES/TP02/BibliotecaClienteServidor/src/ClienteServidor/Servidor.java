package ClienteServidor;

import utilitarios.ChecaTempo;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private ServerSocket servidor;
    //Instância os Socketss dos clientes
    private Socket clienteAtual;
    private Socket cliente1;
    private Socket cliente2;
    private int porta; //Porta a ser utilizada pelo servidor
    private final int MAX_PEND = 2;//Denição do numero de processos, ou pedidos de conexão, que podem aguardar pelo Servidor
    //Saída da comunicação
    private PrintStream out;
    //Buffer que armazena a mensagem enviada por um Cliente
    private BufferedReader in;
    private int clienteConctado;

    private boolean servidorEstabelecido;

    public Servidor(int porta) {
        this.porta = porta;
        this.servidorEstabelecido = false;
    }

    public boolean isServidorEstabelecido() {
        return servidorEstabelecido;
    }

    public int getPorta() {
        return porta;
    }

    public int qtdClientes() { //ve quantos clientes ja estao conectados
        int cont = 0;
        if (this.cliente1 != null) {
            cont++;
        }
        if (this.cliente2 != null) {
            cont++;
        }

        return cont;
    }

    public boolean estabeleceServidor() {
        try {
            if (isServidorEstabelecido()) { //se servidor já foi estabelecido
                this.servidor.close();
            }

            servidor = new ServerSocket(porta, MAX_PEND); //Cria socket e faz o bind
            System.out.println("Servidor disponível na porta " + porta);
            this.servidorEstabelecido = true;
            return true;

        } catch (Exception e) {
            System.err.println("Erro ao estabelecer Servidor na porta " + porta);
            return false;

        }
    }

    public boolean aceitaConexao() { //fica disponivel para cliente conectar até que algum se conecte
        try {
            if (!isServidorEstabelecido()) { //se servidor ainda nao foi estabelecido
                System.err.println("Servidor ainda nao foi estabelecido");
                return false;
            }

            switch (this.qtdClientes()) {
                case 0:
                    this.cliente1 = servidor.accept();
                    return true;
                case 1:
                    this.cliente2 = servidor.accept();
                    return true;
                case 2:
                    System.out.println("Cheio");
                    return false;

            }
            System.err.println("Erro ao aceitar conexão");
            return false;

        } catch (Exception e) {
            System.err.println("Erro ao aceitar conexão");
            return false;

        }
    }

    private boolean estabeleceComunicacao() { //estabelece a conexão das entradas e saidas
        try {
            InputStream input = this.clienteAtual.getInputStream();
            OutputStream output = this.clienteAtual.getOutputStream();
            this.in = new BufferedReader(new InputStreamReader(input));
            this.out = new PrintStream(output);
            return true;

        } catch (Exception e) {
            System.err.println("Impossível estabelecer comunicacao");
            return false;

        }
    }

    private boolean escolheCliente(int clienteEscolha) { //escolhe o cliente ao qual deve-se fazer a comunicação no momento

        if (!isServidorEstabelecido() || clienteEscolha > this.qtdClientes()) { //se servidor não foi estabelecido ou cliente não existe
            System.err.println("Servidor não foi estabelecido ou cliente não existe.");
            return false;

        }

        if (clienteEscolha == 1) { //faz o cliente 1 ser cliente atual
            this.clienteAtual = cliente1;
            this.clienteConctado = 1;

            if (this.estabeleceComunicacao()) {
                return true;

            } else {
                System.err.println("Impossível estabelecer conexão com cliente 1");
                return false;

            }
        } else if (clienteEscolha == 2) { //faz o cliente 2 ser cliente atual
            this.clienteAtual = cliente2;
            this.clienteConctado = 2;

            if (this.estabeleceComunicacao()) {
                return true;

            } else {
                System.err.println("Impossível estabelecer conexão com cliente 2");
                return false;

            }
        }
        System.err.println("Impossível estabelecer conexão com cliente " + clienteEscolha);
        return false;

    }

    private boolean enviarMensagem(String mensagem) {
        try {
            out.println(mensagem); //envia a mensagem para o cliente
            return true;

        } catch (Exception e) {
            System.err.println("Impossível enviar mensagem");
            return false;

        }
    }

    private String recebeMensagem() {
        String mensagem;

        try {
            mensagem = this.in.readLine();
            return mensagem;

        } catch (Exception e) {
            System.err.println("Impossível receber mensagem");
            return null;

        }
    }

    public String fazComunicacao(String mensagemEnviar, int numCliente, int maxTempo) {
        String mensagem;

        try {
            if (numCliente > qtdClientes()) {
                System.err.println("Cliente não existe");
                return null;

            }

            this.escolheCliente(numCliente); //escolhe o cliente
            this.estabeleceComunicacao(); //estabelece a comunicação

            mensagem = this.recebeMensagem(); //recebe mensagem do cliente
            this.enviarMensagem(mensagemEnviar); //envia mensagem
            return mensagem;

        } catch (Exception e) {
            System.err.println("Erro ao fazer comunicação com o cliente " + numCliente);
            return null;

        }
    }

    public boolean encerraConexao(int numCliente) { //encerra conexão com o cliente
        if (!isServidorEstabelecido()) { //se servidor não foi estabelecido
            return true;

        }

        try {
            switch (numCliente) {
                case 1:
                    this.cliente1.close();
                    this.cliente1 = null;
                    return true;
                case 2:
                    this.cliente2.close();
                    this.cliente2 = null;
                    return true;

            }

            return true;

        } catch (Exception e) {
            System.err.println("Impossível encerrar conexão");
            return false;

        }
    }

    public boolean encerraServidor() { //encerra o servidor
        try {
            servidor.close();
            servidor = null;
            this.servidorEstabelecido = false;
            return true;
            
        } catch (Exception ex) {
            System.err.println("Impossível encerrar servidor");
            return false;
            
        }
    }

}
