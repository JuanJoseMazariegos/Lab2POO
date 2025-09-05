import java.util.*;


public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
List<Simbolo> simbolos = Arrays.asList(
    new Simbolo(Character.toString('\u2600')), 
    new Simbolo(Character.toString('\u2601')), 
    new Simbolo(Character.toString('\u2605')),
    new Simbolo(Character.toString('\u2660')), 
    new Simbolo(Character.toString('\u2B50')), 
    new Simbolo(Character.toString('\u2666')), 
    new Simbolo(Character.toString('\u26BD')), 
    new Simbolo(Character.toString('\u2663'))  
);

        System.out.print("Ingrese nombre del Jugador 1: ");
        Jugador j1 = new Jugador(scanner.nextLine());

        System.out.print("Ingrese nombre del Jugador 2: ");
        Jugador j2 = new Jugador(scanner.nextLine());

        int filas = 4, columnas = 4; 

        Partida partida = new Partida(filas, columnas, simbolos, j1, j2);

        while (!partida.haTerminado()) {
            System.out.println("\nTurno de " + partida.getJugadorActual().getNombre());
            System.out.println(partida.getTablero().toStringOculto());

            Posicion p1 = leerPosicion("Primera ficha (primero #fila *espacio* #columna): ");
            while (!partida.puedeSeleccionar(p1)) {
                System.out.println("Posición inválida, intente de nuevo.");
                p1 = leerPosicion("Primera ficha (primero #fila *espacio* #columna): ");
            }
            partida.seleccionar(p1);

            System.out.println(partida.getTablero().toStringRevelando(p1, null));

            Posicion p2 = leerPosicion("Segunda ficha ((primero #fila *espacio* #columna): ");
            while (!partida.puedeSeleccionar(p2) || p2.equals(p1)) {
                System.out.println("Posición inválida, intente de nuevo.");
                p2 = leerPosicion("Segunda ficha (primero #fila *espacio* #columna): ");
            }
            partida.seleccionar(p2);

            System.out.println(partida.getTablero().toStringRevelando(p1, p2));

            boolean acierto = partida.resolverTurno();
            if (acierto) {
                System.out.println("¡Pareja encontrada!");
            } else {
                System.out.println("No coinciden. Turno del siguiente jugador.");
            }
        }

        System.out.println("\nFin de la partida");
        System.out.println(j1);
        System.out.println(j2);

        Jugador ganador = partida.ganador();
        if (ganador != null) {
            System.out.println("Ganador: " + ganador.getNombre());
        } else {
            System.out.println("Es un empate!");
        }
    }

    private static Posicion leerPosicion(String mensaje) {
        System.out.print(mensaje);
        int fila = scanner.nextInt();
        int columna = scanner.nextInt();
        return new Posicion(fila, columna);
    }
}
