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
    private int numVertices; //Quantidade de vertices do grafo
    private int position[]; //Posição para percorrer as adjs do vertice v
    private boolean typeDigrafo;
    private boolean typeSimpleGraph;
    private boolean typeMultiGraph;
    private boolean typeRegular;
    private boolean typeComplete;
    private boolean typeNull;
    private boolean typeBipartite;
    private boolean typeNotDirected;
    
    /**
     * Construtor da matriz de adjacência baseada em no número de vértices
     * @param numVertices Quantidade de vértices do Grafo.
     */
    public Grafo(int numVertices){
        
        this.matrix = new int [numVertices][numVertices];
        this.position = new int [numVertices]; 
	this.numVertices = numVertices;
	
        //Preenche todas as posições da matriz com 0 e sua posição em -1
        for ( int i = 0; i < this.numVertices; i++) {
            for ( int j = 0; j < this.numVertices; j++){
                this.matrix[i][j] = 0;
                this.position[i] = -1;
            }
        }
    }
    
    /**
     * Construtor do grafo com uma matriz predefinida 
     * @param matrix Matriz de adjacência
     */
    public Grafo(int matrix[][]){
        
        this.matrix = matrix;
        this.position = new int[this.matrix.length];
        this.numVertices = this.matrix.length;
        
        //Preenche posição em -1 para buscas
        for ( int i = 0; i < this.numVertices; i++) {
            for ( int j = 0; j < this.numVertices; j++){
                this.position[i] = -1;
            }
        }
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
    
    /**
     * Informa os grais dos vertices do grafo em contexto
     * @return Retorna os grais e a seguencia de grau
     */
    public String grausDoVertice(){
        return degreeVertice(this.matrix);
    }
    
    /**
     * Informa os grais dos vertices do grafo passado por parametro
     * @return Retorna os grais e a seguencia de grau
     */
    public String grausDoVertice(int matrix[][]){
        return degreeVertice(matrix);
    }
    
    /**
     * Informa as arrestas do grafo em contexto
     * @return Retorna as arestas e suas ligações, e a quantidade de arestas
     */
    public String arestasDoGrafo(){
        return edgesOfGraph(this.matrix);
    }
    
    /**
     * Informa as arrestas do grafo passado por parametro
     * @return Retorna as arestas e suas ligações, e a quantidade de arestas
     */
    public String arestasDoGrafo(int matrix[][]){
        return edgesOfGraph(matrix);
    }

    private String degreeVertice(int[][] matrix) {
        StringBuilder returnString = new StringBuilder();
        int[] sequence = new int[this.numVertices];
        
        for (int i = 0; i < matrix.length; i++) {
            int grau = 0;
            
            for (int j = 0; j < matrix.length; j++){
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
        
        return returnString.toString();
    }
    
    private String edgesOfGraph(int[][] matrix) {
        int count = 0;
        StringBuilder returnString = new StringBuilder();
        returnString.append("Arestas: ");
        returnString.append("\n");
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {
                    count++;
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
        int[][] matrix = {{0,1,1,1},
                          {1,0,1,0},
                          {1,1,0,1},
                          {1,0,1,0}};
        
        Grafo grafo = new Grafo(matrix);
        
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
    
    private boolean typeGraphBipartite(int[][] matrix){
        
        if (!typeGraphSimple(matrix)) {
            return false;
        }
        
        int[] colors = new int[matrix.length];
        
        for (int i = 0; i < matrix.length; i++) {
            colors[i] = -1;
        }
        
        return true;
    }
}