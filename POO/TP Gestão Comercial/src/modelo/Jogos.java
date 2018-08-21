package modelo;

public class Jogos extends Produto {

    private String titulo;
    private String plataforma;

    public Jogos(String descricao, int qtdEstoque, Categoria categoria, String titulo, String plataforma) {
        super(descricao, qtdEstoque, categoria);
        this.plataforma = plataforma;
        this.titulo = titulo;
    }

    public Jogos() {
        super();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void alteraTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void alteraPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
}
