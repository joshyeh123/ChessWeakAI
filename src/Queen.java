import java.util.ArrayList;

public class Queen extends ChessPiece{
    ArrayList<String> possMoves = new ArrayList<>();
    public Queen(String position, Boolean color) {
        super(position, color);
    }

    @Override
    ArrayList<String> canMoveTo() {
        checkCross();
        checkDiagonal();
        ArrayList<String> tempMoves = new ArrayList<>(possMoves);
        possMoves.clear();
        return tempMoves;
    }
    public void checkCross(){
        int counter=getColumnInt();
        String str = "";
        while(counter <= 8){
            if(counter!=getColumnInt()){
                str+= (char)(counter+'`');
                str+=getRow();
                possMoves.add(str);
                str="";
            }
            counter++;
        }
        counter=getColumnInt();
        while(counter >= 1){
            if(counter!=getColumnInt()){
                str+= (char)(counter+'`');
                str+=getRow();
                possMoves.add(str);
                str="";
            }
            counter--;
        }
        counter = getRow();
        while(counter<=8){
            if(counter!=getRow()){
                str+= getColumn();
                str+=counter;
                possMoves.add(str);
                str="";
            }
            counter++;
        }
        counter=getRow();
        while(counter>=1){
            if(counter!=getRow()){
                str+= getColumn();
                str+=counter;
                possMoves.add(str);
                str="";
            }
            counter--;
        }
    }
    public void checkDiagonal(){
        int rowCounter = getRow();
        int columnCounter = getColumnInt();
        String str = "";
        while(rowCounter <=8 && columnCounter <=8){
            if(rowCounter!=getRow()&&columnCounter!=getColumnInt()){
                str+=(char)(columnCounter+'`');
                str+=rowCounter;
                possMoves.add(str);
                str="";
            }
            columnCounter++;
            rowCounter++;
        }
        columnCounter=getColumnInt();
        rowCounter=getRow();
        while(rowCounter<=8 && columnCounter >=1){
            if(rowCounter!=getRow()&&columnCounter!=getColumnInt()){
                str+=(char)(columnCounter+'`');
                str+=rowCounter;
                possMoves.add(str);
                str="";
            }
            columnCounter--;
            rowCounter++;
        }
        columnCounter=getColumnInt();
        rowCounter=getRow();
        while(rowCounter >=1 && columnCounter <= 8){
            if(rowCounter!=getRow()&&columnCounter!=getColumnInt()){
                str+=(char)(columnCounter+'`');
                str+=rowCounter;
                possMoves.add(str);
                str="";
            }
            columnCounter++;
            rowCounter--;
        }
        columnCounter=getColumnInt();
        rowCounter=getRow();
        while(rowCounter >=1 && columnCounter >=1){
            if(rowCounter!=getRow()&&columnCounter!=getColumnInt()){
                str+=(char)(columnCounter+'`');
                str+=rowCounter;
                possMoves.add(str);
                str="";
            }
            columnCounter--;
            rowCounter--;
        }
    }
}