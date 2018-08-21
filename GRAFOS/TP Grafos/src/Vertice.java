

public class Vertice {
    private int rotulo;
    private int grau;
    private boolean marcado;
    
    public Vertice(int rotulo, int grau) {
        this.rotulo = rotulo;
        this.grau = grau;
    }

    
    
    public int getRotulo() {
        return rotulo;
    }

    public int getGrau() {
        return grau;
    }
        
    public void marcaVertice(){
        this.marcado = true;
    }
    
    public void desmarcaVertice(){
        this.marcado = false;
    }
}