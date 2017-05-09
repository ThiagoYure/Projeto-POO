/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.MyPersonalAgenda.visao;

import com.ifpb.MyPersonalAgenda.controle.CompromissoDao;
import com.ifpb.MyPersonalAgenda.controle.CompromissoDaoBinario;
import com.ifpb.MyPersonalAgenda.modelo.Compromisso;
import static com.ifpb.MyPersonalAgenda.visao.PaginaInicial.usuarioLogado;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ThigoYure
 */
public class GerenciarCompromissos extends javax.swing.JFrame {

    private static CompromissoDao daoCompromisso;

    /**
     * Creates new form GerenciarCompromissos
     */
    public GerenciarCompromissos() {
        this.getContentPane().setBackground(Color.WHITE);
        daoCompromisso = new CompromissoDaoBinario();
        initComponents();
        ImageIcon imagemTituloJanela = new ImageIcon("C:\\Users\\ThigoYure\\Documents\\Projeto-POO\\Projeto-POO\\MyPersonalAgenda\\src\\com\\ifpb\\MyPersonalAgenda\\images\\Icone.png");
        setIconImage(imagemTituloJanela.getImage());
        atualizarTabela();
    }

    public static void atualizarTabela() {
        List<Compromisso> compromissos;
        jTable1.removeAll();
        try {
            compromissos = daoCompromisso.listCompromissos();
            String[] cabecalho = {"Data", "Hora", "Compromisso", "Agenda"};
            String[][] compromissosMat = new String[compromissos.size()][4];
            for (int i = 0; i < compromissos.size(); i++) {
                Compromisso comp = compromissos.get(i);
                compromissosMat[i][0] = comp.getData().toString();
                compromissosMat[i][1] = comp.getHora();
                compromissosMat[i][2] = comp.getDescricao();
                compromissosMat[i][3] = comp.getAgenda();

            }
            System.out.println(compromissosMat);
            jTable1.removeAll();
            DefaultTableModel modelo = new DefaultTableModel(compromissosMat, cabecalho);
            jTable1.setModel(modelo);

        } catch (ClassNotFoundException | IOException | SQLException ex) {
            JOptionPane.showMessageDialog(jTable1.getRootPane().getContentPane(), "Falha na conexão");
        }
    }

    public static void atualizarTabelaIntervalo() throws IOException, SQLException {
        List<Compromisso> compromissos;
        jTable1.removeAll();
        try {
            compromissos = daoCompromisso.listarCompromissosIntervalo(jDateChooser2.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), jDateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "Todas");
            String[] cabecalho = {"Data", "Hora", "Compromisso", "Agenda"};
            String[][] compromissosMat = new String[compromissos.size()][4];
            for (int i = 0; i < compromissos.size(); i++) {
                Compromisso comp = compromissos.get(i);
                compromissosMat[i][0] = comp.getData().toString();
                compromissosMat[i][1] = comp.getHora();
                compromissosMat[i][2] = comp.getDescricao();
                compromissosMat[i][3] = comp.getAgenda();

            }
            System.out.println(compromissosMat);
            jTable1.removeAll();
            DefaultTableModel modelo = new DefaultTableModel(compromissosMat, cabecalho);
            jTable1.setModel(modelo);

        } catch (ClassNotFoundException | IOException | SQLException ex) {
            JOptionPane.showMessageDialog(jTable1.getRootPane().getContentPane(), "Falha na conexão");
        }
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
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new JDateChooser("dd/MM/yyyy", "##/##/#####", ' ');
        jDateChooser2 = new JDateChooser("dd/MM/yyyy", "##/##/#####", ' ');
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ThigoYure\\Documents\\Projeto-POO\\Projeto-POO\\MyPersonalAgenda\\src\\com\\ifpb\\MyPersonalAgenda\\images\\Compromisso.png")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Vladimir Script", 1, 48)); // NOI18N
        jLabel4.setText("Gerenciar Compromissos");

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setForeground(new java.awt.Color(255, 0, 0));

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setForeground(new java.awt.Color(255, 0, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Início :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Fim :");

        jButton1.setBackground(new java.awt.Color(0, 51, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Atualizar");

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(0, 51, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(94, 94, 94))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jButton1)
                                .addGap(165, 165, 165))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addContainerGap()))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int k = jTable1.getSelectedRow();
        String data = (String) jTable1.getValueAt(k, 0);
        String hora = (String) jTable1.getValueAt(k, 1);
        String descricao = (String) jTable1.getValueAt(k, 2);
        String agenda = (String) jTable1.getValueAt(k, 3);
        Compromisso comp;
        try {
            comp = daoCompromisso.readCompromissos(LocalDate.parse(data), hora, agenda);
            TelaAtualizarExcluirCompromissos gerenciaCompromissos = new TelaAtualizarExcluirCompromissos(comp);
            gerenciaCompromissos.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(GerenciarCompromissos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GerenciarCompromissos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarCompromissos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(GerenciarCompromissos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarCompromissos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarCompromissos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarCompromissos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciarCompromissos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private static com.toedter.calendar.JDateChooser jDateChooser1;
    private static com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
