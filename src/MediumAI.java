import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static java.lang.System.exit;

public class MediumAI {
    ChessBoard board;

    /**
     * constructor, sets the team and inputs the chess board
     * @param color - team color
     * @param board - chess board used for game
     */
    public MediumAI(Boolean color, ChessBoard board){
        this.board = board;
        if(color){
            team = board.getWhites();
            enTeam = board.getBlacks();
        }else{
            team = board.getBlacks();
            enTeam = board.getWhites();
        }
    }
    ArrayList<ChessPiece> team;
    ArrayList<ChessPiece> enTeam;
    boolean hasMoved;

    /**
     * moves a piece based on the position of the pieces on the board
     */
    public void movePiece(){
        if(board.teamCheckMate(team.get(0))){
            System.out.println("checkmate");
        }
        if(board.teamInStalemate(team.get(0))){
            System.out.println("stalemate");
        }
        hasMoved = false;
        while(!hasMoved){
            if(trade()){
                System.out.println("trade");
                hasMoved = true;
            }
            if(!hasMoved){
                if(moveInCheck()){
                    System.out.println("check");
                    hasMoved = true;
                }
            }
            if(!hasMoved){
                if(moveIfAttacked()){
                    System.out.println("attacked");
                    hasMoved = true;
                }
            }
            if(!hasMoved){
                if(movePawn()){
                    System.out.println("pawn");
                    hasMoved = true;
                }
            }
            if(!hasMoved){
                if(moveRandom()){
                    System.out.println("rando");
                    hasMoved = true;
                }
            }
        }
        board.toggleIsWhiteTurn();
    }

