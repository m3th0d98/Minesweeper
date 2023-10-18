import java.awt.GridLayout;

import javax.swing.*;

public class MineSweeper extends JFrame{
    JPanel boardDisplay = new JPanel();
    Square[][] board;

    public MineSweeper(int size) {
        int row,col,tiles;
        board = new Square[size][size];
        Square square;
        setTitle("MineSweeper");
        setSize(size*60,size*60);
        boardDisplay.setLayout(new GridLayout(size,size));
        for (row=0;row<size;row++) {
            for (col=0;col<size;col++) {
                square = new Square((Math.random()<.10));
                square.row = row;
                square.col = col;
                square.game= this;
                boardDisplay.add(square);
                board[row][col]=square;
            }
        }
        add(boardDisplay);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        MineSweeper mygame1, yourgame;
        mygame1 = new MineSweeper(5);
        yourgame = new MineSweeper(10);

    }

}
