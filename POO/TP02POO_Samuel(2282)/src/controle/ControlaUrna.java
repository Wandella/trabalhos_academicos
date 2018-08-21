package controle;

import java.util.ArrayList;
import java.util.Comparator;
import persistencia.PersistenciaUrna;
import modelo.*;
import persistencia.Arquivo;

public class ControlaUrna {
    PersistenciaUrna pUrna;
    Arquivo arquivo;
    public ControlaUrna() {
        this.pUrna = new PersistenciaUrna();
        this.arquivo = new Arquivo();
    }
    
    public boolean carregaDados(String arquivoCandidatos, String arquivoEleitore){//carrega arquivos
        if(arquivo.lerArquivoCandidato(arquivoCandidatos) && arquivo.lerArquivoEleitor(arquivoEleitore)){
            return true;
        }
        return false;
    }
    
    public boolean inicializaUrna(int zona){
        System.out.println("Inicializando " + zona);
        if(pUrna.buscaUrna(zona) == null){
            pUrna.inicializaUrna(zona);
            System.out.println("Inicializada");
            return true;
        }
        System.out.println("Nao inicializada");
        return false;
        
    }
    
    public int qtdUrnas(){
        return pUrna.qtdUrnas();
    }
    
    public boolean liberaEleicoes(){
        UrnaEletronica urna = new UrnaEletronica(0);
        if (pUrna.qtdUrnas() > 0) {
            urna.liberarEleição();
            return true;
        }
        return false;
    }
    
    public Eleitor buscaEleitor(String titulo){
        return pUrna.buscaEleitor(titulo);
    }
    
    public boolean isVotacaoLiberada(){
        UrnaEletronica u = new UrnaEletronica(0);
        return u.isLiberadoParaVotar();
    }
    
    public boolean liberaVotoPrefeito(String titulo, int zona){
        Eleitor e = pUrna.buscaEleitor(titulo);
        if (!this.isVotacaoLiberada()) {
            return false;
        }
        if (e == null) {
            System.out.println("Não existe eleitor " + titulo);
            return false;
        }
        //System.out.println(e.getZonaEleitoral() +" - " + zona);
        if (e.getZonaEleitoral() == zona) {
            System.out.println("Voto liberado");
            return !pUrna.eleitorJaVotouPrefeito(titulo);
        }
        System.out.println("Voto nao liberado");
        return false; 
    }
    
    public boolean liberaVotoVereador(String titulo, int zona){
        Eleitor e = pUrna.buscaEleitor(titulo);
        if (!this.isVotacaoLiberada()) {
            return false;
        }
        
        System.out.println(e.getZonaEleitoral() +" - " + zona);
        if (e.getZonaEleitoral() == zona) {
            System.out.println("Voto liberado");
            return !pUrna.eleitorJaVotouVereador(titulo);
        }
        System.out.println("Voto nao liberado");
        return false; 
    }
    
    public boolean recebeVotoPrefeito(int numeroCandidato, String titulo, int zona){
        UrnaEletronica urna = new UrnaEletronica(zona);
        if (!urna.isLiberadoParaVotar()) {
            System.out.println("Eleições não liberadas");
            return false;
        }
        if (this.liberaVotoPrefeito(titulo, zona)) { //eleitor pode votar
            System.out.println("Pode votar");
            pUrna.recebeVotoPrefeito(numeroCandidato);
            pUrna.eleitorVotaPrefeito(titulo);
            return true;
        }
        System.out.println("Nao pode votar");
        return false;
    }
    
    public boolean recebeVotoVereador(int numeroCandidato, String titulo, int zona){
        UrnaEletronica urna = new UrnaEletronica(zona);
        if (!urna.isLiberadoParaVotar()) {
            System.out.println("Eleições não liberadas");
            return false;
        }
        if (this.liberaVotoVereador(titulo, zona)) { //eleitor pode votar
            System.out.println("Pode votar");
            pUrna.recebeVotoVereador(numeroCandidato);
            pUrna.eleitorVotaVereador(titulo);
            return true;
        }
        System.out.println("Nao pode votar");
        return false;
    }
    
