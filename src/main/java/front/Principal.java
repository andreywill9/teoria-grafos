/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author 55119
 */
public class Principal extends javax.swing.JFrame {
    String myDriver = "com.mysql.jdbc.Driver";
    String myUrl = "jdbc:mysql://localhost:3306/MAPA?useUnicode=true&characterEncoding=utf-8";
    Connection conn;
    int clicks = 0;
    int x_old = 0;
    int y_old=0;
    ArrayList<Line2D.Float> lines = new ArrayList();
    
    
    /**
     * Creates new form Principal
     */
    public Principal() throws ClassNotFoundException, SQLException {
        Class.forName(this.myDriver);
        this.conn = DriverManager.getConnection(this.myUrl, "root", "");
        
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(999, 934));
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\55119\\Desktop\\Processamento de Imagens\\Python\\imgs\\mapa.jpg")); // NOI18N
        jLabel1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jLabel1MouseWheelMoved(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jLabel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel1KeyPressed(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 110, 1000, 824);

        jButton1.setText("Pontos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 30, 134, 50);

        jButton2.setText("Enlaces");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(160, 30, 162, 52);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Criação ou Edição:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 10, 118, 16);

        jButton3.setText("Relatar Erro");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(330, 30, 162, 52);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Mapa Interativo:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 90, 110, 16);

        jButton4.setText("Caminho Mínimo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(820, 30, 162, 52);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel1KeyPressed
        
    }//GEN-LAST:event_jLabel1KeyPressed

    
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.clicks +=1;
        search_city(this.getGraphics(),evt);
        
      
    }//GEN-LAST:event_jLabel1MouseClicked

    
    void search_city(Graphics g, java.awt.event.MouseEvent evt){
    int x=evt.getX();
    int y=evt.getY();
    
    
    
    if(this.clicks == 1){
        this.x_old = x;
        this.y_old = y;
    }
    
    if(this.clicks == 2){
        
        ((Graphics2D)g).setStroke(new BasicStroke(3));
        
        ((Graphics2D)g).setColor(Color.BLUE);
        Line2D.Float line = new Line2D.Float(this.x_old+8, this.y_old+jLabel1.getY()+37, x+8, y+jLabel1.getY()+37);
        this.lines.add(line);
        ((Graphics2D)g).draw(line);
        this.clicks = 0;
    }

    
    //1: comprimento
    //2: y origem
    //3: x destino
    //4: y destino
    
    
    System.out.println(x + "," + y);





    double latitude = 5.48155495 + (y*-0.04885256);
    double longitude = -74.28821548 + (x*0.04895532);
    try {
        viewTable(latitude, longitude);
    } catch (SQLException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex);
    }
    
    }

      
    private void jLabel1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jLabel1MouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseWheelMoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Graphics g = this.getGraphics();
        ((Graphics2D)g).setColor(Color.RED);
        ((Graphics2D)g).setStroke(new BasicStroke(3));
        ((Graphics2D)g).draw(this.lines.get(0));
        
        new Adicionar_Pontos_1().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    void paint_red(){
    
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Adicionar_Enlaces().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Remover_Enlaces().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    public void viewTable(double latitude , double longitude) throws SQLException {
    double compare = 1.5;
    double latitude_max = latitude + compare;
    double latitude_min = latitude - compare;
    double longitude_max = longitude + compare;
    double longitude_min = longitude - compare;
    
    
    double diff_longitude_menor = 100000;
    double diff_latitude_menor = 100000;
    String nome_definitivo = "";
    
    String query = "select * from municipios WHERE latitude BETWEEN " + latitude_min + " and " + latitude_max + "" + " and " +
            "longitude BETWEEN " + longitude_min + " and " + longitude_max + "";
    
    try (java.sql.Statement stmt = this.conn.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String nome = rs.getString("nome");
        double latitude_now = Double.parseDouble(rs.getString("latitude"));
        double longitude_now = Double.parseDouble(rs.getString("longitude"));
        double diff1 = Math.abs(Math.abs(latitude_now) - Math.abs(latitude));
        double diff2 = Math.abs(Math.abs(longitude_now) - Math.abs(longitude));
        if(diff1 < diff_latitude_menor){
            if(diff2  < diff_longitude_menor){
                diff_latitude_menor = diff1;
                diff_longitude_menor = diff2;
                nome_definitivo = nome;
            }
        }
      }
    System.out.println(nome_definitivo);
    } catch (SQLException e) {
        System.out.println(e);
    }
  }
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
