package Persistencia;

import java.util.ArrayList;
import modelo.*;

public class PersistenciaClientes {

    private static ArrayList<Cliente> listaCliente;

    static {
        listaCliente = new ArrayList<Cliente>();
    }

    public boolean removeCliente(Cliente cliente) {
        return listaCliente.remove(cliente);
    }

    public int posicaoCliente(Cliente cliente) {
        int i = 0;
        for (Cliente u : this.listaCliente) {
            if (u.getCPF().equals(cliente.getCPF())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean alteraCliente(Cliente cliente) {
        int posicao = posicaoCliente(cliente);
        if (posicao != -1) {
            listaCliente.set(posicao, cliente).setNome(cliente.getNome());
            listaCliente.set(posicao, cliente).setEmail(cliente.getEmail());
            listaCliente.set(posicao, cliente).setSenha(cliente.getSenha());
            listaCliente.set(posicao, cliente).setEnderecos(cliente.getEnderecos());
            return true;
        }
        return false;
    }

    public Cliente buscaCliente(String CPF) {

        for (Cliente u : listaCliente) {
            if (u.getCPF().equals(CPF)) {
                System.out.println("--------------------------------------------Encontrado");
                return u;
            }
        }
        System.out.println("-------------------------------------------- Não Encontrado");
        return null;
    }

    public void insereCliente(Cliente cliente) {
        this.listaCliente.add(cliente);
    }

    public ArrayList<Cliente> lista() {
        return (ArrayList<Cliente>) this.listaCliente.clone();
    }
}
