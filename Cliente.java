public class Cliente {
    public int id;
    public long tempoChegada;
    public long tempoInicio;
    public long tempoSaida;

    public Cliente(int id) {
        this.id = id;
        this.tempoChegada = System.currentTimeMillis();
    }
}