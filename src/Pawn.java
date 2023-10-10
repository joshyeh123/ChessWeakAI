import java.util.ArrayList;

public class Pawn extends ChessPiece{

    public Pawn(String position, Boolean color) {
        super(position, color);
    }

    @Override
    ArrayList<String> canMoveTo() {
        ArrayList<String> possMoves = new ArrayList<>();
        if(isWhite()){
            String str = "";
            if(getRow()+1<=8){
                str+=getColumn();
                str+=getRow()+1;
                possMoves.add(str);
                str="";
            }
            if(getRow()==2){
                str+=getColumn();
                str+=getRow()+2;
                possMoves.add(str);
            }
        }
        if(!isWhite()){
            String str = "";
            if(getRow()-1>=1){
                str+=getColumn();
                str+=getRow()-1;
                possMoves.add(str);
                str="";
            }
            if(getRow()==7){
                str+=getColumn();
                str+=getRow()-2;
                possMoves.add(str);
            }
        }
        return possMoves;
    }
}
