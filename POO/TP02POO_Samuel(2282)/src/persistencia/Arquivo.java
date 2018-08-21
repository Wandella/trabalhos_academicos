package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.*;

public class Arquivo {

    private PersistenciaUrna pUrna;

    public Arquivo() {
        this.pUrna = new PersistenciaUrna();
    }

    public boolean lerArquivoCandidato(String nomeArq) {
        CargoPretendido cPretendido;
        FileReader fr = null;
        BufferedReader bf = null;
        try {
            fr = new FileReader(nomeArq);
            bf = new BufferedReader(fr);
            String line = "";
            String aux[] = new String[4];
            String nome;
            int numero;
            String partido;
            String cargo;
            while ((line = bf.readLine()) != null) {
                aux = line.split(";");
                System.out.println(aux[0]);
                nome = aux[0];
                numero = Integer.parseInt(aux[1].replaceAll(" ", ""));
                partido = aux[2].replaceAll(" ", "");
                cargo = aux[3].replaceAll(" ", "");
                if (cargo.equalsIgnoreCase("vereador")) {
                    cPretendido = CargoPretendido.VEREADOR;
                } else {
                    cPretendido = CargoPretendido.PREFEITO;
                }
                Candidato c = new Candidato(nome, numero, partido, cPretendido);
                pUrna.recebeCandidato(c);
            }

            bf.close();
            fr.close();
            return true;
        } catch (Exception e) {
            System.out.println("Arquivo não existe.");
            return false;
        }
    }

    public boolean lerArquivoEleitor(String nomeArq) {
        FileReader fr = null;
        BufferedReader bf = null;
        Eleitor eleitor;
        try {
            fr = new FileReader(nomeArq);
            bf = new BufferedReader(fr);
            String line = "";
            String aux[] = new String[4];
            String nome;
            String titulo;
            int zona;
            while ((line = bf.readLine()) != null) {
                aux = line.split(";");
                System.out.println(aux[0]);

                nome = aux[0];
                titulo = aux[1].replaceAll(" ", "");
                System.out.println(titulo);
                zona = Integer.parseInt(aux[2]);

                eleitor = new Eleitor(nome, titulo, zona);
                pUrna.recebeEleitor(eleitor);
            }

            bf.close();
            fr.close();
            return true;
        } catch (Exception e) {
            System.out.println("Arquivo não existe.");
            return false;
        }
    }

    public boolean escreveNoArquivo(String nomeArquivo, String texto) {
        try {
            FileWriter fw = new FileWriter(nomeArquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.close();
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean limpaArquivo(String nomeArquivo) {
        try {
            FileWriter fw = new FileWriter(nomeArquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
