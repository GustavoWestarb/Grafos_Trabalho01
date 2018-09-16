package trabalho_01;

/**
 *
 * @author Gustavo Westarb e Luciane Tedesco
 */
public class Grafo {
    
    private int matrix[][]; //Matriz de pesos dos vertices, em inteiros
    private int numVertices; //Quantidade de vertices do grafo
    private int position[]; //Posição para percorrer as adjs do vertice v
    private boolean loops;
    private boolean doubleEdges;
    private boolean complete;
    private boolean nulo;
    
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
        return graphType(this.matrix);
    }
    
    /**
     * Informa o tipo de um grafo predefinido
     * @param matrix Matriz para verificação de tipo
     * @return Tipo do grafo
     */
    public String tipoDoGrafo(int matrix[][]){
        return graphType(matrix);
    }
    
    /**
     * Informa o tipo do grafo
     * @param matrix Matriz para verificação de tipo
     * @return Tipo do grafo
     */
    private String graphType(int matrix[][]){
        
        StringBuilder result = new StringBuilder();
        
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[j].length; j++) {
                if (matrix[i][i] != 0) {
                    loops = true;
                }
                
                if (matrix[i][j] > 1) {
                    doubleEdges = true;
                }
                
                if (matrix[i][j] != 0 && i != j) {
                    complete = true;
                }
                else{
                    complete = false;
                }
                
                if (matrix[i][j] == 0) {
                    nulo = true;
                }
                else{
                    nulo = false;
                }
            }
            
        }
        
        if (loops) {
            result.append("Possui loops = multigrafo");
        }
        
        if (doubleEdges) {
            result.append("Possui arestas duplas = multigrafo");
        }
        
        if (complete) {
            result.append("É um grafo completo, portanto um grafo regular");
        }
        
        if (nulo) {
            result.append("É um grafo nulo");
        }
        
        return result.toString();
    }
    
    public String grausDoVertice(){
        return degreeVertice(this.matrix);
    }
    
    public String grausDoVertice(int matrix[][]){
        return degreeVertice(matrix);
    }

    private String degreeVertice(int[][] matrix) {
        return "";
    }
}
