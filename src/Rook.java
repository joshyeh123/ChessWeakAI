import java.util.ArrayList;

public class Rook extends Queen{
    public Rook(String position, Boolean color) {
        super(position, color);
    }
    @Override
    ArrayList<String> canMoveTo() {
        super.checkCross();
        ArrayList<String> tempMoves = new ArrayList<>(possMoves);
        possMoves.clear();
        return tempMoves;
    }
}
