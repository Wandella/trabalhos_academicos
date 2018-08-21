import java.util.ArrayList;

public class Teste {

    public static void main(String[] args) {
        Grafo grafo = new Grafo(6);
        ArrayList vizinhos = null;
        
        grafo.insereAresta(1, 2, 5);
        grafo.insereAresta(1, 3, 10);
        grafo.insereAresta(2, 4, 10);
        grafo.insereAresta(3, 4, 1);
        grafo.insereAresta(4, 6, 10);
        grafo.insereAresta(5, 4, 5);
        grafo.insereAresta(6, 5, 2);
        
        System.out.println("Matriz de pesos do grafo:");
        grafo.printaMatriz();
        System.out.println("");
        
        
        //tamanho, ordem, grau, vizinhos, distancia
        /*System.out.println("Tamanho: " + grafo.tamanho());
        System.out.println("Ordem: " + grafo.ordemGrafo());
        grafo.printaMatriz();
        System.out.println("Grau 5: " + grafo.grauVertice(5));
        vizinhos = grafo.vizinhos(1);
        System.out.print("Vizinhos de 1: ");
        for (int i = 0; i < vizinhos.size(); i++) {
            System.out.print(vizinhos.get(i) + " ");
        }
        System.out.println("");
        System.out.println("Tamanho: "+grafo.tamanho());
        grafo.distanciaDoisVertices(1);
        System.out.println("Vizinhos ");*/
        
        //busca profundidade
        /*String retorno = new String();
        Grafo floresta = null;
        floresta = grafo.buscaProfundidade(1, retorno);
        System.out.println("Arestas de retorno: " + retorno);
        System.out.println("Floresta de profundidade:");
        floresta.printaMatriz();*/
        
        //busca Largura
        /*Grafo blargura = null;
        blargura = grafo.buscaLargura(1);
        blargura.printaMatriz();*/
        
        //arvore geradora
        /*Grafo ageradora = grafo.arvoreGeradoraMinima(1);
        System.out.println("Arvore Geradora Mínima:");
        ageradora.printaMatriz();*/
        
        //distancia e caminho min
        //grafo.distanciaDoisVertices(2);
        
        //qtd Componentes Conexas
        System.out.println("Componentes conexas: "+grafo.componentesConexas());
        
        //articulação
        //System.out.println("É articulação? " + grafo.isArticulacao(5));
        System.out.println("É ponte? " + grafo.isPonte(4,5));
        
        //constroi cadeia
        /*ArrayList a = grafo.constroiCadeiaFechada(1, grafo);
        System.out.println("Cadeia:");
        for (int i = 0; i < a.size(); i++) {
            System.out.println(" " + a.get(i));
        }*/
        
        //euleriano
        /*System.out.println("É euleriano? " + grafo.isEuleriano());
        ArrayList cadeiaEuleriana = null;
        cadeiaEuleriana = grafo.cadeiaEuleriana();
        System.out.println("Cadeia Euleriana");
        for (int i = 0; i < cadeiaEuleriana.size(); i++) {
            System.out.print(" " + cadeiaEuleriana.get(i));
        }*/
                
        //conjunto independente
        /*ArrayList teste = new ArrayList();
        System.out.println("Lista:");
        for (int i = 0; i < teste.size(); i++) {
            System.out.println(teste.get(i));
        }
        ArrayList ci = grafo.conjuntoIndependente();
        System.out.print("Conjunto independente: " );
        for (int i = 0; i < ci.size(); i++) {
            System.out.print(" " + ci.get(i));
        }*/
        
    }
    
}
