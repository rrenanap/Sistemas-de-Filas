import java.util.concurrent.*;

public class Simulacao {

    public static void executar(int numAtendentes) throws InterruptedException {

        BlockingQueue<Cliente> fila = new LinkedBlockingQueue<>();
        Metricas metricas = new Metricas();

        ExecutorService pool = Executors.newFixedThreadPool(numAtendentes);

        for (int i = 0; i < numAtendentes; i++) {
            pool.execute(new Atendente(fila, metricas));
        }

        Thread gerador = new Thread(new GeradorClientes(fila));
        gerador.start();

        gerador.join();

        for (int i = 0; i < numAtendentes; i++) {
            fila.put(new Cliente(-1));
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);

        metricas.calcular();
    }
}