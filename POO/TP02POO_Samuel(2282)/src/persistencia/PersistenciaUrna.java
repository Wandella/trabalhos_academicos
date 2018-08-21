package persistencia;

import java.util.ArrayList;
import modelo.*;

public class PersistenciaUrna {
    private static ArrayList<Candidato> listaCandidatosPrefeito  = new ArrayList<>();
    private static ArrayList<Candidato> listaCandidatosVereador  = new ArrayList<>();
    private static ArrayList<Eleitor> listaEleitores  = new ArrayList<>();
    private static ArrayList<UrnaEletronica> listaUrnas  = new ArrayList<>();
    private static int votosBrancosPrefeito;
    private static int votosNulosPrefeito;
    private static int votosValidosPrefeito;
    private static int votosBrancosVereador;
    private static int votosNulosVereador;
    private static int votosValidosVereador;

    public PersistenciaUrna() {
        
    }
    
    /*candidato*/
    public static boolean recebeCandidato(Candidato candidato){
        System.out.println("Candidato a " + candidato.getCargo() + "  -  " + candidato.getNome());
        if (candidato.getCargo() == CargoPretendido.PREFEITO) {
            listaCandidatosPrefeito.add(candidato);
            return true;
        }
        if (candidato.getCargo() == CargoPretendido.VEREADOR) {
            listaCandidatosVereador.add(candidato);
            return true;
        }
        return false;
    }
    
    public Candidato buscaCandidatoPrefeito(int numero){
        for (Candidato u: listaCandidatosPrefeito) {
            if (u.getNumero() == numero) {
                return u;
            }
        }
        return null;
    }
    
    public Candidato buscaCandidatoVerCandidato(int numero){
        for (Candidato u: listaCandidatosVereador) {
            if (u.getNumero() == numero) {
                return u;
            }
        }
        return null;
    }
    
    public void recebeVotoPrefeito(int numero){
        Candidato c = null;
        if (numero == 0) {
            System.out.println("PERSISTENCIA:: votou branco");
            this.votosBrancosPrefeito++;
            return;
        }
        c = buscaCandidatoPrefeito(numero);
        if (c != null) {
            System.out.println("PERSISTENCIA:: votou " + c.getNome());
            c.recebeVoto();
            this.votosValidosPrefeito++;
        }else{
            System.out.println("PERSISTENCIA:: votou nulo");
            this.votosNulosPrefeito++;
        }
    }
    
    public void recebeVotoVereador(int numero){
        Candidato c = null;
        if (numero == 0) {
            this.votosBrancosVereador++;
            System.out.println("PERSISTENCIA:: votou branco");
            return;
        }
        c = buscaCandidatoVerCandidato(numero);
        if (c != null) {
            c.recebeVoto();
            System.out.println("PERSISTENCIA:: votou " + c.getNome());
            this.votosValidosVereador++;
        }else{
            this.votosNulosVereador++;
            System.out.println("PERSISTENCIA:: votou nulo");
        }
    }
    
    public ArrayList<Candidato> getListaCandidatosPrefeito(){
        return (ArrayList<Candidato>) listaCandidatosPrefeito.clone();
    }
    
    public ArrayList<Candidato> getListaCandidatosVereador(){
        return (ArrayList<Candidato>) listaCandidatosVereador.clone();
    }
    
    /*eleitor*/
    public void recebeEleitor(Eleitor eleitor){
        System.out.println("inserindo " + eleitor.getNome() + " - (" + eleitor.getTitulo() + ") z: " + eleitor.getZonaEleitoral());
        this.listaEleitores.add(eleitor);
        System.out.println("PERSISTENCIA:: inseriu eleitor " + listaEleitores.get(listaEleitores.size()-1).getNome());
    }
    
    public Eleitor buscaEleitor(String titulo){
        for (Eleitor e: listaEleitores) {
            if (e.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("PERSISTENCIA:: Eleitor encontrado: " + e.getNome());
                return e;
            }
        }
        System.out.println("PERSISTENCIA:: Eleitor nao encontrado");
        return null;
    }
    
    public boolean eleitorVotaPrefeito(String titulo){
        Eleitor e = buscaEleitor(titulo);
        return e.votarPrefeito();
    }
    
    public boolean eleitorVotaVereador(String titulo){
        Eleitor e = buscaEleitor(titulo);
        return e.votarVereador();
    }
    
    public boolean eleitorJaVotouPrefeito(String titulo){
        Eleitor e = buscaEleitor(titulo);
        return e.jaVotouPrefeito();
    }
    
    public boolean eleitorJaVotouVereador(String titulo){
        Eleitor e = buscaEleitor(titulo);
        return e.jaVotouVereador();
    }
    
    public ArrayList<Eleitor> getListaEleitores(){
        return (ArrayList<Eleitor>) listaEleitores.clone();
    }

    public int getVotosBrancosPrefeito() {
        return votosBrancosPrefeito;
    }
    
    public int getVotosBrancosVereador() {
        return votosBrancosVereador;
    }

    public int getVotosNulosPrefeito() {
        return votosNulosPrefeito;
    }
    
    public int getVotosNulosVereador() {
        return votosNulosVereador;
    }

    public int getVotosValidosPrefeito() {
        return votosValidosPrefeito;
    }
    
    public int getVotosValidosVereador() {
        return votosValidosVereador;
    }

    public void inicializaUrna(int zona) {
        UrnaEletronica e = new UrnaEletronica(zona);
        System.out.println("PERSISTENCIA:: inicializou " + e.getZonaEleitoral());
        this.listaUrnas.add(e);
    }

    public UrnaEletronica buscaUrna(int zona) {
        for (UrnaEletronica u: listaUrnas) {
            if (u.getZonaEleitoral() == zona) {
                return u;
            }
        }
        return null;
    }
    
    public int qtdUrnas(){
        return this.listaUrnas.size();
    }
    
    public ArrayList<UrnaEletronica> getListaUrnas(){
        return (ArrayList<UrnaEletronica>) this.listaUrnas.clone();
    }
}
