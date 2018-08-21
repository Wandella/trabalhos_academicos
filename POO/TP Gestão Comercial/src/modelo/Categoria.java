package modelo;

public enum Categoria {
    MIDIA(0),
    GAMES(1),
    INFORMATICA(2),
    PAPELARIA(3);

    private int Categoria;

    Categoria(int cat) {
        this.Categoria = cat;
    }

    public int getCategoria() {
        return this.Categoria;
    }
}
