package Clases;

import java.util.ArrayList;

public class Partida {
    private Adulto usuario_a;
    private Nino usuario_b;
    private ArrayList <Tablero> Cartones = new ArrayList<Tablero>();
    private int memoria[][] = new int[15][5];
    
    //------>Constructores
    public Partida(Adulto usuario_a) {
        this.usuario_a = usuario_a;
        this.usuario_b = null;
    }
    public Partida(Nino usuario_b) {
        this.usuario_b = usuario_b;
        usuario_a = null;
    }
    //--->Metodos especificos 
    public void jugar(int n_tableros, int n_aleatorios){//Para adultos
        for(int i=0; i<n_tableros; i++){
            if(n_aleatorios > 0){
                n_aleatorios--;
                Cartones.add(new Tablero());
                Cartones.get(Cartones.size()-1).T_aleatorio();
            }else{
                System.out.printf("\nLlenado de datos carton #%d:\n", (i+1));
                Cartones.add(new Tablero());
                Cartones.get(Cartones.size()-1).T_personalizado();
            }
        }
        imprimir_tableros();
    }   

    public void jugar(int aleatorio){                      //Para niños
        Cartones.add(new Tablero());
        if(aleatorio == 1){Cartones.get(0).T_aleatorio();}
        else{Cartones.get(0).T_personalizado();}
        imprimir_tableros();
    }

    public void imprimir_tableros(){
        for(int C_numero = 0; C_numero < Cartones.size(); C_numero++){
            String temp[][] = Cartones.get(C_numero).getCasillas();
            System.out.printf("\n\tTablero #: %d\n", (C_numero+1));
            System.out.printf("\t|");
            for (String letra : Tablero.getLetras()) {System.out.printf("  %s\t|",letra);}
            System.out.println();
            for(int fila =0; fila<5; fila++){ 
                System.out.printf("\t|");
                for(int columna = 0; columna<5; columna++){
                    System.out.printf("  %s\t|", temp[fila][columna]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
