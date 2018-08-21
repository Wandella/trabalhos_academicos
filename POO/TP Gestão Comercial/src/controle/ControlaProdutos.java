package controle;

import Persistencia.PersistenciaProdutos;
import java.util.ArrayList;
import modelo.*;

public class ControlaProdutos {

    PersistenciaProdutos persistenciaProdutos;

    public ControlaProdutos() {
        this.persistenciaProdutos = new PersistenciaProdutos();
    }

    public Produto buscarProduto(Produto produto) {
        return this.persistenciaProdutos.buscaProduto(produto);
    }

    public boolean alteraEstoque(Produto produto, int qtdAdicionar) {
        int qtdNova = produto.getQtdEstoque() + qtdAdicionar;

        if (qtdNova < 0) {
            return false;
        }
        int posicao = persistenciaProdutos.posicaoProduto(produto);
        if (posicao != -1) {
            produto.setQtdEstoque(qtdNova);
            System.out.println("COD: " + produto.getCod());

            return persistenciaProdutos.alteraProduto(produto);
        }
        return false;
    }

    public boolean alteraEstoque(int cod, int qtdAdicionar) {
        Produto produto = persistenciaProdutos.buscaProdutoCod(cod);
        int qtdNova = produto.getQtdEstoque() + qtdAdicionar;

        if (qtdNova < 0) {
            return false;
        }
        int posicao = persistenciaProdutos.posicaoProduto(produto);
        if (posicao != -1) {
            produto.setQtdEstoque(qtdNova);
            System.out.println("COD: " + produto.getCod());

            return persistenciaProdutos.alteraProduto(produto);
        }
        return false;
    }

    public void removeProdutos(Produto produto) {
        this.persistenciaProdutos.removeProduto(produto.getCod());
    }

    public boolean removeProdutos(int cod) {
        return this.persistenciaProdutos.removeProduto(cod);
    }

    public ArrayList<Produto> listaProdutos() {
        return persistenciaProdutos.litaProdutos();
    }

    public boolean cadastraProduto(Produto produto) {
        if (persistenciaProdutos.buscaProduto(produto.getCod())) {
            return false;
        } else {
            persistenciaProdutos.insereProduto(produto);
        }
        return true;

    }

    public Produto buscaProdutoCod(int cod) {
        return this.persistenciaProdutos.buscaProdutoCod(cod);
    }

    public boolean alteraProduto(Produto produto) {
        return persistenciaProdutos.alteraProduto(produto);
    }

    public void alteraCD(CD cd, String artista, String titulo) {
        cd.alteraArtista(artista);
        cd.alteraTitulo(titulo);

    }

    public void alteraDVD(DVD dvd, String titulo, String genero) {
        dvd.alteraGenero(genero);
        dvd.alteraTitulo(titulo);

    }

    public void alteraJogo(Jogos jogo, String titulo, String plataforma) {
        jogo.alteraPlataforma(plataforma);
        jogo.alteraTitulo(titulo);

    }

    public void alteraLivro(Livro livro, String titulo, String artista) {
        livro.alteraAutor(artista);
        livro.alteraTitulo(titulo);

    }

    public void alteraPenDrive(PenDrive penDrive, int capacidade) {
        penDrive.setCapacidade(capacidade);

    }

    public String[] retornaProdutos() {
        ArrayList<Produto> listadeProdutos = persistenciaProdutos.litaProdutos();
        String[] produtos = new String[listadeProdutos.size()];

        for (int i = 0; i < listadeProdutos.size(); i++) {
            /**/
            switch (listadeProdutos.get(i).getDescricao()) {
                case "CD":
                    CD cd = (CD) listadeProdutos.get(i);
                    produtos[i] = cd.getCod()
                            + "      - " + cd.getDescricao()
                            + "   Qtd. " + cd.getQtdEstoque()
                            + "    Título:" + cd.getTitulo()
                            + "    Artista:" + cd.getArtista();
                    break;
                case "DVD":
                    DVD dvd = (DVD) listadeProdutos.get(i);
                    produtos[i] = dvd.getCod()
                            + "      - " + dvd.getDescricao()
                            + "   Qtd. " + dvd.getQtdEstoque()
                            + "    Título:" + dvd.getTitulo()
                            + "    Gênero:" + dvd.getGenero();
                    break;
                case "Livro":
                    Livro livro = (Livro) listadeProdutos.get(i);
                    produtos[i] = livro.getCod()
                            + "      - " + livro.getDescricao()
                            + "   Qtd. " + livro.getQtdEstoque()
                            + "    Título:" + livro.getTitulo();
                    break;
                case "Jogos":
                    Jogos jogo = (Jogos) listadeProdutos.get(i);
                    produtos[i] = jogo.getCod()
                            + "      - " + jogo.getDescricao()
                            + "   Qtd. " + jogo.getQtdEstoque()
                            + "    Título:" + jogo.getTitulo()
                            + "    Plataforma:" + jogo.getPlataforma();
                    break;

                case "PenDrive":
                    PenDrive p = (PenDrive) listadeProdutos.get(i);
                    produtos[i] = p.getCod()
                            + "      - " + p.getDescricao()
                            + "   Qtd. " + p.getQtdEstoque()
                            + "    Capacidade:" + p.getCapacidade();
                    break;
            }

        }
        return produtos;
    }

    public String[] retornaProdutos(String descricao) {
        ArrayList<Produto> listadeProdutos = persistenciaProdutos.litaProdutos();
        String[] produtos = new String[listadeProdutos.size()];
        ArrayList<Produto> listaAux = new ArrayList<>();

        for (int i = 0; i < listadeProdutos.size(); i++) {
            String n = listadeProdutos.get(i).getDescricao().toUpperCase();
            int result = n.indexOf(descricao.toUpperCase());
            if (result != -1) {
                listaAux.add(listadeProdutos.get(i));
            }
        }
        for (int i = 0; i < listaAux.size(); i++) {

            switch (listaAux.get(i).getDescricao()) {
                case "CD":
                    CD cd = (CD) listaAux.get(i);
                    produtos[i] = cd.getCod()
                            + "      - " + cd.getDescricao()
                            + "   Qtd. " + cd.getQtdEstoque()
                            + "    Título:" + cd.getTitulo()
                            + "    Artista:" + cd.getArtista();
                    break;
                case "DVD":
                    DVD dvd = (DVD) listaAux.get(i);
                    produtos[i] = dvd.getCod()
                            + "      - " + dvd.getDescricao()
                            + "   Qtd. " + dvd.getQtdEstoque()
                            + "    Título:" + dvd.getTitulo()
                            + "    Gênero:" + dvd.getGenero();
                    break;
                case "Livro":
                    Livro livro = (Livro) listaAux.get(i);
                    produtos[i] = livro.getCod()
                            + "      - " + livro.getDescricao()
                            + "   Qtd. " + livro.getQtdEstoque()
                            + "    Título:" + livro.getTitulo();
                    break;
                case "Jogos":
                    Jogos jogo = (Jogos) listaAux.get(i);
                    produtos[i] = jogo.getCod()
                            + "      - " + jogo.getDescricao()
                            + "   Qtd. " + jogo.getQtdEstoque()
                            + "    Título:" + jogo.getTitulo()
                            + "    Plataforma:" + jogo.getPlataforma();
                    break;

                case "PenDrive":
                    PenDrive p = (PenDrive) listaAux.get(i);
                    produtos[i] = p.getCod()
                            + "      - " + p.getDescricao()
                            + "   Qtd. " + p.getQtdEstoque()
                            + "    Capacidade:" + p.getCapacidade();
                    break;
            }

        }
        return produtos;
    }

}
