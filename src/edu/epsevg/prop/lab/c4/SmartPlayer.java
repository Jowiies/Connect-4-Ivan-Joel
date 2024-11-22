package edu.epsevg.prop.lab.c4;

/**
 * Un jugador intel·ligent que utilitza l'algorisme MinMax amb poda alfa-beta per determinar la millor jugada en una partida de Connecta 4.
 */
public class SmartPlayer implements Jugador, IAuto {
    private int limitProfunditat; // Profunditat màxima de cerca per a l'algorisme MinMax
    
    private final int arr[] = {4, 3, 5, 2, 1, 6, 0, 7}; //Ordre de middleTheBest
    
    private String nom; // Nom del jugador
    private int nodesExplorats; // Nombre de nodes explorats durant la cerca del MinMax
    private boolean poda;
    private boolean middleTheBest;

    /**
     * Constructor per al SmartPlayer.
     * 
     * @param limitProfunditat La profunditat màxima per a cercar amb l'algorisme MinMax.
     * @param poda Activa o desactiva la poda alpha-beta.
     * @param middle Activa o desactiva la priorització en les columnes del mitg.
     */
    public SmartPlayer(int limitProfunditat, boolean poda, boolean middle) {
        this.nom = "SmartPlayer";
        this.limitProfunditat = limitProfunditat;
        this.poda = poda;
        this.middleTheBest = middle;
    }

    /**
     * Determina la següent jugada a realitzar.
     * 
     * @param t El tauler actual.
     * @param color El color del jugador que fa la jugada.
     * @return L'índex de la columna per a la millor jugada.
     */
    @Override
    public int moviment(Tauler t, int color) {
        nodesExplorats = 0;
        long startTime = System.nanoTime();
        int move = findBestMove(t, color, color * -1);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000_000.0; // Convertir a segons
        System.out.printf("Temps per decidir el moviment: %.2f segons%n", duration);
        System.out.println("Nombre de nodes explorats: " + nodesExplorats);
        return move;
    }

    /**
     * Obten el nom del jugador.
     * 
     * @return El nom del jugador.
     */
    @Override
    public String nom() {
        return this.nom;
    }

    /**
     * L'algorisme MinMax amb poda alfa-beta.
     * 
     * @param tauler El tauler actual.
     * @param depth La profunditat actual de la cerca.
     * @param alpha El valor alfa per a la poda alfa-beta.
     * @param beta El valor beta per a la poda alfa-beta.
     * @param isMax Cert si és el torn del jugador maximitzador, fals altrament.
     * @param colorJugadorActiu El color del jugador actiu.
     * @param colorRival El color del jugador rival.
     * @return El valor heurístic de l'estat del tauler.
     */
    private int MinMax(Tauler tauler, int depth, int alpha, int beta, boolean isMax, int colorJugadorActiu, int colorRival) 
    {
        if (depth == 0 || !tauler.espotmoure())
            return heuristica(tauler, colorJugadorActiu, colorRival);
        
        int maxValue = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int col;
        for (int i = 0; i < tauler.getMida(); i++) {
            col = middleTheBest ? arr[i] : i;
            
            if (tauler.movpossible(col)) {
                
                Tauler taulerSimulat = new Tauler(tauler);
                taulerSimulat.afegeix(col, isMax ? colorJugadorActiu : colorRival);
                
                if (taulerSimulat.solucio(col, isMax ? colorJugadorActiu : colorRival))
                    return isMax ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                
                int avaluacioActual = MinMax(taulerSimulat, depth - 1, alpha, beta, !isMax, colorJugadorActiu, colorRival);
                maxValue = isMax ? Math.max(maxValue, avaluacioActual) : Math.min(maxValue, avaluacioActual);
                
                alpha = isMax ? Math.max(alpha, maxValue) : alpha;
                
                beta = isMax ? beta : Math.min(beta, maxValue);
                
                if (poda && beta <= alpha)
                    break;
            }
        }
        
        return maxValue;
    }

