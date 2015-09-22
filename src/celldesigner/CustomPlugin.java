package celldesigner;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import jp.sbi.celldesigner.plugin.CellDesignerPlugin;
import jp.sbi.celldesigner.plugin.PluginMenu;
import jp.sbi.celldesigner.plugin.PluginMenuItem;
//import jp.sbi.celldesigner.plugin.PluginReaction;
import jp.sbi.celldesigner.plugin.PluginSBase;

public class CustomPlugin extends CellDesignerPlugin {

    /**
     * Constructor
     */
    public CustomPlugin(boolean boo) {
    }

    public CustomPlugin() {
        PluginMenu menu = new PluginMenu("Scoring System");
        //ReactionMenuAction action = new ReactionMenuAction(this);
        //PluginMenuItem reactionsMenuItem = new PluginMenuItem("Reactions", action);
        //menu.add(reactionsMenuItem);
        ProductScoreMenuAction action2 = new ProductScoreMenuAction(this);
        menu.add(new PluginMenuItem("Scores", action2));
        
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

class BasicWindowMonitor extends WindowAdapter {

    public void windowClosing(WindowEvent e) {
        Window w = e.getWindow();
        w.setVisible(false);
        w.dispose();
    }
}
