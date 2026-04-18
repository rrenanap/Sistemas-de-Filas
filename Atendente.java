import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Atendente implements Runnable {

    private BlockingQueue<Cliente> fila;
    private Metricas metricas;
    private Random random = new Random();

    public Atendente(BlockingQueue<Cliente> fila, Metricas metricas) {
        this.fila = fila;
        this.metricas = metricas;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Cliente cliente = fila.take();

                if (cliente.id == -1) break; // sinal de parada

                cliente.tempoInicio = System.currentTimeMillis();

                int tempoAtendimento = random.nextInt(
                        Config.ATENDIMENTO_MAX - Config.ATENDIMENTO_MIN + 1
                ) + Config.ATENDIMENTO_MIN;

                Thread.sleep(tempoAtendimento * 1000L);

                cliente.tempoSaida = System.currentTimeMillis();

                metricas.registrar(cliente);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}