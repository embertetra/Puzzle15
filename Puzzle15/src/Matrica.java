public class Matrica {

    private int[][] niz; //niz koji predstavlja tu matricu
    private Matrica parent;
    private Move lastMove;
    private int xNula;
    private int yNula;

    public Matrica(int[][] niz) {
        this.niz = niz;
        parent = null;
        lastMove = null;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(niz[i][j] == 0){
                    xNula = i;
                    yNula = j;
                }
            }
        }
    }

    ///racunanje heuristike - koliko je svaki element udaljen od svoje ciljne pozicije
    public int heuristika(Matrica cilj){
        int[] row = new int[16];
        int[] col = new int[16];
        ///row je niz za svaki element od 1-15 u kom je redu
        ///col je niz za svaki element od 1-15 u kojoj je koloni
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                row[cilj.getNiz()[i][j]] = i;
                col[cilj.getNiz()[i][j]] = j;
            }
        }

        int h=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(niz[i][j] == 0)continue;
                h+= Math.abs(i-row[niz[i][j]]) + Math.abs(j - col[niz[i][j]]);
            }
        }
        return h;
    }

    public Matrica potez(Move move){
        int newX = xNula + move.x; //potencijane nove kordinate nule
        int newY = yNula + move.y;
        ///provera da li su nove koordinate u granicama
        if(newX < 0 || newX >=4 || newY < 0 || newY>=4)
            return null;

        int[][] noviNiz = new int[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                noviNiz[i][j] = niz[i][j];
            }
        }

        noviNiz[xNula][yNula] = noviNiz[newX][newY];
        noviNiz[newX][newY] = 0;

        Matrica novaMatrica = new Matrica(noviNiz);
        novaMatrica.parent = this;
        novaMatrica.lastMove = move;
        return novaMatrica;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Matrica))return false;
        Matrica m = (Matrica) obj;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(niz[i][j] != m.getNiz()[i][j])
                    return false;
            }
        }
        return true;
    }

    public int[][] getNiz() {
        return niz;
    }

    public Matrica getParent() {
        return parent;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public int getxNula() {
        return xNula;
    }

    public int getyNula() {
        return yNula;
    }
}
