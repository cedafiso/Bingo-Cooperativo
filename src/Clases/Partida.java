package Clases;

import java.util.ArrayList;
import Main.Principal;

public class Partida {
    private Adulto usuario_a;
    private Nino usuario_b;
    private ArrayList <Tablero> cartones = new ArrayList<Tablero>();
    private int memoria[][] = new int[15][5];
    private int mem_columna[] = new int[5];
    
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
        usuario_a.setP_jugadas(usuario_a.getP_jugadas()+1);

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

        boolean salir = false, error = false;
        //String ya_salieron ="";  //Variable de verificación balotas que ya salieron
        while(!todos_los_numeros()){
            error = false;
            String[][] tablero_completo = crearMultiplesTablas(n_tableros);
            Tablero.imprimir_tablero(tablero_completo);
            //System.out.println(ya_salieron);       //Verificación de las valotas que ya salieron
            String balota[] = balota();
            //ya_salieron += Tablero.Letras[Integer.parseInt(balota[0])]+balota[1]+" "; //Agregue la balota recien salida
            System.out.println("| > s: Salir  | > 0: Siguiente balota  |");
            System.out.print("Digite los tableros que tienen la letra con el valor (separado por comas): ");
            String valores[] = Principal.sc.nextLine().replaceAll(" ","").split(",");
            System.out.println();
            if(valores.length == 1 && valores[0].equals("0")){
                continue;
            }else if(valores.length == 1 && valores[0].equalsIgnoreCase("s")){
                salir = true;
                break;
            }else{
                for (String carton : valores) {
                    if(Integer.parseInt(carton) <= cartones.size()){
                        if(!cartones.get(Integer.parseInt(carton)-1).existe(balota)){
                            System.out.printf("Penalización!!, ese valor no está en el tablero %s, se le descontará una ficha.\n", carton);
                            usuario_a.setFichas(usuario_a.getFichas()-1);
                            error = true;
                        }
                    }else{
                        System.out.printf("El carton %s no existe\n", carton);
                        error = true;
                    }
                }
            }
            if(error){Principal.pausa();}
        }

        if(!salir){
            String[][] tablero_completo = crearMultiplesTablas(n_tableros);
            Tablero.imprimir_tablero(tablero_completo);
        }
         
        /* System.out.println(ya_salieron);     //Verificación de ejecución correcta
        System.out.println(ya_salieron.split(" ").length); */ //Verificación de ejecución correcta (salida esperada 75)

        System.out.println("\n\tGAME OVER");
        Calcular_puntaje();
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
        usuario_b.setP_jugadas(usuario_b.getP_jugadas()+1);

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

        boolean salir = false, error = false;
        //String ya_salieron ="";  //Variable de verificación balotas que ya salieron
        while(!todos_los_numeros()){
            error = false;
            String[][] tablero_completo = crearMultiplesTablas(1);
            Tablero.imprimir_tablero(tablero_completo);
            //System.out.println(ya_salieron);       //Verificación de las valotas que ya salieron
            String balota[] = balota();
            //ya_salieron += Tablero.Letras[Integer.parseInt(balota[0])]+balota[1]+" "; //Agregue la balota recien salida
            System.out.println("| > s: Salir  | > 0: Siguiente balota  |");
            System.out.print("Digite 1 si su tablero tiene la letra con el valor: ");
            String valor = Principal.sc.nextLine().replaceAll(" ","");
            System.out.println();
            if(valor.equals("0")){
                continue;
            }else if(valor.equalsIgnoreCase("s")){
                salir = true;
                break;
            }else{  
                if(!cartones.get(0).existe(balota)){
                    System.out.printf("Penalización!!, ese valor no está en su tablero, se le descontará una ficha.\n");
                    usuario_b.setFichas(usuario_b.getFichas()-1);
                    error = true;
                }
                
            }
            if(error){Principal.pausa();}
        }

        if(!salir){
            String[][] tablero_completo = crearMultiplesTablas(1);
            Tablero.imprimir_tablero(tablero_completo);
        }
         
        /* System.out.println(ya_salieron);     //Verificación de ejecución correcta
        System.out.println(ya_salieron.split(" ").length); */ //Verificación de ejecución correcta (salida esperada 75)

