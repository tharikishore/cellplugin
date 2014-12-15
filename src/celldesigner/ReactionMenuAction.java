package celldesigner;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.sbi.celldesigner.plugin.PluginAction;
import jp.sbi.celldesigner.plugin.PluginListOf;
import jp.sbi.celldesigner.plugin.PluginModel;
import jp.sbi.celldesigner.plugin.PluginReaction;
import jp.sbi.celldesigner.plugin.PluginSpeciesAlias;

import com.al.CellDesignerPluginFrame;

class ReactionMenuAction extends PluginAction {
	private static final long serialVersionUID = 1L;
	private CustomPlugin plugin;

	public ReactionMenuAction(CustomPlugin _plugin) {
		this.plugin = _plugin;
	}

	public void myActionPerformed(ActionEvent e) {
		try {
			showPluginframe();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}


	public void showPluginframe() {

		PluginModel selectedModel = this.plugin.getSelectedModel();
		int reactionCount = selectedModel.getNumReactions();

		Object[][] modelData = new Object[reactionCount][8];

		for (int i = 0; i < reactionCount; i++) {
			PluginReaction reaction = selectedModel.getReaction(i);
			String reactionType = reaction.getReactionType();
			List products = getAllProducts(reaction);
			String reactants = GetAllReactants(reaction);
			String modifiers = GetAllModifiers(reaction);

			modelData[i] = new String[] { reaction.getId(), reactionType,
					Arrays.asList(products).toString(),
					Integer.toString(reaction.getNumProducts()), reactants,
					Integer.toString(reaction.getNumReactants()), modifiers,
					Integer.toString(reaction.getNumModifiers()) };
		}
		String[] columnNames = new String[] { "Reaction Name",
				"Reaction Types", "Products", "No. Of Products", "Reactants",
				"No. Of Reactants", "Modifiers", "No.Of Modifiers" };

		CellDesignerPluginFrame fr = new CellDesignerPluginFrame(modelData,
				columnNames);

		fr.addWindowListener(new BasicWindowMonitor());

		fr.setVisible(true);
	}

	public List<String> getAllProducts(PluginReaction reaction) {
		List<String> productList = new ArrayList<String>();
		int num = reaction.getNumProducts();
		for (int i = 0; i < num; i++) {
			productList.add(reaction.getProduct(i).getSpeciesInstance()
					.getName());
		}

		return productList;
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