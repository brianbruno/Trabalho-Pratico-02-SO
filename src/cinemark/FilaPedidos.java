package cinemark;

import java.util.ArrayList;

public class FilaPedidos {

    private ArrayList<Pedido> pedidos;
    private ArrayList<Pedido> pedidosProcessados;

    public FilaPedidos() {
        pedidos = new ArrayList<>();
        pedidosProcessados = new ArrayList<>();
    }

    public void novoPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public synchronized Pedido getPedido() {
        Pedido pedido = null;
        if(pedidos.size() > 0) {
            pedido = pedidos.get(0);
            pedidos.remove(0);
        }
        return pedido;
    }

    public ArrayList<Pedido> getPedidoProcessado() {
        return pedidosProcessados;
    }

    public void novoPedidoProcessado (Pedido pedido) {
        pedidosProcessados.add(pedido);
    }
}
