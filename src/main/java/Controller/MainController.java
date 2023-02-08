/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Crud.UserCrud;
import View.frmUser;
import com.uni.mvc.plataforma.de.desarrollo.semana02.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Farango
 */
public class MainController implements ActionListener{
    
    private Main _mainView;
    private UserCrud _userOperations;
    
    public MainController(Main form){
        this._mainView = form;
        this._userOperations = new UserCrud();
        this._mainView.btnNew.addActionListener(this);
        loadData();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == _mainView.btnNew ) {
            btnCreateClick();
        }
    }
    
    private void btnCreateClick () {
        frmUser frmUser = new frmUser();
    }
    
    private void loadData(){
        DefaultTableModel model = _userOperations.GetAll();
        
        TableRowSorter<TableModel> orderTable = new TableRowSorter<>(model);
        this._mainView.tblUsers.setRowSorter(orderTable);
        this._mainView.tblUsers.setModel(model);
    }
    
}
