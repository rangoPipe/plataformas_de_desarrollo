/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Crud.UserCrud;
import Shared.HelperClass;
import View.frmUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Farango
 */
public class UserController implements ActionListener {

    private final User _userModel;
    private UserCrud _userOperations;
    private frmUser _userView;

    public UserController(frmUser form) {
        this._userModel = new User();
        this._userOperations = new UserCrud();
        this._userView = form;
        this._userView.btnCancel.addActionListener(this);
        this._userView.btnClean.addActionListener(this);
        this._userView.btnSave.addActionListener(this);
    }

    public void Start() {
        _userView.setTitle("User");
        _userView.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _userView.btnSave) {
            saveModel();
        } else if (e.getSource() == _userView.btnClean) {
            cleanForm();
        } else if (e.getSource() == _userView.btnCancel) {
            _userView.setVisible(false);
        }
    }

    public void cleanForm() {
        _userView.txtEmail.setText(null);
        _userView.txtName.setText(null);
        _userView.txtPhone.setText(null);
        _userView.cmbPosition.setSelectedIndex(0);
    }

    public void saveModel() {
        _userModel.setEmail(_userView.txtEmail.getText());
        _userModel.setName(_userView.txtName.getText());
        _userModel.setPhone(_userView.txtPhone.getText());
        _userModel.setJobPosition(_userView.cmbPosition.getSelectedItem().toString());
        _userModel.setId(new HelperClass().TryToParse(_userView.txtId.getText()));

        if (!"".equals(_userView.txtId.getText())) {
            UpdateUser();
        } else {
            CreateUser();
        }
    }

    private void CreateUser() {
        if (_userOperations.Insert(_userModel)) {
            JOptionPane.showMessageDialog(null, "Se guardo exitosamente");
            cleanForm();
            _userView.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar");
        }
    }

    private void UpdateUser() {
        if (_userOperations.Update(_userModel)) {
            JOptionPane.showMessageDialog(null, "Se actualizo exitosamente");
            cleanForm();
            _userView.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar");
        }
    }
}
