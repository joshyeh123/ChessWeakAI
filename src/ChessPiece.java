import java.util.ArrayList;

public abstract class ChessPiece {
    int row;
    int column;
    String pos;
    Boolean white;

    /**
     * Constructor, sets position and color (black or white)
     * @param position - position of piece
     * @param color - true for white, false for black
     */
    public ChessPiece(String position, Boolean color){
        pos = position;
        setRow();
        setColumn();
        white = color;
    }

    /**
     * sets row to the second digit of position
     */
    public void setRow(){
        row = Integer.parseInt(pos.substring(1,2));
    }

    /**
     * returns row
     * @return row - value of row
     */
    public int getRow(){
        return row;
    }

    /**
     * sets column to first char of position
     */
    public void setColumn(){
        column =(pos.charAt(0))-'`';
    }

    /**
     * returns column
     * @return column - value of column (char)
     */
    public char getColumn(){
        return (char)(column+'`');
    }

    /**
     * returns the integer ASCII value of the column character
     * @return column - value of column as integer
     */
    public int getColumnInt(){
        return column;
    }

    /**
     * prints to console the position and possible moves of piece.
     * @return
     */
    public String toString() {
        String str = "";
        str+=getColumn();
        str+=getRow();
        //return "Position: " + str + ", Possible Moves: " + canMoveTo();
        return str;
    }

    /**
     * sets position to inputted coordinate
     * @param coordinates - where the position user wishes to move the piece
     */
    public void setPosition(String coordinates){
        pos = coordinates;
        setRow();
        setColumn();
    }

    /**
     * returns the color of piece
     * @return white - color of piece (true or false)
     */
    public Boolean isWhite(){
        return white;
    }

    /**
     * returns position
     * @return pos - position of piece
     */
    public String getPos(){
        return pos;
    }

    /**
     * Gives the possible places a piece can move in an ArrayList
     * @return possMoves - the array list of possible moves.
     */
    abstract ArrayList<String> canMoveTo();
}
