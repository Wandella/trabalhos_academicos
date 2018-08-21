package utilitarios;


import java.util.Calendar;

public class ChecaTempo {
    Calendar hora;

    public ChecaTempo() {
    }
    
    
    public void conexaoRealizada(int segundos){
        hora = hora.getInstance();
        hora.add(Calendar.SECOND, segundos);
    }
    
    public void terminaConexao(){
        hora = null;
    }
    
    public boolean checaConexao(){
        if (hora == null) {
            return true;
        }
        if (hora.compareTo(Calendar.getInstance()) > 0) { //Dentro do prazo
            return true;
            
        }
        return false; //Fora do prazo
        
    }
    
        public void pausa(int segundos){
        ChecaTempo timer = new ChecaTempo();
        timer.conexaoRealizada(segundos);
        
        while(timer.checaConexao()){
            
        }
    }
    
}
