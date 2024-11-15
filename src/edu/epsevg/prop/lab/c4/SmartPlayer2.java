
package edu.epsevg.prop.lab.c4;

public class SmartPlayer2 implements Jugador, IAuto
{
    private static final int MAX_DEPTH = 4;
    
    private String name;
    
    public SmartPlayer2()
    {
        this.name = "SmartPlayer";
    }

    @Override
    public int moviment(Tauler t, int color) 
    {
        return findBestMove(t, color, color*-1);
    }

    @Override
    public String nom() 
    {
        return this.name;
    }
    
    
    private int minimax(Tauler tauler, int depth, boolean isMax, int currentColor, int opponentColor) {
        if (depth == 0 || !tauler.espotmoure()) {
            return 0;
        }

        int bestScore = 0;

        for (int col = 0; col < tauler.getMida(); col++) {
            if (tauler.movpossible(col)) {
                
                Tauler clonedTauler = new Tauler(tauler);
                
                clonedTauler.afegeix(col, isMax ? currentColor : opponentColor);
                
                if (clonedTauler.solucio(col, isMax ? currentColor : opponentColor)) {
                    return isMax ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                
                int score = minimax(clonedTauler, depth - 1, !isMax, currentColor, opponentColor);
                
                bestScore = isMax ? Math.max(bestScore, score) : Math.min(bestScore, score);
            }
        }
        return bestScore;
    }

    
    private int findBestMove(Tauler tauler, int currentPlayerColor, int opponentColor) 
    {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int col = 0; col < tauler.getMida(); col++) {
            if (tauler.movpossible(col)) {
                Tauler clonedTauler = new Tauler(tauler);

                clonedTauler.afegeix(col, currentPlayerColor);

                if (clonedTauler.solucio(col, currentPlayerColor)) {
                    return col; 
                }

                int score = minimax(clonedTauler, MAX_DEPTH, false, currentPlayerColor, opponentColor);
                
                System.out.println(score);
                
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = col;
                }
            }
        }
	if (bestMove == -1 ){
		for (int col = 0; col < tauler.getMida(); col++) {
			if (tauler.movpossible(col)) {
				return col;
			}
		}
	}

        return bestMove;
    }
    
}


