package trabalho_01;

/**
 *
 * @author Gustavo Westarb e Luciane Tedesco
 */
public class Aresta {
    
    private int verticeSource;
    private int verticeDestiny;
    private int weight;

    public int getVerticeSource() {
        return verticeSource;
    }

    public void setVerticeSource(int verticeSource) {
        this.verticeSource = verticeSource;
    }

    public int getVerticeDestiny() {
        return verticeDestiny;
    }

    public void setVerticeDestiny(int verticeDestiny) {
        this.verticeDestiny = verticeDestiny;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public Aresta(int verticeSource, int verticeDestiny, int weight){
        setVerticeSource(verticeSource);
        setVerticeDestiny(verticeDestiny);
        setWeight(weight);
    }
    
}
