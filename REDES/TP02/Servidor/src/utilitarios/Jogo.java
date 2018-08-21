
package utilitarios;

public class Jogo {
    private String nomeJogador1;
    private String nomeJogador2;
    private int ptsJogador1;
    private int ptsJogador2;
    private int jogadas;

    public Jogo(String nomeJogador1, String nomeJogador2) {
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.ptsJogador1 = 0;
        this.ptsJogador2 = 0;
        this.jogadas = 0;
        
    }

    public String getNomeJogador1() {
        return nomeJogador1;
    }

    public String getNomeJogador2() {
        return nomeJogador2;
    }

    public int getPtsJogador1() {
        return ptsJogador1;
    }

    public int getPtsJogador2() {
        return ptsJogador2;
    }

    public int getJogadas() {
        return jogadas;
    }

    public boolean isJogoTerminado() {
        if (ptsJogador1 >= 3 || ptsJogador2 >= 3) {
            return true;
        }
        return false;
    }
    
    public int vencedor(){
        if (this.isJogoTerminado()) {
            if (this.ptsJogador1 == 3) {
                return 1;
            }else{
                return 2;
            }
        }
        return -1;
    }
    
    
    
    
    private int resultadoJogada(ENUMJogadas jogadaJogador1, ENUMJogadas jogadaJogador2){
        if (jogadaJogador1 == ENUMJogadas.PEDRA) {
            if (jogadaJogador2 == ENUMJogadas.TESOURA) { //jogador 1 vence
                this.ptsJogador1++;
                return 1;
            }else if(jogadaJogador2 == ENUMJogadas.PAPEL){ // jogador 2 vence
                this.ptsJogador2++;
                return 2;
            }else{//empate
                return 0;
            }
        }
        
        if (jogadaJogador1 == ENUMJogadas.PAPEL) {
            if (jogadaJogador2 == ENUMJogadas.PEDRA) { //jogador 1 vence
                this.ptsJogador1++;
                return 1;
            }else if(jogadaJogador2 == ENUMJogadas.TESOURA){ // jogador 2 vence
                this.ptsJogador2++;
                return 2;
            }else{//empate
                return 0;
            }
        }
        
        if (jogadaJogador1 == ENUMJogadas.TESOURA) {
            if (jogadaJogador2 == ENUMJogadas.PAPEL) { //jogador 1 vence
                this.ptsJogador1++;
                return 1;
            }else if(jogadaJogador2 == ENUMJogadas.PEDRA){ // jogador 2 vence
                this.ptsJogador2++;
                return 2;
            }else{//empate
                return 0;
            }
        }
        return -1;
        
    }
    
    public int jogada(ENUMJogadas jogadaJogador1, ENUMJogadas jogadaJogador2){
        
        if (this.ptsJogador1 >= 3 || this.ptsJogador2 >=3) { //partida terminou
            return -1;
            
        }else{
            this.jogadas++;
            return this.resultadoJogada(jogadaJogador1, jogadaJogador2);
            
            }
        }

    public int jogada(int jogada1, int jogada2) {
        ENUMJogadas jogadaJogador1, jogadaJogador2;
        
        
        if (this.ptsJogador1 >= 3 || this.ptsJogador2 >=3) { //partida terminou
            return -1;
            
        }else{
            this.jogadas++;
            
            switch(jogada1){
                case 1: jogadaJogador1 = ENUMJogadas.PEDRA;
                    break;
                case 2: jogadaJogador1 = ENUMJogadas.PAPEL;
                    break;
                case 3:jogadaJogador1 = ENUMJogadas.TESOURA;
                    break;
                    default:
                        jogadaJogador1 = ENUMJogadas.PEDRA;
            }
            
            switch(jogada2){
                case 1: jogadaJogador2 = ENUMJogadas.PEDRA;
                    break;
                case 2: jogadaJogador2 = ENUMJogadas.PAPEL;
                    break;
                case 3:jogadaJogador2 = ENUMJogadas.TESOURA;
                    break;
                    default:
                        jogadaJogador2 = ENUMJogadas.PEDRA;
            }
            
            return this.resultadoJogada(jogadaJogador1, jogadaJogador2);
            
            }
        
    }
    
    
}
