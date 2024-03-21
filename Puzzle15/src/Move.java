public class Move {

    int x;
    int y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Move dole(){
        return new Move(1,0);
    }
    public Move gore(){
        return new Move(-1,0);
    }
    public Move levo(){
        return new Move(0,-1);
    }
    public Move desno(){
        return new Move(0,1);
    }
}
