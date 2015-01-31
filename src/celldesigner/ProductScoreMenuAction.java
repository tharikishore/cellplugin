package celldesigner;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jp.sbi.celldesigner.plugin.PluginAction;
import jp.sbi.celldesigner.plugin.PluginModel;
import jp.sbi.celldesigner.plugin.PluginReaction;

import com.al.CellDesignerPluginFrame;

class ProductScoreMenuAction extends PluginAction {
	private static final long serialVersionUID = 1L;
	private CustomPlugin plugin;
	private Map<String, Integer> scoreMap;
	private Set pSet;

	public ProductScoreMenuAction(CustomPlugin _plugin) {
		this.plugin = _plugin;
		scoreMap = new HashMap<String, Integer>();
	}

	public void myActionPerformed(ActionEvent e) {
		try {
			showScores();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void showScores() {
		String[][] scores2 = calculateScores();

		PluginModel selectedModel = this.plugin.getSelectedModel();
		int reactionCount = selectedModel.getNumReactions();
		String[] reactionNames = new String[reactionCount + 1];

		Object[][] scores = new Object[reactionCount][2];
		
		reactionNames[0] = " ";
		for(int i = 0; i < reactionCount ; i++){
			reactionNames[i+1] = selectedModel.getReaction(i).getId();
		}
		
		// new Object[][]{ {"a","b"},{"c","d"} },
		CellDesignerPluginFrame fr = new CellDesignerPluginFrame(scores2,
				reactionNames);

		fr.addWindowListener(new BasicWindowMonitor());

		fr.setVisible(true);
	}
	
	private String[][] calc2(){
		PluginModel model = this.plugin.getSelectedModel();
		int cReactions = model.getNumReactions();
		int cProducts = pSet.size();
		String[][] allweights = new String[cProducts][cReactions + 1]; 
		
		int pIndex = 0;
		for (Iterator iterator = pSet.iterator(); iterator.hasNext();) {
			boolean counted = false;
			String product = (String) iterator.next();
			String[] weights = new String[cReactions + 1];
			weights[0] = product;
			for(int i = 0; i < cReactions ; i++){
				PluginReaction reaction = model.getReaction(i);
				if(isInvolved(product, reaction)){
					if(counted)
						weights[i+1] = Double.toString(-1d);
					else
						weights[i+1] = Double.toString(1d);
					
					counted = true;
				}else{
					weights[i+1] = Double.toString(0d);
				}
			}//for each reaction
			
			allweights[pIndex] = weights;
			pIndex++;
		}//for each product
		
		return allweights;
	}
	
	private boolean isInvolved(String product, PluginReaction reaction){
		boolean result = false;
		
		int num = reaction.getNumProducts();
		for (int i = 0; i < num; i++) {
			String pName = reaction.getProduct(i).getSpeciesInstance().getName();
			if(pName.equalsIgnoreCase(product))
				result = true;
		}

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String[][] calculateScores() {
		PluginModel selectedModel = this.plugin.getSelectedModel();
		pSet = new HashSet();
		for (int i = 0; i < selectedModel.getNumReactions(); i++) {
			PluginReaction reaction = selectedModel.getReaction(i);
			System.out.println("Reaction is " + reaction.getId());
			int reactants = reaction.getNumReactants();
			int modifiers = reaction.getNumModifiers();
			List<String> productList = getAllProducts(reaction);

			for (Iterator<String> iterator = productList.iterator(); iterator
					.hasNext();) {
				String product = (String) iterator.next();
				System.out.println("Product is " + product);
				int score = 0;
				if (scoreMap.containsKey(product)) {//
					score = new Integer(scoreMap.get(product).intValue() - 1);
				} else {
					score = new Integer(reactants + modifiers);
				}
				scoreMap.put(product, score);
				pSet.add(product);
				System.out.println("Score is " + score);
			}
		}//for each reaction
		
		return calc2();
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
}