package modelo;

public class DVD extends Produto {

    private String titulo;
    private String genero;

    public DVD(String descricao, int qtdEstoque, Categoria categoria, String titulo, String genero) {
        super(descricao, qtdEstoque, categoria);
        this.genero = genero;
        this.titulo = titulo;
        this.categoria = Categoria.MIDIA;
    }

    public DVD() {
        super();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void alteraTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void alteraGenero(String genero) {
        this.genero = genero;
    }
}
