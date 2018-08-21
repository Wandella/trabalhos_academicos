package Persistencia;

import java.util.ArrayList;
import modelo.Usuario;

public class PersistenciaUsuario {
    
    private static ArrayList<Usuario> listaUsuarios;
    
    static{
        listaUsuarios = new ArrayList<Usuario>();
    }
    
    public boolean buscaUsuario(String CPF){
        for(Usuario u : listaUsuarios){
            if(u.getCpf().equals(CPF)){
                System.out.println("Achou!");
                return true;
            }
        }
        System.out.println("Não achou");
        return false;
    }
    
    public void insereUsuario(Usuario usuario){
        listaUsuarios.add(usuario);
    }
    
    public boolean removerUsuario(Usuario usuario){
        return listaUsuarios.remove(usuario);
    }
    
    public String[] listaNomeECPF(){ //retorna vetor com os nomes que estarão na lista
        String a[] = new String[listaUsuarios.size()];
        int i=0;
        for(Usuario u : listaUsuarios){
            a[i] = u.getCpf()+" - " + u.getNome();
            i++;
        }
        return a;
    }
    
    public Usuario retornaUsuario(int posicao){
        return listaUsuarios.get(posicao);
    }

    public Usuario pesquisaUsuario(String CPF) {
        for(Usuario u : listaUsuarios){
            if(u.getCpf().equals(CPF)){
                System.out.println("Achou!");
                return u;
            }
        }
        return null;
    }
    
    public int retornaPosUsuario(String CPF){
        int i=0;
        for(Usuario u : listaUsuarios){
            if (u.getCpf().equals(CPF)) {
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public void alteraDados(int posicao, Usuario usuario){
        listaUsuarios.set(posicao, usuario);
    }
    
    public void removeUsuario(Usuario usuario){
        listaUsuarios.remove(usuario);
    }
}
