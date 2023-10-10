import java.util.ArrayList;
import java.util.Objects;

public class ChessBoard {
    ArrayList<ChessPiece> whites;
    ArrayList<ChessPiece> blacks;
    String lastClick;
    boolean isWhiteTurn;

    /**
     * gets the last click of the user
     * @return lastClick - the last click of the user.
     */
    public String getLastClick() {
        return lastClick;
    }

    /**
     * sets the last click of the user
     * @param lastClick - takes in input of the last click and sets the instance variable to the input.
     */
    public void setLastClick(String lastClick) {
        this.lastClick = lastClick;
    }

    /**
     * checks whose turn it is.
     * @return
     */

    public Boolean getIsWhiteTurn() {
        return isWhiteTurn;
    }

    /**
     * if one side is done with their turn, it switches to the other.
     */
    public void toggleIsWhiteTurn() {
        isWhiteTurn = !isWhiteTurn;
    }

    /**
     * the constructor; sets the teams of the pieces and takes in the pieces on the board.
     * @param whites - the white pieces on the board
     * @param blacks - the black pieces on the board
     */

    public ChessBoard(ArrayList whites, ArrayList blacks) {
        this.whites = whites;
        this.blacks = blacks;
        lastClick = "";
    }

    /**
     * Looks at the other pieces on the board and checks if the inputted piece can move there
     *
     * @param piece - piece that you wish to move
     * @return boolean - true if can move there or false if cannot.
     */
    public ArrayList<String> possMoves(Knight piece) {
        ArrayList<ChessPiece> team;
        if (piece.isWhite()) {
            team = whites;
        } else {
            team = blacks;
        }
        ArrayList<String> moves = new ArrayList<>();
        boolean canMove;
        for (int i = 0; i < piece.canMoveTo().size(); i++) {
            canMove = true;
            for (ChessPiece teammate : team) {
                if (Objects.equals(teammate.getPos(), piece.canMoveTo().get(i))) {
                    canMove = false;
                    break;
                }
            }
            if (canMove) {
                moves.add(piece.canMoveTo().get(i));
            }
        }
        return moves;
    }

