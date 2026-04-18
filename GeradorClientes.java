import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class GeradorClientes implements Runnable {

    private BlockingQueue<Cliente> fila;
    private Random random = new Random();

    public GeradorClientes(BlockingQueue<Cliente> fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        long inicio = System.currentTimeMillis();
        int id = 0;

        try {
            while ((System.currentTimeMillis() - inicio) / 1000 < Config.TEMPO_SIMULACAO) {

                int intervalo = random.nextInt(
                        Config.CHEGADA_MAX - Config.CHEGADA_MIN + 1
                ) + Config.CHEGADA_MIN;

                Thread.sleep(intervalo * 1000L);

                fila.put(new Cliente(id++));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}