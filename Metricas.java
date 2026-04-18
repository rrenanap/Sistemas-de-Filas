import java.util.ArrayList;
import java.util.List;

public class Metricas {

    private List<Cliente> clientes = new ArrayList<>();

    public synchronized void registrar(Cliente c) {
        clientes.add(c);
    }

    public void calcular() {
        double somaEspera = 0;
        double maxEspera = 0;
        double somaTotal = 0;

        for (Cliente c : clientes) {
            double espera = (c.tempoInicio - c.tempoChegada) / 1000.0;
            double total = (c.tempoSaida - c.tempoChegada) / 1000.0;

            somaEspera += espera;
            somaTotal += total;

            if (espera > maxEspera) maxEspera = espera;
        }

        int totalClientes = clientes.size();

        System.out.println("Clientes atendidos: " + totalClientes);
        System.out.println("Tempo médio de espera: " + (somaEspera / totalClientes));
        System.out.println("Tempo máximo de espera: " + maxEspera);
        System.out.println("Tempo médio total: " + (somaTotal / totalClientes));
    }
}