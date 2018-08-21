package modelo;

public class PenDrive extends Produto {

    private int capacidade;

    public PenDrive(String descricao, int qtdEstoque, Categoria categoria, int capacidade) {
        super(descricao, qtdEstoque, categoria);
        this.capacidade = capacidade;
    }

    public PenDrive() {
        super();
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

}
