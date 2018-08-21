package modelo;

public abstract class Produto {

    private static int totalProdutos;
    protected int cod;
    protected String descricao;
    protected int qtdEstoque;
    protected Categoria categoria;

    static {
        totalProdutos = 0;
    }

    public Produto(String descricao, int qtdEstoque, Categoria categoria) {
        this.totalProdutos++;
        this.cod = totalProdutos;
        this.descricao = descricao;
        this.qtdEstoque = qtdEstoque;
        this.categoria = categoria;
    }

    public Produto() {
        /*this.totalProdutos++;
        this.cod = totalProdutos;*/
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public static int getTotalProdutos() {
        return totalProdutos;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public static void setTotalProdutos(int totalProdutos) {
        Produto.totalProdutos = totalProdutos;
    }

}
