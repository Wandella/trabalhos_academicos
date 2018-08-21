package modelo;

public enum TipoUsuario {
    CIDADAO("Cidadão"), ADMINISTRADOR("Administrador");
    private String nome;

    private TipoUsuario(String nome) {
        this.nome = nome;
    }
    
}
