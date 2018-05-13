package file;

import cinemark.FilaPedidos;
import cinemark.Pedido;
import cinemark.Sala;

import java.io.*;
import java.util.ArrayList;

public class Arquivo {

    static FileOutputStream saida = null;
    static OutputStreamWriter gravador = null;
    static BufferedWriter buffer_saida = null;
    static FileOutputStream saida2 = null;
    static OutputStreamWriter gravador2 = null;
    static BufferedWriter buffer_saida2 = null;
    static FileInputStream entrada = null;
    static InputStreamReader leitor = null;
    static BufferedReader buffer_entrada = null;
    static File arq = null;
    static File arq2 = null;
    static final String separadorDeLinha = System.getProperty ("line.separator");

    public Sala lerSala () {

        Sala salaCinema = null;

        try {
            entrada = new FileInputStream("sala.txt");
            leitor = new InputStreamReader(entrada);
            buffer_entrada = new BufferedReader(leitor);
            String linha;
            linha = buffer_entrada.readLine();
            int filas = Integer.valueOf(linha.substring(0, 2));
            int cadeiras = Integer.valueOf(linha.substring(4, 6));

            int sala[][] = new int[filas][cadeiras];
            int j;
            for (int i = 0; i < filas-1; i++) {
                j = 0;
                linha = buffer_entrada.readLine();

                Character[] poltronas = linha.chars().mapToObj(ch -> (char) ch).toArray(Character[]::new);

                for (Character poltrona : poltronas) {
                    sala[i][j] = Character.getNumericValue(poltrona);
                    j++;
                }
            }

            salaCinema = new Sala(sala);

        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            fecharManipuladoresEscrita();
        }

        return salaCinema;
    }

    public FilaPedidos lerPedidos () {
        FilaPedidos fila = null;

        try {
            fila = new FilaPedidos();
            entrada = new FileInputStream("pedidos.txt");
            leitor = new InputStreamReader(entrada);
            buffer_entrada = new BufferedReader(leitor);
            String linha;
            linha = buffer_entrada.readLine();
            int pedidos = Integer.valueOf(linha);
            int tipoPedido = 0, linhaSala = 0, colunaSala = 0, tempoParaConcluir = 0;

            for (int i = 0; i < pedidos-1; i++) {
                linha = buffer_entrada.readLine();
                String[] linhaPedido = linha.split(" ");

                tipoPedido = Integer.valueOf(linhaPedido[0]);
                linhaSala = Integer.valueOf(linhaPedido[1]);
                colunaSala = Integer.valueOf(linhaPedido[2]);
                tempoParaConcluir = Integer.valueOf(linhaPedido[3]);

                Pedido pedido = new Pedido(tipoPedido, linhaSala, colunaSala, tempoParaConcluir);
                fila.novoPedido(pedido);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            fecharManipuladoresEscrita();
        }

        return fila;
    }

    public boolean gravarFilaProcessada (FilaPedidos pedidos) {
        boolean resultado = false;

        try {
            File file = new File("vendidos.txt");
            File file2 = new File("negados.txt");
            file.delete();
            file2.delete();

            arq = new File ("vendidos.txt");
            arq2 = new File ("negados.txt");

            saida = new FileOutputStream (arq, true);
            gravador = new OutputStreamWriter (saida);
            buffer_saida = new BufferedWriter (gravador);

            saida2 = new FileOutputStream (arq2, true);
            gravador2 = new OutputStreamWriter (saida2);
            buffer_saida2 = new BufferedWriter (gravador2);

            ArrayList<Pedido> pedidosProcessados = pedidos.getPedidoProcessado();

            pedidosProcessados.forEach((p) -> {
                try {
                    if(!p.getStatus().equals("NEGADO")) {
                        buffer_saida.write(p.toString() + separadorDeLinha);
                    } else {
                        buffer_saida2.write(p.toString() + separadorDeLinha);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            buffer_saida.flush();
            buffer_saida2.flush();

            resultado = true;
        } catch (Exception e) {
            e.printStackTrace ();
        }  finally {
            fecharManipuladoresEscrita();
        }

        return resultado;
    }

    public static void fecharManipuladoresEscrita() {
        try {
            if (buffer_saida != null) {
                buffer_saida.close();
            }

            if (saida != null) {
                saida.close();
            }

            if (gravador != null) {
                gravador.close();
            }

            if (buffer_saida2 != null) {
                buffer_saida.close();
            }

            if (saida2 != null) {
                saida.close();
            }

            if (gravador2 != null) {
                gravador.close();
            }

            if (buffer_entrada != null) {
                buffer_entrada.close();
            }

            if (leitor != null) {
                leitor.close();
            }

            if (entrada != null) {
                entrada.close();
            }

        } catch (IOException e) {
            System.out.println("ERRO ao fechar os manipuladores de escrita do arquivo");
        }
    }

    public void imprimirSala (int sala[][], int filas, int cadeiras) {

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cadeiras; j++)
                System.out.print(sala[i][j] + " ");
            System.out.println(" ");
        }
    }

}
