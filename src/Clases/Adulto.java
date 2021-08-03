package Clases;

public class Adulto extends Usuario{
    private int fichas;
    //-------------->CONSTRUCTOR
    public Adulto(String nombre, String nick, String sexo, int edad) {
        super(nombre, nick, sexo, edad);
        this.fichas = 2000;
    }
    //------------->METODOS ESPECIFICOS
    public void info(){
        super.info();
        System.out.printf("| Fichas: %d\t|\n", fichas);
        System.out.printf("| Partidas jugadas: %d\t|\n", p_jugadas);
    }
    //------------->GETTERS AND SETTERS
    public int getFichas() {
        return fichas;
    }
    public void setFichas(int fichas) {
        this.fichas = fichas;
    }
}
