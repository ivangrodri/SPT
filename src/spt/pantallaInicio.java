/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spt;

import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author rodri
 */

class GridsCanvas extends JPanel {
          int width, height;
          int rows;
          int cols;
          String form, AElec;
          double longx, longy, longx2, longy2;
          GridsCanvas(String f, int w, int h, int r, int c, double x, double y, double x2, double y2, String DElec) {
            setSize(width = w, height = h);
            rows = r;
            cols = c;
            form = f;
            longx = x;
            longy = y;
            longx2 = x2;
            longy2 = y2;
            AElec = DElec;
          }

          public void paint(Graphics g) {
            int i;
            width = getSize().width-1;
            height = getSize().height-1;
            if ("Cuadrada".equals(form)||"Rectangular".equals(form)) {
                        int height2, height3, width2;
                          height2 = (int) Math.round((longy/longx)*height);
                          height3 = (int) Math.round((height-height2)/2); 
                          int rowHt = height2 / (rows);
                          int rowWid = width / (cols);
                          width2 = (width-rowWid*cols)/2;
                          // dibujando las filas
                          for (i = 0; i <= rows; i++){
                          g.drawLine(width2, (i * rowHt)+height3, (rowWid*cols)+width2, (i * rowHt)+height3);
                          }           
                          // dibujando las columnas
                          for (i = 0; i <= cols; i++){
                          g.drawLine((i * rowWid)+width2, height3, (i * rowWid)+width2, (rowHt*rows)+height3);
                          }
                        //dubujar electrodos
                        if ("Agrupados en las esquinas".equals(AElec)){
                            g.fillOval(width2-3, height3-3, 6, 6);
                            g.fillOval((rowWid*cols)+width2-3, height3-3, 6, 6);
                            g.fillOval(width2-3, (rowHt*rows)+height3-3, 6, 6);
                            g.fillOval((rowWid*cols)+width2-3, (rowHt*rows)+height3-3, 6, 6);
                        }
            }else{
                      int height2, height3, width2, width3, h, w, h2, difh, w2, difw;
                      if (longx>longy){
                          height2 = (int) Math.round((longy/longx)*height);
                          height3 = (int) Math.round((height-height2)/2);
                          width2 = width;
                          width3 = 0;
                          h = (int) Math.round((longy2/longx)*height);
                          if ("Forma L".equals(form)){
                          w = (int) Math.round(((longx-longx2)/longx)*height);
                          }else{
                          w = (int) Math.round((longx2/longx)*height);
                          } 
                      }else {
                          width2 = (int) Math.round((longx/longy)*width);
                          width3 = (int) Math.round((width-width2)/2);
                          height2 = height;
                          height3 = 0;
                          h = (int) Math.round((longy2/longy)*width);
                          if ("Forma L".equals(form)){
                          w = (int) Math.round(((longx-longx2)/longy)*width);
                          }else{
                          w = (int) Math.round((longx2/longy)*width);
                          }
                      }          
                      int rowHt = height2 / (rows); //rowHt*rows
                      int rowWid = width2 / (cols); //rowWid*cols
                      //ajustando el valor de tope de las columna
                      difh = Math.abs((rowHt)-h);
                      h2 = rowHt;
                      for (i = 0; i <= rows; i++){
                      if (Math.abs((rowHt*i)-h)<difh){
                      difh = Math.abs((rowHt*i)-h);
                      h2 = rowHt*i;
                      }
                      }
                      //ajustando el valor de tope de las fila
                      difw = Math.abs((rowWid)-w);
                      w2 = rowWid;
                      for (i = 0; i <= cols; i++){
                      if (Math.abs((rowWid*i)-w)<difw){
                      difw = Math.abs((rowWid*i)-w);
                      w2 = rowWid*i;    
                      }
                      }
                     if ("Forma L".equals(form)){
                      // dibujando las filas
                      for (i = 0; i <= rows; i++){
                          if ((i * rowHt)<h2){
                          g.drawLine(width3, (i * rowHt)+height3, w2+width3, (i * rowHt)+height3);
                          }else{
                          g.drawLine(width3, (i * rowHt)+height3, (rowWid*cols)+width3, (i * rowHt)+height3);
                          }
                      }             
                      // dibujando las columnas
                      for (i = 0; i <= cols; i++){
                          if ((i * rowWid)<=w2){
                          g.drawLine((i * rowWid)+width3, height3, (i * rowWid)+width3, (rowHt*rows)+height3);
                          }else{
                          g.drawLine((i * rowWid)+width3, h2+height3, (i * rowWid)+width3, (rowHt*rows)+height3);
                          }
                      }
                      //dubujar electrodos
                        if ("Agrupados en las esquinas".equals(AElec)){
                            g.fillOval(width3-3, height3-3, 6, 6);
                            g.fillOval(w2-3, height3-3, 6, 6);
                            g.fillOval(width3-3, (rowHt*rows)+height3-3, 6, 6);
                            g.fillOval(width3+w2-3, height3+h2-3, 6, 6);
                            g.fillOval((rowWid*cols)+width3-3, height3+h2-3, 6, 6);
                            g.fillOval((rowWid*cols)+width3-3, (rowHt*rows)+height3-3, 6, 6);
                        }
                      //Forma T
                  }else{
                    // dibujando las filas
                      for (i = 0; i <= rows; i++){
                          if ((i * rowHt)<=((rowHt*rows)-h2)){
                          g.drawLine(width3, (i * rowHt)+height3, (rowWid*cols)+width3, (i * rowHt)+height3);
                          }else{
                          g.drawLine(width3+w2, (i * rowHt)+height3, (rowWid*cols)-w2+width3, (i * rowHt)+height3);
                          }
                      }
                    // dibujando las columnas
                      for (i = 0; i <= cols; i++){
                          if ((i * rowWid)<w2||(i * rowWid)>((rowWid*cols)-w2)){
                          g.drawLine((i * rowWid)+width3, height3, (i * rowWid)+width3, (rowHt*rows)-h2+height3);
                          }else{
                          g.drawLine((i * rowWid)+width3, height3, (i * rowWid)+width3, (rowHt*rows)+height3);
                          }
                      }
                    //dubujar electrodos
                    if ("Agrupados en las esquinas".equals(AElec)){
                        g.fillOval(width3-3, height3-3, 6, 6);
                        g.fillOval((rowWid*cols)+width3-3, height3-3, 6, 6);
                        g.fillOval(width3-3, (rowHt*rows)-h2+height3-3, 6, 6);
                        g.fillOval((rowWid*cols)+width3-3, (rowHt*rows)-h2+height3-3, 6, 6);
                        g.fillOval(w2-3, (rowHt*rows)-h2+height3-3, 6, 6);
                        g.fillOval((rowWid*cols)-w2-3, (rowHt*rows)-h2+height3-3, 6, 6);
                        g.fillOval(w2-3, (rowHt*rows)+height3-3, 6, 6);
                        g.fillOval((rowWid*cols)-w2-3, (rowHt*rows)+height3-3, 6, 6);
                    }  
                  }
                }
              } 
          }
public class pantallaInicio extends javax.swing.JFrame {

