package cinemark;

import java.util.Random;

public class Pedido {

    private int tipoPedido, linha, coluna, tempoParaConcluir;
    private static int pedidos;
    private String codigoPedido;
    private String status;

    public Pedido(int tipoPedido, int linha, int coluna, int tempoParaConcluir) {
        pedidos++;
        this.tipoPedido = tipoPedido;
        this.linha = linha;
        this.coluna = coluna;
        this.tempoParaConcluir = tempoParaConcluir;
        this.codigoPedido = gerarCodigoPedido();
        this.status = "NEGADO";
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public int getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(int tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getTempoParaConcluir() {
        return tempoParaConcluir;
    }

    public void setTempoParaConcluir(int tempoParaConcluir) {
        this.tempoParaConcluir = tempoParaConcluir;
    }

    public static int getPedidos() {
        return pedidos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String gerarCodigoPedido() {
        Random gerador = new Random();
        String cod;
//        cod = (100 + gerador.nextInt(899)) + "-" + String.format("%03d", pedidos);
        cod = String.format("%03d", pedidos);
        return cod;
    }

    public String toString() {
        String retorno;

        retorno = getStatus() + ", PEDIDO " + getCodigoPedido() + ", POLTRONA " + getLinha() + " " + getColuna();

        return retorno;
    }
}
