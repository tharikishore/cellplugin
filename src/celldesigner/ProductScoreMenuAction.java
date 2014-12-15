package celldesigner;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jp.sbi.celldesigner.plugin.PluginAction;
import jp.sbi.celldesigner.plugin.PluginModel;
import jp.sbi.celldesigner.plugin.PluginReaction;

import com.al.CellDesignerPluginFrame;

class ProductScoreMenuAction extends PluginAction {
	private static final long serialVersionUID = 1L;
	private CustomPlugin plugin;
	private Map<String, Integer> scoreMap;

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
		calculateScores();

		PluginModel selectedModel = this.plugin.getSelectedModel();
		int reactionCount = selectedModel.getNumReactions();

		Object[][] scores = new Object[reactionCount][2];

		int index = 0;
		for (Iterator<Entry<String, Integer>> iterator = scoreMap.entrySet()
				.iterator(); iterator.hasNext();) {
			@SuppressWarnings("rawtypes")
			Entry entry = (Entry) iterator.next();

			scores[index] = new Object[] { entry.getKey(), entry.getValue() };
			System.out.println(scores[index]);
			index++;
		}
		// new Object[][]{ {"a","b"},{"c","d"} },
		CellDesignerPluginFrame fr = new CellDesignerPluginFrame(scores,
				new String[] { "Product", "Score" });

		fr.addWindowListener(new BasicWindowMonitor());

		fr.setVisible(true);
	}

	private void calculateScores() {
		PluginModel selectedModel = this.plugin.getSelectedModel();

		for (int i = 0; i < selectedModel.getNumReactions(); i++) {
			PluginReaction reaction = selectedModel.getReaction(i);
			int reactants = reaction.getNumReactants();
			int modifiers = reaction.getNumModifiers();
			List<String> productList = getAllProducts(reaction);

			for (Iterator<String> iterator = productList.iterator(); iterator
					.hasNext();) {
				String product = (String) iterator.next();

				if (scoreMap.containsKey(product)) {//
					scoreMap.put(product, new Integer(scoreMap.get(product)
							.intValue() - 1));
				} else {
					scoreMap.put(product, new Integer(reactants + modifiers));
				}
			}
		}
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