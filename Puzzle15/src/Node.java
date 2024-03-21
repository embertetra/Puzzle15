public class Node implements Comparable<Node>{
    //node je element u priority queue koji ima pored matrice: g i h, uz pomoc cega
    //se izvrsava funkcija f() i sortira priority queue;
    private Matrica matrica;
    ///vrednost cvora
    private int g;
    ///heuristika
    private int h;

    public Node(Matrica matrica, int g, int h) {
        this.matrica = matrica;
        this.g = g;
        this.h = h;
    }

    @Override
    public int compareTo(Node node) {
        return f() - node.f(); //poredi dva node-a za priority queue, onaj koji ima manju vrednost ide prvi
    }

    public int f(){
        return h+g;
    }

    public Matrica getMatrica() {
        return matrica;
    }

    public int getG() {
        return g;
    }

}
