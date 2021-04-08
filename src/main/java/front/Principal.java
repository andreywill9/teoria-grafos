/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
import javax.swing.UIManager;

/**
 *
 * @author 55119
 */
public class Principal extends javax.swing.JFrame {
    String myDriver = "com.mysql.cj.jdbc.Driver";
    String myUrl = "jdbc:mysql://localhost:3306/MAPA?useUnicode=true&characterEncoding=utf-8";
    Connection conn;
    int clicks = 0;
    int x_old = 0;
    int y_old=0;
    Graphics universal_graph;
    ArrayList<Line2D.Float> lines = new ArrayList();
    
    
    /**
     * Creates new form Principal
     */
    public Principal() throws ClassNotFoundException, SQLException {
        Class.forName(this.myDriver);
        this.conn = DriverManager.getConnection(this.myUrl, "root", "");
        
        initComponents();
        initComponents2();
        
    }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMap = new javax.swing.JPanel();
        Title1 = new javax.swing.JLabel();
        Map = new javax.swing.JLabel();
        SuperiorMenu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Algoritmo");
        setBackground(new java.awt.Color(127, 127, 127));
        setMinimumSize(new java.awt.Dimension(999, 934));
        setResizable(false);
        getContentPane().setLayout(null);

        PanelMap.setBackground(new java.awt.Color(102, 102, 102));
        PanelMap.setLayout(null);

        Title1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        Title1.setForeground(new java.awt.Color(255, 255, 255));
        Title1.setText("Mapa Interativo:");
        PanelMap.add(Title1);
        Title1.setBounds(0, 10, 190, 19);

        Map.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mapa_brasil.png"))); // NOI18N
        Map.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                MapMouseWheelMoved(evt);
            }
        });
        Map.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MapMouseClicked(evt);
            }
        });
        Map.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MapKeyPressed(evt);
            }
        });
        PanelMap.add(Map);
        Map.setBounds(60, 40, 1000, 824);

        getContentPane().add(PanelMap);
        PanelMap.setBounds(0, 0, 1000, 860);

        SuperiorMenu.setBackground(new java.awt.Color(51, 51, 51));
        SuperiorMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SuperiorMenu.setForeground(new java.awt.Color(255, 255, 255));
        SuperiorMenu.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        SuperiorMenu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        SuperiorMenu.setMinimumSize(new java.awt.Dimension(0, 15));
        SuperiorMenu.setPreferredSize(new java.awt.Dimension(200, 35));
        SuperiorMenu.setSelectionModel(null);

        jMenu1.setBackground(new java.awt.Color(51, 51, 51));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Ponto");
        jMenu1.setOpaque(true);

        jMenuItem1.setText("Adicionar Ponto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Remover Ponto");
        jMenu1.add(jMenuItem2);

        SuperiorMenu.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(51, 51, 51));
        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Enlace");
        jMenu2.setOpaque(true);

        jMenuItem3.setText("Adicionar Enlace");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Remover Enlace");
        jMenu2.add(jMenuItem4);

        SuperiorMenu.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(51, 51, 51));
        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Erro");
        jMenu3.setOpaque(true);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem5.setText("Reportar Ponto");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem6.setText("Reportar Enlace");
        jMenu3.add(jMenuItem6);

        SuperiorMenu.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(51, 51, 51));
        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("Caminho");
        jMenu4.setOpaque(true);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Realizar Caminho Minimo");
        jMenu4.add(jMenuItem7);

        SuperiorMenu.add(jMenu4);

        setJMenuBar(SuperiorMenu);
        UIManager.put("MenuBar.background" , new java.awt.Color(51, 51, 51));
        SuperiorMenu.setOpaque(true);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponents2(){
        setSize(800,800);
        universal_graph =  Map.getGraphics();
       
    }
    
    void add_point(String name_city, int x, int y, java.awt.event.MouseEvent evt){
        if(name_city != ""){
            int tam_img = 45;
            System.out.println("x:" +x + ", y:"+y);
            javax.swing.JButton new_point = new javax.swing.JButton();
            new_point.setBackground(Color.yellow);
            new_point.setBounds(x-tam_img/2, y-tam_img/2, tam_img, tam_img);
            new_point.setMinimumSize(new Dimension(50, 50));
            new_point.setPreferredSize(new Dimension(50, 50));
            new_point.setVisible(true);
            new_point.setName(name_city);
            new_point.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    System.out.println(new_point.getName());
                    set_click();
                    add_line_aresta(x, y);
                }
            });
            Map.add(new_point);
            Map.paintComponents(universal_graph);
        
        }
        
        
    }
    
    void reset_graph(){
        universal_graph = Map.getGraphics();
        Map.paint(Map.getGraphics());
    }
    
    Graphics get_graph(){
        return Map.getGraphics();
    }
    
    void set_click(){
        this.clicks +=1;
    }
    
    private String search_city(int x, int y){
    System.out.println(x + "," + y);
    String name_city = "";
    double latitude = 5.48155495 + (y*-0.04885256);
    double longitude = -74.28821548 + (x*0.04895532);
    try {
        name_city = viewTable(latitude, longitude);
    } catch (SQLException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex);
    }
    
    return name_city;
    }
    
    private void add_line_aresta(int x, int y){
    if(this.clicks == 1){
        this.x_old = x;
        this.y_old = y;
    }
    
    if(this.clicks == 2){
        //((Graphics2D)universal_graph).setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D)universal_graph).setStroke(new BasicStroke(4));
        
        
        Line2D.Float line = new Line2D.Float(this.x_old, this.y_old, x, y);
        this.lines.add(line);
        ((Graphics2D)universal_graph).draw(line);
        this.clicks = 0;
    }
    }

    
    //1: comprimento
    //2: y origem
    //3: x destino
    //4: y destino

      
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void MapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MapKeyPressed

    }//GEN-LAST:event_MapKeyPressed

    private void MapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MapMouseClicked
        int x=evt.getX();
        int y=evt.getY();
        this.clicks = 0;
        String name_city = search_city(x, y);
        add_point(name_city, x, y, evt);
        
        //search_city(this.Map.getGraphics(),evt);

    }//GEN-LAST:event_MapMouseClicked

    private void MapMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_MapMouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_MapMouseWheelMoved

    void paint_red(){
    
    }
    
    public String viewTable(double latitude , double longitude) throws SQLException {
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
    return nome_definitivo;
    } catch (SQLException e) {
        System.out.println(e);
    }
    return "";
  }
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
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

        // create our mysql database connection
        //</editor-fold>

        // create our mysql database connection
        
        
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
    private javax.swing.JLabel Map;
    private javax.swing.JPanel PanelMap;
    private javax.swing.JMenuBar SuperiorMenu;
    private javax.swing.JLabel Title1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    // End of variables declaration//GEN-END:variables
}
