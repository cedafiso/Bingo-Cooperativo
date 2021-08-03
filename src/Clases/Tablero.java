package Clases;
import Main.Principal;
import java.util.ArrayList;

public class Tablero {
    private String Casillas[][] = new String[5][6];
    private static final String letras[] = {"B","I","N","G","O"," "};

    public void T_personalizado(){
        for(int i=0; i<5; i++){Casillas[i][5] = " ";}

        for(int i=0; i<5; i++){
            int inferior = (i*15)+1;
            int superior = (i*15)+15;
            int memoria[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            System.out.printf("\nValores de la columna: %s (entre %d y %d)\n", letras[i], inferior, superior);
            for(int j=0; j<5; j++){

                String temp;
                int indice;
                do{
                    System.out.printf("valor fila %d: ", j+1);
                    temp = Principal.sc.nextLine();
                    indice = (Integer.parseInt(temp)-1)-(15*i);

                    if(Integer.parseInt(temp) > superior || Integer.parseInt(temp) < inferior || memoria[indice] == 1){
                        System.out.println("Error, verifique que el número digitado está en los rangos establecidos y no lo digitó anteriormente!!");
                        Principal.pausa();
                    }

                }while(Integer.parseInt(temp) > superior || Integer.parseInt(temp) < inferior || memoria[indice] == 1);
                
                Casillas[j][i] = temp;
                memoria[indice] = 1; 
            }
        }
    }

    public void T_aleatorio(){
        for(int i=0; i<5; i++){Casillas[i][5] = " ";}
        
        for(int columna =0; columna<5; columna++){
            String Numeros[] = generar_numeros(columna);
            for(int fila =0; fila<5; fila++){
                Casillas[fila][columna] = Numeros[fila]; 
            }
        }
    }

    public String[] generar_numeros(int columna){
        int inferior = (columna*15)+1;
        int superior = (columna*15)+15;
        ArrayList <String> nums = new ArrayList<String>();
        String Numeros[] = new String[5];
        int memoria[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        while(nums.size()!=5){
            int n = (int)(Math.random()*(superior-inferior+1)+inferior);
            int indice = (n-1)-(15*columna);
            if(memoria[indice] == 0){
                memoria[indice] = 1;
                nums.add(n+"");
            }
        }
        
        for(int i=0; i<5; i++){Numeros[i] = nums.get(i);}
        
        return Numeros;
    }

    public String[][] getCasillas() {
        return Casillas;
    }

    public void setCasillas(String[][] casillas) {
        Casillas = casillas;
    }

    public static String[] getLetras() {
        return letras;
    }

    /* public void imprimir_tablero (){

        System.out.printf("|");
        for (String letra : letras) {System.out.printf("  %s\t|",letra);}
        System.out.println();

        for(int i = 0; i<5; i++){
            System.out.printf("|");
            for(int j = 0; j<5; j++){    
                System.out.printf("  %s\t|",Casillas[i][j]);
            }
            System.out.println();
        }
    } */
}
