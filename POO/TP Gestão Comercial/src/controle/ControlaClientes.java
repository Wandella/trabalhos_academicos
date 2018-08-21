package controle;

import Persistencia.PersistenciaClientes;
import java.util.ArrayList;
import modelo.*;

public class ControlaClientes {

    PersistenciaClientes persistenciaClientes = new PersistenciaClientes();
    static ArrayList<Endereco> listaEndereco = new ArrayList<>();

    public boolean removeCliente(Cliente cliente) {
        cliente = buscaCliente(cliente.getCPF());
        return this.persistenciaClientes.removeCliente(cliente);
    }

    public Cliente buscaCliente(String CPF) {
        return persistenciaClientes.buscaCliente(CPF);
    }

    public boolean alteraCliente(String CPF, String email, String nome, String senha, ArrayList<Endereco> enderecos) {
        Cliente Clientealt = new Cliente(); // cliente auxiliar para pegar dados novos do cliente

        //CRIA CLIENTE COM OS CAMPOS NOVOS E MANDA O CLIENTE"novo" PRO ALTERA CLIENTE
        Clientealt.setEnderecos(enderecos);
        Clientealt.setCPF(CPF);
        Clientealt.setEmail(email);
        Clientealt.setNome(nome);
        Clientealt.setSenha(senha);

        return persistenciaClientes.alteraCliente(Clientealt);
    }

    public ArrayList<Cliente> listaClientes() {
        ArrayList<Cliente> copiaLista = persistenciaClientes.lista();
        return copiaLista;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if (persistenciaClientes.buscaCliente(cliente.getCPF()) != null) {
            return false;
        } else {
            persistenciaClientes.insereCliente(cliente);
        }
        return true;
    }

    public boolean insereEndereco(Endereco end) {
        System.out.println("Insere Endereco");
        return listaEndereco.add(end);
    }

    public ArrayList<Endereco> listaEnderecos() {
        ArrayList<Endereco> lista = (ArrayList<Endereco>) listaEndereco.clone();
        return lista;
    }

    public String[] retornaEnderecos() {
        String[] enderecos = new String[listaEndereco.size()];

        for (int i = 0; i < listaEndereco.size(); i++) {
            enderecos[i] = listaEndereco.get(i).getLagradouro()
                    + ", n. " + listaEndereco.get(i).getNumero()
                    + ", " + listaEndereco.get(i).getBairro()
                    + ", " + listaEndereco.get(i).getCidade();
        }
        return enderecos;
    }

    public String[] retornaClientes() {
        ArrayList<Cliente> listaClientes = persistenciaClientes.lista();
        String[] clientes = new String[listaClientes.size()];

        for (int i = 0; i < listaClientes.size(); i++) {
            clientes[i] = listaClientes.get(i).getCPF() + " - " + listaClientes.get(i).getNome();
        }
        return clientes;
    }

    public String[] retornaClientes(String nome) {
        ArrayList<Cliente> listaClientes = persistenciaClientes.lista();

        ArrayList<Cliente> listaAux = new ArrayList<>();
        for (int i = 0; i < listaClientes.size(); i++) {
            String n = listaClientes.get(i).getNome().toUpperCase();
            int result = n.indexOf(nome.toUpperCase());
            if (result != -1) {
                listaAux.add(listaClientes.get(i));
            }
        }
        String[] clientes = new String[listaAux.size()];
        for (int i = 0; i < listaAux.size(); i++) {
            String n = listaAux.get(i).getNome().toUpperCase();
            int result = n.indexOf(nome.toUpperCase());
            if (result != -1) {
                clientes[i] = listaAux.get(i).getCPF() + " - " + listaAux.get(i).getNome();
            }
        }
        return clientes;
    }

    public ArrayList<Cliente> retornaListaClientesSalvos() {
        return persistenciaClientes.lista();
    }

    public void limpaListaEnderecos() {
        listaEndereco.clear();
    }
}
