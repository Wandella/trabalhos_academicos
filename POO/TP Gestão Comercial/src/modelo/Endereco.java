package modelo;

public class Endereco {

    private String lagradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String CEP;

    public Endereco(String lagradouro, int numero, String bairro, String cidade, String CEP) {
        this.lagradouro = lagradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.CEP = CEP;
    }

    public Endereco() {

    }

    public String getLagradouro() {
        return lagradouro;
    }

    public void setLagradouro(String lagradouro) {
        this.lagradouro = lagradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

}
