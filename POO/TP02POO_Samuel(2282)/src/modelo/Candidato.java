package modelo;

public class Candidato {
    private String nome;
    private int numero;
    private String partido;
    private CargoPretendido cargo;
    int votosRecebidos;

    public Candidato(String nome, int numero, String partido, CargoPretendido cargo) {
        this.nome = nome;
        this.numero = numero;
        this.partido = partido;
        this.cargo = cargo;
        this.votosRecebidos = 0;
    }
    
    public void recebeVoto(){
        this.votosRecebidos++;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getPartido() {
        return partido;
    }

    public CargoPretendido getCargo() {
        return cargo;
    }

    public int getVotosRecebidos() {
        return votosRecebidos;
    }
    
    
    
}
