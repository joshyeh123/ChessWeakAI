import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static java.lang.System.exit;

public class EasyAI extends MediumAI {
    public EasyAI(Boolean color, ChessBoard board) {
        super(color, board);
    }
    public void movePiece(){
        hasMoved = false;
        while(!hasMoved) {
            if (moveInCheck()) {
                hasMoved = true;
            }
            if (!hasMoved) {
                if (moveRandom()) {
                    hasMoved = true;
                }
            }
        }
        board.toggleIsWhiteTurn();
    }
}