    /**
     * Creates new form pantallaInicio
     */
    public pantallaInicio() {
        initComponents();                     
        JTRSuperf.setText(SelecTerrenoSup(JCMSuperf));
        JTLX.setEditable(false);
        JTLY.setEditable(false);
        JTLX2.setEditable(false);
        JTLY2.setEditable(false);
        JTCp.setText("100");
        Double[] array1 = new Double[]{100.0, 0.00393, 234.0, 1.72, 1083.0, 3.42};
        map.put("Cobre blando estirado", array1);
        Double[] array2 = new Double[]{97.0, 0.00381, 242.0, 1.78, 1084.0, 3.42};
        map.put("Cobre endurecido", array2);
        Double[] array3 = new Double[]{61.0, 0.00403, 228.0, 2.86, 657.0, 2.56};
        map.put("Aluminio grado EC", array3);
        Double[] array4 = new Double[]{52.5, 0.00347, 268.0, 3.28, 654.0, 2.6};
        map.put("Aleación de Aluminio 6201", array4);
        map.forEach((key, value) -> JCTipoConductor.addItem(key));
        Double[] array5 = new Double[]{40.0, 0.00378, 245.0, 4.4, 1084.0, 3.85};
        map.put("Conductor de acero revestido de cobre", array5);
        Double[] array6 = new Double[]{20.0, 0.00378, 245.0, 8.62, 1084.0, 3.85};
        map.put("Varilla de acero revestida de cobre", array6);
        Double[] array7 = new Double[]{53.5, 0.00353, 263.0, 3.22, 652.0, 2.6};
        map.put("Aleación de Aluminio 5005", array7);
        Double[] array8 = new Double[]{20.3, 0.0036, 258.0, 8.48, 657.0, 3.58};
        map.put("Conductor de acero revestido de aluminio", array8);
        Double[] array9 = new Double[]{10.8, 0.0016, 605.0, 15.9, 1510.0, 3.28};
        map.put("Acero 1020", array9);
        Double[] array10 = new Double[]{9.8, 0.0016, 605.0, 17.5, 1400.0, 4.44};
        map.put("Varilla de acero revestido de acero inoxidable", array10);
        Double[] array11 = new Double[]{8.5, 0.0032, 293.0, 20.1, 419.0, 3.93};
        map.put("Varilla de acero galvanizado", array11);
        Double[] array12 = new Double[]{2.4, 0.0013, 749.0, 72.0, 1400.0, 4.03};
        map.put("Acero inoxidable 304", array12);
        map.forEach((key, value) -> JCTipoElec.addItem(key));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        JRBCuadrada = new javax.swing.JRadioButton();
        JRBRectangular = new javax.swing.JRadioButton();
        JRBFormaL = new javax.swing.JRadioButton();
        JRBFormaT = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JTLX = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JTLY = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JTLX2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        JTLY2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JCTipoConductor = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        JTProfMalla = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        JTN = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        JTM = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        C1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        C4 = new javax.swing.JTextField();
        C5 = new javax.swing.JTextField();
        C2 = new javax.swing.JTextField();
        C6 = new javax.swing.JTextField();
        C3 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        JLNElectrodos = new javax.swing.JLabel();
        JTNElectrodos = new javax.swing.JTextField();
        JLDiamElec = new javax.swing.JLabel();
        JTDiamElec = new javax.swing.JTextField();
        JLLongElec = new javax.swing.JLabel();
        JTLongElec = new javax.swing.JTextField();
        JLDiamElec2 = new javax.swing.JLabel();
        JLLongElec2 = new javax.swing.JLabel();
        JCDistElec = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        JLTipoE = new javax.swing.JLabel();
        JCTipoElec = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        C11 = new javax.swing.JTextField();
        C22 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        C33 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        C66 = new javax.swing.JTextField();
        C55 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        C44 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        JCMSuperf = new javax.swing.JComboBox<>();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        JTRSuperf = new javax.swing.JTextField();
        JTPSuperf = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        JTRProm = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        JCPeso = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        JTTamb = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        JTFrec = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        JTSf = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        JTCp = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        JTTf = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        JTTc = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        JTTs = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        JTIo3 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        JTXR = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        JLVista = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonGroup1.add(JRBCuadrada);
        JRBCuadrada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JRBCuadrada.setText("Cuadrada");
        JRBCuadrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRBCuadradaActionPerformed(evt);
            }
        });

        buttonGroup1.add(JRBRectangular);
        JRBRectangular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JRBRectangular.setText("Rectangular");
        JRBRectangular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRBRectangularActionPerformed(evt);
            }
        });

        buttonGroup1.add(JRBFormaL);
        JRBFormaL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JRBFormaL.setText("Forma L");
        JRBFormaL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRBFormaLActionPerformed(evt);
            }
        });

        buttonGroup1.add(JRBFormaT);
        JRBFormaT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JRBFormaT.setText("Forma T");
        JRBFormaT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRBFormaTActionPerformed(evt);
            }
        });

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/4.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Tamaño de la Malla");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Longitud X:");

        JTLX.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTLX.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTLX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTLXActionPerformed(evt);
            }
        });
        JTLX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTLXKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("m");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Longitud Y:");

        JTLY.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTLY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTLY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTLYKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("m");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Para el caso de mallas en forma de\" L\" y \"T\"");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Longitud X2:");

        JTLX2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTLX2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTLX2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTLX2KeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("m");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Longitud Y2");

        JTLY2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTLY2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTLY2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTLY2KeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("m");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Forma de la Malla");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JRBCuadrada))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JRBRectangular))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JRBFormaL))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JRBFormaT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JTLX, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JTLY, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(jLabel6)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTLX2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTLY2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)))))
                .addGap(118, 118, 118))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JRBCuadrada)
                    .addComponent(JRBRectangular)
                    .addComponent(JRBFormaL)
                    .addComponent(JRBFormaT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(JTLX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(JTLY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(JTLX2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(JTLY2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(138, 138, 138))
        );

        jTabbedPane1.addTab("Forma", jPanel3);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Material Conductor");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Tipo:");

        JCTipoConductor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JCTipoConductor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JCTipoConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCTipoConductorActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Profundidad de la malla:");

        JTProfMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTProfMallaActionPerformed(evt);
            }
        });
        JTProfMalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTProfMallaKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("m");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Numero de Conductores");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("En dirección X:");

        JTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JTNMouseReleased(evt);
            }
        });
        JTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTNActionPerformed(evt);
            }
        });
        JTN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTNKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTNKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("En dirección Y:");

        JTM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTMActionPerformed(evt);
            }
        });
        JTM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTMKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Constantes de los Materiales");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Conductividad:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Factor αr:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Factor Ko:");

        C1.setEditable(false);
        C1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Resistividad:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Temp de Fusión:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("TCAP:");

        C4.setEditable(false);
        C4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });

        C5.setEditable(false);
        C5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5ActionPerformed(evt);
            }
        });

        C2.setEditable(false);
        C2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C2ActionPerformed(evt);
            }
        });

        C6.setEditable(false);
        C6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });

        C3.setEditable(false);
        C3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JTProfMalla, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JCTipoConductor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JTN, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JTM, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(C1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                            .addComponent(C2)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(C5)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(C3)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(C6))))
                        .addGap(112, 112, 112)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(JCTipoConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(JTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(JTProfMalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18)
                    .addComponent(JTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(163, 163, 163))
        );

        jTabbedPane1.addTab("Conductores", jPanel4);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Cantidad y Material de Electrodos");

        JLNElectrodos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLNElectrodos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JLNElectrodos.setText("N° de Electrodos:");

        JTNElectrodos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTNElectrodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTNElectrodosActionPerformed(evt);
            }
        });
        JTNElectrodos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTNElectrodosKeyTyped(evt);
            }
        });

        JLDiamElec.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLDiamElec.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JLDiamElec.setText("Diámetro:");

        JTDiamElec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTDiamElec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTDiamElecActionPerformed(evt);
            }
        });
        JTDiamElec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTDiamElecKeyTyped(evt);
            }
        });

        JLLongElec.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLLongElec.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JLLongElec.setText("Longitud:");

        JTLongElec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTLongElec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTLongElecActionPerformed(evt);
            }
        });
        JTLongElec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTLongElecKeyTyped(evt);
            }
        });

        JLDiamElec2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLDiamElec2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLDiamElec2.setText("cm");

        JLLongElec2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLLongElec2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JLLongElec2.setText("m");

        JCDistElec.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JCDistElec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por toda el area", "Por el perimetro", "Agrupados en las esquinas", "En el interior", "Sin electrodos" }));
        JCDistElec.setToolTipText("");
        JCDistElec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCDistElecActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Arreglo:");

        JLTipoE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLTipoE.setText("Tipo:");

        JCTipoElec.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JCTipoElec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCTipoElecActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Constantes de los Materiales");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Conductividad:");

        C11.setEditable(false);
        C11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C11ActionPerformed(evt);
            }
        });

        C22.setEditable(false);
        C22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C22ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Factor αr:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Factor Ko:");

        C33.setEditable(false);
        C33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C33ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("TCAP:");

        C66.setEditable(false);
        C66.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C66.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C66ActionPerformed(evt);
            }
        });

        C55.setEditable(false);
        C55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C55.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C55ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Temp de Fusión:");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Resistividad:");

        C44.setEditable(false);
        C44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        C44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        C44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C44ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(JLNElectrodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTNElectrodos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JLDiamElec, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTDiamElec, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JLDiamElec2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JLLongElec, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTLongElec, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JLLongElec2)
                        .addContainerGap(93, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JLTipoE, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JCTipoElec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JCDistElec, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addGap(252, 252, 252)
                                    .addComponent(C66))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addComponent(C44, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(C55, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(C11, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(C22)
                                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(C33)
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 157, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLNElectrodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JTNElectrodos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JLDiamElec, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTDiamElec, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JLDiamElec2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JLLongElec, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTLongElec, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JLLongElec2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLTipoE)
                            .addComponent(JCTipoElec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(JCDistElec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C33, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C44, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C55, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C66, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Electrodos", jPanel5);

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Resistividad");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Material Superficial");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Profundidad");

        JCMSuperf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JCMSuperf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Concreto", "Granito triturado", "Piedra caliza limpia", "Roca triturada", "Granito limpio similar a la Grava", "#57 Granito limpio", "Grava", "Asfalto" }));
        JCMSuperf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCMSuperfActionPerformed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("Ohm-m");
        jLabel77.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("m");
        jLabel78.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        JTRSuperf.setEditable(false);
        JTRSuperf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTRSuperf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTRSuperfActionPerformed(evt);
            }
        });

        JTPSuperf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTPSuperf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTPSuperfActionPerformed(evt);
            }
        });
        JTPSuperf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTPSuperfKeyTyped(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Resistividad Promedio");

        JTRProm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTRProm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTRPromActionPerformed(evt);
            }
        });
        JTRProm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTRPromKeyTyped(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Ohm-m");
        jLabel80.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("Superficie:");

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("Suelo:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JTRProm, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(JTRSuperf, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel77)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTPSuperf, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JCMSuperf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCMSuperf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JTRSuperf, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel77)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTPSuperf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel78)))
                .addGap(18, 18, 18)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTRProm, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80))
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Terreno", jPanel6);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Opciones");

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("Peso:");

        JCPeso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JCPeso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "50.0", "70.0" }));
        JCPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCPesoActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("Temp Ambiente:");

        JTTamb.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTTamb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTTambActionPerformed(evt);
            }
        });
        JTTamb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTTambKeyTyped(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("°C");

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Hz");

        JTFrec.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTFrec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFrecActionPerformed(evt);
            }
        });
        JTFrec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTFrecKeyTyped(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("Frecuencia:");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("Factores de la Corriente de la Malla");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setText("Sf:");

        JTSf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTSfActionPerformed(evt);
            }
        });
        JTSf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTSfKeyTyped(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setText("%");

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel57.setText("Cp:");

        JTCp.setEditable(false);
        JTCp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTCpActionPerformed(evt);
            }
        });
        JTCp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTCpKeyTyped(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel58.setText("%");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Duración de la Falla");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel60.setText("tf:");

        JTTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTTfActionPerformed(evt);
            }
        });
        JTTf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTTfKeyTyped(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setText("s");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setText("s");

        JTTc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTTcActionPerformed(evt);
            }
        });
        JTTc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTTcKeyTyped(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel63.setText("tc:");

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel64.setText("s");

        JTTs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTTsActionPerformed(evt);
            }
        });
        JTTs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTTsKeyTyped(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel65.setText("ts:");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText("Corriente de Cortocircuito a Tierra");

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel67.setText("3Io:");

        JTIo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTIo3ActionPerformed(evt);
            }
        });
        JTIo3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTIo3KeyTyped(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel68.setText("kA");

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel69.setText("(Corriente de falla simetrica)");

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel70.setText("Relacion X/R:");

        JTXR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTXRActionPerformed(evt);
            }
        });
        JTXR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTXRKeyTyped(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("Kg");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(JTFrec, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JCPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addComponent(JTTamb, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTSf, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel56)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTCp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58))
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTTf, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel61)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTTc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel62)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTTs, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel64))
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTXR, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(JTIo3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel69)))))
                .addGap(145, 145, 145))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JCPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(JTSf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57)
                    .addComponent(JTCp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTTamb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTFrec, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(281, 281, 281))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(JTTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61)
                            .addComponent(jLabel63)
                            .addComponent(JTTc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62)
                            .addComponent(jLabel65)
                            .addComponent(JTTs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(JTIo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(JTXR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(224, 224, 224))))
        );

        jTabbedPane1.addTab("Caso de Estudio", jPanel7);

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton14.setText("Aceptar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton15.setText("Limpiar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(197, Short.MAX_VALUE))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(300, 300));

        JLVista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/t1.png"))); // NOI18N

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("0.00");

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("--.---");

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("--.---");

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("--.---");

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("m");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(JLVista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(JLVista)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel31)
                .addGap(6, 6, 6)
                .addComponent(jLabel28)
                .addGap(6, 6, 6)
                .addComponent(jLabel27)
                .addGap(25, 25, 25)
                .addComponent(jLabel29)
                .addGap(135, 135, 135)
                .addComponent(jLabel30)
                .addContainerGap())
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(300, 300));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");
        jMenu1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jMenu1ItemStateChanged(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inuevo.png"))); // NOI18N
        jMenuItem1.setText("Nuevo Proyecto");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iabrir.png"))); // NOI18N
        jMenuItem2.setText("Abrir Proyecto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iguardar.png"))); // NOI18N
        jMenuItem3.setText("Guardar Proyecto");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/isalir.png"))); // NOI18N
        jMenuItem4.setText("Salir");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opciones");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icalculo.png"))); // NOI18N
        jMenuItem5.setText("Cálculo de la malla de tierra");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/igrafica.png"))); // NOI18N
        jMenu5.setText("Graficas");

        jMenuItem6.setText("Tensión de Paso");
        jMenu5.add(jMenuItem6);

        jMenuItem7.setText("Tensión de Toque");
        jMenu5.add(jMenuItem7);

        jMenu2.add(jMenu5);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ireporte.png"))); // NOI18N
        jMenuItem8.setText("Generar Reporte");
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Ayuda");

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imanual.png"))); // NOI18N
        jMenuItem9.setText("Manual de Usuario");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iayuda.png"))); // NOI18N
        jMenuItem10.setText("Acerca de SPT");
        jMenu4.add(jMenuItem10);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JRBCuadradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRBCuadradaActionPerformed
        // TODO add your handling code here:
        acceso();
    }//GEN-LAST:event_JRBCuadradaActionPerformed

    private void JTNElectrodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTNElectrodosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTNElectrodosActionPerformed

    private void JTDiamElecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTDiamElecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTDiamElecActionPerformed

    private void JTLongElecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTLongElecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTLongElecActionPerformed

    private void C11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C11ActionPerformed

    private void C22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C22ActionPerformed

    private void C33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C33ActionPerformed

    private void C66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C66ActionPerformed

    private void C55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C55ActionPerformed

    private void C44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C44ActionPerformed

    private void JCPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCPesoActionPerformed

    private void JTTambActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTTambActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTTambActionPerformed

    private void JTFrecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFrecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFrecActionPerformed

    private void JTSfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTSfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTSfActionPerformed

    private void JTCpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTCpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTCpActionPerformed

    private void JTTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTTfActionPerformed

    private void JTTsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTTsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTTsActionPerformed

    private void JTTcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTTcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTTcActionPerformed

    private void JTIo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTIo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTIo3ActionPerformed

    private void JTXRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTXRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTXRActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        permiso = false;
        jPanel1.removeAll();
        jPanel1.revalidate();
        jPanel1.add(jLabel41);
        jPanel1.repaint();
        Icon icono;
        icono = new ImageIcon(getClass().getResource("/img/t1.png"));
        JLVista.setIcon(icono);
        jLabel28.setText("--.---");
        jLabel29.setText("--.---");
        jLabel30.setText("--.---");
        buttonGroup1.clearSelection();
        JTLX.setEditable(false);
        JTLX.setText("");
        JTLY.setEditable(false);
        JTLY.setText("");
        JTLX2.setText("");
        JTLX2.setEditable(false);
        JTLY2.setText("");
        JTLY2.setEditable(false); 
        JCTipoConductor.setSelectedItem("Cobre endurecido");
        JTM.setText("");
        JTN.setText("");
        JTProfMalla.setText("");
        JCDistElec.setSelectedItem("Por toda el area");
        JCTipoElec.setSelectedItem("Acero 1020");
        JTNElectrodos.setText("");
        JTDiamElec.setText("");
        JTLongElec.setText("");
        JCMSuperf.setSelectedItem("Concreto");
        JTPSuperf.setText("");
        JTRProm.setText("");
        JCPeso.setSelectedItem("50.0");
        JTSf.setText("");
        JTTamb.setText("");
        JTTc.setText("");
        JTTf.setText("");
        JTTs.setText("");
        JTFrec.setText("");
        JTIo3.setText("");
        JTXR.setText("");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C3ActionPerformed

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C6ActionPerformed

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C2ActionPerformed

    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C5ActionPerformed

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C4ActionPerformed

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C1ActionPerformed

    private void JTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTMActionPerformed

    private void JTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTNActionPerformed

    private void JTProfMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTProfMallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTProfMallaActionPerformed

    private void jMenu1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jMenu1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ItemStateChanged

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void JCTipoConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCTipoConductorActionPerformed
        // TODO add your handling code here:
        //map.get();
        Double[] lista = map.get(JCTipoConductor.getSelectedItem());
        C1.setText(lista[0].toString());
        C2.setText(lista[1].toString());
        C3.setText(lista[2].toString());
        C4.setText(lista[3].toString());
        C5.setText(lista[4].toString());
        C6.setText(lista[5].toString());
    }//GEN-LAST:event_JCTipoConductorActionPerformed

    private void JTRSuperfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTRSuperfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTRSuperfActionPerformed

    private void JTPSuperfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTPSuperfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTPSuperfActionPerformed

    private void JTRPromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTRPromActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JTRPromActionPerformed

    private void JCTipoElecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCTipoElecActionPerformed
        // TODO add your handling code here:
        Obtener_constantes ();
    }//GEN-LAST:event_JCTipoElecActionPerformed

    private void JTNElectrodosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNElectrodosKeyTyped
        // TODO add your handling code here:
        validar_entero(evt);
    }//GEN-LAST:event_JTNElectrodosKeyTyped

    private void JCMSuperfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCMSuperfActionPerformed
        // TODO add your handling code here:
        JTRSuperf.setText(SelecTerrenoSup(JCMSuperf));
    }//GEN-LAST:event_JCMSuperfActionPerformed

    private void JRBRectangularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRBRectangularActionPerformed
        // TODO add your handling code here:
        acceso1();
    }//GEN-LAST:event_JRBRectangularActionPerformed

    private void JRBFormaLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRBFormaLActionPerformed
        // TODO add your handling code here:
        acceso2();
    }//GEN-LAST:event_JRBFormaLActionPerformed

    private void JRBFormaTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRBFormaTActionPerformed
        // TODO add your handling code here:
        acceso2();
    }//GEN-LAST:event_JRBFormaTActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        ConstruirMalla ();
        if (permiso){
        DibujarMalla ();
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void JTDiamElecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTDiamElecKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTDiamElecKeyTyped

    private void JTLongElecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTLongElecKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTLongElecKeyTyped

    private void JTProfMallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTProfMallaKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTProfMallaKeyTyped

    private void JTNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNKeyTyped
        // TODO add your handling code here:
        validar_entero(evt);
    }//GEN-LAST:event_JTNKeyTyped

    private void JTMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTMKeyTyped
        // TODO add your handling code here:
        validar_entero(evt);
    }//GEN-LAST:event_JTMKeyTyped

    private void JTLXKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTLXKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTLXKeyTyped

    private void JTLYKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTLYKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTLYKeyTyped

    private void JTLX2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTLX2KeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTLX2KeyTyped

    private void JTLY2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTLY2KeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTLY2KeyTyped

    private void JTPSuperfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPSuperfKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTPSuperfKeyTyped

    private void JTTambKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTTambKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTTambKeyTyped

    private void JTFrecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTFrecKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTFrecKeyTyped

    private void JTSfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTSfKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTSfKeyTyped

    private void JTCpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTCpKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTCpKeyTyped

    private void JTTfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTTfKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTTfKeyTyped

    private void JTTcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTTcKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTTcKeyTyped

    private void JTTsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTTsKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTTsKeyTyped

    private void JTIo3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTIo3KeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTIo3KeyTyped

    private void JTXRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTXRKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTXRKeyTyped

    private void JTNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_JTNKeyReleased

    private void JTNMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTNMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_JTNMouseReleased

    private void JCDistElecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCDistElecActionPerformed
        // TODO add your handling code here:
        String arreglo;
        arreglo = (String) JCDistElec.getSelectedItem();
            if ("Sin electrodos".equals(arreglo)){
                JTNElectrodos.setText("0");
                JTNElectrodos.setEditable(false);
                JTDiamElec.setText("0");
                JTDiamElec.setEditable(false);
                JTLongElec.setText("0");
                JTLongElec.setEditable(false);
                JCTipoElec.setVisible(false);
                JLTipoE.setVisible(false);
                C11.setText("0");
                C22.setText("0");
                C33.setText("0");
                C44.setText("0");
                C55.setText("0");
                C66.setText("0");
            }else{
                JTNElectrodos.setEditable(true);
                JTDiamElec.setEditable(true);
                JTLongElec.setEditable(true);
                JCTipoElec.setVisible(true);
                JLTipoE.setVisible(true);
                Obtener_constantes ();
            }
    }//GEN-LAST:event_JCDistElecActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void JTLXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTLXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTLXActionPerformed

    private void JTRPromKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTRPromKeyTyped
        // TODO add your handling code here:
        validar_decimal(evt);
    }//GEN-LAST:event_JTRPromKeyTyped

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
         //TODO add your handling code here:
        if (permiso){
        String path = "malla.json";
                try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
                Gson gson = new Gson();
                String jsonString = gson.toJson(mallaN);
                out.write(jsonString);
                } catch (Exception e) {
                e.printStackTrace();
                }
        }else{
        JOptionPane.showMessageDialog(this, "Primero debe generar la mmala presionando ACEPTAR",
                                              "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(pantallaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pantallaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pantallaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pantallaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new pantallaInicio().setVisible(true);

            }
        });
    }
    public HashMap<String, Double[]> map = new HashMap<>();
    
    
        public static String SelecTerrenoSup (javax.swing.JComboBox JCTerrenoS){
    String terreno = (String)JCTerrenoS.getSelectedItem();
    String a = "";
        switch (terreno) {
            case "Concreto" -> a="100.0";
            case "Granito triturado" -> a="1318.7";
            case "Piedra caliza limpia" -> a="2500.0";
            case "Roca triturada" -> a="4267.2";
            case "Granito limpio similar a la Grava" -> a="5000.0";
            case "#57 Granito limpio" -> a="8106.8";
            case "Grava" -> a="8534.4";
            case "Asfalto" -> a="6000000.0";
            default -> {
            }
        }
        return a;
        //devuelve el valor de resistencia de la capa supercifial
    }
        public void acceso (){
            JTLX.setEditable(true);
            JTLY.setEditable(false);
            JTLY.setText("0");
            JTLX2.setText("0");
            JTLX2.setEditable(false);
            JTLY2.setText("0");
            JTLY2.setEditable(false);    
        }//habilita el ingreso de las medidas en funcion de la forma del terreno
        public void acceso1 (){
            JTLX.setEditable(true);
            JTLY.setEditable(true);
            JTLY.setText("");
            JTLX2.setText("0");
            JTLX2.setEditable(false);
            JTLY2.setText("0");
            JTLY2.setEditable(false);    
        }//habilita el ingreso de las medidas en funcion de la forma del terreno
        public void acceso2 (){
            JTLX.setEditable(true);
            JTLY.setEditable(true);
            JTLY.setText("");
            JTLX2.setText("");
            JTLX2.setEditable(true);
            JTLY2.setText("");
            JTLY2.setEditable(true);    
        }//habilita el ingreso de las medidas en funcion de la forma del terreno
        public void validar_entero(java.awt.event.KeyEvent evt){
            char []p = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
            int b = 0;
            for (int i = 0; i <= 9; i++) {
            if (p[i]==evt.getKeyChar()){b=1;}
            }
            if(b==0){evt.consume();}
        }//Valida entrada numerica entero
        public void validar_decimal(java.awt.event.KeyEvent evt){
            char []p = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.'};
            int b = 0;
            for (int i = 0; i <= 10; i++) {
            if (p[i]==evt.getKeyChar()){b=1;}
            }
            if(b==0){evt.consume();}
        }//Valida entrada numerica decimal
        public void Obtener_constantes (){
        Double[] lista = map.get(JCTipoElec.getSelectedItem());
        C11.setText(lista[0].toString());
        C22.setText(lista[1].toString());
        C33.setText(lista[2].toString());
        C44.setText(lista[3].toString());
        C55.setText(lista[4].toString());
        C66.setText(lista[5].toString());
        }
        
        public class Dimensiones{
            public String Forma;
            public double LX;
            public double LX2;
            public double LY;
            public double LY2;
            
            public Dimensiones(String forma, double lX, double lX2, double lY, double lY2){
                 this.Forma = forma;
                 this.LX = lX;
                 this.LX2 = lX2;
                 this.LY = lY;
                 this.LY2 = lY2;
            }
        }
        
        public class Conductores{
        public String TipoConductor;
        public double ProfMalla;
        public int M;
        public int N;
        public CteMateriales CteMC;
        
            public Conductores (String tipoConductor, double profMalla, int m, int n, CteMateriales cteMC){
                this.TipoConductor = tipoConductor;
                this.ProfMalla = profMalla;
                this.M = m;
                this.N = n;
                this.CteMC = cteMC;
            }
        }
        
        public class CteMateriales{
        public double Conduc;
        public double Far;
        public double Fko;
        public double Resist;
        public double TempF;
        public double TCAP;
        
            public CteMateriales (double conduc, double far, double fko, double resist, double tempF, double tCAP){
                this.Conduc = conduc;
                this.Far = far;
                this.Fko = fko;
                this.Resist = resist;
                this.TempF = tempF;
                this.TCAP = tCAP;
            }
        }
        
        public class Electrodos{
        public int NElectrodos;
        public double DiamElec;
        public double LongElec;
        public String TipoElec;
        public String DistElec;
        public CteMateriales CteME;
        
            public Electrodos (int nElectrodos, double diamElec, double longElec, String tipoElec, String distElec,
                                CteMateriales cteME){
                this.NElectrodos = nElectrodos;
                this.DiamElec = diamElec;
                this.LongElec = longElec;
                this.TipoElec = tipoElec;
                this.DistElec = distElec;
                this.CteME = cteME;
            }
        }
        
        public class Terreno{
        public double RSuperf;
        public String MSuperf;
        public double PSuperf;
        public double RProm;
        
   
            public Terreno (double rSuperf, String mSuperf, double pSuperf, double rProm){
                this.RSuperf = rSuperf;
                this.MSuperf = mSuperf;
                this.PSuperf = pSuperf;
                this.RProm = rProm;
            }
        }
        
        public class CEstudio{
        public double Peso;
        public double Tamb;
        public double Frec;
        public double Sf;
        public double Cp;
        public double Tf;
        public double Tc;
        public double Ts;
        public double Io3;
        public double XR;
        
            public CEstudio(double peso, double tamb, double frec, double sf, double cp, double tf, double tc,
                            double ts, double io3, double xR){
                this.Peso = peso;
                this.Tamb = tamb;
                this.Frec = frec;
                this.Sf = sf;
                this.Cp = cp;
                this.Tf = tf;
                this.Tc = tc;
                this.Ts = ts;
                this.Io3 = io3;
                this.XR = xR;
            }
        }
        
        public class Malla {
        public Dimensiones MallaDim;
        public Conductores MallaCon;
        public Electrodos MallaEle;
        public Terreno MallaTer;
        public CEstudio MallaCEs;
        
            public Malla(Dimensiones MallaDim, Conductores MallaCon, Electrodos MallaEle, Terreno MallaTer, CEstudio MallaCEs){
                this.MallaDim = MallaDim;
                this.MallaCon = MallaCon;
                this.MallaEle = MallaEle;
                this.MallaTer = MallaTer;
                this.MallaCEs = MallaCEs;
            }
        }
        //variables de calculo
        public double D, Dx, Dy, ResistP, CsP;
        public double EtP = 0.0, EpP = 0.0;
        public double Lt, A, Rg, Ta, Df, Ig, GPR, LC, LR, LM,LP, na, nb, nc, nd, n, Dm1, Dm2, Dm, Ki, Kii, Kh;
        public double If, Akcmil, dc, Km, Em, Ls, Ks, Es;
        public String calibre, Mpaso, Mtoque;
        resultados result = new resultados();
        //Variables de dibujo
        public String formaM, ArregloElec;
        public double Lx, Ly, Lx2, Ly2;
        public int ElecDisp, ElecPerm;
        public int Cx, Cy;
        public boolean permiso;
        public Malla mallaN;
        
        public void ConstruirMalla (){
            result.setVisible(false);
            String fmalla = "";
                for (Enumeration<AbstractButton> buttons = buttonGroup1.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        fmalla = button.getText();
                        formaM = fmalla;
                    }
                }
            if (JTLX.getText().length()==0||JTLY.getText().length()==0||JTLX2.getText().length()==0||JTLY2.getText().length()==0
                ||JTProfMalla.getText().length()==0||JTN.getText().length()==0||JTM.getText().length()==0
                ||JTNElectrodos.getText().length()==0|JTDiamElec.getText().length()==0||JTLongElec.getText().length()==0
                ||JTPSuperf.getText().length()==0||JTTamb.getText().length()==0
                ||JTFrec.getText().length()==0||JTSf.getText().length()==0||JTCp.getText().length()==0
                ||JTTf.getText().length()==0||JTTc.getText().length()==0||JTTs.getText().length()==0||JTIo3.getText().length()==0
                ||JTXR.getText().length()==0){
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                permiso = false;
             }else if (!"Cuadrada".equals(fmalla)&&(Double.parseDouble(JTLX.getText())<=Double.parseDouble(JTLX2.getText())||
                       Double.parseDouble(JTLY.getText())<=Double.parseDouble(JTLY2.getText()))){
                JOptionPane.showMessageDialog(this, "Las dimensiones secundarias no pueden ser mayores o iguales a las principales",
                                              "Error", JOptionPane.ERROR_MESSAGE); 
                permiso = false;
             }else if("Rectangular".equals(fmalla)&&(Double.parseDouble(JTLX.getText())<=Double.parseDouble(JTLY.getText()))){
                 JOptionPane.showMessageDialog(this, "Para las mallas rectangulares la longitud LX debe ser mayor que la longitud LY",
                                              "Error", JOptionPane.ERROR_MESSAGE); 
                 permiso = false;
             }else if("Forma T".equals(fmalla)&&(Double.parseDouble(JTLX.getText())<=(2*Double.parseDouble(JTLX2.getText())))){
                 JOptionPane.showMessageDialog(this, "Para las mallas con forma T la longitud LX debe ser mayor que el doble de la longitud LX2",
                                              "Error", JOptionPane.ERROR_MESSAGE);
                 permiso = false;
             }else{       
                permiso = true;
                if ("Cuadrada".equals(fmalla)){
                    JTLY.setText(JTLX.getText());
                }
                
                if (null==fmalla) { //Cantidad minima de contuctores
                } else {
                    switch (fmalla) {
                        case "Cuadrada", "Rectangular" -> {
                            if (Integer.parseInt(JTN.getText())<2){
                                JTN.setText("2");
                            }   if (Integer.parseInt(JTM.getText())<2){
                                JTM.setText("2");
                            }
                        }
                        case "Forma L" -> {
                            if (Integer.parseInt(JTN.getText())<3){
                                JTN.setText("3");
                            }   if (Integer.parseInt(JTM.getText())<3){
                                JTM.setText("3");
                            }
                        }
                        case "Forma T" -> {
                            if (Integer.parseInt(JTN.getText())<3){
                                JTN.setText("3");
                            }   if (Integer.parseInt(JTM.getText())<4){
                                JTM.setText("4");
                            }
                        }
                        default -> {
                        }
                    }
                }
                if (Double.parseDouble(JTProfMalla.getText())<0.5){
                    JOptionPane.showMessageDialog(this, "Se recomienda una profundidad mayor a 0.5m",
                                                  "Ajustar profundidad de malla", JOptionPane.WARNING_MESSAGE);
                } else if (Double.parseDouble(JTProfMalla.getText())>1.5){
                    JOptionPane.showMessageDialog(this, "Se recomienda una profundidad menor a 1.5m",
                                                  "Ajustar profundidad de malla", JOptionPane.WARNING_MESSAGE);
                }
                if (Double.parseDouble(JTPSuperf.getText())<0.1){
                    JOptionPane.showMessageDialog(this, "Se recomienda una profundidad mayor a 0.1m",
                                                  "Ajustar profundidad de la capa superficial", JOptionPane.WARNING_MESSAGE);
                } else if (Double.parseDouble(JTPSuperf.getText())>0.15){
                    JOptionPane.showMessageDialog(this, "Se recomienda una profundidad menor a 0.15m",
                                                  "Ajustar profundidad de la capa superficial", JOptionPane.WARNING_MESSAGE);
                }
                //ajuste de dimensiones secundarias
                Cx = Integer.parseInt(JTN.getText());
                Cy = Integer.parseInt(JTM.getText());
                if ("Forma T".equals(fmalla)||"Forma L".equals(fmalla)){
                    Dx= Double.parseDouble(JTLX.getText())/(Cy-1);
                    Dy= Double.parseDouble(JTLY.getText())/(Cx-1);
                    Lx2=Math.rint(((Math.round(Double.parseDouble(JTLX2.getText())/Dx))*Dx)*100)/100;
                    Ly2=Math.rint(((Math.round(Double.parseDouble(JTLY2.getText())/Dy))*Dy)*100)/100;
                    if (Lx2<Dx){
                       Lx2=Math.rint(Dx*100)/100;
                    }
                    if (Ly2<Dy){
                       Ly2=Math.rint(Dy*100)/100;
                    }
                    JTLX2.setText(""+Lx2);
                    JTLY2.setText(""+Ly2);
                }
                //Electrodos disponibles segun la configuracion
                ElecDisp = Integer.parseInt(JTNElectrodos.getText());
                ArregloElec = JCDistElec.getSelectedItem().toString();
                if (null!=fmalla)switch (fmalla) {
                    case "Cuadrada", "Rectangular" -> {
                        if (null == ArregloElec){
                            ElecPerm = 0;
                        } else ElecPerm = switch (ArregloElec) {
                            case "Por toda el area" -> Cx*Cy;
                            case "Por el perimetro" -> (2*Cx)+(2*Cy)-4;
                            case "En el interior" -> (Cx-2)*(Cy-2);
                            case "Agrupados en las esquinas" -> 4;
                            default -> 0;
                        };
                    }
                    case "Forma L" -> {
                        if (null == ArregloElec){
                            ElecPerm = 0;
                        } else ElecPerm = switch (ArregloElec) {
                            case "Por toda el area" -> (Cx*Cy)-(int)((Ly2/Dy)*(Lx2/Dx));
                            case "Por el perimetro" -> (2*Cx)+(2*Cy)-4;
                            case "En el interior" -> (Cx*Cy)-((int)((Ly2/Dy)*(Lx2/Dx)))-((2*Cx)+(2*Cy)-4);
                            case "Agrupados en las esquinas" -> 6;
                            default -> 0;
                        };
                    }
                    case "Forma T" -> {
                        if (null == ArregloElec){
                            ElecPerm = 0;
                        } else ElecPerm = switch (ArregloElec) {
                            case "Por toda el area" -> (Cx*Cy)-(2*((int)((Ly2/Dy)*(Lx2/Dx))));
                            case "Por el perimetro" -> (2*Cx)+(2*Cy)-4;
                            case "En el interior" -> (Cx*Cy)-(2*((int)((Ly2/Dy)*(Lx2/Dx))))-((2*Cx)+(2*Cy)-4);
                            case "Agrupados en las esquinas" -> 8;
                            default -> 0;
                        };
                    }
                    default -> {
                    }
                }
                if (ElecDisp>ElecPerm||"Agrupados en las esquinas".equals(ArregloElec)){
                        ElecDisp = ElecPerm;
                        JTNElectrodos.setText(ElecDisp+"");
                }
                //Toma de datos de la malla
                Dimensiones dimN = new Dimensiones(fmalla, Double.parseDouble(JTLX.getText()), Double.parseDouble(JTLX2.getText()),
                                                    Double.parseDouble(JTLY.getText()), Double.parseDouble(JTLY2.getText()));
                CteMateriales matCondN = new CteMateriales(Double.parseDouble(C1.getText()), Double.parseDouble(C2.getText()),
                                                          Double.parseDouble(C3.getText()), Double.parseDouble(C4.getText()),
                                                          Double.parseDouble(C5.getText()), Double.parseDouble(C6.getText()));
                Conductores condN = new Conductores(JCTipoConductor.getSelectedItem().toString(),
                                                    Double.parseDouble(JTProfMalla.getText()),Integer.parseInt(JTM.getText()),
                                                    Integer.parseInt(JTN.getText()), matCondN);
                CteMateriales matElecN = new CteMateriales(Double.parseDouble(C11.getText()), Double.parseDouble(C22.getText()),
                                                          Double.parseDouble(C33.getText()), Double.parseDouble(C44.getText()),
                                                          Double.parseDouble(C55.getText()), Double.parseDouble(C66.getText()));
                Electrodos elecN = new Electrodos (Integer.parseInt(JTNElectrodos.getText()), Double.parseDouble(JTDiamElec.getText()),
                                                   Double.parseDouble(JTLongElec.getText()), JCTipoElec.getSelectedItem().toString(),
                                                   JCDistElec.getSelectedItem().toString(),matElecN);
                Terreno terN = new Terreno (Double.parseDouble(JTRSuperf.getText()), JCMSuperf.getSelectedItem().toString(),
                                            Double.parseDouble(JTPSuperf.getText()), Double.parseDouble(JTRProm.getText()));
                CEstudio cEstN = new CEstudio(Double.parseDouble(JCPeso.getSelectedItem().toString()),
                                              Double.parseDouble(JTTamb.getText()), Double.parseDouble(JTFrec.getText()),
                                              Double.parseDouble(JTSf.getText()), Double.parseDouble(JTCp.getText()), 
                                              Double.parseDouble(JTTf.getText()), Double.parseDouble(JTTc.getText()),
                                              Double.parseDouble(JTTs.getText()), Double.parseDouble(JTIo3.getText()), 
                                              Double.parseDouble(JTXR.getText()));
                mallaN = new Malla (dimN, condN, elecN, terN, cEstN);
//                String path = "malla.json";
//                try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
//                Gson gson = new Gson();
//                String jsonString = gson.toJson(mallaN);
//                out.write(jsonString);
//                } catch (Exception e) {
//                e.printStackTrace();
//                }
                Lx = dimN.LX;
                Ly = dimN.LY;
                Lx2 = dimN.LX2;
                Ly2 = dimN.LY2;
                Icon icono;
                if ("Sin electrodos".equals(elecN.DistElec)){
                    icono = new ImageIcon(getClass().getResource("/img/t2.png"));
                }else{
                    icono = new ImageIcon(getClass().getResource("/img/t3.png"));
                }
                JLVista.setIcon(icono);
                //Calculos de parametros
                Dx = dimN.LX/(condN.M-1);
                Dy = dimN.LY/(condN.N-1);
                D = (Dx+Dy)/2.0;
                 
                if (D<3.0){
                JOptionPane.showMessageDialog(this, "Se recomienda aumentar el distanciamiento entre conductores a más de 3m",
                                              "Ajustar distanciamiento entre conductores", JOptionPane.WARNING_MESSAGE);
                }
                if (D>15.0){
                JOptionPane.showMessageDialog(this, "Se recomienda disminuir el distanciamiento entre conductores a menos de 15m",
                                              "Ajustar distanciamiento entre conductores", JOptionPane.WARNING_MESSAGE);
                }
                ResistP = terN.RProm;
                CsP = 1-(0.09*(1-ResistP/terN.RSuperf))/(2*terN.PSuperf+0.09);
                if (cEstN.Peso==50.0){
                    EpP = (1000+6.0*CsP*terN.RSuperf)*(0.116/(Math.sqrt(cEstN.Ts)));
                    EtP = (1000+1.5*CsP*terN.RSuperf)*(0.116/(Math.sqrt(cEstN.Ts)));
                } else if (cEstN.Peso==70.0){
                    EpP = (1000+6.0*CsP*terN.RSuperf)*(0.157/(Math.sqrt(cEstN.Ts)));
                    EtP = (1000+1.5*CsP*terN.RSuperf)*(0.157/(Math.sqrt(cEstN.Ts)));
                }
                //calculo de LC
                if ("Forma L".equals(dimN.Forma)||"Forma T".equals(dimN.Forma)){
                    double LadoX = 0;
                    double LadoY = 0;
                    double espacio1 = 0;
                    double espacio2 = 0;
                    double LX3;
                    double LY3;
                    //calculo de longitud de conductor para forma L
                    if ("Forma L".equals(dimN.Forma)){
                    LX3 = dimN.LX-dimN.LX2;
                    LY3 = dimN.LY-dimN.LY2;
                        while (espacio1 <= dimN.LY) {
                            if (espacio1 <= LY3){ 
                            LadoX = LadoX + dimN.LX;
                            }else{
                            LadoX = LadoX + LX3;
                            }
                            espacio1 = espacio1 + Dy;
                        }
                        while (espacio2 <= dimN.LX) {
                            if (espacio2 <= LX3){ 
                            LadoY = LadoY + dimN.LY;
                            }else{
                            LadoY = LadoY + LY3;
                            }
                            espacio2 = espacio2 + Dx;
                        }
                    }else{
                    //calculo de longitud de conductor fotma T
                    LX3 = dimN.LX-(2*dimN.LX2);
                    LY3 = dimN.LY-dimN.LY2;
                        while (espacio1 <= dimN.LY) {
                            if (espacio1<=LY3){
                            LadoX = LadoX + dimN.LX;
                            }else{
                            LadoX = LadoX + LX3;
                            }
                            espacio1 = espacio1 + Dy;
                        }
                        while (espacio2 <= dimN.LX){
                            if (espacio2 >= dimN.LX2 && espacio2 <= (dimN.LX2+LX3)){
                            LadoY = LadoY + dimN.LY;
                            }else{
                            LadoY = LadoY + LY3;
                            }
                            espacio2 = espacio2 + Dx;
                        }
                    }
                    LC = LadoX+LadoY;
                }else{
                    LC = condN.N*dimN.LX+condN.M*dimN.LY;
                }
                LR = elecN.NElectrodos*elecN.LongElec;
                Lt= LC + LR;
                //Calculo de area
                if (!"Forma T".equals(dimN.Forma)){
                A = (dimN.LX*dimN.LY)-(dimN.LX2*dimN.LY2);
                }else{
                A = (dimN.LX*dimN.LY)-(2.0*(dimN.LX2*dimN.LY2));
                }
                Rg = ResistP*((1.0/Lt) + (1.0/(Math.sqrt(20*A)))*(1.0 + (1.0/(1.0+condN.ProfMalla*Math.sqrt(20.0/A)))));
                Ta = cEstN.XR*(1.0/(2.0*Math.PI*cEstN.Frec));
                Df = Math.sqrt(1+((Ta/cEstN.Tf)*(1-Math.exp((-2.0*cEstN.Tf)/Ta))));
                Ig = Df*(cEstN.Sf/100)*(cEstN.Io3*1000); //revisar
                GPR = Rg*Ig;
                if (GPR<EtP){
                    System.out.println("tension tolerable");
                }else{
                    System.out.println("Calcular tensiones reales");
                }
                if ("En el interior".equals(elecN.DistElec)||"Sin electrodos".equals(elecN.DistElec)){
                    LM = LC+LR;
                }else{
                    LM = LC + (1.55 + (1.22*(elecN.LongElec/(Math.sqrt(Math.pow(dimN.LX, 2)+Math.pow(dimN.LY, 2))))))*LR;
                }
                LP = (2*dimN.LX)+(2*dimN.LY); //perimetro de la malla
                na = 2.0*(LC/LP);
                nb = Math.sqrt(LP/(4*Math.sqrt(A)));
                nc = Math.pow((dimN.LX*dimN.LY)/A, (0.7*A)/(dimN.LX*dimN.LY));
                Dm1 = Math.sqrt(Math.pow(dimN.LX, 2)+Math.pow(dimN.LY-dimN.LY2, 2));
                Dm2 = Math.sqrt(Math.pow(dimN.LY, 2)+Math.pow(dimN.LX-dimN.LX2, 2));
                if (Dm1>Dm2){
                    Dm = Dm1;// fija el valor mayor
                }else{
                    Dm = Dm2;
                }
                nd = Dm/(Math.sqrt(Math.pow(dimN.LX, 2)+Math.pow(dimN.LY, 2)));
                if (null == dimN.Forma){
                    n=na*nb*nc*nd;
                }else n = switch (dimN.Forma) {
                    case "Cuadrada" -> na;
                    case "Rectangular" -> na*nb;
                    case "Forma L" -> na*nb*nc;
                    default -> na*nb*nc*nd;
                };
                Ki=0.644+(0.148*n);
                if("En el interior".equals(elecN.DistElec)||"Sin electrodos".equals(elecN.DistElec)){
                    Kii=1/(Math.pow((2*n), (2/n)));
                }else{
                    Kii=1;
                }
                Kh = Math.sqrt(1+condN.ProfMalla);
                If = (Df*cEstN.Io3);
                Akcmil = (If*197.4)/Math.sqrt((condN.CteMC.TCAP/(cEstN.Tc*condN.CteMC.Far*condN.CteMC.Resist))
                          *Math.log((condN.CteMC.Fko+condN.CteMC.TempF)/(condN.CteMC.Fko+cEstN.Tamb)));
                if (Akcmil>400){
                    calibre = "500";
                    dc = 0.0187;
                }else if (Akcmil>350 && Akcmil<=400){
                    calibre = "400";
                    dc = 0.0167;
                }else if (Akcmil>300 && Akcmil<=350){
                    calibre = "350";
                    dc = 0.0157;
                }else if (Akcmil>250 && Akcmil<=300){
                    calibre = "300";
                    dc = 0.0139;
                }else if (Akcmil>211.6 && Akcmil<=250){
                    calibre = "250";
                    dc = 0.0127;
                }else if (Akcmil>167.8064 && Akcmil<=211.6){
                    calibre = "4/0";
                    dc = 0.0117;
                }else if (Akcmil>133.0717 && Akcmil<=167.8064){
                    calibre = "3/0";
                    dc = 0.0104;
                }else{
                    calibre = "2/0";
                    dc = 0.0093;
                }
                Km = (1.0/(2.0*Math.PI))*(Math.log((Math.pow(D, 2)/(16.0*condN.ProfMalla*dc))+
                        (Math.pow(D+2*condN.ProfMalla, 2)/(8.0*D*dc))-(condN.ProfMalla/(4.0*dc)))+
                        ((Kii/Kh)*(Math.log(8.0/(Math.PI*(2*n-1))))));
                Em = (ResistP*Km*Ki*Ig)/LM;
                Ls = (0.75*LC)+(0.85*elecN.NElectrodos*elecN.LongElec);
                Ks = (1.0/Math.PI)*((1.0/(2.0*condN.ProfMalla))+(1.0/(D+condN.ProfMalla))+((1.0/D)*(1-Math.pow(0.5, n-2))));
                Es = (ResistP*Ks*Ki*Ig)/Ls;
                jLabel28.setText(Double.toString((terN.PSuperf*100.0)/100.0));
                jLabel29.setText("-"+Double.toString((condN.ProfMalla*100.0)/100.0));
                if ("Sin electrodos".equals(elecN.DistElec)){
                jLabel30.setText("");
                }else{
                jLabel30.setText("-"+Double.toString(((condN.ProfMalla+elecN.LongElec)*100.0)/100.0));
                }
                System.out.println("tipo "+formaM);
                System.out.println("Distanciamiento cond: "+D);
                System.out.println(ResistP);
                System.out.println(terN.RSuperf);
                System.out.println(terN.PSuperf);
                System.out.println("Cs: "+CsP);
                System.out.println("Tension de paso peritida: "+EpP);
                System.out.println("Tension de toque permitida: "+EtP);
                System.out.println("Lc: "+LC);
                System.out.println("Lr: "+LR);
                System.out.println("Lt: "+Lt);
                System.out.println("A: "+A);
                System.out.println("Rg: "+Rg);
                System.out.println("Ta: "+Ta);
                System.out.println("Df: "+Df);
                System.out.println("Ig: "+Ig);
                System.out.println("GPR: "+GPR);
                System.out.println("LM: "+LM);
                System.out.println("n: "+n);
                System.out.println("Ki: "+Ki);
                System.out.println("Kii: "+Kii);
                System.out.println("Kh: "+Kh);
                System.out.println("Ks: "+Ks);
                System.out.println("Km: "+Km);
                System.out.println("kcmil: "+Akcmil);
                System.out.println("Calibre: "+calibre); 
                System.out.println("Em: "+Em);
                System.out.println("Es: "+Es);
                if (Em>EtP){
                    System.out.println("Exede el valor permitido de tension de toque");
                    Mtoque="La tension de toque supera el limite máximo";
                } else{
                    System.out.println("La tension de toque no supera el limite máximo");
                    Mtoque="La tension de toque no supera el limite máximo";
                }
                if (Es>EpP){
                    System.out.println("Exede el valor permitido de tension de paso");
                    Mpaso="La tension de paso supera el limite máximo";
                }else{
                    System.out.println("La tension de paso no supera el limite máximo");
                    Mpaso="La tension de paso no supera el limite máximo";
                }
                result.setVisible(true);
                Em=Math.rint(Em*1000)/1000;
                resultados.JTEm.setText(""+Em);
                Es=Math.rint(Es*1000)/1000;
                resultados.JTEs.setText(""+Es);
                EtP=Math.rint(EtP*1000)/1000;
                resultados.JTEt.setText(""+EtP);
                EpP=Math.rint(EpP*1000)/1000;
                resultados.JTEp.setText(""+EpP);
                GPR=Math.rint(GPR*1000)/1000;
                resultados.JTGPR.setText(""+GPR);
                Rg=Math.rint(Rg*1000)/1000;
                resultados.JTRg.setText(""+Rg);
                resultados.JLpaso.setText(Mpaso);
                resultados.JLtoque.setText(Mtoque);
        } 
        }
        
        public void DibujarMalla (){      
            jPanel1.removeAll();
            jPanel1.revalidate();
            GridsCanvas Mallado = new GridsCanvas (formaM, jPanel1.getWidth()-1, jPanel1.getHeight()-1, 
                    Integer.parseInt(JTN.getText())-1, Integer.parseInt(JTM.getText())-1, Lx, Ly, Lx2, Ly2, ArregloElec);
            jPanel1.add(Mallado);
            jPanel1.repaint();
        }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField C1;
    private javax.swing.JTextField C11;
    private javax.swing.JTextField C2;
    private javax.swing.JTextField C22;
    private javax.swing.JTextField C3;
    private javax.swing.JTextField C33;
    private javax.swing.JTextField C4;
    private javax.swing.JTextField C44;
    private javax.swing.JTextField C5;
    private javax.swing.JTextField C55;
    private javax.swing.JTextField C6;
    private javax.swing.JTextField C66;
    private javax.swing.JComboBox<String> JCDistElec;
    private javax.swing.JComboBox<String> JCMSuperf;
    private javax.swing.JComboBox<String> JCPeso;
    private javax.swing.JComboBox<String> JCTipoConductor;
    private javax.swing.JComboBox<String> JCTipoElec;
    private javax.swing.JLabel JLDiamElec;
    private javax.swing.JLabel JLDiamElec2;
    private javax.swing.JLabel JLLongElec;
    private javax.swing.JLabel JLLongElec2;
    private javax.swing.JLabel JLNElectrodos;
    private javax.swing.JLabel JLTipoE;
    public javax.swing.JLabel JLVista;
    private javax.swing.JRadioButton JRBCuadrada;
    private javax.swing.JRadioButton JRBFormaL;
    private javax.swing.JRadioButton JRBFormaT;
    private javax.swing.JRadioButton JRBRectangular;
    private javax.swing.JTextField JTCp;
    private javax.swing.JTextField JTDiamElec;
    private javax.swing.JTextField JTFrec;
    private javax.swing.JTextField JTIo3;
    private javax.swing.JTextField JTLX;
    private javax.swing.JTextField JTLX2;
    private javax.swing.JTextField JTLY;
    private javax.swing.JTextField JTLY2;
    private javax.swing.JTextField JTLongElec;
    private javax.swing.JTextField JTM;
    private javax.swing.JTextField JTN;
    private javax.swing.JTextField JTNElectrodos;
    private javax.swing.JTextField JTPSuperf;
    private javax.swing.JTextField JTProfMalla;
    private javax.swing.JTextField JTRProm;
    private javax.swing.JTextField JTRSuperf;
    private javax.swing.JTextField JTSf;
    private javax.swing.JTextField JTTamb;
    private javax.swing.JTextField JTTc;
    private javax.swing.JTextField JTTf;
    private javax.swing.JTextField JTTs;
    private javax.swing.JTextField JTXR;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