    /**
     * if the team is in check, this allows them to move
     * @return - true if a piece was moved.
     */
    public Boolean moveInCheck(){

        ArrayList<String> teamMoves = new ArrayList<>();
        ArrayList<String> enTeamMoves = new ArrayList<>();
        ArrayList<String> conflictingMoves = new ArrayList<>();
        for(ChessPiece teammate : team){
            if(!board.teamInCheck(teammate)){
                return false;
            }
            ArrayList<String> temp = board.possMoves(teammate);
            temp.removeAll(teamMoves);
            teamMoves.addAll(temp);
        }
        for(ChessPiece enemy : enTeam){
            ArrayList<String> temp = board.possMoves(enemy);
            temp.removeAll(enTeamMoves);
            ArrayList<String> enPos = new ArrayList<>();
            enTeamMoves.addAll(temp);
            for(String moves : teamMoves){
                if(!Objects.equals(moves, enemy.getPos())){
                    enPos.add(enemy.getPos());
                    break;
                }
            }
            enTeamMoves.addAll(enPos);
        }
        for(String move : teamMoves){
            for(String enMove : enTeamMoves){
                if(Objects.equals(move,enMove)){
                    conflictingMoves.add(move);
                }
            }
        }
        for(String possMoves : conflictingMoves){
            for(ChessPiece pce : team){
                String pcePos = pce.getPos();
                for(String move : board.possMoves(pce)){
                    if(Objects.equals(move, possMoves)){
                        pce.setPosition(move);
                        if (board.teamInCheck(pce)) {
                            pce.setPosition(pcePos);
                            break;
                        }else{
                            pce.setPosition(pcePos);
                            board.movePiece(pce, move);
                            return true;
                        }
                    }
                }
            }
        }
        for(ChessPiece teammate : team){
            if(teammate instanceof King){
                String piecePos = teammate.getPos();
                for(String move : board.possMoves(teammate)){
                    teammate.setPosition(move);
                    if(board.teamInCheck(teammate)){
                        teammate.setPosition(piecePos);
                        break;
                    }else{
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * if there is a piece available to trade, they will trade
     * @return - true if a piece is moved
     */
    public Boolean trade(){
        boolean canMove = false;
        for(ChessPiece teammate : team){
            String oldPos = teammate.getPos();
            if(!(teammate instanceof Pawn) && !(teammate instanceof Queen)){
                for(String move : board.possMoves(teammate)){
                    for(ChessPiece enemy: enTeam){
                        if(!(enemy instanceof Pawn)){
                            if(Objects.equals(enemy.getPos(), move)){
                                teammate.setPosition(move);
                                if(!board.teamInCheck(teammate) && canMove){
                                    teammate.setPosition(oldPos);
                                    board.movePiece(teammate, move);
                                }else{
                                    teammate.setPosition(oldPos);
                                }
                                if(!Objects.equals(teammate.getPos(), oldPos)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }else if(teammate instanceof Queen){
                for(String move : board.possMoves(teammate)){
                    for(ChessPiece enemy: enTeam){
                        canMove = true;
                            if(Objects.equals(enemy.getPos(), move)){
                                teammate.setPosition(move);
                                if(!(enemy instanceof Queen)){
                                    if(board.isAttacked(teammate)){
                                        canMove = false;
                                    }
                                }
                                if(canMove){
                                    if(!board.teamInCheck(teammate)){
                                        teammate.setPosition(oldPos);
                                        board.movePiece(teammate, move);
                                    }else{
                                        teammate.setPosition(oldPos);
                                    }
                                }else{
                                    teammate.setPosition(oldPos);
                                }
                                if(!Objects.equals(teammate.getPos(), oldPos)){
                                    return true;
                                }
                            }
                        }
                    }
            }else{
                for(String move : board.possMoves(teammate)){
                    for(ChessPiece enemy: enTeam){
                        if(Objects.equals(enemy.getPos(), move)){
                            teammate.setPosition(move);
                            if(!board.teamInCheck(teammate)){
                                teammate.setPosition(oldPos);
                                board.movePiece(teammate, move);
                            }else{
                                teammate.setPosition(oldPos);
                            }
                            if(!Objects.equals(teammate.getPos(), oldPos)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * if a piece is attacked, they will move
     * @return - true if piece is moved
     */
    public Boolean moveIfAttacked(){
        for(ChessPiece teammate : team){
            String oldPos = teammate.getPos();
            if(!(teammate instanceof Pawn)){
                if(board.isAttacked(teammate)){
                    for(String move: board.possMoves(teammate)){
                        teammate.setPosition(move);
                        if(!board.teamInCheck(teammate) && !board.isAttacked(teammate)){
                            teammate.setPosition(oldPos);
                            board.movePiece(teammate, move);
                        }else{
                            teammate.setPosition(oldPos);
                        }
                        if(!Objects.equals(teammate.getPos(), oldPos)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * moves a random pawn
     * @return true if a piece is moved
     */
    public Boolean movePawn(){
        Random rand = new Random();
        ArrayList<ChessPiece> pawns = new ArrayList<>();
        for(ChessPiece teammate: team){
            if(teammate instanceof Pawn){
                pawns.add(teammate);
            }
        }
        ChessPiece teammate;
        if(pawns.size()>0){
            teammate = pawns.get(rand.nextInt(0, pawns.size()));

        }else{
            return false;
        }
        String randPos;
        String oldPos = teammate.getPos();
        if(board.possMoves(teammate).size()>0){
            randPos = board.possMoves(teammate).get(rand.nextInt(0, board.possMoves(teammate).size()));
        }else{
            return false;
        }
        teammate.setPosition(randPos);
        if(!board.teamInCheck(teammate)){
            teammate.setPosition(oldPos);
            board.movePiece(teammate, randPos);
        }else{
            teammate.setPosition(oldPos);
        }
        return !Objects.equals(oldPos, teammate.getPos());
    }

    /**
     * moves a random piece
     * @return true if a piece is moved
     */
    public Boolean moveRandom(){
        Random rand = new Random();
        ChessPiece randPiece = team.get(rand.nextInt(0, team.size()));
        String oldPos = randPiece.getPos();
        String randPos;
        if(board.possMoves(randPiece).size()>0){
            randPos = board.possMoves(randPiece).get(rand.nextInt(0, board.possMoves(randPiece).size()));
        }else{
            return false;
        }
        randPiece.setPosition(randPos);
        if(!board.teamInCheck(randPiece) && !board.isAttacked(randPiece)){
            randPiece.setPosition(oldPos);
            board.movePiece(randPiece, randPos);
        }else{
            randPiece.setPosition(oldPos);
        }
        return !Objects.equals(oldPos, randPiece.getPos());
    }

}
