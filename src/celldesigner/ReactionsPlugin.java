package celldesigner;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;

import javax.swing.JFrame;
import javax.swing.JLabel;

import jp.sbi.celldesigner.plugin.CellDesignerPlugin;
import jp.sbi.celldesigner.plugin.PluginAction;
import jp.sbi.celldesigner.plugin.PluginMenu;
import jp.sbi.celldesigner.plugin.PluginMenuItem;
import jp.sbi.celldesigner.plugin.PluginModel;
import jp.sbi.celldesigner.plugin.PluginSBase;


public class ReactionsPlugin extends CellDesignerPlugin{

	PluginModel mymodel = getSelectedModel();
	
	public ReactionsPlugin() {
		// TODO Auto-generated constructor stub
		PluginMenu menu = new PluginMenu("Get All Reactions");
		PluginAction click = new PluginAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void myActionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				getreactiongui();
			}
		};
		PluginMenuItem mitem = new PluginMenuItem("Reactions", click);
		menu.add(mitem);
		addCellDesignerPluginMenu(menu);
		
		
	}
	
	public void getreactiongui() {
		JFrame fr = new JFrame("Get Reactions");
		fr.setSize(250, 250);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);
		
		Panel pnl = new Panel();
		fr.add(pnl);
		
		JLabel lbl1 = new JLabel("Number of Reactions are : ");
		pnl.add(lbl1);
		
		JLabel lbl2 = new JLabel("compartments are : ");
		pnl.add(lbl2);
		
		
		
	}
	
	
	public int getnumreactions() {
		
		int comp = mymodel.getNumCompartments();
		return comp;
		
		
	}

	@Override
	public void SBaseAdded(PluginSBase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SBaseChanged(PluginSBase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SBaseDeleted(PluginSBase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPluginMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelClosed(PluginSBase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelOpened(PluginSBase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelSelectChanged(PluginSBase arg0) {
		// TODO Auto-generated method stub
		
	}

}
