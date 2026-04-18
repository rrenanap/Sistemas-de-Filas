public class Main {

    public static void main(String[] args) throws InterruptedException {

        for (int n = 1; n <= 5; n++) {
            System.out.println("\n===== SIMULAÇÃO COM " + n + " ATENDENTES =====\n");
            Simulacao.executar(n);
        }
    }
}