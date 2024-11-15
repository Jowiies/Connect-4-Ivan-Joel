package edu.epsevg.prop.lab.c4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author Hume
 */
public class Juga2 extends javax.swing.JFrame {

    int c1, c2;
    Tauler t;
    static double Ymax;
    static double Xmax;
    static int Step;
    static double REL_SIZE=0.8;
    Jugador player1;
    Jugador player2;

    Jugador currentPlayer;
    Jugador otherPlayer;
    int currentColor;
    int otherColor;
    boolean autoMode = true;
    boolean estaPensant = false;

    /**
     * Creates new form NewJFrame
     */
    public Juga2(Jugador p1, Jugador p2, boolean useAutoMode) {
        initComponents();

        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        player1 = p1;
        player2 = p2;
        this.autoMode = useAutoMode;

        init();

    }

    private void init() {
        t = new Tauler(8);

        currentPlayer = player1;
        otherPlayer = player2;
        currentColor = 1;
        otherColor = -1;

        jTextField1.setText(player1.nom());
        jTextField3.setText(player2.nom());

        Dimension mides = jLayeredPane1.getSize();
        Ymax = mides.getHeight();
        Xmax = mides.getWidth();
        Step = (int) Xmax / 8;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // Definiu al vostre gust els jugadors a enfrontar.
        Jugador p1 = new SmartPlayer(4);
        //Jugador p1 = new Aleatori();

        //Jugador p2 = new Profe(2,false);
        //Jugador p2 = new Aleatori();
        Jugador p2 = new Manual();

        boolean autoMode = true;
        final Juga2 j = new Juga2(p1, p2, autoMode);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                j.setVisible(true);
                j.mostraTornActual();
            }
        });
    }

    private void mostraTornActual() {
        jTextField2.setText("TOCA JUGAR " + currentPlayer.nom() + " " + ((currentColor == 1) ? "P1(RED)" : "P2(BLUE)"));
        if (autoMode && currentPlayer instanceof IAuto) {
            runAuto();
        }
    }

    private void canviTorn() {
        Jugador tmp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = tmp;
        currentColor *= -1;
        otherColor *= -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 400), new java.awt.Dimension(0, 400), new java.awt.Dimension(32767, 400));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 400), new java.awt.Dimension(0, 400), new java.awt.Dimension(32767, 400));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 400), new java.awt.Dimension(0, 400), new java.awt.Dimension(32767, 400));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 400), new java.awt.Dimension(0, 400), new java.awt.Dimension(32767, 400));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 400), new java.awt.Dimension(0, 400), new java.awt.Dimension(32767, 400));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 400), new java.awt.Dimension(0, 400), new java.awt.Dimension(32767, 400));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 400), new java.awt.Dimension(0, 400), new java.awt.Dimension(32767, 400));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 400), new java.awt.Dimension(0, 400), new java.awt.Dimension(32767, 400));
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Versus");

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLayeredPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLayeredPane1MouseClicked(evt);
            }
        });

        filler2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        filler2.setOpaque(true);
        filler2.setBounds(350, 0, 50, 400);
        jLayeredPane1.add(filler2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        filler4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        filler4.setOpaque(true);
        filler4.setBounds(0, 0, 50, 400);
        jLayeredPane1.add(filler4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        filler5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        filler5.setOpaque(true);
        filler5.setBounds(50, 0, 50, 400);
        jLayeredPane1.add(filler5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        filler6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        filler6.setOpaque(true);
        filler6.setBounds(100, 0, 50, 400);
        jLayeredPane1.add(filler6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        filler7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        filler7.setOpaque(true);
        filler7.setBounds(150, 0, 50, 400);
        jLayeredPane1.add(filler7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        filler8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        filler8.setOpaque(true);
        filler8.setBounds(200, 0, 50, 400);
        jLayeredPane1.add(filler8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        filler9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        filler9.setOpaque(true);
        filler9.setBounds(250, 0, 50, 400);
        jLayeredPane1.add(filler9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        filler10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        filler10.setOpaque(true);
        filler10.setBounds(300, 0, 50, 400);
        jLayeredPane1.add(filler10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        p = new MyPanel();
        add(p);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(p);//getContentPane());
        p.setLayout(layout);
        //getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(jTextField2)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                //.addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLayeredPane1, 400, 400, 400)
                                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(0,0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                //.addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, Short.MAX_VALUE)
                                .addComponent(jLayeredPane1, 400, 400, 400)
                                .addContainerGap(0, Short.MAX_VALUE))
        ); 
       
        pack();
    }// </editor-fold>

    private int whichx(int col) {
        int m = (int)((Step * (1-REL_SIZE))*0.5);
        return getInsets().left+  this.jLayeredPane1.getX()+(Step * col ) + m;//+ 70);
    }

    private int whichy(int fil) {
        //return (446 - fil * 50);
        int m = (int)((Step * (1-REL_SIZE))*0.5);
        return getInsets().top+this.jLayeredPane1.getY()+((int)Ymax - (fil+1) * (int)(Ymax/8.0)) + m;
    }
    private class MyPanel extends JPanel {

    }
    private void jLayeredPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLayeredPane1MouseClicked
//        // TODO add your handling code here:


        if (estaPensant) {
            return;
        }

        int X = evt.getX();
        int Y = evt.getY();

        int col = (int) X / Step;

        if (! (currentPlayer instanceof IAuto) ){//"Manual".equals(currentPlayer.nom())) {
            mouCurrentPlayer(col);
        } else {
            runAuto();
        }
        //mostraTornActual();

    } //GEN-LAST:event_jLayeredPane1MouseClicked

    private void mouCurrentPlayer(int colu) {

        try {
            t.afegeix(colu, currentColor);
            repaint();
            verificaSiHaAcabat(colu, currentColor);
        } 
        catch (Exception ex) 
        {
             System.out.println("Excepció:"+(ex!=null?ex.getMessage():""));
        }

    }

    private void runAuto() {
        estaPensant = true;
        // Executem l'automàtic en background
        String t = (currentPlayer == player1 ? "P1" : "P2") + " ESTIC PENSANT.....";
        jTextField2.setText(t);
        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 0));
        jLayeredPane1.setEnabled(false);
        (new Mover(currentColor, currentPlayer)).execute();
    }

    private void verificaSiHaAcabat(int colu, int color) {

        String text1 = "", text2 = "", text3 = "", dTitle = "";

        if (t.solucio(colu, color) || !t.espotmoure()) {

            if (t.solucio(colu, color)) {
                if (currentPlayer == player1) {
                    text1 = "WINNER";
                    text3 = "LOSER";
                    text2 = "VERMELL AMB MOVIMENT A COLUMNA " + (colu + 1);
                    dTitle = "GUANYA P1(" + currentPlayer.nom() + ")";
                } else {
                    text3 = "WINNER";
                    text1 = "LOSER";
                    text2 = "BLAU AMB MOVIMENT A COLUMNA " + (colu + 1);
                    dTitle = "GUANYA P2(" + currentPlayer.nom() + ")";
                }
            } else {
                // no es pot moure
                text1 = "NO PUC MOURE";
                text3 = "NO PUC MOURE";
                text2 = "TAULES";
                dTitle = "TAULES";
            }

            jTextField1.setText(text1);
            jTextField2.setText(text2);
            jTextField3.setText(text3);

            int n = JOptionPane.showConfirmDialog(
                    this, dTitle,
                    "Tornar a jugar",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                init();
            } else if (n == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            canviTorn();
        }
        mostraTornActual();
    }

    class Mover extends SwingWorker<Integer, Object> {

        int color;
        Jugador jugador;

        Mover(int color, Jugador jugador) {
            this.color = color;
            this.jugador = jugador;
        }

        @Override
        public Integer doInBackground() {
            return jugador.moviment(t, color);
        }

        @Override
        protected void done() {
            try {

                jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
                jLayeredPane1.setEnabled(true);

                mouCurrentPlayer(get());
                repaint();
                estaPensant = false;
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public void paint(Graphics g1) {
        super.paint(g1);
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
        RenderingHints.VALUE_ANTIALIAS_ON);
        int size = (int)(Step * REL_SIZE);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (t.getColor(j, i) == 1) {
                    g.setColor(Color.RED);                    
                    g.fillOval(whichx(i), whichy(j), size, size);
                    g.setColor(Color.BLACK);
                    g.drawOval(whichx(i), whichy(j), size, size);
                } else if (t.getColor(j, i) == -1) {
                    g.setColor(Color.BLUE);                    
                    g.fillOval(whichx(i), whichy(j), size, size);
                    g.setColor(Color.BLACK);
                    g.drawOval(whichx(i), whichy(j), size, size);
                }
            }
        }
    }

    // Variables declaration - do not modify
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private JPanel p;
    // End of variables declaration
}
