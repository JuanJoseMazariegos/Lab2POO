import java.util.*;

public class Tablero {
    private int filas;
    private int columnas;
    private Ficha[][] celdas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.celdas = new Ficha[filas][columnas];
    }

    public void inicializar(List<Simbolo> simbolos) {
        List<Ficha> listaFichas = new ArrayList<>();
        int id = 0;
        for (Simbolo s : simbolos) {
            listaFichas.add(new Ficha(id++, s));
            listaFichas.add(new Ficha(id++, s));
        }
        Collections.shuffle(listaFichas);

        int index = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = listaFichas.get(index++);
            }
        }
    }

    public boolean dentroDeRango(Posicion pos) {
        return pos.getFila() >= 0 && pos.getFila() < filas &&
               pos.getColumna() >= 0 && pos.getColumna() < columnas;
    }

    public Ficha getFicha(Posicion pos) {
        return celdas[pos.getFila()][pos.getColumna()];
    }

    public boolean todasEmparejadas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!celdas[i][j].esEmparejada()) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toStringOculto() {
        StringBuilder sb = new StringBuilder();

        
        sb.append("   ");
        for (int j = 0; j < columnas; j++) {
            sb.append(" ").append(j).append(" ");
        }
        sb.append("\n");

       
        for (int i = 0; i < filas; i++) {
            sb.append(i).append(" ");
            if (i < 10) sb.append(" ");
            for (int j = 0; j < columnas; j++) {
                Ficha f = celdas[i][j];
                if (f.getEstado() == EstadoFicha.REVELADA || f.getEstado() == EstadoFicha.EMPAREJADA) {
                    sb.append(" ").append(f.toString()).append(" ");
                } else {
                    sb.append(" ❓ ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String toStringRevelando(Posicion p1, Posicion p2) {
        StringBuilder sb = new StringBuilder();

        
        sb.append("   ");
        for (int j = 0; j < columnas; j++) {
            sb.append(" ").append(j).append(" ");
        }
        sb.append("\n");

        
        for (int i = 0; i < filas; i++) {
            sb.append(i).append(" ");
            if (i < 10) sb.append(" ");
            for (int j = 0; j < columnas; j++) {
                Posicion actual = new Posicion(i, j);
                Ficha f = celdas[i][j];
                if (f.getEstado() == EstadoFicha.EMPAREJADA ||
                    (p1 != null && actual.equals(p1)) ||
                    (p2 != null && actual.equals(p2))) {
                    sb.append(" ").append(f.toString()).append(" ");
                } else {
                    sb.append(" ❓ ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