    public ArrayList<String> checkCross(Queen piece) {
        ArrayList<ChessPiece> team;
        ArrayList<ChessPiece> enteam;
        if (piece.isWhite()) {
            team = whites;
            enteam = blacks;
        } else {
            team = blacks;
            enteam = whites;
        }
        int counter = piece.getColumnInt();
        ArrayList<String> moves = new ArrayList<>();
        boolean canMove = true;
        String str = "";
        while (counter <= 8) {
            if (counter != piece.getColumnInt()) {
                str += (char) (counter + '`');
                str += piece.getRow();
                for (ChessPiece enemy : enteam) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        moves.add(str);
                        canMove = false;
                        break;
                    }
                }
                for (ChessPiece teammate : team) {
                    if (Objects.equals(teammate.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                if (canMove) {
                    moves.add(str);
                } else {
                    break;
                }
                str = "";
            }
            counter++;
        }
        str = "";
        canMove = true;
        counter = piece.getColumnInt();
        while (counter >= 1) {
            if (counter != piece.getColumnInt()) {
                str += (char) (counter + '`');
                str += piece.getRow();
                for (ChessPiece teammate : team) {
                    if (Objects.equals(teammate.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                for (ChessPiece enemy : enteam) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        moves.add(str);
                        canMove = false;
                        break;
                    }
                }
                if (canMove) {
                    moves.add(str);
                } else {
                    break;
                }
                str = "";
            }
            counter--;
        }
        str = "";
        canMove = true;
        counter = piece.getRow();
        while (counter <= 8) {
            if (counter != piece.getRow()) {
                str += piece.getColumn();
                str += counter;
                for (ChessPiece enemy : enteam) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        moves.add(str);
                        canMove = false;
                        break;
                    }
                }
                for (ChessPiece teammate : team) {
                    if (Objects.equals(teammate.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                if (canMove) {
                    moves.add(str);
                } else {
                    break;
                }
                str = "";
            }
            counter++;
        }
        str = "";
        canMove = true;
        counter = piece.getRow();
        while (counter >= 1) {
            if (counter != piece.getRow()) {
                str += piece.getColumn();
                str += counter;
                for (ChessPiece enemy : enteam) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        moves.add(str);
                        canMove = false;
                        break;
                    }
                }
                for (ChessPiece teammate : team) {
                    if (Objects.equals(teammate.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                if (canMove) {
                    moves.add(str);
                } else {
                    break;
                }
                str = "";
            }
            counter--;
        }
        return moves;
    }

    public ArrayList<String> checkDiag(Queen piece) {
        ArrayList<ChessPiece> team;
        ArrayList<ChessPiece> enteam;
        if (piece.isWhite()) {
            team = whites;
            enteam = blacks;
        } else {
            team = blacks;
            enteam = whites;
        }
        ArrayList<String> moves = new ArrayList<>();
        boolean canMove = true;
        int rowCounter = piece.getRow();
        int columnCounter = piece.getColumnInt();
        String str = "";
        while (rowCounter <= 8 && columnCounter <= 8) {
            if (rowCounter != piece.getRow() && columnCounter != piece.getColumnInt()) {
                str += (char) (columnCounter + '`');
                str += rowCounter;
                for (ChessPiece enemy : enteam) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        moves.add(str);
                        canMove = false;
                        break;
                    }
                }
                for (ChessPiece teammate : team) {
                    if (Objects.equals(teammate.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                if (canMove) {
                    moves.add(str);
                } else {
                    break;
                }
                str = "";
            }
            columnCounter++;
            rowCounter++;
        }
        str = "";
        canMove = true;
        columnCounter = piece.getColumnInt();
        rowCounter = piece.getRow();
        while (rowCounter <= 8 && columnCounter >= 1) {
            if (rowCounter != piece.getRow() && columnCounter != piece.getColumnInt()) {
                str += (char) (columnCounter + '`');
                str += rowCounter;
                for (ChessPiece enemy : enteam) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        moves.add(str);
                        canMove = false;
                        break;
                    }
                }
                for (ChessPiece teammate : team) {
                    if (Objects.equals(teammate.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                if (canMove) {
                    moves.add(str);
                } else {
                    break;
                }
                str = "";
            }
            columnCounter--;
            rowCounter++;
        }
        str = "";
        canMove = true;
        columnCounter = piece.getColumnInt();
        rowCounter = piece.getRow();
        while (rowCounter >= 1 && columnCounter <= 8) {
            if (rowCounter != piece.getRow() && columnCounter != piece.getColumnInt()) {
                str += (char) (columnCounter + '`');
                str += rowCounter;
                for (ChessPiece enemy : enteam) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        moves.add(str);
                        canMove = false;
                        break;
                    }
                }
                for (ChessPiece teammate : team) {
                    if (Objects.equals(teammate.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                if (canMove) {
                    moves.add(str);
                } else {
                    break;
                }
                str = "";
            }
            columnCounter++;
            rowCounter--;
        }
        str = "";
        canMove = true;
        columnCounter = piece.getColumnInt();
        rowCounter = piece.getRow();
        while (rowCounter >= 1 && columnCounter >= 1) {
            if (rowCounter != piece.getRow() && columnCounter != piece.getColumnInt()) {
                str += (char) (columnCounter + '`');
                str += rowCounter;
                for (ChessPiece enemy : enteam) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        moves.add(str);
                        canMove = false;
                        break;
                    }
                }
                for (ChessPiece teammate : team) {
                    if (Objects.equals(teammate.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                if (canMove) {
                    moves.add(str);
                } else {
                    break;
                }
                str = "";
            }
            columnCounter--;
            rowCounter--;
        }
        return moves;
    }

    /**
     * checks the possible moves that a king can move
     * @param piece - the inputted piece, must be King type
     * @return an arraylist of the possible moves the king can move
     */
    public ArrayList<String> possMoves(King piece) {
        ArrayList<String> moves = new ArrayList<>();
        boolean canMove=true;
        ArrayList<ChessPiece> team;
        if (piece.isWhite()) {
            team = whites;
        } else {
            team = blacks;
        }
        for (int i = 0; i < piece.canMoveTo().size(); i++) {
            for (ChessPiece teammate : team) {
                if (Objects.equals(teammate.getPos(), piece.canMoveTo().get(i))) {
                    canMove = false;
                }
            }
            if (canMove) {
                moves.add(piece.canMoveTo().get(i));
            }
            canMove=true;
        }
        return moves;
    }

    /**
     * checks the possible moves a pawn can move
     * @param piece - input piece, must be a pawn
     * @return - an arraylist of possible moves
     */
    public ArrayList<String> possMoves(Pawn piece) {
        ArrayList<String> possMoves = new ArrayList<>();
        boolean canMove = true;
        if (piece.isWhite()) {
            String str = "";
            if (piece.getRow() + 1 <= 8) {
                str += piece.getColumn();
                str += piece.getRow() + 1;
                for (ChessPiece teammate : whites) {
                    if (Objects.equals(teammate.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                for (ChessPiece enemy : blacks) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                if (canMove) {
                    possMoves.add(str);
                }
                str = "";
                canMove = true;
                if (piece.getRow() == 2) {
                    str += piece.getColumn();
                    str += piece.getRow() + 2;
                    for (ChessPiece teammate : whites) {
                        if (Objects.equals(teammate.getPos(), str)) {
                            canMove = false;
                            break;
                        }
                    }
                    for (ChessPiece enemy : blacks) {
                        if (Objects.equals(enemy.getPos(), str)) {
                            canMove = false;
                            break;
                        }
                    }
                    if (canMove) {
                        if (!possMoves.isEmpty()) {
                            possMoves.add(str);
                        }
                    }
                }
                str = "";
                canMove = false;
                if (piece.getColumnInt() + 1 <= 8) {
                    str += (char) ((piece.getColumnInt() + 1) + '`');
                }
                str += piece.getRow() + 1;
                for (ChessPiece enemy : blacks) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        canMove = true;
                        break;
                    }
                }
                if (canMove) {
                    possMoves.add(str);
                }
                str = "";
                canMove = false;
                if (piece.getColumnInt() - 1 >= 1) {
                    str += (char) ((piece.getColumnInt() - 1) + '`');
                }
                str += piece.getRow() + 1;
                for (ChessPiece enemy : blacks) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        canMove = true;
                        break;
                    }
                }
                if (canMove) {
                    possMoves.add(str);
                }
            }
        } else {
            String str = "";
            if (piece.getRow() - 1 >= 1) {
                str += piece.getColumn();
                str += piece.getRow() - 1;
                for (ChessPiece teammate : blacks) {
                    if (Objects.equals(teammate.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                for (ChessPiece enemy : whites) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        canMove = false;
                        break;
                    }
                }
                if (canMove) {
                    possMoves.add(str);
                }
                str = "";
                canMove = true;
                if (piece.getRow() == 7) {
                    str += piece.getColumn();
                    str += piece.getRow() - 2;
                    for (ChessPiece teammate : blacks) {
                        if (Objects.equals(teammate.getPos(), str)) {
                            canMove = false;
                            break;
                        }
                    }
                    for (ChessPiece enemy : whites) {
                        if (Objects.equals(enemy.getPos(), str)) {
                            canMove = false;
                            break;
                        }
                    }
                    if (canMove) {
                        if (!possMoves.isEmpty()) {
                            possMoves.add(str);
                        }
                    }
                }
                str = "";
                canMove = false;
                if (piece.getColumnInt() + 1 <= 8) {
                    str += (char) ((piece.getColumnInt() + 1) + '`');
                }
                str += piece.getRow() - 1;
                for (ChessPiece enemy : whites) {
                    if (Objects.equals(enemy.getPos(), str)) {

                        canMove = true;
                        break;
                    }
                }
                if (canMove) {
                    possMoves.add(str);
                }
                str = "";
                canMove = false;
                if (piece.getColumnInt() - 1 >= 1) {
                    str += (char) ((piece.getColumnInt() - 1) + '`');
                }
                str += piece.getRow() - 1;
                for (ChessPiece enemy : whites) {
                    if (Objects.equals(enemy.getPos(), str)) {
                        canMove = true;
                        break;
                    }
                }
                if (canMove) {
                    possMoves.add(str);
                }
            }
        }

        return possMoves;
    }

    /**
     * returns the possible moves a bishop can go
     * @param piece - bishop piece
     * @return an arraylist of all possible moves of the bishop
     */
    public ArrayList<String> possMoves(Bishop piece) {
        return checkDiag(piece);
    }

    /**
     * returns the possible moves a rook can move
     * @param piece - Rook chess piece
     * @return an array list of all possible moves the rook can move
     */
    public ArrayList<String> possMoves(Rook piece) {
        return checkCross(piece);
    }

    /**
     * returns the possible move a queen can move
     * @param piece - queen chess piece
     * @return an array list of all possible moves the queen can move
     */
    public ArrayList<String> possMoves(Queen piece) {
        ArrayList<String> cross = checkCross(piece);
        ArrayList<String> diag = checkDiag(piece);
        cross.addAll(diag);
        return cross;
    }

    /**
     * checks if a move is legal, and moves it
     *
     * @param piece       is the piece to be moved
     * @param newPosition is the space you want to move to
     */
    public Boolean movePiece(ChessPiece piece, String newPosition) {
        ArrayList<ChessPiece> enTeam;
        ChessPiece enemy = null;
        if (piece.isWhite()) {
            enTeam = blacks;
        } else {
            enTeam = whites;
        }
        String oldPos = piece.getPos();
        if (piece instanceof Knight) {
            for (String str : possMoves((Knight) piece)) {
                if (Objects.equals(str, newPosition)) {
                    piece.setPosition(newPosition);
                    for (ChessPiece enPiece : enTeam) {
                        if (Objects.equals(enPiece.getPos(), newPosition)) {
                            enemy = enPiece;
                        }
                    }
                    enTeam.removeIf(enPiece -> Objects.equals(enPiece.getPos(), newPosition));
                    if (teamInCheck(piece)) {
                        piece.setPosition(oldPos);
                        if(enemy!=null){
                            enTeam.add(enemy);
                        }
                        break;
                    }
                    return true;
                }
            }
        } else if (piece instanceof Rook) {
            for (String str : possMoves((Rook) piece)) {
                if (Objects.equals(str, newPosition)) {
                    piece.setPosition(newPosition);
                    for (ChessPiece enPiece : enTeam) {
                        if (Objects.equals(enPiece.getPos(), newPosition)) {
                            enemy = enPiece;
                        }
                    }
                    enTeam.removeIf(enPiece -> Objects.equals(enPiece.getPos(), newPosition));
                    if (teamInCheck(piece)) {
                        piece.setPosition(oldPos);
                        if(enemy!=null){
                            enTeam.add(enemy);
                        }
                        break;
                    }
                    return true;
                }
            }
        } else if (piece instanceof Bishop) {
            for (String str : possMoves((Bishop) piece)) {
                if (Objects.equals(str, newPosition)) {
                    piece.setPosition(newPosition);
                    for (ChessPiece enPiece : enTeam) {
                        if (Objects.equals(enPiece.getPos(), newPosition)) {
                            enemy = enPiece;
                        }
                    }
                    enTeam.removeIf(enPiece -> Objects.equals(enPiece.getPos(), newPosition));
                    if (teamInCheck(piece)) {
                        piece.setPosition(oldPos);
                        if(enemy!=null){
                            enTeam.add(enemy);
                        }
                        break;
                    }
                    return true;
                }
            }

        } else if (piece instanceof Queen) {
            for (String str : possMoves((Queen) piece)) {
                if (Objects.equals(str, newPosition)) {
                    piece.setPosition(newPosition);
                    for (ChessPiece enPiece : enTeam) {
                        if (Objects.equals(enPiece.getPos(), newPosition)) {
                            enemy = enPiece;
                        }
                    }
                    enTeam.removeIf(enPiece -> Objects.equals(enPiece.getPos(), newPosition));
                    if (teamInCheck(piece)) {
                        piece.setPosition(oldPos);
                        if(enemy!=null){
                            enTeam.add(enemy);
                        }
                        break;
                    }
                    return true;
                }
            }

        } else if (piece instanceof King) {
            for (String str : possMoves((King) piece)) {
                if (Objects.equals(str, newPosition)) {
                    piece.setPosition(newPosition);
                    for (ChessPiece enPiece : enTeam) {
                        if (Objects.equals(enPiece.getPos(), newPosition)) {
                            enemy = enPiece;
                        }
                    }
                    enTeam.removeIf(enPiece -> Objects.equals(enPiece.getPos(), newPosition));
                    if (teamInCheck(piece)) {
                        piece.setPosition(oldPos);
                        if(enemy!=null){
                            enTeam.add(enemy);
                        }
                        break;
                    }
                    return true;
                }
            }

        } else if (piece instanceof Pawn) {
            for (String str : possMoves((Pawn) piece)) {
                if (Objects.equals(str, newPosition)) {
                    piece.setPosition(newPosition);
                    for (ChessPiece enPiece : enTeam) {
                        if (Objects.equals(enPiece.getPos(), newPosition)) {
                            enemy = enPiece;
                        }
                    }
                    enTeam.removeIf(enPiece -> Objects.equals(enPiece.getPos(), newPosition));
                    if (teamInCheck(piece)) {
                        piece.setPosition(oldPos);
                        if(enemy!=null){
                            enTeam.add(enemy);
                        }
                        break;
                    }
                    if (piece.isWhite()) {
                        if (piece.getRow() == 8) {
                            whites.remove(piece);
                            whites.add(new Queen(newPosition, true));
                        }
                    } else {
                        if (piece.getRow() == 1) {
                            blacks.remove(piece);
                            blacks.add(new Queen(newPosition, false));
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * this allows the programmer to call possMoves with any chess piece and then calls the overloaded method according to the piece
     * @param piece - chesspiece
     * @return list of possible moves for the inputted chess piece
     */
    public ArrayList<String> possMoves(ChessPiece piece) {
        ArrayList<String> tempMoves = new ArrayList<>();
        if (piece instanceof Knight) {
            tempMoves = possMoves((Knight) piece);
        } else if (piece instanceof Rook) {
            tempMoves = possMoves((Rook) piece);
        } else if (piece instanceof Bishop) {
            tempMoves = possMoves((Bishop) piece);
        } else if (piece instanceof Queen) {
            tempMoves = possMoves((Queen) piece);
        } else if (piece instanceof King) {
            tempMoves = possMoves((King) piece);
        } else if (piece instanceof Pawn) {
            tempMoves = possMoves((Pawn) piece);
        }
        return tempMoves;
    }

    /**
     * checks if piece is attacked
     * @param piece - any ChessPiece
     * @return a true or false value - true if attacked and false otherwise
     */

    public Boolean isAttacked(ChessPiece piece) {
        ArrayList<ChessPiece> enTeam;
        if (piece.isWhite()) {
            enTeam = blacks;
        } else {
            enTeam = whites;
        }
        for (ChessPiece enPiece : enTeam) {
            if(!(enPiece instanceof Pawn)){
                for (String move : possMoves(enPiece)) {
                    if (Objects.equals(move, piece.getPos())) {
                        return true;
                    }
                }
            }
            if(enPiece instanceof Pawn){
                if(!enPiece.isWhite()){
                    String str = String.valueOf((char) ((piece.getColumnInt() + 1) + '`'));
                    str+=(enPiece.getRow()-1);
                    if(Objects.equals(piece.getPos(), str)){
                        return true;
                    }
                    str = String.valueOf((char) ((piece.getColumnInt() - 1) + '`'));
                    str+=(enPiece.getRow()-1);
                    if(Objects.equals(piece.getPos(), str)){
                        return true;
                    }
                }else{
                    String str = String.valueOf((char) ((piece.getColumnInt() + 1) + '`'));
                    str+=(enPiece.getRow()+1);
                    if(Objects.equals(piece.getPos(), str)){
                        return true;
                    }
                    str = String.valueOf((char) ((piece.getColumnInt() - 1) + '`'));
                    str+=(enPiece.getRow()+1);
                    if(Objects.equals(piece.getPos(), str)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * if team is in check, returns true
     * @param pce - any chess piece
     * @return - true if team is in check, false otherwise
     */

    public Boolean teamInCheck(ChessPiece pce) {
        ArrayList<ChessPiece> team;
        if (pce.isWhite()) {
            team = whites;
        } else {
            team = blacks;
        }
        for (ChessPiece piece : team) {
            if (piece instanceof King) {
                if (isAttacked(piece)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    /**
     * returns the arraylist of black pieces
     * @return black pieces
     */

    public ArrayList<ChessPiece> getBlacks() {
        return blacks;
    }

    /**
     * returns the arraylist of white pieces
     * @return white pieces
     */

    public ArrayList<ChessPiece> getWhites() {
        return whites;
    }

    /**
     * checks if team is in mate
     * @param piece - any piece
     * @return - true if team is in stalemate, false otherwise
     */
    public Boolean teamCheckMate(ChessPiece piece) {
        if (teamInCheck(piece)) {
            return teamInStalemate(piece);
        }
        return false;
    }

    /**
     * this is very similar to checkmate, but without the check. If a side has no possible moves and not in check, they are in stalemate
     * @param piece - any chess piece
     * @return true if in stalemate, false otherwise
     */
    public Boolean teamInStalemate(ChessPiece piece){
        ArrayList<ChessPiece> team;
        ArrayList<ChessPiece> enTeam;
        ArrayList<String> allTeamMoves = new ArrayList<>();
        ArrayList<String> allenTeamMoves = new ArrayList<>();
        ArrayList<String> conflictingMoves = new ArrayList<>();
        boolean checkMate = false;
        if(piece.isWhite()) {
            team = whites;
            enTeam = blacks;
        }else{
            team = blacks;
            enTeam = whites;
        }
        for(ChessPiece teammate : team){
            ArrayList<String> temp = possMoves(teammate);
            temp.removeAll(allTeamMoves);
            allTeamMoves.addAll(temp);
        }
        for(ChessPiece enemy : enTeam){
            ArrayList<String> temp = possMoves(enemy);
            temp.removeAll(allenTeamMoves);
            ArrayList<String> enPos = new ArrayList<>();
            allenTeamMoves.addAll(temp);
            for(String moves : allenTeamMoves){
                if(!Objects.equals(moves, enemy.getPos())){
                    enPos.add(enemy.getPos());
                    break;
                }
            }
            allenTeamMoves.addAll(enPos);
        }
        for(String move : allTeamMoves){
            for(String enMove : allenTeamMoves){
                if(Objects.equals(move,enMove)){
                    conflictingMoves.add(move);
                }
            }
        }
        ChessPiece enemy = null;
        for(String possMoves : conflictingMoves){
            for(ChessPiece pce : team){
                piece = pce;
                String pcePos = pce.getPos();
                for(String move : possMoves(pce)){
                    if(Objects.equals(move, possMoves)){
                        piece.setPosition(move);
                        for (ChessPiece enPiece : enTeam) {
                            if (Objects.equals(enPiece.getPos(), move)) {
                                enemy = enPiece;
                            }
                        }
                        enTeam.removeIf(enPiece -> Objects.equals(enPiece.getPos(), move));

                        if (teamInCheck(piece)) {
                            piece.setPosition(pcePos);
                            if(enemy!=null){
                                enTeam.add(enemy);
                            }
                            checkMate = true;
                        }else{
                            piece.setPosition(pcePos);
                            if(enemy!=null){
                                enTeam.add(enemy);
                            }
                            return false;
                        }
                    }
                }
            }
        }
        //king can move
        for(ChessPiece teammate : team){
            if(teammate instanceof King){
                piece = teammate;
                String piecePos = piece.getPos();
                for(String move : possMoves(teammate)){
                    piece.setPosition(move);
                    if(teamInCheck(piece)){
                        piece.setPosition(piecePos);
                        checkMate = true;
                    }else{
                        piece.setPosition(piecePos);
                        return false;
                    }
                }
            }
        }
        return checkMate;
    }
}
