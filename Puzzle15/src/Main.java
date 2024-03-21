import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/ulaz.txt"));

        int[][] cilj = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        int[][] start = new int[4][4];

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                start[i][j] = sc.nextInt();
            }
        }

        Azvezda algoritam = new Azvezda();
        if(!algoritam.proveraDaLiJeResivo(start)){
            System.out.println("Nije resivo");
            return;
        }

        Matrica startMatrica = new Matrica(start);
        Matrica ciljnaMatrica = new Matrica(cilj);

        algoritam.resi(startMatrica, ciljnaMatrica);
    }
}