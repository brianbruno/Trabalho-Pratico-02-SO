package core;

import cinemark.FilaPedidos;
import cinemark.Pedido;
import cinemark.Sala;

public class ProcessadorPedidos extends Thread {

    FilaPedidos fila;
    Sala sala;
    private int idThread;
    private static int processadores = 0;
    private int pedidosProcessados = 0;

    public ProcessadorPedidos(FilaPedidos fila, Sala sala) {
        processadores++;
        idThread = processadores;
        this.fila = fila;
        this.sala = sala;
        super.setName("Processador de pedidos " + processadores);
    }

    public void run() {
        try {
            Pedido pedido = null;
            while((pedido = fila.getPedido()) != null) {

                int linha = pedido.getLinha(); int coluna = pedido.getColuna();
                int tipo = pedido.getTipoPedido();

                boolean disponibilidade = sala.getDisponibilidade(linha, coluna);

                if(disponibilidade && tipo == 2) {
                    Thread.sleep(pedido.getTempoParaConcluir());
                    if(sala.reservarPoltrona(linha, coluna)) {
                        pedido.setStatus("RESERVADO");
                    }
                } else if (disponibilidade && tipo == 3) {
                    if(sala.comprarPoltrona(linha, coluna)) {
                        pedido.setStatus("VENDIDO");
                    }
                }
                pedidosProcessados++;
                fila.novoPedidoProcessado(pedido);
            }

            System.out.println("Pedidos Processados pela thread " + idThread + ": " + pedidosProcessados);

        }
        catch (Exception e) {
            // Throwing an exception
            e.printStackTrace();
            System.out.println ("Exception is caught");
        }
    }

}
