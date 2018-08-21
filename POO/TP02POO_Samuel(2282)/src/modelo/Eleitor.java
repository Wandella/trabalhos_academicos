package modelo;

public class Eleitor {
    String nome;
    String titulo;
    int zonaEleitoral;
    boolean votouPrefeito;
    boolean votouVereador;

    public Eleitor(String nome, String titulo, int zonaEleitoral) {
        this.nome = nome;
        this.titulo = titulo;
        this.zonaEleitoral = zonaEleitoral;
        this.votouPrefeito = false;
        this.votouVereador = false;
    }
    
    public boolean jaVotouPrefeito(){
        return votouPrefeito;
    }
    
    public boolean jaVotouVereador(){
        return votouVereador;
    }
    
    public boolean votarPrefeito(){
        if (votouPrefeito) {
            return false;
        }
        votouPrefeito = true;
        return true;
    }
    
    public boolean votarVereador(){
        if (votouVereador) {
            return false;
        }
        votouVereador = true;
        return true;
    }

    public String getNome() {
        return nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getZonaEleitoral() {
        return zonaEleitoral;
    }

    
}
