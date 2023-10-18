import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Square extends JButton{
    boolean mine = false;	boolean flagged = false;
    int row, col;
    boolean revealed = false;
    MineSweeper game;

    public Square(boolean makemine) {
        mine = makemine;
        //if (mine) setText("*");
        addMouseListener(new SquareListener());
    }

    class SquareListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            int endCondition = 0;
            int county=0;
            System.out.println("Clicked Button: " + e.getButton());

            if (flagged && e.getButton()==1) return;
            if (e.getButton()==3) {
                if (flagged) {
                    flagged = false;
                    setText("");
                    setBackground(Color.white);
                }
                else {
                    flagged =true;
                    setText("|>");
                    setBackground(Color.yellow);
                    for (int a=0; a<game.board.length; a++) {
                        for (int b=0; b<game.board.length; b++) {
                            if (((game.board[a][b].revealed) || ((game.board[a][b].mine) && (game.board[a][b].flagged)))) {
                                continue;
                            }
                            else county++;
                        }
                    }
                    if (county<1) {
                        JOptionPane.showMessageDialog(null, "You Won!");
                    }
                }
                return;


            }
            int minecount=0;
            int r,c;
            if (mine) {
                setBackground(Color.red);
                setText("*");
                JOptionPane.showMessageDialog(null, "BOOM, Game Over");
                return;
            }
            expose(row,col);

            for (int a=0; a<game.board.length; a++) {
                for (int b=0; b<game.board.length; b++) {
                    if ((game.board[a][b].revealed) || ((game.board[a][b].mine) && game.board[a][b].flagged)) {
                        continue;
                    }
                    else endCondition++;
                }
            }
            if (endCondition==0) {
                JOptionPane.showMessageDialog(null, "You Won!");
            }

        }

        public void expose(int x, int y) {
            int r,c;
            int minecount = 0;
            if (game.board[x][y].revealed) return;
            game.board[x][y].setBackground(Color.white);
            game.board[x][y].revealed = true;
            for (r=x-1; r<=x+1 ;r++) {
                for (c=y-1; c<=y+1; c++) {
                    if (r<0 || r>=game.board.length) break;
                    if (c<0 || c>=game.board.length) continue;
                    if (game.board[r][c].mine)
                        minecount++;
                }
            }
            if (minecount>0)
                game.board[x][y].setText(""+minecount);


            else {
                for (r=x-1; r<=x+1 ;r++) {
                    for (c=y-1; c<=y+1; c++) {
                        if (r<0 || r>=game.board.length) break;
                        if (c<0 || c>=game.board.length) continue;
                        expose(r,c);
                        //game.board[r][c].doClick();
                    }
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {


        }

        @Override
        public void mouseEntered(MouseEvent e) {


        }

        @Override
        public void mouseExited(MouseEvent e) {


        }
    }
}
