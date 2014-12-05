package celldesigner;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		calculateScores();
	}

	private void calculateScores() {
		PluginModel selectedModel = this.plugin.getSelectedModel();
		Map<String, Integer> scoreMap = new HashMap();
		
		for (int i = 0; i < selectedModel.getNumReactions(); i++) {
			PluginReaction reaction = selectedModel.getReaction(i);
			int reactants = reaction.getNumReactants();
			int modifiers = reaction.getNumModifiers();
			List productList = getAllProducts(reaction);
			
			for (Iterator iterator = productList.iterator(); iterator.hasNext();) {
				String product = (String) iterator.next();
				
				if(scoreMap.containsKey(product)){
					scoreMap.put(product, scoreMap.get(product) - 1);
				}else{
					scoreMap.put(product, reactants + modifiers);
				}
			}
		}

	}

	public void myActionPerformed(ActionEvent e) {
		showPluginframe();
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

			// fOR EACH PRODUCT IN A REACTION SCORE OF PRODUCT = NO. OF
			// REACTANTS + NO. OF PRODUCTS
			// IF A PRODUCT IS OCCURING IN ANOTHER REACTION, REDUCE THE SCORE BY
			// 1
		}
		String[] columnNames = new String[] { "Reaction Name",
				"Reaction Types", "Products", "No. Of Products", "Reactants",
				"No. Of Reactants", "Modifiers", "No.Of Modifiers" };

		CellDesignerPluginFrame fr = new CellDesignerPluginFrame(modelData,
				columnNames);

		fr.addWindowListener(new BasicWindowMonitor());

		fr.setVisible(true);
	}

	public List getAllProducts(PluginReaction reaction) {
		List productList = new ArrayList();
		int num = reaction.getNumProducts();
		String products = new String();
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