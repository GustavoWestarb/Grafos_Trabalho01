package trabalho_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Gustavo Westarb e Luciane Tedesco
 */
public class Grafo {
    
    private int matrix[][]; //Matriz de pesos dos vertices, em inteiros
    private int colors[];
    private boolean typeDigrafo;
    private boolean typeSimpleGraph;
    private boolean typeMultiGraph;
    private boolean typeRegular;
    private boolean typeComplete;
    private boolean typeNull;
    private boolean typeBipartite;
    private boolean typeNotDirected;
    
    private Grafo() {
    }
    
    /**
     * Insere a aresta no grafo
     * @param verticeSource Vertice de origem da aresta
     * @param verticeDestiny Vertice de destino da aresta
     * @param weight  Peso da aresta
     */
    public void insertEdge(int verticeSource, int verticeDestiny, int weight){
        this.matrix[verticeSource][verticeDestiny] = weight;
    }
    
    /**
     * Verifica a existencia da aresta
     * @param verticeSource Vertice de origem da aresta
     * @param verticeDestiny Vertice de destino da aresta
     * @return Se existe aresta retorna 'True'...
     */
    public boolean existEdge(int verticeSource, int verticeDestiny){
        return(this.matrix[verticeSource][verticeDestiny] > 0);
    }
    
    /**
     * Informa o tipo do grafo em contexto
     * @return Tipo do grafo
     */
    public String tipoDoGrafo(){
        checkTypes(this.matrix);
        return graphType();
    }
    
    /**
     * Informa o tipo de um grafo predefinido
     * @param matrix Matriz para verificação de tipo
     * @return Tipo do grafo
     */
    public String tipoDoGrafo(int matrix[][]){
        checkTypes(matrix);
        return graphType();
    }
    
    /**
     * Chama os métodos que verificam o tipo do grafo
     * @param matrix 
     */
    private void checkTypes(int[][] matrix) {
        
        typeNull = typeGraphNull(matrix);
        typeSimpleGraph = typeGraphSimple(matrix);
        typeMultiGraph = typeGraphMulti(matrix);
        typeComplete = typeGraphComplete(matrix);
        typeRegular = typeGraphRegular(matrix);
        typeNotDirected = typeGraphNotDirected(matrix);
        //typeBipartite = typeGraphBipartite(matrix);
        
    }
    
    /**
     * Informa o tipo do grafo
     * @param matrix Matriz para verificação de tipo
     * @return Tipo do grafo
     */
    private String graphType(){
        
        StringBuilder result = new StringBuilder();

        result.append("O grafo possui os seguintes tipos: ").append("\n");
        
        if (typeNull) {
            result.append("    Nulo").append("\n");
        }
        
        if (typeMultiGraph) {
            result.append("    Multigrafo").append("\n");
        }
        
        if (typeSimpleGraph) {
            result.append("    Simples").append("\n");
        }
        
        if (typeComplete) {
            result.append("    Completo").append("\n");
        }
        
        if (typeRegular) {
            result.append("    Regular").append("\n");
        }
        
        if (typeBipartite) {
            result.append("    Bipartido").append("\n");
        }
        
        if (typeDigrafo) {
            result.append("    Dirigido").append("\n");
        }
        
        if(typeNotDirected){
            result.append("    Não dirigido").append("\n");
        }
                
        return result.toString();
    }

    private String grausDoVertice(int[][] matrix) {
        StringBuilder returnString = new StringBuilder();
        int[] sequence = new int[matrix.length];
        int[] sequenceIn = new int[matrix.length];
        int[] sequenceOut = new int[matrix.length];
        
        
        if (typeGraphNotDirected(matrix)) {
            for (int i = 0; i < matrix.length; i++) {
                int grau = 0;
            
                for (int j = 0; j < matrix[i].length; j++){
                    grau += matrix[i][j];
                }
            
                sequence[i] = grau;
            
                returnString.append("Vértice V");
                returnString.append(i);
                returnString.append(", grau dele é: ");
                returnString.append(grau);
                returnString.append(";");
                returnString.append("\n");
                
            }
            
            Arrays.sort(sequence);
        
            returnString.append("Sequência de graus: ");
            returnString.append(Arrays.toString(sequence));
        }
        else{
            
            for (int i = 0; i < matrix.length; i++) {
                int grauIn = 0;
                int grauOut = 0;
            
                for (int j = 0; j < matrix[i].length; j++){
                    grauOut += matrix[i][j];
                    grauIn += matrix[j][i];
                }
            
                sequenceIn[i] = grauIn;
                sequenceOut[i] = grauOut;
            
                returnString.append("Vértice V");
                returnString.append(i);
                returnString.append(", grau de entrada é: ");
                returnString.append(grauIn);
                returnString.append(", grau de saída é: ");
                returnString.append(grauOut);
                returnString.append(";");
                returnString.append("\n");
                
            }
            
            Arrays.sort(sequenceIn);
            Arrays.sort(sequenceOut);
        
            returnString.append("Sequência de graus de entrada: ");
            returnString.append(Arrays.toString(sequenceIn));
            returnString.append("\n");
            returnString.append("Sequência de graus de saída: ");
            returnString.append(Arrays.toString(sequenceOut));
            
        }
        return returnString.toString();
    }
    
