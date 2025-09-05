public class Simbolo {
    private String valor;

    public Simbolo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Simbolo)) return false;
        Simbolo otro = (Simbolo) obj;
        return valor.equals(otro.valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}
