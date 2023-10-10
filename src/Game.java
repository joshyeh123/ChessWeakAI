import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Game {
    ArrayList<ChessPiece> whites = new ArrayList<>();
    ArrayList<ChessPiece> blacks = new ArrayList<>();
    ChessBoard board = new ChessBoard(whites, blacks);
    JButton[][] buttonBoard;
    String gameMode="";
    Boolean isTurn=false;
    EasyAI easyAI;
    MediumAI mediumAI;
    Color c = Color.GRAY;

    public Game() throws FileNotFoundException {
        Rook a1rook = new Rook("a1", true);
        Knight b1knight = new Knight("b1", true);
        Bishop c1bishop = new Bishop("c1", true);
        Queen d1queen = new Queen("d1", true);
        King e1king = new King("e1", true);
        Bishop f1bishop = new Bishop("f1", true);
        Knight g1knight = new Knight("g1", true);
        Rook h1rook = new Rook("h1", true);
        Pawn a2pawn = new Pawn("a2", true);
        Pawn b2pawn = new Pawn("b2", true);
        Pawn c2pawn = new Pawn("c2", true);
        Pawn d2pawn = new Pawn("d2", true);
        Pawn e2pawn = new Pawn("e2", true);
        Pawn f2pawn = new Pawn("f2", true);
        Pawn g2pawn = new Pawn("g2", true);
        Pawn h2pawn = new Pawn("h2", true);
        Rook a8rook = new Rook("a8", false);
        Knight b8knight = new Knight("b8", false);
        Bishop c8bishop = new Bishop("c8", false);
        Queen d8queen = new Queen("d8", false);
        King e8king = new King("e8", false);
        Bishop f8bishop = new Bishop("f8", false);
        Knight g8knight = new Knight("g8", false);
        Rook h8rook = new Rook("h8", false);
        Pawn a7pawn = new Pawn("a7", false);
        Pawn b7pawn = new Pawn("b7", false);
        Pawn c7pawn = new Pawn("c7", false);
        Pawn d7pawn = new Pawn("d7", false);
        Pawn e7pawn = new Pawn("e7", false);
        Pawn f7pawn = new Pawn("f7", false);
        Pawn g7pawn = new Pawn("g7", false);
        Pawn h7pawn = new Pawn("h7", false);
        whites.add(a1rook);
        whites.add(b1knight);
        whites.add(c1bishop);
        whites.add(d1queen);
        whites.add(e1king);
        whites.add(f1bishop);
        whites.add(g1knight);
        whites.add(h1rook);
        whites.add(a2pawn);
        whites.add(b2pawn);
        whites.add(c2pawn);
        whites.add(d2pawn);
        whites.add(e2pawn);
        whites.add(f2pawn);
        whites.add(g2pawn);
        whites.add(h2pawn);
        blacks.add(a8rook);
        blacks.add(b8knight);
        blacks.add(c8bishop);
        blacks.add(d8queen);
        blacks.add(e8king);
        blacks.add(f8bishop);
        blacks.add(g8knight);
        blacks.add(h8rook);
        blacks.add(a7pawn);
        blacks.add(b7pawn);
        blacks.add(c7pawn);
        blacks.add(d7pawn);
        blacks.add(e7pawn);
        blacks.add(f7pawn);
        blacks.add(g7pawn);
        blacks.add(h7pawn);
        board.toggleIsWhiteTurn();


        //game layout
        JFrame frame = new JFrame();
        JFrame frame2=new JFrame();
        JLabel winText = new JLabel("White Won!!!");
        winText.setHorizontalAlignment(JLabel.CENTER);
        frame2.add(winText);
        JLabel label = new JLabel("Chess Game");
        label.setHorizontalTextPosition(SwingConstants.RIGHT);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        JMenuItem submenu = new JMenuItem("Close");
        submenu.setActionCommand("close");
        mainPanel.add(tools, BorderLayout.PAGE_START);
        JPanel panel = new JPanel(new GridLayout(8, 8, 0, 0));
        buttonBoard = new JButton[8][8];

        class AddListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                /**if(easyAI.getGameOver()){
                 frame2.setVisible(true);
                 }**/
                String fullPosition = event.getActionCommand();
                boolean moved = false;
                if(board.getIsWhiteTurn()) {
                    for (int i = 0; i < whites.size(); i++) {
                        if (whites.get(i).toString().equals(board.getLastClick())) {
                            //System.out.println(board.possMoves(whites.get(i)));
                            moved = board.movePiece(whites.get(i), fullPosition);
                        }
                    }
                }else {
                    for (int i = 0; i < blacks.size(); i++) {
                        if (blacks.get(i).toString().equals(board.getLastClick())) {
                            //System.out.println(board.possMoves(whites.get(i)));
                            moved = board.movePiece(blacks.get(i), fullPosition);
                        }
                    }
                }

                if (!moved) {
                    board.setLastClick(fullPosition);
                }
                else{
                    board.toggleIsWhiteTurn();
                    board.setLastClick("");
                    if(board.teamCheckMate(whites.get(0))){
                        winText.setText("Black Won!");
                        frame2.setVisible(true);
                        frame.setVisible(false);
                    }else if(board.teamInStalemate(whites.get(0))){
                        winText.setText("Stalemate.");
                        frame2.setVisible(true);
                        frame.setVisible(false);
                    }
                    if(board.teamCheckMate(blacks.get(0))){
                        winText.setText("White Won!");
                        frame2.setVisible(true);
                        frame.setVisible(false);
                    }else if(board.teamInStalemate(blacks.get(0))){
                        winText.setText("Stalemate.");
                        frame2.setVisible(true);
                        frame.setVisible(false);
                    }
                    if(gameMode=="easy"){
                        if(board.teamCheckMate(whites.get(0))){
                            winText.setText("Black Won!");
                            frame2.setVisible(true);
                        }else if(board.teamInStalemate(whites.get(0))){
                            winText.setText("Stalemate.");
                            frame2.setVisible(true);
                        }
                        else if(board.teamCheckMate(blacks.get(0))){
                            winText.setText("White Won!");
                            frame2.setVisible(true);
                        }else if(board.teamInStalemate(blacks.get(0))){
                            winText.setText("Stalemate.");
                            frame2.setVisible(true);
                        }else{
                            easyAI.movePiece();
                        }
                    }else if(gameMode=="hard"){
                        if(board.teamCheckMate(whites.get(0))){
                            winText.setText("Black Won!");
                            frame2.setVisible(true);
                        }else if(board.teamInStalemate(whites.get(0))){
                            winText.setText("Stalemate.");
                            frame2.setVisible(true);
                        }
                        else if(board.teamCheckMate(blacks.get(0))){
                            winText.setText("White Won!");
                            frame2.setVisible(true);
                        }else if(board.teamInStalemate(blacks.get(0))){
                            winText.setText("Stalemate.");
                            frame2.setVisible(true);
                        }else{
                            mediumAI.movePiece();
                        }
                    }
                }
                updateBoard();
            }
        }
        ActionListener listener = new AddListener();

        //sets up board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                String numberPosition = "" + (8 - i);
                char letterPosition = (char) ('a' + j);
                String fullPosition = Character.toString(letterPosition) + numberPosition;
                button.setActionCommand(fullPosition);
                if (i % 2 == j % 2) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(c);
                }
                button.setMargin(new Insets(0, 0, 0, 0));
                if (i == 7) {
                    char letterChar = (char) ('a' + j);
                    button.setText(Character.toString(letterChar));
                    button.setVerticalTextPosition(SwingConstants.BOTTOM);
                    button.setHorizontalTextPosition(SwingConstants.RIGHT);
                    if (j % 2 == 0)
                        button.setForeground(Color.WHITE);
                }
                if (j == 0) {
                    String position = "" + (8 - i);
                    button.setText(position);
                    button.setVerticalTextPosition(SwingConstants.TOP);
                    button.setHorizontalTextPosition(SwingConstants.LEFT);
                    if (i % 2 == 1)
                        button.setForeground(Color.WHITE);
                }

                buttonBoard[i][j] = button;

                panel.add(button);
                button.addActionListener(listener);
            }
        }
        updateBoard();

        //menu screen
        JFrame frame1 = new JFrame();
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel menuPanel =  new JPanel(new GridLayout(10, 1, 10, 5));
        JRadioButton multiplayer=new JRadioButton("Multiplayer");
        multiplayer.setSelected(true);
        JRadioButton easy=new JRadioButton("Easy");
        JRadioButton hard=new JRadioButton("Hard");
        ButtonGroup group = new ButtonGroup();
        Button startGame = new Button("Start Game");
        JTextArea textArea = new JTextArea(5, 20);
        JLabel name = new JLabel("name: ");
        JScrollPane scrollPane = new JScrollPane(textArea);
        JLabel boardColor = new JLabel("Board Color: ");
        String[] colors = getBoardColors();
        JComboBox colorList = new JComboBox(colors);
        colorList.setSelectedIndex(3);
        JToggleButton white= new JToggleButton("Color");
        white.setBackground(Color.BLACK);
        white.setForeground(c);
        UIManager.put("ToggleButton.select", Color.WHITE);
        SwingUtilities.updateComponentTreeUI(white);
        multiplayer.setActionCommand("multi");
        easy.setActionCommand("easy");
        hard.setActionCommand("hard");
        white.setActionCommand("white");
        startGame.setActionCommand("startGame");
        class menuListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Ranny");
                if(event.getActionCommand().equals("startGame")){
                    System.out.println("ran");
                    label.setText(textArea.getText());
                    if(gameMode=="easy"){
                        easyAI = new EasyAI(!isTurn, board);
                        if(!isTurn)
                            easyAI.movePiece();
                    }else if(gameMode=="hard"){
                        mediumAI = new MediumAI(!isTurn, board);
                        if(!isTurn)
                            mediumAI.movePiece();
                    }
                    String squareColor = (String)colorList.getSelectedItem();
                    if(squareColor=="BLACK")
                        c=Color.BLACK;
                    else if(squareColor=="GREEN")
                        c=Color.GREEN;
                    else if(squareColor=="RED")
                        c=Color.RED;
                    else if(squareColor=="GRAY")
                        c=Color.GRAY;
                    updateBoard();
                    frame.setVisible(true);
                    frame1.setVisible(false);
                }else if(event.getActionCommand().equals("white")){
                    if(isTurn) {
                        isTurn = false;
                    }else {
                        isTurn = true;
                    }
                }else if(event.getActionCommand().equals("close")){
                    exit(0);
                }else{
                    System.out.println("ran2");
                    gameMode=event.getActionCommand();
                }
            }
        }
        ActionListener menuListener = new menuListener();
        multiplayer.addActionListener(menuListener);
        easy.addActionListener(menuListener);
        hard.addActionListener(menuListener);
        white.addActionListener(menuListener);
        startGame.addActionListener(menuListener);
        colorList.addActionListener(menuListener);
        submenu.addActionListener(menuListener);
        group.add(multiplayer);
        group.add(easy);
        group.add(hard);
        menuPanel.add(multiplayer);
        menuPanel.add(easy);
        menuPanel.add(hard);
        menuPanel.add(white);
        menuPanel.add(name);
        menuPanel.add(textArea);
        menuPanel.add(boardColor);
        menuPanel.add(colorList);
        menuPanel.add(startGame);
        layout.add(menuPanel);
        frame1.add(layout, BorderLayout.CENTER);



        menu.add(submenu);
        menuBar.add(menu);
        mainPanel.add(menuBar);
        mainPanel.add(tools);
        tools.add(label);
        mainPanel.add(panel);
        frame.add(mainPanel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(800, 800);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(800, 800);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
        //frame2.setVisible(true);
        File file = new File("src/Doo.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
        } catch(Exception e){

        }
    }

    public void updateBoard() {
        ImageIcon whitePawn = new ImageIcon("src/whitepawn.png");
        ImageIcon whiteBishop = new ImageIcon("src/whitebishop.png");
        ImageIcon whiteKing = new ImageIcon("src/whiteking.png");
        ImageIcon whiteKnight = new ImageIcon("src/whiteknight.png");
        ImageIcon whiteRook = new ImageIcon("src/whiterook.png");
        ImageIcon whiteQueen = new ImageIcon("src/whitequeen.png");
        ImageIcon blackBishop = new ImageIcon("src/blackbishop.png");
        ImageIcon blackKing = new ImageIcon("src/blackking.png");
        ImageIcon blackKnight = new ImageIcon("src/blackknight.png");
        ImageIcon blackPawn = new ImageIcon("src/blackpawn.png");
        ImageIcon blackRook = new ImageIcon("src/blackrook.png");
        ImageIcon blackQueen = new ImageIcon("src/blackqueen.png");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttonBoard[i][j].setIcon(null);
                if (i % 2 == j % 2) {
                    buttonBoard[i][j].setBackground(Color.WHITE);
                } else {
                    buttonBoard[i][j].setBackground(c);
                }
            }
        }
        for (int i = 0; i < whites.size(); i++) {
            String pos = whites.get(i).toString();
            int numberPos = Character.getNumericValue(pos.charAt(1));
            int iIndex = 8 - numberPos;
            int jIndex = pos.charAt(0) - 'a';
            if (whites.get(i) instanceof Pawn) {
                buttonBoard[iIndex][jIndex].setIcon(whitePawn);
            } else if (whites.get(i) instanceof Bishop) {
                buttonBoard[iIndex][jIndex].setIcon(whiteBishop);
            } else if (whites.get(i) instanceof Knight) {
                buttonBoard[iIndex][jIndex].setIcon(whiteKnight);
            } else if (whites.get(i) instanceof Rook) {
                buttonBoard[iIndex][jIndex].setIcon(whiteRook);
            } else if (whites.get(i) instanceof Queen) {
                buttonBoard[iIndex][jIndex].setIcon(whiteQueen);
            } else if (whites.get(i) instanceof King) {
                buttonBoard[iIndex][jIndex].setIcon(whiteKing);
            }
        }
        for (int i = 0; i < blacks.size(); i++) {
            String pos = blacks.get(i).toString();
            int numberPos = Character.getNumericValue(pos.charAt(1));
            int iIndex = 8 - numberPos;
            int jIndex = pos.charAt(0) - 'a';
            if (blacks.get(i) instanceof Pawn) {
                buttonBoard[iIndex][jIndex].setIcon(blackPawn);
            } else if (blacks.get(i) instanceof Bishop) {
                buttonBoard[iIndex][jIndex].setIcon(blackBishop);
            } else if (blacks.get(i) instanceof Knight) {
                buttonBoard[iIndex][jIndex].setIcon(blackKnight);
            } else if (blacks.get(i) instanceof Rook) {
                buttonBoard[iIndex][jIndex].setIcon(blackRook);
            } else if (blacks.get(i) instanceof Queen) {
                buttonBoard[iIndex][jIndex].setIcon(blackQueen);
            } else if (blacks.get(i) instanceof King) {
                buttonBoard[iIndex][jIndex].setIcon(blackKing);
            }
        }
        //highlight moves
        ArrayList<String> possible=new ArrayList<>();
        if(board.getIsWhiteTurn()) {
            for (int i = 0; i < whites.size(); i++) {
                if (whites.get(i).toString().equals(board.getLastClick())) {
                    possible = board.possMoves(whites.get(i));
                }
            }
        }else{
            for (int i = 0; i < blacks.size(); i++) {
                if (blacks.get(i).toString().equals(board.getLastClick())) {
                    possible = board.possMoves(blacks.get(i));
                }
            }
        }
        for(int i = 0; i < possible.size(); i++){
            int numberPos = Character.getNumericValue(possible.get(i).charAt(1));
            int iIndex = 8 - numberPos;
            int jIndex = possible.get(i).charAt(0) - 'a';
            buttonBoard[iIndex][jIndex].setBackground(Color.darkGray);
        }
    }
    public String[] getBoardColors() throws FileNotFoundException {
        File colors = new File("src/colors.txt");
        Scanner file = new Scanner(colors);
        ArrayList<String> colorArrayList = new ArrayList<>();
        while(file.hasNext()){
            colorArrayList.add(file.next());
        }
        String[] colorArray = new String[colorArrayList.size()];
        for(int i = 0; i<colorArrayList.size();i++){
            colorArray[i] = colorArrayList.get(i);
        }
        return colorArray;
    }
}
