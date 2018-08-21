import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Grafo {

    private double matrizPesos[][];
    private int maxVertices;
    private final int INFINITO = 999999;

    public Grafo(int qtdVertices) { //construtor
        qtdVertices++; //descarta a primeira coluna
        this.matrizPesos = new double[qtdVertices][qtdVertices];
        this.maxVertices = qtdVertices - 1;
    }

    /*Métodos básicos do grafo*/
    public Grafo copiaGrafo(Grafo original) {
        Grafo copia = new Grafo(original.getQtdVertices());
        double matriz[][] = original.getMatrizPesos();
        for (int i = 1; i < matriz.length; i++) {
            for (int j = 1; j < matriz.length; j++) {
                copia.insereAresta(i, j, matriz[i][j]);
            }
        }
        return copia;
    }

    public boolean insereAresta(int vertice1, int vertice2, double peso) {
        /*verifica se os vértices são diferentes, se existem e se já existe a aresta*/
        if ((vertice1 != vertice2) && (vertice1 > 0) && (vertice1 <= maxVertices) && (vertice2 > 0) && (vertice2 <= maxVertices) && (matrizPesos[vertice1][vertice2] == 0)) {
            /*salva o peso nas duas posições*/
            matrizPesos[vertice1][vertice2] = peso;
            matrizPesos[vertice2][vertice1] = peso;
            return true;
        } else {
            return false;
        }
    }

    public boolean retiraAresta(int vertice1, int vertice2) {
        /*verifica se os vértices são diferentes, se existem e se já existe a aresta*/
        if ((vertice1 != vertice2) && (vertice1 > 0) && (vertice1 <= maxVertices) && (vertice2 > 0) && (vertice2 <= maxVertices) && (matrizPesos[vertice1][vertice2] != 0)) {
            matrizPesos[vertice1][vertice2] = 0;
            matrizPesos[vertice2][vertice1] = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean existeAresta(int v1, int v2) {
        if (matrizPesos[v1][v2] != 0) {
            return true;
        }
        return false;
    }

    public void printaMatriz() {
        for (int i = 1; i < matrizPesos.length; i++) {
            for (int j = 1; j < matrizPesos.length; j++) {
                System.out.print(matrizPesos[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public double[][] getMatrizPesos() {
        return matrizPesos;
    }

    public int getQtdVertices() {
        return maxVertices;
    }

    /*Retornar a ordem do grafo*/
    public int ordemGrafo() {
        int ordem = 0, cont;
        for (int i = 1; i < matrizPesos.length; i++) { //percorre a matriz olhando cada coluna, se ela é vazia
            cont = 0;
            for (int j = 1; j < matrizPesos.length; j++) {
                if (matrizPesos[i][j] != 0) {
                    cont++;
                }
            }
            if (cont != 0) { //se a coluna não for vazia, existe o vertice
                ordem++;
            }
        }
        return ordem;
    }

    /*Retornar o tamanho do grafo*/
    public int tamanho() {
        int cont = 0;
        for (int i = 1; i < matrizPesos.length; i++) { //percorre a matriz
            for (int j = 1; j < matrizPesos.length; j++) {
                if (matrizPesos[i][j] != 0) { //se existe peso, entao existe a 
                    //System.out.println("Existe (" + i + "," + j + ")");
                    cont++;
                }
            }
        }
        return cont / 2;
    }

    /*Retornar os vizinhos de um vértice fornecido*/
    public ArrayList vizinhos(int vertice) {
        ArrayList vizinhos = new ArrayList();
        for (int i = 1; i < matrizPesos.length; i++) {
            if (matrizPesos[vertice][i] != 0) {
                vizinhos.add(i);
            }
        }
        return vizinhos;
    }

    /*Determinar o grau de um vértice fornecido*/
    public int grauVertice(int vertice) {
        int cont = 0;
        for (int i = 1; i < matrizPesos.length; i++) {
            if (matrizPesos[vertice][i] != 0) {
                cont++;
            }
        }
        return cont;
    }

    /*Determinar se um grafo é bipartido (Dica: ver aula 16 slide 20)*/
    public boolean isBipartido() {

    }

    /*Verificar se um vértice é articulação*/
    public boolean isArticulacao(int vertice) {
        Grafo grafo = this.copiaGrafo(this);
        int corte;
        int componentes = grafo.componentesConexas();
        System.out.println("Antes: " +componentes);
        for (int i = 0; i < matrizPesos.length; i++) {
            grafo.retiraAresta(vertice, i);
        }
        corte = grafo.componentesConexas();
        System.out.println("Corte: " + corte);
        if(corte > componentes+1){
            return true;
        }
        return false;
        
    }

    /*Verificar se uma aresta é ponte*/
    public boolean isPonte(int vertice1, int vertice2) {
        Grafo grafo = this.copiaGrafo(this);
        int corte;
        int componentes = grafo.componentesConexas();
        System.out.println("Antes: " +componentes);
        if(!grafo.retiraAresta(vertice1, vertice2)){
            System.out.println("Aresta não existe");
            return false;
        }
        corte = grafo.componentesConexas();
        System.out.println("Corte: " + corte);
        if(corte > componentes){
            return true;
        }
        return false;
    }

    /*Determinar a sequência de vértices visitados na busca em profundidade e informar a(s) aresta(s) de retorno*/
    public Grafo buscaProfundidade(int verticeInicial, String salvaArestasRetorno) {
        boolean[] marcados = new boolean[matrizPesos.length];
        boolean todosMarcados = false;
        salvaArestasRetorno = "";
        Grafo floresta = new Grafo(matrizPesos.length - 1);
        for (int i = 1; i < marcados.length; i++) {
            marcados[i] = false;
        }
        while (todosMarcados == false) {
            System.out.println("RODOU----------------------------");
            BP(verticeInicial, salvaArestasRetorno, marcados, floresta);
            todosMarcados = true;
            for (int i = 1; i < marcados.length; i++) {
                if (marcados[i] == false) {
                    todosMarcados = false;
                    verticeInicial = i;
                    break;
                }
            }
        }
        return floresta;
    }

    private void BP(int verticeInicial, String salvaArestasRetorno, boolean[] marcados, Grafo floresta) {
        ArrayList vizinhos;
        int aux;
        System.out.println("Vertice Inicial" + verticeInicial);
        marcados[verticeInicial] = true; //marca v
        vizinhos = vizinhos(verticeInicial); //pega vizinhos de v

        for (int i = 0; i < vizinhos.size(); i++) { //para todos os vizinhos de v
            aux = (int) vizinhos.get(i);
            System.out.println("Vizinho: " + aux);
            if (marcados[aux] == false) { //se é não marcado
                floresta.insereAresta(verticeInicial, aux, matrizPesos[verticeInicial][aux]); //explora
                System.out.println("Aresta: (" + i + "," + aux + ") = " + matrizPesos[i][aux]);
                System.out.println("COMUM");
                //}
                marcados[aux] = true; //marca
                BP(aux, salvaArestasRetorno, marcados, floresta); //chama BP
                salvaArestasRetorno = salvaArestasRetorno.concat("SIM");
            } else {
                System.out.println("Nao entra");
                if (!floresta.existeAresta(verticeInicial, aux)) {
                    System.out.println("aresta de retorno "
                            + aux + " " + verticeInicial);
                    salvaArestasRetorno = salvaArestasRetorno.concat("(" + verticeInicial + "," + aux + "), ");
                } else {
                    salvaArestasRetorno = salvaArestasRetorno.concat("NAO");
                }
            }
        }

        //return floresta;
    }

    /*Determinar a sequência de vértices visitados na busca em largura e informar a(s) aresta(s) que não faz(em) parte da árvore de busca em largura*/
    public Grafo buscaLargura(int vertice) {
        boolean marcados[] = new boolean[matrizPesos.length];
        Grafo floresta = new Grafo(matrizPesos.length - 1);
        ArrayList filaQ = new ArrayList();
        ArrayList vizinhos = new ArrayList();
        int vizinho;
        boolean todosMarcados = false;

        while (todosMarcados != true) {
            marcados[vertice] = true; //marca
            filaQ.add(vertice); //adiciona na lista;
            System.out.println("RODOU!!!!!---");
            while (filaQ.size() != 0) {

                vertice = (int) filaQ.remove(0);
                vizinhos = vizinhos(vertice);
                for (int i = 0; i < vizinhos.size(); i++) { //para todos os vizinhos
                    vizinho = (int) vizinhos.get(i);
                    if (marcados[vizinho] == false) {
                        floresta.insereAresta(vizinho, vertice, matrizPesos[vizinho][vertice]); //explora
                        filaQ.add(vizinho);
                        marcados[vizinho] = true; //marca
                    } else if (floresta.getMatrizPesos()[vizinho][vertice] == 0) {
                        //não faz parte da árvore
                    }
                }
            }

            todosMarcados = true;
            for (int i = 1; i < marcados.length; i++) {
                if (marcados[i] == false) {
                    todosMarcados = false;
                    vertice = i;
                    break;
                }
            }
        }
        return floresta;
    }

    /*Determinar o número de componentes conexas do grafo e os vértices de cada componente (Dica: pesquise o algoritmo de Roy para computar componentes conexas)*/
    public int componentesConexas() {
        int vertice;
        int qtdComponentes = 0;
        int compVertice[] = new int[matrizPesos.length - 1];
        
        for (int i = 1; i < matrizPesos.length - 1; i++) { //inicia todos os vértices pertencendo a nenhuma componente
            compVertice[i] = -1;
        }

        for (int i = 1; i < matrizPesos.length - 1; i++) {
            if (compVertice[i] == -1) {
                verificaAsComponentes(i, qtdComponentes, compVertice);
                qtdComponentes++;
            }
        }

        return qtdComponentes;
    }
    
    private void verificaAsComponentes(int v, int id, int compVertice[]){
        compVertice[v] = id; //v pertence a componeente atual
        
        for (int i = 1; i < matrizPesos.length-1; i++){ //Descobre os vizinhos de v
            if (this.matrizPesos[v][i] != 0 && compVertice[i] == -1){//Se houver ligação entre v e w, então w pertence a componente atual
                 verificaAsComponentes(i, id,compVertice);//Verifica todos os vértices alcançáveis a partir de w, esses também fazem parte da componente conexa
            }
        }
    }

    /*Determinar distância e caminho mínimo*/
    public void distanciaDoisVertices(int verticeOrigem) {
        double dt[] = new double[matrizPesos.length];
        int rot[] = new int[matrizPesos.length];
        ArrayList antecessores = new ArrayList();
        boolean altera;
        int aux;
        int verticeDestino, verticeAtual;
        //PRECISA VER SE HA CIRCUITO NEGATIVO

        for (int i = 0; i < matrizPesos.length; i++) {//inicializa para todos os vertices
            if (matrizPesos[verticeOrigem][i] != 0) {//se for vizinho
                rot[i] = verticeOrigem;
                dt[i] = matrizPesos[verticeOrigem][i];
            } else {//se nao for vizinho
                rot[i] = 0;
                dt[i] = INFINITO;
            }
            if (i == verticeOrigem) { //se for o vértice escolhido
                dt[verticeOrigem] = 0;
                rot[verticeOrigem] = INFINITO;
            }
        }

        for (int k = 1; k < matrizPesos.length - 1; k++) {
            altera = false;
            for (int i = 1; i < matrizPesos.length; i++) {
                if (i == verticeOrigem) {//nao faz para o de origem
                    continue;
                }
                antecessores = vizinhos(i);
                for (int j = 0; j < antecessores.size(); j++) { //para todos os antecessores de i
                    aux = (int) antecessores.get(j);
                    if (dt[i] > dt[aux] + matrizPesos[aux][i]) {
                        dt[i] = dt[aux] + matrizPesos[aux][i];
                        rot[i] = aux;
                        altera = true;
                    }
                }
            }
            if (altera == false) {
                k = matrizPesos.length;
            }
        }
        /**/
        System.out.println("dt: ");
        for (int i = 1; i < dt.length; i++) {
            System.out.print("   " + i + "-" + dt[i]);
        }
        System.out.println("");
        System.out.println("rot:");
        for (int i = 1; i < rot.length; i++) {
            System.out.print("   " + i + "-" + rot[i]);
        }
        System.out.println("");
        /**/

        for (int i = 1; i < rot.length; i++) {

        }
        ArrayList caminho = new ArrayList();

        verticeDestino = 1;
        verticeAtual = verticeDestino;
        for (int i = 1; i < matrizPesos.length; i++) {
            /*if (i == verticeOrigem) { //caso seja o vertice de origem
                verticeDestino++;
                continue;
            }*/

            System.out.println("Distancia de " + verticeOrigem + " a " + verticeDestino + " = " + dt[verticeDestino]);
            /*while (verticeAtual != verticeOrigem) {
                caminho.add(rot[i]);
                verticeAtual = rot[i];
                System.out.println(i);
                i = rot[i];
            }*/
            verticeDestino++;
        }
        int i = verticeDestino;

        System.out.print("Caminho: ");
        for (int j = caminho.size() - 1; j >= 0; j--) {
            System.out.print(caminho.get(j) + ", ");
        }
        System.out.println("");

    }

    /*Determinar se há ocorrência de circuito negativo no grafo*/
    public boolean haCircuitoNegativo() {

    }

    /*Determinar a árvore geradora mínima de um grafo.*/
    private int[] encontraMin(ArrayList jaSelecionados, ArrayList naoSelecionados, int sel, int nsel) {
        sel = (int) jaSelecionados.get(0);
        nsel = (int) naoSelecionados.get(0);
        int resposta[] = new int[2];
        double min = matrizPesos[sel][nsel];
        for (int i = 0; i < jaSelecionados.size(); i++) {
            for (int j = 0; j < naoSelecionados.size(); j++) {
                if (matrizPesos[(int) jaSelecionados.get(i)][(int) naoSelecionados.get(j)] < min) {
                    sel = (int) jaSelecionados.get(i);
                    nsel = (int) naoSelecionados.get(j);
                    min = matrizPesos[sel][nsel];
                }
            }
        }
        resposta[0] = sel;
        resposta[1] = nsel;
        return resposta;
    }

    public Grafo arvoreGeradoraMinima(int vertice) { //PRIM
        Grafo arvore = new Grafo(matrizPesos.length - 1);
        ArrayList jaSelecionados = new ArrayList(); //T
        ArrayList naoSelecionados = new ArrayList(); //V
        int selecionado = 0, naoselecionado = 0;
        int aux[] = null;
        jaSelecionados.add(vertice);
        for (int i = 1; i < matrizPesos.length; i++) { //pega todos os outros vertices
            if (i != vertice) {
                naoSelecionados.add(i);
            }
        }
        while (jaSelecionados.size() != matrizPesos.length - 1) {
            aux = this.encontraMin(jaSelecionados, naoSelecionados, selecionado, naoselecionado);
            selecionado = aux[0];
            naoselecionado = aux[1];
            System.out.println("selecionado: " + selecionado + " nao selecionado: " + naoselecionado);
            jaSelecionados.add(selecionado); //adiciona
            for (int i = 0; i < naoSelecionados.size(); i++) {//remove
                if ((int) naoSelecionados.get(i) == naoselecionado) {
                    naoSelecionados.remove(i);
                    break;
                }
            }

            arvore.insereAresta(selecionado, naoselecionado, matrizPesos[selecionado][naoselecionado]);
        }
        return arvore;
    }

    /*Verificar se um grafo é euleriano. Em caso afirmativo, determinar uma cadeia euleriana fechada.*/
    public boolean isEuleriano() {
        for (int i = 0; i < matrizPesos.length; i++) {
            if ((grauVertice(i) % 2) != 0) { //caso o grau seja impar
                return false;
            }
        }
        return true;
    }

    public ArrayList constroiCadeiaFechada(int verticeInicial, Grafo grafo) {
        ArrayList cadeia = new ArrayList();
        int atual = verticeInicial;
        int cont = 0;
        cadeia.add(verticeInicial);
        System.out.println("Chamou constroi cadeia");
        do {
            if (atual == 0) {
                break;
            }
            System.out.println("Atual: " + atual);
            for (int i = 1; i < grafo.getMatrizPesos().length; i++) {
                if (grafo.getMatrizPesos()[atual][i] != 0) {
                    cadeia.add(i);
                    grafo.retiraAresta(atual, i);
                    atual = i;
                    break;
                }
            }
            cont++;

        } while (atual != verticeInicial);
        //cadeia.add(atual);
        return cadeia;
    }

    private int escolheVertice(Grafo K, ArrayList cadeiaC) {

        for (int i = 0; i < cadeiaC.size(); i++) {
            if (K.grauVertice((int) cadeiaC.get(i)) > 0) {
                return (int) cadeiaC.get(i);
            }
        }
        /*for (int i = 1; i < K.getQtdVertices(); i++) {
            if (grauVertice(i) > 0) {
                return i;
            }
        }*/
        return 1;
    }

    private ArrayList Hierholzer() {
        int atual, prox;
        Grafo M1 = this.copiaGrafo(this); //Ler os dados de G=(N,M)
        Grafo K = null;
        ArrayList cadeiaH = new ArrayList();
        int v = 1; //Escolher um vértice v qualquer de G

        ArrayList cadeiaC = this.constroiCadeiaFechada(v, this);//constroi cadeiaC a partir de v, aleatoria

        for (int i = 0; i < cadeiaC.size() - 1; i++) {// retira de G as arestas de C
            atual = (int) cadeiaC.get(i);
            prox = (int) cadeiaC.get(i + 1);
            M1.retiraAresta(atual, prox); //retira as arestas de M
        }
        K = this.copiaGrafo(M1);
        while (M1.tamanho() != 0) { //enquanto houver arestas em M
            v = K.escolheVertice(K, cadeiaC); //Escolher v tal que d(v) >0 em K e pert C
            System.out.println("Manda " + v);
            cadeiaH = constroiCadeiaFechada(v, K); //constroi cadeia fechada a partir de v

            for (int i = 0; i < cadeiaH.size() - 1; i++) { //retira de H os vertices da cadeia encontrada
                atual = (int) cadeiaH.get(i);
                prox = (int) cadeiaH.get(i + 1);
                M1.retiraAresta(atual, prox); //retira as arestas de M
            }
            for (int i = 0; i < cadeiaC.size(); i++) { // a cadeia H é colocada em C antes do vértice v
                if (cadeiaC.get(i) == cadeiaH.get(0)) {
                    int cont = i + 1;
                    for (int j = 1; j < cadeiaH.size(); j++) {
                        cadeiaC.add(cont, cadeiaH.get(j));
                        cont++;
                    }
                    break;
                }
            }
            cadeiaH.clear();
        }
        return cadeiaC;
    }

    public ArrayList cadeiaEuleriana() {
        if (isEuleriano()) {
            ArrayList cadeiaEuleriana = new ArrayList();
            return this.Hierholzer();
        } else {
            return null;
        }
    }

    /*Determinar o conjunto independente ou estável de um grafo por meio de uma heurística gulosa.*/
    public ArrayList conjuntoIndependente() {
        ArrayList verticesS = new ArrayList();
        ArrayList vizinhos;
        int numIndependencia = 0;
        int k;
        ArrayList<Vertice> verticesN = new ArrayList<>();

        for (int i = 1; i < matrizPesos.length; i++) { //pega o grau de cada vertice
            Vertice v = new Vertice(i, grauVertice(i));
            verticesN.add(v);
        }
        Comparator<Vertice> cmprtr = new Comparator<Vertice>() {
            @Override
            public int compare(Vertice t, Vertice t1) {
                if (t.getGrau() > t1.getGrau()) {
                    return -1;
                }
                if (t.getGrau() < t1.getGrau()) {
                    return 1;
                }
                return 0;
            }
        };

        verticesN.sort(cmprtr); //ordena em ordem decrescente

        while (verticesN.size() != 0) {
            k = verticesN.remove(0).getRotulo(); //pega e remove vertice de maior grau
            vizinhos = vizinhos(k); //pega vizinhos de k
            for (int i = 0; i < vizinhos.size(); i++) { //remove os vizinhos de k de verticesN
                for (int j = 0; j < verticesN.size(); j++) {
                    if (verticesN.get(j).getRotulo() == (int) vizinhos.get(i)) {
                        verticesN.remove(j);
                        break;
                    }
                }
            }
            verticesS.add(k);
            numIndependencia++;
        }

        return verticesS;
    }
}
