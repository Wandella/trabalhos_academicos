package modelo;

public class CD extends Produto {

    private String artista;
    private String titulo;

    public CD(String descricao, int qtdEstoque, Categoria categoria, String artista, String titulo) {
        super(descricao, qtdEstoque, categoria);
        this.artista = artista;
        this.titulo = titulo;
    }

    public CD() {
        super();
    }

    public String getArtista() {
        return artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void alteraArtista(String artista) {
        this.artista = artista;
    }

    public void alteraTitulo(String titulo) {
        this.titulo = titulo;
    }

}
