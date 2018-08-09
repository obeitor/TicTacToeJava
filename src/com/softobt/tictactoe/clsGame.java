
package com.softobt.tictactoe;

/**
 *
 * @author Abdulgafar Obeitor
 */
public class clsGame {
    protected int table[][];
    protected int[][] winningLine;
    public clsGame(){
        table = new int[3][3];
        winningLine = new int[3][2];
        for (int[] table1 : this.table) {
            for (int j = 0; j<this.table.length; j++) {
                table1[j] = 0;
            }
        }
    }
    private int analyze(){
        int sum1 = 0, sum2 = 0;
        for(int i = 0 ; i< 3; i++){
            sum1+=table[i][i];
            sum2+=table[i][2-i];
        }
        if(sum1==-3||sum1==3){
            winningLine[0][0]=0;winningLine[0][1]=0;winningLine[1][0]=1;winningLine[1][1]=1;winningLine[2][0]=2;winningLine[2][1]=2;
            return sum1;
        }
        if(sum2==-3||sum2==3){
            winningLine[0][0]=0;winningLine[0][1]=2;winningLine[1][0]=1;winningLine[1][1]=1;winningLine[2][0]=2;winningLine[2][1]=0;
            return  sum2;
        }
        for(int i = 0;i<3;i++){
            sum2 = 0;sum1 =  0;
            for(int j=0;j<3;j++){
                sum1+=table[i][j];
                sum2+=table[j][i];
            }
            if(sum1==-3 || sum1==3){
                winningLine[0][0]=i;winningLine[0][1]=0;winningLine[1][0]=i;winningLine[1][1]=1;winningLine[2][0]=i;winningLine[2][1]=2;
                return sum1;
            }
            if(sum2==-3||sum2==3){
                winningLine[0][0]=0;winningLine[0][1]=i;winningLine[1][0]=1;winningLine[1][1]=i;winningLine[2][0]=2;winningLine[2][1]=i;
                return sum2;
            }
        }
        return 0;
    }
    public char getWinner(){
        int a = analyze();
        if(a==-3){
            return 'O';
        }
        if(a==3){
            return 'X';
        }
        return 'E';
    }
    public void printTable(){
        for(int[] table1 : this.table){
            for(int j = 0; j< 3;j++){
                System.out.print(table1[j]+"\t");
            }
            System.out.println();
        }
    }
}
