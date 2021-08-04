package Main;
import java.util.ArrayList;
import java.util.Scanner;

import Clases.*;

public class Principal {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        ArrayList <Adulto> U_mayores = new ArrayList<Adulto>();
        ArrayList <Nino> U_menores = new ArrayList<Nino>();
        String nombre, nick, sexo;
        int edad, fichas, C_cartones, C_aleatorios;
        //Si se descomepntarea el segundo salir, no entrará al menú (Para pruebas especificas)
        boolean salir = false; //salir = true;
        Adulto usuario_a;
        Nino usuario_b;
        Partida p_actual;
        while(!salir){
            System.out.println("\n-------->Bienvenido<-----------");
            System.out.println("1> Registrar jugador");
            System.out.println("2> Jugar");
            System.out.println("3> Ver el Top ten");
            System.out.println("4> Canjear fichas");
            System.out.println("5> Información sobre la cuenta");
            System.out.println("6> Salir");
            System.out.print("Digite una opción del menú: ");
            String r = sc.nextLine();
            System.out.println();

            switch (r){
                case "1":   //Registro de usuarios
                    System.out.print("Digite el Nombre de usuario: ");
                    nombre = sc.nextLine();
                    System.out.print("Digite el gamerTag: ");
                    nick = sc.nextLine();
                    if(!Antes_registrado(U_mayores, U_menores, nombre, nick)){ 
                        System.out.print("Digite el sexo: ");
                        sexo = sc.nextLine();
                        System.out.print("Digite la edad: ");
                        edad = Integer.parseInt(sc.nextLine());
                        
                        if(edad > 15){
                            usuario_a = new Adulto(nombre, nick, sexo, edad);
                            U_mayores.add(usuario_a);
                        
                        }else{
                            usuario_b = new Nino(nombre, nick, sexo, edad);
                            U_menores.add(usuario_b);
                        }
                        System.out.println("Usuario creado con exito!!");
                    }else{
                        System.out.println("Error, ese nombre de usuario o gamerTag ya existe!!");
                    }
                    
                    pausa();
                    break;

                case "2": //Jugar
                    if(U_mayores.size() == 0 && U_menores.size() ==0){System.out.println("No hay usuarios registrados!!");}
                    else{
                        System.out.print("Digite su nombre de usuario: ");
                        nombre = sc.nextLine();
                        
                        usuario_a = buscar_ad(U_mayores, nombre);
                        usuario_b = buscar_ni(U_menores, nombre);
                        if(usuario_a == null && usuario_b == null){
                            System.out.println("Solo pueden jugar usuarios registrados!!");
                        }else{
                            if(usuario_a != null){
                                if(usuario_a.getFichas() < 500){System.out.println("No tiene fichas suficientes para comprar un tablero, comuniquese con nuestra linea de soporte!!");}
                                else{
                                    do{
                                        System.out.print("Digite la cantidad de cartones para jugar, recuerde, cada carton vale 500 fichas y maximo se puede tener 4 cartones por partida: ");
                                        C_cartones = Integer.parseInt(sc.nextLine());
                                        System.out.print("Digite la cantidad de cartones que se generarán con números aleatorios: ");
                                        C_aleatorios = Integer.parseInt(sc.nextLine());
    
                                        if(C_cartones > 4 || C_cartones < 1 || C_aleatorios < 0 || C_aleatorios > C_cartones){
                                            System.out.println("\nError, verifique que las dos cantidades no sean negativas, que la cantidad de cartones no sea mayor a 4 y que la cantidad de cartones generados aleatoriamente no sobrepasen los cartones con los que va a jugar!!");
                                            pausa();
                                        }
                                    }while(C_cartones > 4 || C_cartones < 1 || C_aleatorios < 0 || C_aleatorios > C_cartones);
                                    
                                    p_actual = new Partida(usuario_a);
                                    p_actual.jugar(C_cartones, C_aleatorios);
                                }

                            }else{
                                if(usuario_b.getFichas() < 500){System.out.println("No tiene fichas suficientes para comprar un tablero, comuniquese con nuestra linea de soporte!!");}
                                else{
                                    do
                                    {System.out.print("Digite uno (1) si desea un tablero generado aleatoriamente de lo contrario digite cero (0): ");
                                    C_aleatorios = Integer.parseInt(sc.nextLine());

                                    if(C_aleatorios != 0 && C_aleatorios != 1){
                                        System.out.println("\nError, digite una opción valida (0 ó 1)!!");
                                        pausa();
                                    }
                                    }while(C_aleatorios != 0 && C_aleatorios != 1);
                                    
                                    p_actual = new Partida(usuario_b);
                                    p_actual.jugar(C_aleatorios);
                                }
                            }
                        }
                    }

                    pausa();
                    break;

                case "3": //Ver top ten
                    System.out.println("TOP TEN USUARIOS +18");

                    System.out.println("TOP TEN USUARIOS -18");
                    break;

                case "4": //Canjear premios
                    if(U_mayores.size() == 0 && U_menores.size() ==0){System.out.println("No hay usuarios registrados!!");}
                    else{
                        System.out.print("Digite su nombre de usuario: ");
                        nombre = sc.nextLine();
                        
                        usuario_a = buscar_ad(U_mayores, nombre);
                        usuario_b = buscar_ni(U_menores, nombre);
                        if(usuario_a == null && usuario_b == null){
                            System.out.println("El nombre digitado no existe!!");
                        }else{
                            if(usuario_a != null){
                                System.out.print("Digite la cantidad de fichas que desea cambiar: ");
                                fichas = Integer.parseInt(sc.nextLine());
                                if(fichas <= usuario_a.getFichas() && fichas > 0){
                                    Tienda.Canjear_premios(usuario_a, fichas);
                                }else{System.out.println("Error, el valor digitado es negativo o sobrepasa la cantidad de fichas que usted tiene actualmente!!");}
                            }else{
                                int premio = sub_menu_premios();
                                if(premio != 4){Tienda.Canjear_premios(usuario_b, premio);}
                            }
                        }
                    }
                    
                    pausa();
                    break;

                case "5": //Información sobre la cuenta
                    if(U_mayores.size() == 0 && U_menores.size() ==0){System.out.println("No hay usuarios registrados!!");}
                    else{
                        System.out.print("Digite su nombre de usuario: ");
                        nombre = sc.nextLine();
                        
                        usuario_a = buscar_ad(U_mayores, nombre);
                        usuario_b = buscar_ni(U_menores, nombre);
                        if(usuario_a == null && usuario_b == null){
                            System.out.println("El nombre digitado no existe!!");
                        }else{
                            if(usuario_a != null){usuario_a.info();}
                            else{usuario_b.info();}
                        }
                    }
                    
                    pausa();
                    break;
                
                case "6": //Salir
                    salir= true;
                    System.out.println("Gracias por usar nuestro software!! :D");
                    pausa();
                    break;

                default:
                    System.out.println("Error, por favor digite una opción valida del menú!!!");
                    pausa();
                    break;
            }
        }
    }
    public static void pausa(){
        System.out.print("Presione enter para continuar...");
        sc.nextLine();
    }

    public static boolean Antes_registrado(ArrayList <Adulto> mayores, ArrayList <Nino> menores, String nombre, String nick){
        for (Nino usuario : menores) {
            if(usuario.getNombre().equalsIgnoreCase(nombre) || usuario.getNick().equalsIgnoreCase(nick)){
                return true;
            }
        }
        
        for (Adulto usuario : mayores) {
            if(usuario.getNombre().equalsIgnoreCase(nombre) || usuario.getNick().equalsIgnoreCase(nick)){
                return true;
            }
        }
    
        return false;
    }

    public static Adulto buscar_ad(ArrayList <Adulto> mayores, String nombre){
        for (Adulto usuario : mayores) {
            if(usuario.getNombre().equalsIgnoreCase(nombre)){
                return usuario;
            }
        }
        return null;
    }
    
    public static Nino buscar_ni(ArrayList <Nino> menores, String nombre){
        for (Nino usuario : menores) {
            if(usuario.getNombre().equalsIgnoreCase(nombre)){
                return usuario;
            }
        }
        return null;
    }

    public static int sub_menu_premios(){
        String o;
        do{
            System.out.println("\n-------->Menú de premios!!");
            System.out.println("1> Muñeco de peluche         -> 5000 fichas");
            System.out.println("2> Balón de hule             -> 10000 fichas");
            System.out.println("3> 2 Tickets de montaña rusa -> 15000 fichas");
            System.out.println("4> Volver al menú principal");
            System.out.print("Digite una opción del menú: ");
            o = sc.nextLine();
            System.out.println();

            if(!o.equals("1") && !o.equals("2") && !o.equals("3") && !o.equals("4")){
                System.out.println("Error, opción no valida!!, intente de nuevo");
                pausa();
            } 
        }while(!o.equals("1") && !o.equals("2") && !o.equals("3") && !o.equals("4"));

        return Integer.parseInt(o);
    }

    public static void ordenamiento(ArrayList <Adulto> mayores, ArrayList <Nino> menores){
        for(int i = 0; i<(mayores.size()-1); i++){
            for(int j = i+1; j<mayores.size(); j++){
                
            }
        }

        for(int i = 0; i<(menores.size()-1); i++){

        }
    }
}
