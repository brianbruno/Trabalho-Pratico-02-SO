import cinemark.FilaPedidos;
import cinemark.Pedido;
import cinemark.Sala;
import core.ProcessadorPedidos;
import file.Arquivo;

import java.util.ArrayList;

public class App {

    public static Sala sala;
    public static FilaPedidos fila;
    public static ProcessadorPedidos[] proc;
    public static final int THREADS = 10;

    public static void main(String[] args) {
        proc = new ProcessadorPedidos[THREADS];
        Arquivo arquivo = new Arquivo();
        sala = arquivo.lerSala();
        fila = arquivo.lerPedidos();

        System.out.println("---- Processando Pedidos ----");

        for (int i=0; i<THREADS; i++) {
            proc[i] = new ProcessadorPedidos(fila, sala);
            proc[i].start();
        }
        for (int i = 0; i<THREADS; i++) {
            try {
                proc[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("---- Pedidos Processados ----");
        arquivo.gravarFilaProcessada(fila);
        System.out.println("Sala: " + sala.getNumeroSala());
        System.out.println(sala);
    }

}
