/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Crud.UserCrud;
import View.frmUser;
import Model.User;
import Shared.JClickRightMenu;
import Shared.JTableModel;
import com.uni.mvc.plataforma.de.desarrollo.semana02.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Farango
 */
public class MainController implements ActionListener {

    private Main _mainView;
    private UserCrud _userOperations;
    private JTable _table;

    public MainController(Main form) {
        this._mainView = form;
        this._userOperations = new UserCrud();
        this._mainView.btnNew.addActionListener(this);
        this._mainView.btnSearch.addActionListener(this);
        this._mainView.txtSearch.addActionListener(this);
        this._table = this._mainView.tblUsers;
        loadData(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _mainView.btnNew) {
            btnCreateClick();
        }
        else if( e.getSource() == _mainView.btnSearch || e.getSource() == _mainView.txtSearch ) {
            loadData( _mainView.txtSearch.getText() );
        }
    }

    private void btnCreateClick() {
        createUserView(0);
    }

    private void loadData(String term) {
        String[] columns = {"Id", "Nombre", "Email", "Teléfono", "Cargo"};
        List<User> users = _userOperations.GetAll( term );
        int userPropertiesCount = User.class.getDeclaredFields().length;
        Object[][] rows = new Object[users.size()][userPropertiesCount];

        for (int idx = 0; idx < users.size(); idx++) {
            rows[idx][0] = users.get(idx).getId();
            rows[idx][1] = users.get(idx).getName();
            rows[idx][2] = users.get(idx).getEmail();
            rows[idx][3] = users.get(idx).getPhone();
            rows[idx][4] = users.get(idx).getJobPosition();
        }

        JTableModel model = new JTableModel(columns, rows);
        TableRowSorter<TableModel> orderTable = new TableRowSorter<>(model);
        this._table.setRowSorter(orderTable);
        this._table.setModel(model);

        JMenuItem editItem = new JMenuItem("Editar");
        editItem.addActionListener((ActionEvent e) -> {
            int row = this._table.getSelectedRow();
            int id = Integer.parseInt(this._table.getValueAt(row, 0).toString());
            createUserView(id);
        });

        JMenuItem deleteItem = new JMenuItem("Eliminar");
        deleteItem.addActionListener((ActionEvent e) -> {
            int row = this._table.getSelectedRow();
            int id = Integer.parseInt(this._table.getValueAt(row, 0).toString());
            String name = this._table.getValueAt(row, 1).toString();
            String email = this._table.getValueAt(row, 2).toString();

            int response = JOptionPane.showConfirmDialog(null, MessageFormat.format("Esta seguro que desea eliminar el usuario: \r\n {0} ({1})", name, email), "Eliminar", JOptionPane.YES_NO_OPTION);
            if (response == 0) {
                _userOperations.Delete(id);
                JOptionPane.showMessageDialog(null, "Se elimino correctamente", "Operaciòn Exitosa", JOptionPane.NO_OPTION);
                loadData(term);
            }
        });

        JClickRightMenu popupMenu = new JClickRightMenu(Arrays.asList(editItem, deleteItem), this._table);
        this._table.setComponentPopupMenu(popupMenu._popupMenu);

    }

    private void createUserView(int id) {

        frmUser viewUser = new frmUser(id);
        viewUser.setVisible(true);
        viewUser.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentHidden(ComponentEvent e) {
                loadData(null);
            }
        });
    }
}
