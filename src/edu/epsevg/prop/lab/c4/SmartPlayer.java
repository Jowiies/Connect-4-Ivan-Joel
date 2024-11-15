package edu.epsevg.prop.lab.c4;

/**
 * Jugador Smart que utiliza el algoritmo MIN-MAX.
 * @author Profe
 */
public class SmartPlayer implements Jugador, IAuto {
    private String nom;
    private int profunditatMax;

    public SmartPlayer(int profunditatMax) {
        this.nom = "SmartPlayer";
        this.profunditatMax = profunditatMax;
    }

    @Override
    public int moviment(Tauler t, int color) {
        int millorMoviment = -1;
        int millorValor = Integer.MIN_VALUE;

        // Evaluar todos los movimientos posibles
        for (int col = 0; col < t.getMida(); col++) {
            if (t.movpossible(col)) {
                Tauler copia = new Tauler(t);
                copia.afegeix(col, color);

                // Llamada a la función recursiva para calcular el valor de este movimiento
                int valor = minmax(copia, profunditatMax - 1, false, color, col);
                if (valor > millorValor) {
                    millorValor = valor;
                    millorMoviment = col;
                }
            }
        }

        return millorMoviment;
    }

    private int minmax(Tauler t, int profunditat, boolean esMaximizador, int colorOriginal, int columna) {
        // Comprobar condiciones de parada
        if (t.solucio(columna,colorOriginal) || profunditat == 0) {
            return evaluarTauler(t, colorOriginal);
        }

        int millorValor = esMaximizador ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int colorActual = esMaximizador ? colorOriginal : 3 - colorOriginal; // Cambiar color

        for (int col = 0; col < t.getMida(); col++) {
            if (t.movpossible(col)) {
                Tauler copia = new Tauler(t);
                copia.afegeix(col, colorActual);

                int valor = minmax(copia, profunditat - 1, !esMaximizador, colorOriginal, col);

                if (esMaximizador) {
                    millorValor = Math.max(millorValor, valor);
                } else {
                    millorValor = Math.min(millorValor, valor);
                }
            }
        }

        return millorValor;
    }

    private int evaluarTauler(Tauler t, int color) {
        // Implementa aquí la heurística para evaluar el tablero.
        // Por ejemplo, contar cuántas fichas consecutivas tiene el jugador.
        return 0; // Sustituir por la lógica de evaluación.
    }

    @Override
    public String nom() {
        return nom;
    }
}
