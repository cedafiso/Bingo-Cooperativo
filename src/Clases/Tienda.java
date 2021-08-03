package Clases;

public class Tienda {
    public static void Canjear_premios(Adulto usuario, int fichas){
        if(usuario.getP_jugadas() >= 1 && usuario.getFichas() > 5000){
            int dinero = fichas/10;
            usuario.setFichas(usuario.getFichas()-fichas);
            System.out.printf("Cambio realizado con exito!!, usted recibió $%d y actualmente cuenta con %d fichas en su cuenta\n",dinero, usuario.getFichas());

        }else{System.out.println("No se pueden canjear las fichas, no se ha alcanzado el numero minimo de partidas jugadas o fichas conseguidas");}
    }
    
    public static void Canjear_premios(Nino usuario, int premio){
        int p_valor[] = {5000, 10000, 15000};
        String premios[] = {"un (1) muñeco de peluche", "un (1) balón de hule", "dos (2) Tickets de montaña rusa"};
        if(usuario.getP_jugadas() >= 1){
            if(usuario.getFichas() >= p_valor[premio-1]){
                usuario.setFichas(usuario.getFichas() - p_valor[premio-1]);
                System.out.printf("Cambio realizado con exito!!, usted recibió %s y actualmente cuenta con %d fichas en su cuenta\n",premios[premio-1], usuario.getFichas());

            }else{System.out.println("No cuenta con las suficientes fichas para reclamar ese premio!!");}
        }else{System.out.println("No se pueden reclamar premios, no se ha alcanzado el numero minimo de partidas jugadas");}
    }
}
