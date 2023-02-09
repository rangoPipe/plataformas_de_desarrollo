/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shared;

import java.awt.Component;
import java.awt.Point;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author felipearango
 */
public class JClickRightMenu {

    public final JPopupMenu _popupMenu = new JPopupMenu();
    private final List<JMenuItem> _items;
    private JTable _reference;

    public JClickRightMenu(List<JMenuItem> items, JTable reference) {
        this._items = items;
        this._reference = reference;
        
        this.AddItems(this._items);

        _popupMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(() -> {
                    int rowAtPoint = _reference.rowAtPoint(SwingUtilities.convertPoint(_popupMenu, new Point(0, 0), _reference));
                    if (rowAtPoint > -1) {
                        _reference.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }
    
    public void AddItems(List<JMenuItem> items) {
        items.forEach(item -> _popupMenu.add(item));
    }

}
