package io.github.reeceyang.tictactoe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class TicTacToe extends JFrame {
    private JButton[] cells;
    private JTextField jumbotron;
    private Board board;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu helpMenu;
    private JMenuItem menuItem;
    private JPanel windowPanel;
    private JPanel cellPanel;
    private boolean onePlayerMode = false;
    private int BOARD_LENGTH = 3;

    public TicTacToe() {
        windowPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        windowPanel.setLayout(borderLayout);

        setMenuBar();
        setJumbotron();
        setCellPanel();
        setFrame();
        newGame();
    }

    private void setFrame() {
        setContentPane(windowPanel);
        setJMenuBar(menuBar);
        setTitle("Tic-Tac-Toe");
        setSize(600, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setCellPanel() {
        // Construct and set GridLayout layout manager
        cellPanel = new JPanel();
        GridLayout gL2 = new GridLayout(BOARD_LENGTH, BOARD_LENGTH);
        cellPanel.setLayout(gL2);

        cells = new JButton[BOARD_LENGTH * BOARD_LENGTH];
        board = new Board(BOARD_LENGTH);
        class CellActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                if (!clickedButton.getText().equals("X") && !clickedButton.getText().equals("O")) {
                    // The cell is empty
                    // Find which button was clicked
                    int clickedButtonHashCode = clickedButton.hashCode();
                    int index;
                    for (index = 0; index < BOARD_LENGTH * BOARD_LENGTH; index++) {
                        if (clickedButtonHashCode == cells[index].hashCode()) {
                            // The clicked button is cells[index]
                            break;
                        }
                    }
                    // Find out who's turn it is
                    Piece mover = board.getTurnPiece();
                    // Set the clicked button's text to that piece
                    cells[index].setText(mover.toString());
                    // Place the piece in the corresponding cell on the board
                    board.place(index / BOARD_LENGTH, index % BOARD_LENGTH, mover);
                    System.out.println("Button clicked");
                    if (board.isEndPosition()) {
                        showGameOverDialog();
                    } else {
                        // Next turn
                        board.nextTurn();
                        updateJumbotron();
                    }
                    computerMove();
                }
            }
        }
        for (int i = 0; i < BOARD_LENGTH * BOARD_LENGTH; i++) {
            cells[i] = new JButton("");
            ActionListener cellListener = new CellActionListener();
            cells[i].addActionListener(cellListener);
            cellPanel.add(cells[i]);
        }
        windowPanel.add("Center", cellPanel);
    }

    private void setMenuBar() {
        menuBar = new JMenuBar();

        setFileMenu();

        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(editMenu);

        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuItem = new JMenuItem("About", KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "About Tic-Tac-Toe");
        class AboutActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Tic-Tac-Toe v1.0 \u00a9 Reece Yang 2017");
            }
        }
        menuItem.addActionListener(new AboutActionListener());
        helpMenu.add(menuItem);
        menuBar.add(helpMenu);
    }

    private void setFileMenu() {
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        menuItem = new JMenuItem("New Game", KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Start a new game");
        class NewGameActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        }
        menuItem.addActionListener(new NewGameActionListener());
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Export Game", KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Export the current game");
        class ExportGameActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                export();
            }
        }
        menuItem.addActionListener(new ExportGameActionListener());
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Import Game", KeyEvent.VK_I);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Import a saved game");
        class ImportGameActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                open();
            }
        }
        menuItem.addActionListener(new ImportGameActionListener());
        fileMenu.add(menuItem);

        fileMenu.addSeparator();

        menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Quit the game");
        class QuitActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                Utilities.quit();
            }
        }
        menuItem.addActionListener(new QuitActionListener());
        fileMenu.add(menuItem);

        menuBar.add(fileMenu);
    }

    private void setJumbotron() {
        jumbotron = new JTextField(30);
        jumbotron.setHorizontalAlignment(JTextField.CENTER);
        jumbotron.setEditable(false);
        windowPanel.add("North", jumbotron);
    }

    private void showGameOverDialog() {
        Piece winner = board.checkForWin();
        // Find out if it's a tie or if X or O won
        String message = winner.equals(Piece.EMPTY) ?
                "The game has tied."
                : winner + " has won the game.";
        // Show option dialog with quit and new game choices
        Object[] choices = {"Quit", "New Game"};
        int chosen = JOptionPane.showOptionDialog(null,
                message,
                "Tic-Tac-Toe",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices,
                choices[1]);
        if (chosen == 0) {
            Utilities.quit();
        } else {
            newGame();
        }
    }

    private void computerMove() {
        if (onePlayerMode) {
            PerfectPlayer reeceYang = new PerfectPlayer(board.getTurnPiece(), board);
            reeceYang.minimax(board);
            board.place(reeceYang.getChoice()[0], reeceYang.getChoice()[1], reeceYang.getMe());
            refreshBoard();
            if (board.isEndPosition()) {
                showGameOverDialog();
            } else {
                // Next turn
                board.nextTurn();
                updateJumbotron();
            }
        }
    }

    private void refreshBoard() {
        for (int i = 0; i < BOARD_LENGTH * BOARD_LENGTH; i++) {
            cells[i].setText(board.getPiece(i / BOARD_LENGTH, i % BOARD_LENGTH).toString());
        }
        System.out.println("Board refreshed");
        updateJumbotron();
    }

    private void updateJumbotron() {
        jumbotron.setText(board.getTurnPiece() + "'s turn");
    }

    private void newGame() {
        board.reset();
        refreshBoard();
        jumbotron.setText("X's Turn");
    }

    private void open() {
        try {
            Scanner in = new Scanner(new File("src/U7A3/board.brd"));
            int boardLength = in.nextInt();
            int turn = in.nextInt();
            String boardString = "";
            for (int i = 0; i <= boardLength; i++) {
                boardString += in.nextLine();
            }
            board = new Board(boardString, boardLength, turn);
            refreshBoard();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    private void export() {
        try {
            FileWriter writer = new FileWriter("src/U7A3/board.brd");
            PrintWriter out = new PrintWriter(writer);
            out.println(board.getBoardLength());
            out.println(board.getTurn());
            out.println(board.toString());
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
    }
}
