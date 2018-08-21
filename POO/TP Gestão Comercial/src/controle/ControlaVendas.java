package controle;

import Persistencia.PersistenciaVendas;
import java.util.ArrayList;
import modelo.*;

public class ControlaVendas {

    private PersistenciaVendas persistenciaVendas = new PersistenciaVendas();
    private ControlaClientes controlaClientes = new ControlaClientes();
    private ControlaProdutos controlaProdutos = new ControlaProdutos();
    private static Cliente clienteVendaAtual;
    private static ArrayList<Produto> listadeProdutosCompra = new ArrayList<Produto>();

    public boolean alterarVenda(Venda venda) {
        if (persistenciaVendas.buscaVenda(venda) != null) {

            return persistenciaVendas.alteraVenda(venda);
        }
        return false;
    }

    public ArrayList<Venda> listaVendas() {
        return persistenciaVendas.listaVendas();
    }

    public boolean novaVenda(Venda venda) {
        if (persistenciaVendas.buscaVenda(venda) == venda) {
            return false;
        } else {
            persistenciaVendas.insereVenda(venda);
        }
        return true;
    }

    public Venda buscaVenda(Venda venda) {
        return persistenciaVendas.buscaVenda(venda);
    }

    public Venda buscaVenda(int cod) {
        return persistenciaVendas.buscaVenda(cod);
    }

    public void escolheClienteVenda(String CPF) {
        this.clienteVendaAtual = controlaClientes.buscaCliente(CPF);
    }

    public Cliente pegaClienteVenda() {
        return this.clienteVendaAtual;
    }

    public String[] retornaVendas() {
        ArrayList<Venda> listaVendas = persistenciaVendas.listaVendas();
        String[] vendas = new String[listaVendas.size()];

        for (int i = 0; i < listaVendas.size(); i++) {
            vendas[i] = listaVendas.get(i).getCodVenda()
                    + " - " + listaVendas.get(i).getCliente().getNome()
                    + " - " + listaVendas.get(i).getStatusVenda().toString();

        }
        return vendas;
    }

    public boolean alteraStatus(int cod, StatusVenda status) {
        return persistenciaVendas.alteraStatus(cod, status);
    }

    public boolean excluiVenda(int cod) {
        return persistenciaVendas.removeVenda(cod);
    }

    public boolean addProdutoLCompras(int cod, int qtd) {
        Produto produto = controlaProdutos.buscaProdutoCod(cod);
        if (produto.getQtdEstoque() >= qtd) {//se há estoque suficiente

            switch (produto.getDescricao()) {
                case "CD":
                    CD cd = (CD) produto;
                    CD cd1 = new CD();
                    cd1.setCod(cd.getCod());
                    cd1.setDescricao(cd.getDescricao());
                    cd1.setCategoria(cd.getCategoria());
                    cd1.setQtdEstoque(qtd);//usado para determinar a qtd comprada
                    cd1.alteraArtista(cd.getArtista());
                    cd1.alteraTitulo(cd.getTitulo());
                    this.listadeProdutosCompra.add(cd1);
                    break;
                case "DVD":
                    DVD dvd = (DVD) produto;
                    DVD dvd1 = new DVD();
                    dvd1.setCod(dvd.getCod());
                    dvd1.setDescricao(dvd.getDescricao());
                    dvd1.setCategoria(dvd.getCategoria());
                    dvd1.setQtdEstoque(qtd);//usado para determinar a qtd comprada
                    dvd1.alteraGenero(dvd.getGenero());
                    dvd1.alteraTitulo(dvd.getTitulo());
                    this.listadeProdutosCompra.add(dvd1);
                    break;
                case "Livro":
                    Livro livro = (Livro) produto;
                    Livro livro1 = new Livro();
                    livro1.setCod(livro.getCod());
                    livro1.setDescricao(livro.getDescricao());
                    livro1.setCategoria(livro.getCategoria());
                    livro1.setQtdEstoque(qtd);//usado para determinar a qtd comprada
                    livro1.alteraAutor(livro.getAutor());
                    livro1.alteraTitulo(livro.getTitulo());
                    this.listadeProdutosCompra.add(livro1);
                    break;
                case "PenDrive":
                    PenDrive pendrive = (PenDrive) produto;
                    PenDrive pendrive1 = new PenDrive();
                    pendrive1.setCod(pendrive.getCod());
                    pendrive1.setDescricao(pendrive.getDescricao());
                    pendrive1.setCategoria(pendrive.getCategoria());
                    pendrive1.setQtdEstoque(qtd);//usado para determinar a qtd comprada
                    pendrive1.setCapacidade(pendrive.getCapacidade());
                    this.listadeProdutosCompra.add(pendrive1);
                    break;
                case "Jogos":
                    Jogos jogo = (Jogos) produto;
                    Jogos jogo1 = new Jogos();
                    jogo1.setCod(jogo.getCod());
                    jogo1.setDescricao(jogo.getDescricao());
                    jogo1.setCategoria(jogo.getCategoria());
                    jogo1.setQtdEstoque(qtd);//usado para determinar a qtd comprada
                    jogo1.alteraPlataforma(jogo.getPlataforma());
                    jogo1.alteraTitulo(jogo.getTitulo());
                    this.listadeProdutosCompra.add(jogo1);
                    break;
            }
            return true;
        }
        return false;
    }