    /**
     * Troba la millor jugada per al jugador actual.
     * 
     * @param tauler El tauler actual.
     * @param currentPlayerColor El color del jugador actual.
     * @param colorRival El color del jugador rival.
     * @return L'índex de la columna per a la millor jugada.
     */
    private int findBestMove(Tauler tauler, int currentPlayerColor, int colorRival) {
        int valorMesAlt = Integer.MIN_VALUE;
        int columnaOptima = -1;
        int col;
        for (int i = 0; i < tauler.getMida(); i++) {
            
            col = middleTheBest ? arr[i] : i;
            
            if (tauler.movpossible(col)) {
                Tauler taulerSimulat = new Tauler(tauler);
                taulerSimulat.afegeix(col, currentPlayerColor);

                if (taulerSimulat.solucio(col, currentPlayerColor)) {
                    return col;
                }

                int avaluacioActual = MinMax(taulerSimulat, limitProfunditat, Integer.MIN_VALUE, Integer.MAX_VALUE, false, currentPlayerColor, colorRival);

                if (avaluacioActual > valorMesAlt) {
                    valorMesAlt = avaluacioActual;
                    columnaOptima = col;
                }
            }
        }
        
        if (columnaOptima == -1) {
            for (int c = 0; c < tauler.getMida(); c++) {
                if (tauler.movpossible(c)) return c;
            }
        }

        return columnaOptima;
    }

    /**
     * Avaluació heurística del tauler actual.
     * 
     * @param tauler El tauler actual.
     * @param colorJugadorActiu El color del jugador actiu.
     * @param colorRival El color del jugador rival.
     * @return El valor heurístic de l'estat del tauler.
     */
    private int heuristica(Tauler tauler, int colorJugadorActiu, int colorRival) {
        nodesExplorats++;
        int avaluacioActual = 0;

        // Avalua les línies horitzontals, verticals i diagonals
        for (int row = 0; row < tauler.getMida(); row++) {
            for (int col = 0; col < tauler.getMida(); col++) {
                if (tauler.getColor(row, col) == colorJugadorActiu) {
                    avaluacioActual += evalPos(tauler, row, col, colorJugadorActiu);
                } else if (tauler.getColor(row, col) == colorRival) {
                    avaluacioActual -= evalPos(tauler, row, col, colorRival);
                }
            }
        }

        return avaluacioActual;
    }

    /**
     * Avalua la posició al tauler.
     * 
     * @param tauler El tauler actual.
     * @param row L'índex de la fila de la posició a avaluar.
     * @param col L'índex de la columna de la posició a avaluar.
     * @param color El color del jugador per a qui s'està avaluant la posició.
     * @return El valor heurístic de la posició.
     */
    private int evalPos(Tauler tauler, int row, int col, int color) {
        int avaluacioActual = 0;
        int[][] directions = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { 1, -1 } };

        for (int[] direction : directions) {
            int llargadaSeq = 0;
            int extremsDisponibles = 0;

            // Compta les peces consecutives en la direcció donada
            for (int i = 0; i < 4; i++) {
                int newRow = row + i * direction[0];
                int newCol = col + i * direction[1];

                if (newRow >= 0 && newRow < tauler.getMida() && newCol >= 0 && newCol < tauler.getMida()) {
                    if (tauler.getColor(newRow, newCol) == color) {
                        llargadaSeq++;
                    } else if (tauler.getColor(newRow, newCol) == 0) {
                        extremsDisponibles++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            // Puntuació basada en les peces consecutives i els extrems oberts
            if (llargadaSeq == 4) {
                avaluacioActual += 100;
            } else if (llargadaSeq == 3 && extremsDisponibles > 0) {
                avaluacioActual += 10;
            } else if (llargadaSeq == 2 && extremsDisponibles > 1) {
                avaluacioActual += 5;
            } else if (llargadaSeq == 1 && extremsDisponibles > 1) {
                avaluacioActual += 1;
            }
        }

        return avaluacioActual;
    }
}
