package cinemark;

public class Sala {

    Character sala[][];
    private int numeroSala;
    private static int salas = 0;
    private int X;
    private int Y;

    public Sala(int[][] sala) {
        salas++;

        X = sala.length;
        Y = sala[0].length;

        this.sala = new Character[X][Y];

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if(sala[i][j] == 1)
                    this.sala[i][j] = 'L';
                else
                    this.sala[i][j] = 'N';
            }
        }

        this.numeroSala = salas;
    }

    public Character[][] getSala() {
        return sala;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public static int getSalas() {
        return salas;
    }

    public synchronized boolean getDisponibilidade(int linha, int coluna) {
        boolean resultado = false;
        if(sala[linha][coluna] == 'L') {
            resultado = true;
        }
        return resultado;
    }

    public synchronized int getStatusPoltrona (int linha, int coluna) {
        return sala[linha][coluna];
    }

    public synchronized boolean reservarPoltrona (int linha, int coluna) {
        boolean resultado = true;

        if (getStatusPoltrona(linha, coluna) != 'L') {
            resultado = false;
        } else {
            sala[linha][coluna] = 'R';
        }

        return resultado;
    }

    public synchronized boolean comprarPoltrona (int linha, int coluna) {
        boolean resultado = true;

        if (getStatusPoltrona(linha, coluna) == 'L') {
            sala[linha][coluna] = 'V';
        } else {
            resultado = false;
        }

        return resultado;
    }

    public String toString() {
        String resultado = "    ";

        for (int i = 0; i < Y; i++) {
            resultado = resultado + String.format("%02d", i) + " ";
        }

        resultado = resultado + "\n";

        for (int i = 0; i < X; i++) {
            resultado = resultado + String.format("%02d", i) + " ";
            for (int j = 0; j < Y; j++)
                resultado = resultado + "  " + sala[i][j];
            resultado = resultado + "\n";
        }

        return resultado;
    }
}
