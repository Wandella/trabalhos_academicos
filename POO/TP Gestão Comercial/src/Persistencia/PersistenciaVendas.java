package Persistencia;

import java.util.ArrayList;
import modelo.*;

public class PersistenciaVendas {

    private static ArrayList<Venda> listaVenda;

    static {
        listaVenda = new ArrayList<Venda>();
    }

    public void insereVenda(Venda venda) {
        this.listaVenda.add(venda);
    }

    public Venda buscaVenda(Venda venda) {
        for (Venda v : listaVenda) {
            if (v == venda) {
                return v;
            }
        }
        return null;
    }

    public Venda buscaVenda(int cod) {
        for (Venda v : listaVenda) {
            if (v.getCodVenda() == cod) {
                return v;
            }
        }
        return null;
    }

    public boolean removeVenda(int cod) {
        for (Venda v : listaVenda) {
            if (v.getCodVenda() == cod) {
                listaVenda.remove(v);
                return true;
            }
        }
        return false;
    }

    public int posicaoVenda(int codVenda) {
        int i = 0;
        for (Venda u : this.listaVenda) {
            if (u.getCodVenda() == codVenda) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean alteraVenda(Venda venda) {
        int posicao = posicaoVenda(venda.getCodVenda());
        if (posicao != -1) {
            listaVenda.set(posicao, venda).setCliente(venda.getCliente());
            listaVenda.set(posicao, venda).setProdutos(venda.getProdutos());
            listaVenda.set(posicao, venda).setStatusVenda(venda.getStatusVenda());
            return true;
        }
        return false;
    }

    public boolean alteraStatus(int cod, StatusVenda status) {
        int posicao = posicaoVenda(cod);
        Venda venda = buscaVenda(cod);
        if (venda != null) {
            venda.setStatusVenda(status);
            listaVenda.set(posicao, venda).setStatusVenda(status);
            return true;
        }
        return false;
    }

    public ArrayList<Venda> listaVendas() {
        return listaVenda;
    }
}
