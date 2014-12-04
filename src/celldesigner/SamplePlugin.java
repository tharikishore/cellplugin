package celldesigner;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import jp.sbi.celldesigner.plugin.CellDesignerPlugin;
import jp.sbi.celldesigner.plugin.PluginMenu;
import jp.sbi.celldesigner.plugin.PluginMenuItem;
//import jp.sbi.celldesigner.plugin.PluginReaction;
import jp.sbi.celldesigner.plugin.PluginSBase;

public class SamplePlugin extends CellDesignerPlugin {

    /**
     * Constructor
     */
    public SamplePlugin(boolean boo) {
    }

    public SamplePlugin() {
        PluginMenu menu = new PluginMenu("Flux Based Scoring System - Mod");
        PlainPluginAction action = new PlainPluginAction(this);
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

class BasicWindowMonitor extends WindowAdapter {

    public void windowClosing(WindowEvent e) {
        Window w = e.getWindow();
        w.setVisible(false);
        w.dispose();
    }
}
