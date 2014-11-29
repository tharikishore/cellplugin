package celldesigner;

import java.awt.Panel;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

import jp.sbi.celldesigner.plugin.CellDesignerPlugin;
import jp.sbi.celldesigner.plugin.PluginAction;
import jp.sbi.celldesigner.plugin.PluginListOf;
import jp.sbi.celldesigner.plugin.PluginMenu;
import jp.sbi.celldesigner.plugin.PluginMenuItem;
import jp.sbi.celldesigner.plugin.PluginModel;
import jp.sbi.celldesigner.plugin.PluginReaction;
//import jp.sbi.celldesigner.plugin.PluginReaction;
import jp.sbi.celldesigner.plugin.PluginSBase;
import jp.sbi.celldesigner.plugin.PluginSpeciesAlias;

import com.al.ReactantsFrame;

public class SamplePlugin extends CellDesignerPlugin {
	/**
	 * Constructor
	 */
	public SamplePlugin(boolean boo) {
	}

	public SamplePlugin() {
		PluginMenu menu = new PluginMenu("Flux Based Scoring System - Mod");
		SampleAction action = new SampleAction(this);
		PluginMenuItem item = new PluginMenuItem("FBSS-X", action);
		menu.add(item);
		addCellDesignerPluginMenu(menu);
	}

	@Override
	public void SBaseAdded(PluginSBase arg0) {
	}

	@Override
	public void SBaseChanged(PluginSBase arg0) {
	}

	@Override
	public void SBaseDeleted(PluginSBase arg0) {
	}

	@Override
	public void addPluginMenu() {
	}

	@Override
	public void modelClosed(PluginSBase arg0) {
	}

	@Override
	public void modelOpened(PluginSBase arg0) {
	}

	@Override
	public void modelSelectChanged(PluginSBase arg0) {
	}
}

class SampleAction extends PluginAction {
	private static final long serialVersionUID = 1L;
	private SamplePlugin plugin;

	public SampleAction(SamplePlugin _plugin) {
		this.plugin = _plugin;
	}

	public void myActionPerformed(ActionEvent e) {
		pluginframe();
	}

	private void pluginframe() {
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
            java.util.logging.Logger.getLogger(ReactantsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReactantsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReactantsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReactantsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReactantsFrame().setVisible(true);
            }
        });
		
	}

	public void pluginframe1() {
		JFrame fr = new ReactantsFrame();
		
		//JFrame fr = new JFrame("Flux Based Scoring System");
		//fr.setSize(250, 250);
		fr.addWindowListener(new BasicWindowMonitor());
		Panel pnl = new Panel();
		fr.add(pnl);
		JLabel lbl1 = new JLabel("Flux Based Scoring System");
		pnl.add(lbl1);
		JLabel lbl2 = new JLabel();
		pnl.add(lbl2);
		JLabel lbl3 = new JLabel();
		pnl.add(lbl3);
		JLabel lbl4 = new JLabel();
		pnl.add(lbl4);
		JLabel lbl5 = new JLabel();
		pnl.add(lbl5);

		PluginModel selectedModel = this.plugin.getSelectedModel();
		lbl2.setText("The selected Model is : " + selectedModel.getId());
		int reactionCount = selectedModel.getNumReactions();
		
		lbl3.setText("num of reactions in model is" + reactionCount);
		
		String reactions = new String();
		JTable jt = new JTable(reactionCount, 4);
		jt.setName("name");
		pnl.add(jt);

		for (int i = 0; i < reactionCount; i++) {
			String reactionType = selectedModel.getReaction(i).getReactionType();
			String products = getAllProducts(selectedModel.getReaction(i));
			String reactants = GetAllReactants(selectedModel.getReaction(i));
			String modifiers = GetAllModifiers(selectedModel.getReaction(i));
			
			System.out.println("Reaction types "+reactionType);
			System.out.println("Products "+products);
			System.out.println("Reactants "+reactants);
			System.out.println("Modifiers "+modifiers);
			
			
			jt.setValueAt(reactionType, i, 0);
			jt.setValueAt(reactants, i, 1);
			jt.setValueAt(products, i, 2);
			jt.setValueAt(modifiers, i, 3);
		}

		lbl4.setText(reactions);
		fr.setVisible(true);
	}

	public String getAllProducts(PluginReaction reaction) {
		int num = reaction.getNumProducts();
		String products = new String();
		for (int i = 0; i < num; i++) {
			products = (String) products + "#"
					+ reaction.getProduct(i).getSpeciesInstance().getName();
		}

		return products;
	}

	public String GetAllReactants(PluginReaction reaction) {
		int num = reaction.getNumReactants();
		String reactants = new String();
		for (int i = 0; i < num; i++) {
			reactants = (String) reactants + "#"
					+ reaction.getReactant(i).getSpeciesInstance().getName();
		}

		return reactants;
	}

	public String GetAllModifiers(PluginReaction reaction) {
		int num = reaction.getNumModifiers();
		String MyModfiers = new String();
		for (int i = 0; i < num; i++) {
			MyModfiers = (String) MyModfiers + "#"
					+ reaction.getModifier(i).getSpeciesInstance().getName();
		}

		return MyModfiers;
	}

	public PluginSpeciesAlias getSelectedSpecies() {
		PluginListOf listof = this.plugin.getSelectedSpeciesNode();
		PluginSpeciesAlias alias = (PluginSpeciesAlias) listof.get(0);
		return alias;
	}

}

class BasicWindowMonitor extends WindowAdapter {

	public void windowClosing(WindowEvent e) {
		Window w = e.getWindow();
		w.setVisible(false);
		w.dispose();
	}
}
