package celldesigner;

import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.sbi.celldesigner.plugin.PluginAction;
import jp.sbi.celldesigner.plugin.PluginListOf;
import jp.sbi.celldesigner.plugin.PluginModel;
import jp.sbi.celldesigner.plugin.PluginReaction;
import jp.sbi.celldesigner.plugin.PluginSpeciesAlias;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import com.al.CellDesignerPluginFrame;
import com.al.cellplugin.MainFrame;

public class ProductScoreMenuAction extends PluginAction {
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

		MainFrame fr1 = new MainFrame(this.plugin.getSelectedModel(), this);
		
		fr1.addWindowListener(new BasicWindowMonitor());

		fr1.setVisible(true);
	}

	public String[] getReactionNames(PluginModel selectedModel) {
		int reactionCount = selectedModel.getNumReactions();
		String[] reactionNames = new String[reactionCount + 1];

		reactionNames[0] = " ";
		for(int i = 0; i < reactionCount ; i++){
			reactionNames[i+1] = selectedModel.getReaction(i).getId();
		}
		return reactionNames;
	}
	
	private String[][] calcSvd(String[][] scores2) {
		double[][] mData = new double[scores2.length - 1][scores2[0].length - 1];
		
		for (int i = 0; i < mData.length; i++) {
			mData[i] = new double[scores2[0].length - 1];
			String[] source = scores2[i+1];
			for (int j = 0; j < source.length - 1; j++) {
				mData[i][j] = Double.parseDouble(source[j + 1]);
			}
			System.out.println(Arrays.asList(mData[i]));
		}
		
		RealMatrix m = MatrixUtils.createRealMatrix(mData);
		SingularValueDecomposition svd = new SingularValueDecomposition(m);
		RealMatrix v = svd.getV();
		double[][] vdata = v.getData();
		NumberFormat formatter = new DecimalFormat("#0.0000000000");
		for (int i = 0; i < vdata.length; i++) {
			double[] ds = vdata[i];
			for (int j = 0; j < ds.length; j++) {
				System.out.print(ds[j]+"~");
				scores2[j+1][i+1] = formatter.format(ds[j]);
			}
			System.out.println("\n");
		}
		return scores2;
		
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
	public String[][] calculateScores(PluginModel selectedModel) {
		//PluginModel selectedModel = this.plugin.getSelectedModel();
		pSet = new HashSet();
		for (int i = 0; i < selectedModel.getNumReactions(); i++) {
			PluginReaction reaction = selectedModel.getReaction(i);
			//System.out.println("Reaction is " + reaction.getId());
			int reactants = reaction.getNumReactants();
			int modifiers = reaction.getNumModifiers();
			List<String> productList = getAllProducts(reaction);

			for (Iterator<String> iterator = productList.iterator(); iterator
					.hasNext();) {
				String product = (String) iterator.next();
				//System.out.println("Product is " + product);
				int score = 0;
				if (scoreMap.containsKey(product)) {//
					score = new Integer(scoreMap.get(product).intValue() - 1);
				} else {
					score = new Integer(reactants + modifiers);
				}
				scoreMap.put(product, score);
				pSet.add(product);
				//System.out.println("Score is " + score);
			}
		}//for each reaction
		
		String[][] scores = calc2();
		scores = calcSvd(scores);

		return scores;
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
	
	public Object[][] getReactionData(PluginModel selectedModel) {

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
		
		return modelData;
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