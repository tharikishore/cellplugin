package com.al.cellplugin;

import java.awt.Button;

import javax.swing.table.DefaultTableModel;

import jp.sbi.celldesigner.plugin.PluginModel;

import org.jfree.layout.CenterLayout;

import celldesigner.ProductScoreMenuAction;

import com.al.ExcelExporter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HariKishore
 */
public class MainFrame extends javax.swing.JFrame {
	
	private PluginModel model;
	private Object[][] reactionData;
	private Object[] names = new String[] { "Reaction Name",
			"Reaction Types", "Products", "No. Of Products", "Reactants",
			"No. Of Reactants", "Modifiers", "No.Of Modifiers" };

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame(PluginModel mdl, ProductScoreMenuAction action) {
    	this.model = mdl;
    	scoreData = action.calculateScores(model);
    	reactionNames = action.getReactionNames(model);
    	reactionData =  action.getReactionData(model);
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

        mainTabPane = new javax.swing.JTabbedPane();
        scorePanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        reactionPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rcnTable = new javax.swing.JTable();
        exportButtonS = new javax.swing.JButton();
        scorePane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        scoreTable = new javax.swing.JTable();
        graphButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        graphPane = new javax.swing.JPanel();
        algoPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        algoLabel = new javax.swing.JLabel();
        abtPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        aboutLabel = new javax.swing.JLabel();
        authPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        authLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainTabPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        scorePanel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                scorePanelFocusGained(evt);
            }
        });
        scorePanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                scorePanelComponentShown(evt);
            }
        });

        rcnTable.setModel(new ScoreTableModel(reactionData, names));
        jScrollPane1.setViewportView(rcnTable);

        exportButtonS.setText("Export");
        exportButtonS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reactionPaneLayout = new javax.swing.GroupLayout(reactionPane);
        reactionPane.setLayout(reactionPaneLayout);
        reactionPaneLayout.setHorizontalGroup(
            reactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
            .addGroup(reactionPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exportButtonS))
        );
        reactionPaneLayout.setVerticalGroup(
            reactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reactionPaneLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButtonS)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reactions", reactionPane);

        scorePane.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                scorePaneComponentShown(evt);
            }
        });

        scoreTable.setModel(new ScoreTableModel(scoreData, reactionNames));
        jScrollPane2.setViewportView(scoreTable);

        graphButton.setText("Graph");
        graphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphButtonActionPerformed(evt);
            }
        });

        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scorePaneLayout = new javax.swing.GroupLayout(scorePane);
        scorePane.setLayout(scorePaneLayout);
        scorePaneLayout.setHorizontalGroup(
            scorePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, scorePaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphButton)
                .addContainerGap())
        );
        scorePaneLayout.setVerticalGroup(
            scorePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scorePaneLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(scorePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(graphButton)
                    .addComponent(exportButton))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Scores", scorePane);

        javax.swing.GroupLayout graphPaneLayout = new javax.swing.GroupLayout(graphPane);
        graphPane.setLayout(graphPaneLayout);
        graphPaneLayout.setHorizontalGroup(
            graphPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
        graphPaneLayout.setVerticalGroup(
            graphPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Graph", graphPane);

        javax.swing.GroupLayout scorePanelLayout = new javax.swing.GroupLayout(scorePanel);
        scorePanel.setLayout(scorePanelLayout);
        scorePanelLayout.setHorizontalGroup(
            scorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        scorePanelLayout.setVerticalGroup(
            scorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        mainTabPane.addTab("Score Data", scorePanel);

        algoLabel.setText("<html><body><h1>H1 Heading Algo</h1><h2>H2 Heading</h2><br/>Normal Text: Your Text can go here too.. </body></html>");
        algoLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        algoLabel.setAutoscrolls(true);
        algoLabel.setMinimumSize(new java.awt.Dimension(400, 600));
        algoLabel.setPreferredSize(new java.awt.Dimension(400, 600));
        jScrollPane3.setViewportView(algoLabel);

        javax.swing.GroupLayout algoPanelLayout = new javax.swing.GroupLayout(algoPanel);
        algoPanel.setLayout(algoPanelLayout);
        algoPanelLayout.setHorizontalGroup(
            algoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        algoPanelLayout.setVerticalGroup(
            algoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );

        mainTabPane.addTab("Algorithm", algoPanel);

        aboutLabel.setText("<html><body><h1>H1 Heading for About</h1><h2>H2 Heading</h2><br/>Normal Text: Your Text can go here too.. </body></html>");
        aboutLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jScrollPane4.setViewportView(aboutLabel);

        javax.swing.GroupLayout abtPanelLayout = new javax.swing.GroupLayout(abtPanel);
        abtPanel.setLayout(abtPanelLayout);
        abtPanelLayout.setHorizontalGroup(
            abtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        abtPanelLayout.setVerticalGroup(
            abtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );

        mainTabPane.addTab("About", abtPanel);

        authLabel.setText("<html><body><h1>H1 Heading for Authors</h1><h2>H2 Heading</h2><br/>Normal Text: Your Text can go here too.. </body></html>");
        authLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jScrollPane5.setViewportView(authLabel);

        javax.swing.GroupLayout authPanelLayout = new javax.swing.GroupLayout(authPanel);
        authPanel.setLayout(authPanelLayout);
        authPanelLayout.setHorizontalGroup(
            authPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        authPanelLayout.setVerticalGroup(
            authPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );

        mainTabPane.addTab("Authors", authPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void graphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Graph action");
        LineChart chart = new LineChart("SVD V Chart", this.scoreData, this.reactionNames);
        graphPane.add(chart.getChartPanel());
        chart.getChartPanel().revalidate();
        graphPane.revalidate();
    }//GEN-LAST:event_graphButtonActionPerformed

    private void scorePanelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_scorePanelFocusGained
        // TODO add your handling code here:
        System.out.println("Score Panel lFocus gained *** ");
    }//GEN-LAST:event_scorePanelFocusGained

    private void scorePanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_scorePanelComponentShown
        // TODO add your handling code here:
        System.out.println("Score Panel Component shown *****");
    }//GEN-LAST:event_scorePanelComponentShown

    private void scorePaneComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_scorePaneComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_scorePaneComponentShown

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        // TODO add your handling code here:
    	ExcelExporter exp = new ExcelExporter(this.scoreTable);
    }//GEN-LAST:event_exportButtonActionPerformed

    private void exportButtonSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonSActionPerformed
        // TODO add your handling code here:
    	ExcelExporter exp = new ExcelExporter(this.rcnTable);
    }//GEN-LAST:event_exportButtonSActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aboutLabel;
    private javax.swing.JPanel abtPanel;
    private javax.swing.JLabel algoLabel;
    private javax.swing.JPanel algoPanel;
    private javax.swing.JLabel authLabel;
    private javax.swing.JPanel authPanel;
    private javax.swing.JButton exportButton;
    private javax.swing.JButton exportButtonS;
    private javax.swing.JButton graphButton;
    private javax.swing.JPanel graphPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane mainTabPane;
    private javax.swing.JTable rcnTable;
    private javax.swing.JPanel reactionPane;
    private javax.swing.JPanel scorePane;
    private javax.swing.JPanel scorePanel;
    private javax.swing.JTable scoreTable;
    // End of variables declaration//GEN-END:variables
	private Object[][] scoreData;
        private Object[] reactionNames;

    private static class ScoreTableModel extends DefaultTableModel {

        public ScoreTableModel() {
        }
        
        public ScoreTableModel(Object[][] data, Object[] cols){
            super(data, cols);
        }
    }
}
