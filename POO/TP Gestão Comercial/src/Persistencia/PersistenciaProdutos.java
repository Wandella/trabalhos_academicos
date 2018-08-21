package Persistencia;

import java.util.ArrayList;
import modelo.*;

public class PersistenciaProdutos {

    private static ArrayList<Produto> listaProduto;

    static {
        listaProduto = new ArrayList<Produto>();
    }

    public void insereProduto(Produto produto) {
        this.listaProduto.add(produto);

    }

    public boolean alteraProduto(Produto produto) {
        int posicao = posicaoProduto(produto);
        if (posicao != 1) {
            System.out.println("Posicao " + posicao);
            this.listaProduto.set(posicao, produto);
            return true;
        }
        return false;
    }

    public boolean alteraEstoque(Produto produto, int qtdNova) {
        int posicao = posicaoProduto(produto);
        if (posicao != -1) {
            produto.setQtdEstoque(qtdNova);
            this.listaProduto.set(posicao, produto);
            return true;
        }
        return false;
    }

    public int posicaoProduto(Produto produto) {
        int i = 0;
        for (Produto u : this.listaProduto) {
            if (u.getCod() == produto.getCod()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean removeProduto(int cod) {
        for (Produto u : listaProduto) {
            if (u.getCod() == cod) {
                listaProduto.remove(u);
                return true;
            }
        }
        return false;
    }

    public boolean buscaProduto(int cod) {
        for (Produto u : this.listaProduto) {
            if (u.getCod() == cod) {
                return true;
            }
        }
        return false;
    }

    public Produto buscaProduto(Produto produto) {
        for (Produto u : this.listaProduto) {
            if (u.getCod() == produto.getCod()) {
                return u;
            }
        }
        return null;
    }

    public Produto buscaProdutoCod(int cod) {
        for (Produto u : this.listaProduto) {
            if (u.getCod() == cod) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Produto> litaProdutos() {
        ArrayList<Produto> lista = new ArrayList<Produto>();
        for (Produto u : this.listaProduto) {
            lista.add(u);
        }
        return lista;
    }

}
