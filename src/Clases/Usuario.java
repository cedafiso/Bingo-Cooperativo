package Clases;

public class Usuario {
    protected String nombre, nick, sexo;
    protected int edad, p_jugadas;
    //-------->CONSTRUCTOR
    public Usuario(String nombre, String nick, String sexo, int edad) {
        this.nombre = nombre;
        this.nick = nick;
        this.sexo = sexo;
        this.edad = edad;
        this.p_jugadas = 0;
    }
    //------->METODOS ESPECIFICOS
    public void info(){
        System.out.printf("| Nombre: %s\t|\n", nombre);
        System.out.printf("| Gamertag: %s\t|\n", nick);
        System.out.printf("| Sexo: %s\t|\n", sexo);
        System.out.printf("| Edad: %d\t|\n", edad);
    }
    //------->GETTERS AND SETTERS    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public int getP_jugadas() {
        return p_jugadas;
    }
    public void setP_jugadas(int p_jugadas) {
        this.p_jugadas = p_jugadas;
    }
}