        System.out.println("\n\tGAME OVER");
        Calcular_puntaje();
    }

    public String[] balota(){    
        int columna, indice;
        while(true){
            columna = (int)(Math.random()*(5)+1);
            indice = columna-1;
            if(mem_columna[indice] == 0){
                break;
            }
        }
        //System.out.println("->");  //Seña para saber que salió del primer for
        int num;
        int inferior = (indice*15)+1;
        int superior = (indice*15)+15;

        while(true){
            int n = (int)(Math.random()*(superior-inferior+1)+inferior);
            int indice2 = (n-1)-(15*indice);
            if(memoria[indice2][indice] == 0 && n<=superior && n>=inferior){
                memoria[indice2][indice] = 1;
                num = n;
                break;
            }
        }

        boolean c_llena = true;
        for (int i = 0; i < 15; i++) {
            if(memoria[i][indice]==0){
                c_llena = false;
                break;
            }
        }
        if(c_llena){mem_columna[indice]=1;}

        System.out.printf("\n|   Balota: %s%d   |\n",Tablero.Letras[indice], num);
        String balota[] = {indice+"", num+""};
        return balota;
    }

    public boolean todos_los_numeros(){
        for (int columna : mem_columna) {
            if(columna == 0){return false;}
        }
        return true;
    }
    public void Calcular_puntaje(){
        int fichas = 0;    //Variable acumuladora

        for (Tablero carton : cartones) {  //recorremos cada tablero
            if(verificar_tablero_BINGO(carton)){  //bingo
                if(usuario_a != null){fichas += 10000;}  //Si el usuario es mayor de edad
                else{fichas += 5000;}                    //Si el usuario es menor de edad

            }else if(verificar_o(carton)){  //O
                if(usuario_a != null){fichas += 2000;}
                else{
                    if(usuario_b.getEdad() < 10){fichas += 1000;} //Si el menor de edad tiene menos de 10 años
                    else{fichas += 900;}                          //Si es menor de edad pero al menos tiene 10 años
                }

            }else if(verificar_u(carton)){  //U
                if(usuario_a != null){fichas += 1500;}           //Solo los adultos reciben fichas por esta figura

            }/* else if(verificar_o(carton)){  //X
                if(usuario_a != null){fichas += 1200;}
                else{fichas += 800;}

            } */else if(verificar_c(carton)){  //C  
                if(usuario_a != null){fichas += 1000;}             //Solo los adultos reciben fichas por esta figura

            }/* else if(verificar_o(carton)){  //Diagonal
                if(usuario_a != null){fichas += 800;}
                else{fichas += 450;}

            } */else if(verificar_horizontal(carton).size()>0 || verificar_verticales(carton).size()>0){  //Linea 
                if(usuario_a != null){fichas += 400;}
                else{fichas += 200;}
            }
        }

        if(fichas > 0){   // si ganó fichas
            System.out.printf("\n¡¡Felicidades usted ganó %d fichas!!\n", fichas);
            if(usuario_a != null){usuario_a.setFichas(usuario_a.getFichas()+fichas);}
            else{usuario_b.setFichas(usuario_b.getFichas()+fichas);}

        }else{System.out.println("\nNo ganó fichas");}
        
    }

    public ArrayList<Integer> verificar_horizontal(Tablero tablero){ ///Verificador horizontal, se usa en las filas
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 1; i<tablero.getTable_final().length;i++){
            boolean verificador = true;
            for(int j = 0; j<tablero.getTable_final()[0].length-1;j++){
                if(!tablero.getTable_final()[i][j].equals("X")){
                    verificador = false;
                }
            }
            if (verificador){
            indexes.add(i);
            }
        }
        return indexes; //Devolvera el index de las filas que cumplen con la condición de ser toda igual a "X"
    }
    public ArrayList<Integer> verificar_verticales(Tablero tablero){ ///Verificador para verticales, se usa en las columnas
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i<tablero.getTable_final()[0].length-1;i++){
            boolean verificador = true;
            for(int j = 1; j<tablero.getTable_final().length;j++){
                if(!tablero.getTable_final()[j][i].equals("X")){
                    verificador = false;
                }
            }
            if (verificador){
                indexes.add(i);
                }
        }
        return indexes;///Devolvera el index de las columnas que cumplen con la condición de ser toda igual a "X"

    }
    public boolean verificar_c(Tablero tablero){
        ArrayList<Integer> filas = verificar_horizontal(tablero);
        ArrayList<Integer> columnas = verificar_verticales(tablero);
        if(columnas.contains(0) && filas.contains(1) && filas.contains(5)){
            return true;
        }else{
            return false;
        }
    }
    public boolean verificar_o(Tablero tablero){
        ArrayList<Integer> columnas = verificar_verticales(tablero);
        if(verificar_c(tablero) && columnas.contains(4)){
            return true;
        }else{
            return false;
        }
    }
    public boolean verificar_u(Tablero tablero){
        ArrayList<Integer> filas = verificar_horizontal(tablero);
        ArrayList<Integer> columnas = verificar_verticales(tablero);
        if(columnas.contains(0) && columnas.contains(4) && filas.contains(5)){
            return true;
        }else{
            return false;
        }
    }
    public boolean verificar_tablero_BINGO(Tablero tablero){
        ArrayList<Integer> filas = verificar_horizontal(tablero);
        ArrayList<Integer> columnas = verificar_verticales(tablero);
        if(columnas.size() == 5 && filas.size() == 5){
            return true;
        }else{
            return false;
        }
    }
    
}   