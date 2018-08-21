package modelo;

import java.util.ArrayList;

public class Cliente {

    private static int totalClientes;
    private int codCliente;
    private String CPF, email, nome, senha;
    private ArrayList<Endereco> enderecos;

    static {
        totalClientes = 0;
    }

    public Cliente(String CPF, String email, String nome, String senha, ArrayList<Endereco> enderecos) {
        this.CPF = CPF;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.enderecos = enderecos;
        codCliente = totalClientes;
        totalClientes++;
    }

    public Cliente() {
        this.enderecos = new ArrayList<Endereco>();
    }

    public static int getTotalClientes() {
        return totalClientes;
    }

    public static void setTotalClientes(int totalClientes) {
        Cliente.totalClientes = totalClientes;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

}
