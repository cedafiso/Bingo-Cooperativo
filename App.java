import src.Table;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    static private ArrayList<Table> tables = new ArrayList<Table>();
    static Scanner input = new Scanner(System.in);
    static int cantidad = input.nextInt();
    static String[][] ec1 = new String[6][6*cantidad];
    public static void main(String[] args) throws Exception {
        crearMultiplesTablas(cantidad);
        Table.imprimirTablero(ec1);
        String[] random_balota = generarBalota();
        System.out.println("La balota aleatoria es: "+random_balota[0]+random_balota[1]);
        System.out.println("Digite tablero en la que esta: ");
        int numero_tablero = input.nextInt();
        System.out.println("Digite fila en la que esta: ");
        int fila = input.nextInt();
        System.out.println("Digite columna en la que esta: ");
        int columna = input.nextInt();
        hacerMovimiento(numero_tablero, fila, columna, random_balota);
        Table.imprimirTablero(ec1);

    }

    static public void crearMultiplesTablas(int cantidad){
        ArrayList<ArrayList<String>> tablero_completo = new ArrayList<ArrayList<String>>();
        for(int i = 0; i<cantidad;i++){
            Table ec = new Table();
            ec.setTable(Table.llenarTableroAletorio());
            ec.carton();
            tables.add(ec);
        }
        for(int i =0; i<6;i++){
            ArrayList<String> temporal = new ArrayList<String>();
            for (Table table : tables) {
                for(int j = 0; j<(table.getTable_final()[i]).length;j++){
                    temporal.add((table.getTable_final())[i][j]);
                }
            }
            tablero_completo.add(temporal);  
        }
        String[][] table_prueba = new String[6][6*cantidad];
        for (int i = 0; i<tablero_completo.size();i++){
            for(int j = 0; j<tablero_completo.get(i).size();j++){
                table_prueba[i][j] = tablero_completo.get(i).get(j); 
            }
        }
        ec1 = table_prueba;
    } 
    static public String[] generarBalota(){
        String[] random_balota = new String[2];
        Random rnd = new Random();
        String characters = "BINGO";
        char randomChar = characters.charAt(rnd.nextInt(characters.length()));
        int index = characters.indexOf(randomChar);
        random_balota[0] = String.valueOf(randomChar);
        random_balota[1] = String.valueOf((int) (Math.random()*(15*(index+1)-15*index+1-1)+(15*index+1)));
        return random_balota;
    }
    static public void hacerMovimiento(int numero_tablero, int fila, int columna, String[] random_balota){
        if(ec1[fila][(columna+(numero_tablero-1)+5*(numero_tablero-1))-1].equals(random_balota[1]) || ec1[0][(columna+(numero_tablero-1)+5*(numero_tablero-1))-1].equals(random_balota[0])){
            ec1[fila][(columna+(numero_tablero-1)+5*(numero_tablero-1))-1] = "X";
        }else{
            System.out.println("No coincide");
        }
    }
}
