package Clases;
import java.util.ArrayList;
import java.util.Scanner;

public class Tablero {
    private int[][] table = new int[5][6];
    private String[][] table_final = new String[6][6];
    static private Scanner input = new Scanner(System.in);

    public Tablero(){
        
    }
    public int[][] getTable() {
        return table;
    }
    public void setTable(int[][] table) {
        this.table = table;
    }
    public String[][] getTable_final() {
        return table_final;
    }
    public void setTable_final(String[][] table_final) {
        this.table_final = table_final;
    }
    public void carton(){
        String[] bingo = {"B","I","N","G","O"," "};
        table_final[0] = bingo;
        for (int i = 1; i<table_final.length;i++){
                for(int j = 0;j<table[i-1].length;j++){
                    if (j == 5){
                    table_final[i][j] = " ";
                    }else{
                    table_final[i][j] = String.valueOf(table[(i-1)][j]);   
                    }
            }
        }
    }

    static public void imprimir_tablero(String[][] table){
        for (int i = 0; i<table.length;i++){
            System.out.println();
            System.out.println("--------------------");
            for( int j = 0;j<table[i].length-1;j++){
                if(((j+1) % 6 == 0) && j != 0){
                    System.out.print("| ");
                    System.out.printf("%-3.10s", " ");
                }else{
                System.out.print("| ");
                System.out.printf("%-3.10s", table[i][j]);
                }
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.println("--------------------");
    }
   public static int[][] llenar_tablero_aletorio(){
    int[][] table = new int[5][6];
    int[] temporal = new int[5];
    for (int j = 0; j<table[0].length;j++){
        ArrayList<Integer> numeros_usados = new ArrayList<Integer>();
        for(int k = 0; k<temporal.length;k++){
            Boolean activador = true;
            while(activador){
                activador = false;
                int random_int = (int) (Math.random()*(15*(j+1)-15*j+1-1)+(15*j+1));
                for (Integer integer : numeros_usados) {
                    if(integer == random_int){
                        activador = true;
                    }
                }
                if(activador == false){
                    temporal[k] = random_int;
                    numeros_usados.add(random_int);
                    }
            }
        }
        for(int i = 0;i<table.length;i++){
             table[i][j] = temporal[i];
         }
        }
        return table;
    }
    public static int[][] llenar_tablero_personalizado(){
        int[][] table = new int[5][6];
        int[] temporal = new int[5];
        for (int j = 0; j<table[0].length;j++){
            ArrayList<Integer> numeros_usados = new ArrayList<Integer>();
            if (j < table[0].length-1 ){
                for(int k = 0; k<temporal.length;k++){
                    Boolean activador = true;
                    while(activador){
                        activador = false;
                        System.out.println("Digite numero entre: "+(15*j+1)+" y "+(15*(j+1)));
                        int random_int = input.nextInt();
                        if(random_int<= 15*(j+1) && random_int>=15*j+1){
                            for (Integer integer : numeros_usados) {
                                if(integer == random_int){
                                    activador = true;
                                    System.out.println("Ese numero ya esta en uso");
                                }
                            }
                            if(activador == false){
                                temporal[k] = random_int;
                                numeros_usados.add(random_int);
                                }
                        }else{
                            System.out.println("Numero fuera del rango");
                            activador = true;
                        }
                    }
                }
            }
            if (j == table[0].length-1 ){
                for(int i = 0; i<temporal.length;i++){
                    temporal [i] = 0;
                }
            }
            for(int i = 0;i<table.length;i++){
                 table[i][j] = temporal[i];
             }
            }
            return table;
    }
}
