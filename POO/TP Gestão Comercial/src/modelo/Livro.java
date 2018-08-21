package modelo;

public class Livro extends Produto {

    private String titulo;
    private String autor;

    public Livro(String descricao, int qtdEstoque, Categoria categoria, String titulo, String autor) {
        super(descricao, qtdEstoque, categoria);
        this.autor = autor;
        this.titulo = titulo;
    }

    public Livro() {
        super();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void alteraTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void alteraAutor(String autor) {
        this.autor = autor;
    }
}
