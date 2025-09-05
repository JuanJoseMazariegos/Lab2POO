public class Jugador {
    private String nombre;
    private int pares;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.pares = 0;
    }

    public void anotarPunto() {
        pares++;
    }

    public int getPares() {
        return pares;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (pares: " + pares + ")";
    }
}
