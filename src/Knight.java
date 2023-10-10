import java.util.ArrayList;

public class Knight extends ChessPiece{
    ArrayList<String> possMoves = new ArrayList<>();
    public Knight(String position, Boolean color) {
        super(position, color);
    }

    @Override
    ArrayList<String> canMoveTo() {
        if(getColumnInt()+2<=8){
            addPossMove(2,1);
        }
        if(getColumnInt()+1<=8){
            addPossMove(1,2);
        }
        if(getColumnInt()-2>=1){
            addPossMove(-2,1);
        }
        if(getColumnInt()-1>=1){
            addPossMove(-1,2);
        }
        ArrayList<String> tempMoves = new ArrayList<>(possMoves);
        possMoves.clear();
        return tempMoves;
    }
    private void addPossMove(int columnDiff, int rowDiff){
        String str = "";
            if(getRow()+rowDiff<=8){
                str+=(char)(getColumnInt()+columnDiff+'`');
                str+=getRow()+rowDiff;
                if(str.length()==2){
                    possMoves.add(str);
                }
                str="";
            }
            if(getRow()-rowDiff>=1){
                str+=(char)(getColumnInt()+columnDiff+'`');
                str+=getRow()-rowDiff;
                if(str.length()==2){
                    possMoves.add(str);
                }
            }
    }
}
