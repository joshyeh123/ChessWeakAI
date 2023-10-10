import java.util.ArrayList;

public class Bishop extends Queen{
    public Bishop(String position, Boolean color) {
        super(position, color);
    }
    @Override
    ArrayList<String> canMoveTo() {
        checkDiagonal();
        ArrayList<String> tempMoves = new ArrayList<>(possMoves);
        possMoves.clear();
        return tempMoves;
    }
}
