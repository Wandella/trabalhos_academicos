package modelo;

import java.util.ArrayList;

public class UrnaEletronica {
    private int zonaEleitoral;
    /*private ArrayList<Candidato> candidatos;
    private ArrayList<Eleitor> eleitores;*/
    private static boolean liberadoParaVotar;
    
    public UrnaEletronica(int zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    public static boolean isLiberadoParaVotar() {
        return liberadoParaVotar;
    }

    public static void liberarEleição() {
        UrnaEletronica.liberadoParaVotar = true;
    }
    
    public static void encerrarEleição() {
        UrnaEletronica.liberadoParaVotar = false;
    }
    
    public int getZonaEleitoral() {
        return zonaEleitoral;
    }

    public void setZonaEleitoral(int zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    /*public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(ArrayList<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public ArrayList<Eleitor> getEleitores() {
        return eleitores;
    }

    public void setEleitores(ArrayList<Eleitor> eleitores) {
        this.eleitores = eleitores;
    }*/
    
    
}
