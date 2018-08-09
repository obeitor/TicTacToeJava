/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softobt.tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Abdulgafar Obeitor
 */
public class TicTacToeGame {
    private JFrame gameApp;
    private  char currentPlayer;
    private clsGame game;
    private JButton[][] table;
    private int clicks;
    private TicTacToeGame(char a){
        this.currentPlayer = a;
        setup();
    }
    public TicTacToeGame(){
        this.currentPlayer = 'X';
        setup();
    }
    private void setup(){
        game = new clsGame();
        clicks = 0;
        gameApp = new JFrame("Tic Tac Toe");//set name of the JFrame
        gameApp.setMinimumSize(new Dimension(500, 500));//set the minimum size of the frame
        gameApp.setResizable(false);//cannot resize frame with mouse
        gameApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//should not close when cancel is clicked
        gameApp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent){//rather it should confirm closing first
            int userOpt = JOptionPane.showConfirmDialog(gameApp,"Are You Sure???","Closing", JOptionPane.YES_NO_OPTION);
            if(userOpt  == 0){//0 is yes, 1 is no, (arrange in array format, the first is yes-0)
                System.exit(0);
            }
            }
        });
        
        GridLayout layout = new GridLayout(3, 3, 50, 50);//this layout has 3 rows and columns, each 50x50 size
        JPanel mainPanel = new JPanel(layout); //create a new Panel with the layout
        table = new JButton[3][3]; //create an array to hold the buttons, 3 x 3
        Font buttonFont = new Font("Tahoma",Font.BOLD,30);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(20,20,20,20),null));
        for(JButton[] btn : table ){
            for(int i = 0;i<3;i++){
                btn[i] = new JButton("");
                mainPanel.add(btn[i]);
                btn[i].setFont(buttonFont);
                btn[i].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton btnPressed = (JButton)e.getSource();
                        clicks++;
                        btnPressed.setEnabled(false);btnPressed.setText(currentPlayer+"");
                        changePlayer();
                        convertTable();
                        char winner = game.getWinner();
                        if(winner == 'X' || winner == 'O'){
                            Color c;
                            if(winner == 'O')c=Color.orange;
                            else c=Color.magenta;
                            for(int i=0;i<3;i++){
                                table[game.winningLine[i][0]][game.winningLine[i][1]].setBackground(c);
                            }
                           int opt = JOptionPane.showConfirmDialog(null, "GAME OVER, Winner is :: "+game.getWinner()+" \nStart new Game???","Game Over",JOptionPane.YES_NO_OPTION);
                            if(opt==0){
                             gameApp.setVisible(false);
                             new TicTacToeGame();
                            }
                        }
                        else if(clicks==9){
                            new TicTacToeGame(currentPlayer);
                            gameApp.setVisible(false);
                        }
                    }
                });
            }
        }
        gameApp.add(mainPanel,BorderLayout.CENTER);
        gameApp.pack();
        gameApp.setVisible(true);
    }
    private void changePlayer(){
        if(currentPlayer == 'X')
            currentPlayer = 'O';
        else
            currentPlayer = 'X';
    }
    private void convertTable(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(table[i][j].getText().equalsIgnoreCase("X"))
                    this.game.table[i][j] = 1;
                else if(table[i][j].getText().equalsIgnoreCase("O"))
                    this.game.table[i][j] = -1;
            }
        }
        
    }
    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