    public ArrayList<Produto> retornaListaPVendas() {
        return (ArrayList<Produto>) listadeProdutosCompra.clone();
    }

    public String[] retornaListaProdutosVenda() {

        String[] produtos = new String[listadeProdutosCompra.size()];

        for (int i = 0; i < listadeProdutosCompra.size(); i++) {

            switch (listadeProdutosCompra.get(i).getDescricao()) {
                case "CD":
                    CD cd = (CD) listadeProdutosCompra.get(i);
                    produtos[i] = cd.getCod()
                            + "      - " + cd.getDescricao()
                            + "   Qtd. " + cd.getQtdEstoque()
                            + "    Título:" + cd.getTitulo()
                            + "    Artista:" + cd.getArtista();
                    break;
                case "DVD":
                    DVD dvd = (DVD) listadeProdutosCompra.get(i);
                    produtos[i] = dvd.getCod()
                            + "      - " + dvd.getDescricao()
                            + "   Qtd. " + dvd.getQtdEstoque()
                            + "    Título:" + dvd.getTitulo()
                            + "    Gênero:" + dvd.getGenero();
                    break;
                case "Livro":
                    Livro livro = (Livro) listadeProdutosCompra.get(i);
                    produtos[i] = livro.getCod()
                            + "      - " + livro.getDescricao()
                            + "   Qtd. " + livro.getQtdEstoque()
                            + "    Título:" + livro.getTitulo();
                    break;
                case "Jogos":
                    Jogos jogo = (Jogos) listadeProdutosCompra.get(i);
                    produtos[i] = jogo.getCod()
                            + "      - " + jogo.getDescricao()
                            + "   Qtd. " + jogo.getQtdEstoque()
                            + "    Título:" + jogo.getTitulo()
                            + "    Plataforma:" + jogo.getPlataforma();
                    break;

                case "PenDrive":
                    PenDrive p = (PenDrive) listadeProdutosCompra.get(i);
                    produtos[i] = p.getCod()
                            + "      - " + p.getDescricao()
                            + "   Qtd. " + p.getQtdEstoque()
                            + "    Capacidade:" + p.getCapacidade();
                    break;
            }

        }
        return produtos;
    }

    public void finalizaVenda() {
        for (int i = 0; i < this.listadeProdutosCompra.size(); i++) {
            controlaProdutos.alteraEstoque(listadeProdutosCompra.get(i).getCod(), -listadeProdutosCompra.get(i).getQtdEstoque());
        }
        listadeProdutosCompra.clear();
        clienteVendaAtual = null;
    }
}
