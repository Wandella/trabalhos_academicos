
package utilitarios;

public enum ENUMJogadas {
    PEDRA(1), PAPEL(2), TESOURA(3);
    
    private int valorJogada;

    private ENUMJogadas(int valor) {
        valorJogada = valor;
    }
    
    public ENUMJogadas getTipo(int num){
        switch(num){
            case 1: return ENUMJogadas.PEDRA;
            case 2: return ENUMJogadas.PAPEL;
            case 3: return ENUMJogadas.TESOURA;
        }
        return null;
    }
    
    
}
