package com.jessy.entity.vues;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Objects;

public class Afficher extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable JTableau;

    public Afficher() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(900, 400);
        setLocationRelativeTo(null);

        DefaultTableModel Model = new DefaultTableModel();
        JTableau = new JTable(Model);

        Model.addColumn("ID");
        Model.addColumn("Raison Sociale");
        Model.addColumn("Numéro de rue");
        Model.addColumn("Nom de rue");
        Model.addColumn("Code Postal");
        Model.addColumn("Ville");
        Model.addColumn("Numéro de Téléphone");
        Model.addColumn("Adresse mail");
        Model.addColumn("Commentaire");
        if (Objects.equals(Accueil.Flag, "CLIENT")){
            Model.addColumn("Chiffre d'Affaire");
            Model.addColumn("Nombre d'Employés");
        }
        else{
            Model.addColumn("Date Prospection");
            Model.addColumn("Intéret de la prospection");
        }


        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
