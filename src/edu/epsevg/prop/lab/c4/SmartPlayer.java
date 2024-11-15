package edu.epsevg.prop.lab.c4;

/**
 * Jugador aleatori
 * "Alea jacta est"
 * @author Profe
 */
public class SmartPlayer
  implements Jugador, IAuto
{
  private String nom;
  
  public SmartPlayer()
  {
    nom = "SmartPlayer";
  }
  
  public int moviment(Tauler t, int color)
  {
    
  }
  
  public String nom()
  {
    return nom;
  }
}