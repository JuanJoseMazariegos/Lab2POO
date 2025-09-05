public class Ficha {
    private int id;
    private Simbolo simbolo;
    private EstadoFicha estado;

    public Ficha(int id, Simbolo simbolo) {
        this.id = id;
        this.simbolo = simbolo;
        this.estado = EstadoFicha.OCULTA;
    }

    public void revelar() {
        if (estado == EstadoFicha.OCULTA) {
            estado = EstadoFicha.REVELADA;
        }
    }

    public void ocultar() {
        if (estado == EstadoFicha.REVELADA) {
            estado = EstadoFicha.OCULTA;
        }
    }

    public void emparejar() {
        estado = EstadoFicha.EMPAREJADA;
    }

    public boolean esEmparejada() {
        return estado == EstadoFicha.EMPAREJADA;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }

    public EstadoFicha getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return simbolo.toString();
    }
}