    private String arestasDoGrafo(int[][] matrix) {
        int count = 0;
        StringBuilder returnString = new StringBuilder();
        returnString.append("Arestas: ");
        returnString.append("\n");
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    count = count + matrix[i][j];
                    returnString.append("V");
                    returnString.append(i);
                    returnString.append("---");
                    returnString.append("V");
                    returnString.append(j);
                    returnString.append("\n");
                    
                    matrix[j][i] = 0;
                }
            }
        }
        
        returnString.append("Quantidade de arestas existentes no grafo: ");
        returnString.append(count);
        return returnString.toString();
    }
    
    public static void main(String args[]) {
        
        //matriz 4x4 simples;
        /*int[][] matrix = {{0,0,0,1},
                          {1,0,0,0},
                          {0,1,0,0},
                          {0,1,1,0}};*/
        
        /*int[][] matrix = {{0,1,1,2},
                          {1,0,1,0},
                          {1,1,0,1},
                          {2,0,1,0}};*/
        
        //ESSE DA PAU NAS ARESTAS
        /*int[][] matrix = {{0,0,1,0},
                          {1,0,2,0},
                          {0,0,0,1},
                          {1,0,1,1}};*/
        
        /*int[][] matrix = {{0,1,1,1},
                          {1,0,1,0},
                          {1,1,1,1},
                          {1,0,1,0}};*/
        
        /*int[][] matrix = {{0,1,1},
                          {1,0,1},
                          {1,1,0}};*/
        
        /*int[][] matrix = {{0,1,1},
                          {1,0,1},
                          {1,1,0}};*/
        
        int[][] matrix = {{0,0,0,0},
                          {0,0,0,0},
                          {0,0,0,0},
                          {0,0,0,0}};
        
        /*int[][] matrix = {{0,1,1,1},
                          {1,0,1,1},
                          {1,1,0,1},
                          {1,1,1,0}};*/
        
        Grafo grafo = new Grafo();
        
        System.out.println(grafo.tipoDoGrafo(matrix));
        System.out.println(grafo.grausDoVertice(matrix));
        System.out.println(grafo.arestasDoGrafo(matrix));
    }

    private boolean typeGraphSimple(int[][] matrix) {
    
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if ((i == j && matrix[i][j] != 0) || matrix[i][j] > 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean typeGraphMulti(int[][] matrix) {
    
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][i] != 0 || matrix[i][j] > 1) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean typeGraphNull(int[][] matrix){
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean typeGraphComplete(int[][] matrix){
        
        if (!typeGraphSimple(matrix)) {
            return false;
        }
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j && matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    //TODO testar este método bem, pois usei HashSet...
    private boolean typeGraphRegular(int[][] matrix){
        
        List<Integer> degrees = new ArrayList<>();
        
        
        for (int i = 0; i < matrix.length; i++) {
            int degree = 0;
            for (int j = 0; j < matrix.length; j++) {
                degree += matrix[i][j];
            }
            degrees.add(degree);
        }
        
        return new HashSet<Integer>(degrees).size() == 1;
    }
    
    private boolean typeGraphNotDirected(int[][] matrix){
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    typeDigrafo = true;
                    return false;
                }
            }
        }
        
        typeDigrafo = false;
        return true;
    }
    
//    public class Main {
//    public boolean ehBipartido(int[][] matriz) {
//
//        //tu tem um método ai pra ver se ele não é simples, então eu vou assumir que ele é
//        List<Aresta> arestas = new ArrayList<>();
//        for (int i = 0; i < matriz.length; i++) {
//            for (int j = 0; j < matriz[i].length; i++) {
//                if (i != j) {
//                    if (matriz[i][j] == 1) {
//                        arestas.add(new Aresta(i, j));
//                    }
//                }
//            }
//        }
//
//        Map<Integer, Integer> verticeCor = new HashMap<>();
//        int cor = 2;
//        IntStream.rangeClosed(0, matriz.length).forEach(i -> verticeCor.put(i, flipColor(cor)));
//
//        for (Aresta aresta : arestas) {
//            int aresta1 = aresta.getEntrada();
//            int aresta2 = aresta.getSaida();
//
//            if (verticeCor.get(aresta1) == verticeCor.get(aresta2)) return false;
//        }
//        return true;
//    }
//
//    public int flipColor(int color) {
//        return color == 1 ? 2 : 1;
//    }
//}
//
//class Aresta {
//    private final int entrada;
//    private final int saida;
//
//    public Aresta(int entrada, int saida) {
//        this.entrada = entrada;
//        this.saida = saida;
//    }
//
//    public int getEntrada() {
//        return entrada;
//    }
//
//    public int getSaida() {
//        return saida;
//    }
//}
    
//    private boolean typeGraphBipartite(int[][] matrix){
//        
//        if (!typeGraphSimple(matrix)) {
//            return false;
//        }
//        
//        this.colors = new int[matrix.length];
//        
//        for (int i = 0; i < matrix.length; i++) {
//            colors[i] = 0;
//        }
//        
//        for (int x = 0; x < colors.length; ++x) {
//            if (colors[x] == 0){
//                if (!differentiateColor(x, 1)){
//                    return false;
//                }
//            }
//	}
//        
//        return true;
//    }
//    
//    private boolean differentiateColor(int v, int u){
//       
//        this.colors[v] = u;
//        u = (u == 1) ? 2 : 1;
//        
//        for (int i = 0; i < colors.length; i++) {
//            if (colors[i] == 0) {
//                differentiateColor(i, u);
//            } else if (colors[i] == u){
//                return false;
//            }
//        }
//        return true;
//    }
}