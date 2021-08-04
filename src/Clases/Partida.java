package Clases;

import java.util.ArrayList;

public class Partida {
    private Adulto usuario_a;
    private Nino usuario_b;
    private ArrayList <Tablero> cartones = new ArrayList<Tablero>();
    
    public ArrayList<Tablero> getCartones() {
        return cartones;
    }
    public void setCartones(ArrayList<Tablero> cartones) {
        this.cartones = cartones;
    }
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
                Tablero ec = new Tablero();
                ec.setTable(Tablero.llenar_tablero_aletorio());
                ec.carton();
                cartones.add(ec);
            }else{
                System.out.printf("\nLlenado de datos carton #%d:\n", (i+1));
                Tablero ec = new Tablero();
                ec.setTable(Tablero.llenar_tablero_personalizado());
                ec.carton();
                cartones.add(ec);
            }
        }
        String[][] tablero_completo = crearMultiplesTablas(n_tableros);
        Tablero.imprimir_tablero(tablero_completo);
    } 
    public String[][] crearMultiplesTablas(int cantidad){
        ArrayList<ArrayList<String>> tablero_completo = new ArrayList<ArrayList<String>>();
        for(int i =0; i<6;i++){
            ArrayList<String> temporal = new ArrayList<String>();
            for (Tablero table : cartones) {
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
        return table_prueba;
    } 

    public void jugar(int aleatorio){                      //Para niños
        cartones.add(new Tablero());
        if(aleatorio == 1){
            Tablero ec = new Tablero();
            ec.setTable(Tablero.llenar_tablero_aletorio());
            ec.carton();
            cartones.add(ec);
        }
        else{
            Tablero ec = new Tablero();
            ec.setTable(Tablero.llenar_tablero_personalizado());
            ec.carton();
            cartones.add(ec);
        }
        String[][] tablero_completo = crearMultiplesTablas(1);
        Tablero.imprimir_tablero(tablero_completo);
    }
}   