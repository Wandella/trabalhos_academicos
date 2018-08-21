
package Controle;

import Persistencia.PersistenciaUsuario;
import modelo.Usuario;

public class ControleUsuario {
    
    private PersistenciaUsuario persistencia;
        
    public ControleUsuario(){
        persistencia = new PersistenciaUsuario();
    }

    public Usuario pesquisaUsuario(String cpf){
        if (cpf.equals("")) {
            return null;
        }
        return persistencia.pesquisaUsuario(cpf);
    }
    
    public boolean InsereUsuario(String nome, String CPF, String email, String telefone){
        Usuario usuario = new Usuario(nome, CPF, email, telefone);
        if (nome.equals("")) {
            System.out.println("Nome nao inserido");
            return false;
        }
        if (CPF.equals("")) {
            System.out.println("CPF nao inserido");
            return false;
        }
        if (email.equals("")) {
            System.out.println("Email nao inserido");
            return false;
        }
        if (telefone.equals("")) {
            System.out.println("Telefone nao inserido");
            return false;
        }
        if(persistencia.buscaUsuario(CPF)){
            System.out.println("Usuario já existente");
            return false;
        }
        persistencia.insereUsuario(usuario);
        System.out.println("Inserido com sucesso");
        return true;
    }
    
    public boolean alterarUsuario(Usuario usuario){
        int posicao = persistencia.retornaPosUsuario(usuario.getCpf());
        if (posicao != -1) {
            persistencia.alteraDados(posicao, usuario);
            return true;
        }
        return false;
    }
    
    public boolean removeUsuario(String CPF){
        Usuario usuario = persistencia.pesquisaUsuario(CPF);
        if (usuario != null) {
            persistencia.removeUsuario(usuario);
            return true;
        }
        return false;
    }
    
    public String[] pegaCPFeNome(){
        return persistencia.listaNomeECPF();
    }
}