    public void encerraVotacao(UrnaEletronica urna){
        ArrayList<Candidato> listaCandidatosPrefeito = pUrna.getListaCandidatosPrefeito();
        ArrayList<Candidato> listaCandidatosVereador = pUrna.getListaCandidatosVereador();
        Comparator<Candidato> cmprtr = new Comparator<Candidato>() {
            @Override
            public int compare(Candidato t, Candidato t1) {
                if (t.getVotosRecebidos()> t1.getVotosRecebidos()) {
                    return -1;
                }
                if (t.getVotosRecebidos()< t1.getVotosRecebidos()) {
                    return 1;
                }
                return 0;
            }
        };
        String nome, partido;
        int numero, votosRecebidos;
        int vBrancosPrefeito = pUrna.getVotosBrancosPrefeito();
        int vNulosPrefeito = pUrna.getVotosNulosPrefeito();
        int vValidosPrefeito = pUrna.getVotosValidosPrefeito();
        int totalVotosPrefeito = vBrancosPrefeito + vNulosPrefeito + vValidosPrefeito;
        double porcentagemPrefeito = 0;
        int vBrancosVereador = pUrna.getVotosBrancosVereador();
        int vNulosVereador = pUrna.getVotosNulosVereador();
        int vValidosVereador = pUrna.getVotosValidosVereador();
        int totalVotosVereador = vBrancosVereador + vNulosVereador + vValidosVereador;
        double porcentagemVereador = 0;
        int cont = 0;
        System.out.println("");
        System.out.println("Resultado das Eleições");
        System.out.println("---------------------------------------------------");
        System.out.println("---- PREFEITO ------");
        System.out.println("Votos Brancos: " + vBrancosPrefeito);
        System.out.println("Votos Nulos: " + vNulosPrefeito);
        System.out.println("Total de votos: " + totalVotosPrefeito);
        System.out.println("Total de votos válidos: " + vValidosPrefeito);
        arquivo.limpaArquivo("resultado.txt");
        arquivo.escreveNoArquivo("resultado.txt", "RELATÓRIO DAS ELEIÇÕES\r\n\r\n");
        
        arquivo.escreveNoArquivo("resultado.txt", "Resultado para PREFEITO:\r\n");
        arquivo.escreveNoArquivo("resultado.txt", "Brancos: "+vBrancosPrefeito+"\r\n");
        arquivo.escreveNoArquivo("resultado.txt", "Nulos:   "+vNulosPrefeito+"\r\n");
        arquivo.escreveNoArquivo("resultado.txt", "Válidos: "+vValidosPrefeito+"\r\n");
        arquivo.escreveNoArquivo("resultado.txt", "TOTAL:   "+totalVotosPrefeito+"\r\n");
        arquivo.escreveNoArquivo("resultado.txt", "---------------CANDIDATOS---------------\r\n");
        listaCandidatosPrefeito.sort(cmprtr);
        for (Candidato u: listaCandidatosPrefeito) {
            cont++;
            nome = u.getNome();
            numero = u.getNumero();
            partido = u.getPartido();
            votosRecebidos = u.getVotosRecebidos();
            if(vValidosPrefeito != 0) porcentagemPrefeito = (double)votosRecebidos/vValidosPrefeito * 100;
            System.out.println("Candidato: " + nome + numero + "-" + "("+partido+"): " + votosRecebidos + "(" + porcentagemPrefeito + "%)");
            arquivo.escreveNoArquivo("resultado.txt", cont + "° - " + nome + numero + "-" + "("+partido+"): " + votosRecebidos + "(" + porcentagemPrefeito + "%)"+"\r\n");
        }
        arquivo.escreveNoArquivo("resultado.txt", "\r\n___________________________________________________________\r\n");
        
        arquivo.escreveNoArquivo("resultado.txt", "Resultado para VEREADOR:\r\n");
        arquivo.escreveNoArquivo("resultado.txt", "Brancos: "+vBrancosVereador+"\r\n");
        arquivo.escreveNoArquivo("resultado.txt", "Nulos:   "+vNulosVereador+"\r\n");
        arquivo.escreveNoArquivo("resultado.txt", "Válidos: "+vValidosVereador+"\r\n");
        arquivo.escreveNoArquivo("resultado.txt", "TOTAL:   "+totalVotosVereador+"\r\n");
        arquivo.escreveNoArquivo("resultado.txt", "---------------CANDIDATOS---------------\r\n");
        
        System.out.println("---- VEREADOR ------");
        System.out.println("Votos Brancos: " + vBrancosVereador);
        System.out.println("Votos Nulos: " + vNulosVereador);
        System.out.println("Total de votos: " + totalVotosVereador);
        System.out.println("Total de votos válidos: " + vValidosVereador);
        
        listaCandidatosVereador.sort(cmprtr);
        cont = 0;
        for (Candidato u: listaCandidatosVereador) {
            cont++;
            nome = u.getNome();
            numero = u.getNumero();
            partido = u.getPartido();
            votosRecebidos = u.getVotosRecebidos();
            if(vValidosVereador != 0)porcentagemVereador = (double)votosRecebidos/vValidosVereador * 100;
            System.out.println("Candidato: " + numero + "-" + nome + "("+partido+"): " + votosRecebidos + "(" + porcentagemVereador + "%)");
            arquivo.escreveNoArquivo("resultado.txt", cont + "° - " + nome + numero + "-" + "("+partido+"): " + votosRecebidos + "(" + porcentagemVereador + "%)"+"\r\n");
        }
        urna.encerrarEleição();
    }
    
    public void listaEleitores(){
        ArrayList<Eleitor> lista = pUrna.getListaEleitores();
        System.out.println("Printando Eleitores");
        System.out.println("TAM: " + lista.size());
        for (Eleitor u: lista) {
            System.out.println(u.getNome()+ " - "+u.getTitulo() + " - z:" + u.getZonaEleitoral());
        }
    }
    
    public Candidato buscaCandidatoPrefeito(int numero){
        return pUrna.buscaCandidatoPrefeito(numero);
    }
    
    public Candidato buscaCandidatoVereador(int numero){
        return pUrna.buscaCandidatoVerCandidato(numero);
    }
    
    public String[] listaUrnas(){
        Comparator<UrnaEletronica> cmprtr = new Comparator<UrnaEletronica>() {
            @Override
            public int compare(UrnaEletronica t, UrnaEletronica t1) {
                if (t.getZonaEleitoral()< t1.getZonaEleitoral()) {
                    return -1;
                }
                if (t.getZonaEleitoral()> t1.getZonaEleitoral()) {
                    return 1;
                }
                return 0;
            }
        };
        ArrayList<UrnaEletronica> lista = pUrna.getListaUrnas();
        lista.sort(cmprtr);
        String a[] = new String[lista.size()];
        
        for (int i = 0; i < lista.size(); i++) {
            a[i] = Integer.toString(lista.get(i).getZonaEleitoral());
        }
        return a;
    }
}
