import java.util.*;

public class Azvezda {

    public Azvezda() {
    }

    public void resi(Matrica start, Matrica cilj){

        PriorityQueue<Node> red = new PriorityQueue<>();
        Map<Matrica, Integer> map = new HashMap<>();

        red.add(new Node(start, 0, start.heuristika(cilj)));
        map.put(start, 0);

        Matrica kraj = null;

        while(!red.isEmpty()){

            Node trenutni = red.poll();

            ///ako smo dosli do zeljene matrice
            if(trenutni.getMatrica().equals(cilj)){
                kraj = trenutni.getMatrica();
                break;
            }

            Move move = new Move(0,0);
            List<Move> listaPokreta = new ArrayList<>();
            listaPokreta.add(move.levo());
            listaPokreta.add(move.desno());
            listaPokreta.add(move.gore());
            listaPokreta.add(move.dole());

            for(Move m : listaPokreta){

                Matrica matrica = trenutni.getMatrica().potez(m);

                ///potez nije validan, van table
                if(matrica == null)continue;

                ///uzimas njenu vrednost iz mape
                Integer novaG = map.get(matrica);
                ///ako nemamo ovaj novi potez u redu ili ako je vrednost drugog puta do ovog cvora veca nego nova putanja
                if(novaG == null || novaG > trenutni.getG()+1){
                    map.put(matrica, trenutni.getG()+1);
                    red.add(new Node(matrica, trenutni.getG()+1, matrica.heuristika(cilj)));
                }
            }
        }

        if(kraj == null){
            System.out.println("greska");
            return;
        }

        List<Matrica> put = odrediPutanju(kraj);

        for(int i=0; i<put.size();i++){

            ///pocinje od 1 jer je 0 pocetna matrica i nema last move
            if(i>0){
                Move move = put.get(i).getLastMove();
                if(move.x == 1 && move.y == 0){
                    System.out.println("Pokret dole");
                }
                else if(move.x == -1 && move.y == 0){
                    System.out.println("Pokret gore");
                }
                else if(move.x == 0 && move.y == 1){
                    System.out.println("Pokret desno");
                }
                else if(move.x == 0 && move.y == -1){
                    System.out.println("Pokret levo");
                }
            }

            for(int x=0;x<4;x++){
                for(int y=0;y<4;y++){
                    if (put.get(i).getNiz()[x][y] < 10)
                        System.out.print(put.get(i).getNiz()[x][y] + "  ");
                    else System.out.print(put.get(i).getNiz()[x][y] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("Broj poteza: " + (put.size() - 1));
    }

    public List<Matrica> odrediPutanju(Matrica kraj){

        List<Matrica> put = new ArrayList<>();
        while(kraj != null){
            put.add(kraj);
            kraj = kraj.getParent();
        }
        Collections.reverse(put);
        return put;
    }

    public boolean proveraDaLiJeResivo(int[][] matrica){

        int[] niz = new int[16];
        int suma = 0;

        int redNula = 0;
        ///kopiranje matrica u niz, i pronalazenje nule
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(matrica[i][j] == 0)redNula = i;
                niz[i*4+j] = matrica[i][j];
            }
        }
        //primenom teoreme o inverznom brojanju odredjujemo sumu manjih brojeva u matrici od trenutne celije
        /*
            ako je suma inverznih brojeva parna i red u kom se nalazi nula + 1 parna,
            ili ako je suma neparna i red u kom se nalazi nula +1 neparna onda je resivo,
            u ostalim slucajevima nije resivo
         */
        for(int i=0; i<16; i++){
            if(niz[i] == 0)continue;
            for(int j=i+1; j<16; j++){
                if(niz[j] == 0)continue;
                if(niz[i] > niz[j])suma++;
            }
        }

        ///uvecamo za jedan radi lakseg zapisa
        redNula++;

        if(suma%2 == 0 && redNula%2 == 0)return true;
        else if(suma%2 != 0 && redNula%2 != 0)return true;
        return false;
    }

}
