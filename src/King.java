import java.util.ArrayList;

public class King extends ChessPiece{
    ArrayList<String> possMoves = new ArrayList<>();
    public King(String position, Boolean color) {
        super(position, color);
    }

    @Override
    ArrayList<String> canMoveTo() {
        int rowCounter = getRow();
        int columnCounter = getColumnInt();
        String str = "";
        for(int i = -1; i<2;i++){
            if(columnCounter-1 >= 1){
                str+=(char)(columnCounter-1+'`');
            }
            if(rowCounter+i>=1 && rowCounter+i <= 8){
                str+=rowCounter+i;
            }
            if(str.length()==2){
                possMoves.add(str);
            }
            str="";
            if(columnCounter + 1 <= 8){
                str+=(char)(columnCounter+1+'`');
            }
            if(rowCounter+i>=1 && rowCounter+i <= 8){
                str+=rowCounter+i;
            }
            if(str.length()==2){
                possMoves.add(str);
            }
            str="";
            str+=(char)(columnCounter+'`');
            if(rowCounter+i>=1 && rowCounter+i <= 8){
                str+=rowCounter+i;
            }
            if(!str.equals(getPos())){
                if(str.length()==2){
                    possMoves.add(str);
                }
            }
            str="";
        }
        ArrayList<String> tempMoves = new ArrayList<>(possMoves);
        possMoves.clear();
        return tempMoves;
    }
}
