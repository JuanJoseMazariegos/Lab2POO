import java.util.*;

public class Partida {
    private Tablero tablero;
    private List<Jugador> jugadores;
    private int turnoActual;
    private List<Posicion> seleccionTemporal;

    public Partida(int filas, int columnas, List<Simbolo> simbolos, Jugador j1, Jugador j2) {
        this.tablero = new Tablero(filas, columnas);
        this.tablero.inicializar(simbolos);
        this.jugadores = Arrays.asList(j1, j2);
        this.turnoActual = 0;
        this.seleccionTemporal = new ArrayList<>();
    }

    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    public boolean puedeSeleccionar(Posicion pos) {
        if (!tablero.dentroDeRango(pos)) return false;
        Ficha f = tablero.getFicha(pos);
        return !f.esEmparejada();
    }

    public void seleccionar(Posicion pos) {
        Ficha f = tablero.getFicha(pos);
        f.revelar();
        seleccionTemporal.add(pos);
    }

    public boolean resolverTurno() {
        if (seleccionTemporal.size() != 2) return false;

        Posicion p1 = seleccionTemporal.get(0);
        Posicion p2 = seleccionTemporal.get(1);

        Ficha f1 = tablero.getFicha(p1);
        Ficha f2 = tablero.getFicha(p2);

        boolean acierto = f1.getSimbolo().equals(f2.getSimbolo());
        if (acierto) {
            f1.emparejar();
            f2.emparejar();
            getJugadorActual().anotarPunto();
        } else {
            f1.ocultar();
            f2.ocultar();
            alternarTurno();
        }
        seleccionTemporal.clear();
        return acierto;
    }

    public void alternarTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }

    public boolean haTerminado() {
        return tablero.todasEmparejadas();
    }

    public Jugador ganador() {
        if (jugadores.get(0).getPares() > jugadores.get(1).getPares()) {
            return jugadores.get(0);
        } else if (jugadores.get(1).getPares() > jugadores.get(0).getPares()) {
            return jugadores.get(1);
        }
        return null;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
