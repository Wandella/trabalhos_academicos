package modelo;

import java.util.ArrayList;

public class Venda {

    private static int totalVendas;
    private int codVenda;
    private Cliente cliente;
    private ArrayList<Produto> produtos;
    private StatusVenda statusVenda;

    static {
        totalVendas = 0;
    }

    public Venda(Cliente cliente, ArrayList<Produto> produtos, StatusVenda statusVenda) {
        totalVendas++;
        this.cliente = cliente;
        this.produtos = produtos;
        this.statusVenda = statusVenda;
        codVenda = totalVendas;
    }

    public Venda() {

    }

    public int getCodVenda() {
        return codVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public StatusVenda getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(StatusVenda statusVenda) {
        this.statusVenda = statusVenda;
    }

}
