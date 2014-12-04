package celldesigner;

import java.awt.event.ActionEvent;

import jp.sbi.celldesigner.plugin.PluginAction;
import jp.sbi.celldesigner.plugin.PluginModel;
import jp.sbi.celldesigner.plugin.PluginSpecies;

import com.al.CellDesignerPluginFrame;

class SpeciesMenuAction extends PluginAction {
	private static final long serialVersionUID = 1L;
	private CustomPlugin plugin;

	public SpeciesMenuAction(CustomPlugin _plugin) {
		this.plugin = _plugin;
	}

	public void myActionPerformed(ActionEvent e) {
		showPluginframe();
	}

	public void showPluginframe() {

		PluginModel selectedModel = this.plugin.getSelectedModel();
		int speciesCount = selectedModel.getNumSpecies();

		Object[][] modelData = new Object[speciesCount][8];

		for (int i = 0; i < speciesCount; i++) {
			PluginSpecies species = selectedModel.getSpecies(i);
			String speciesId = species.getId();
			String speciesType = species.getSpeciesType();

			modelData[i] = new String[] { speciesId, speciesType };
		}
		String[] columnNames = new String[] { "Id", "Type" };

		CellDesignerPluginFrame fr = new CellDesignerPluginFrame(modelData,
				columnNames);

		fr.addWindowListener(new BasicWindowMonitor());

		fr.setVisible(true);
	}
}