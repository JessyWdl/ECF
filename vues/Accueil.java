package com.jessy.entity.vues;

import com.jessy.entity.controleurs.ControleurAccueil;
import com.jessy.entity.controleurs.ControleurFormulaire;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Accueil extends JDialog {
    private JPanel contentPane;
    private JButton buttonQuitter;
    private JButton creerButton;
    private JButton afficherButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JRadioButton clientRadioButton;
    private JRadioButton prospectRadioButton;
    private JPanel PanelButton;
    private JPanel PanelSelectionSociete;
    private JPanel ButtonPanel;
    private JComboBox selectSociete;

    protected static String Flag = null;
    public static Object Value;
    private Object selectedValue = selectSociete.getSelectedItem();

    public Accueil() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonQuitter);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        modifierButton.setVisible(false);
        afficherButton.setVisible(false);
        supprimerButton.setVisible(false);
        creerButton.setVisible(false);
        PanelSelectionSociete.setVisible(false);

        ButtonGroup table = new ButtonGroup();
        table.add(clientRadioButton);
        table.add(prospectRadioButton);
        //-----------------------------------------------------------------------------------------------------------
        buttonQuitter.addActionListener(e -> onCancel());
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        //-----------------------------------------------------------------------------------------------------------
        clientRadioButton.addActionListener(e -> {
            PanelSelectionSociete.setVisible(true);
            Flag = "CLIENT";
            try {
                clearComboBox();
                addPlaceholder();
                fillComboBox();
                isEmpty();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        prospectRadioButton.addActionListener(e -> {
            PanelSelectionSociete.setVisible(true);
            Flag = "PROSPECT";
            try {
                clearComboBox();
                addPlaceholder();
                fillComboBox();
                isEmpty();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        //-----------------------------------------------------------------------------------------------------------
        creerButton.addActionListener(e -> {
            try {
                ControleurFormulaire.ajouter(Flag);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        modifierButton.addActionListener(e -> {
            Value = selectSociete.getSelectedItem();
            try {
                ControleurFormulaire.modifier(Flag, Value);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        supprimerButton.addActionListener(e -> {
            Value = selectSociete.getSelectedItem();
            try {
                ControleurFormulaire.supprimer(Flag, Value);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        selectSociete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedValue = selectSociete.getSelectedItem();
                isEmpty();
            }
        });
    }
    private void clearComboBox(){
        selectSociete.removeAllItems();
    }
    private void fillComboBox() throws Exception {
        if (Objects.equals(Flag, "CLIENT")) {
            ArrayList<Client> clientArrayList = ControleurAccueil.addClientInSelectSociete();
            for (Client client : clientArrayList) {
                selectSociete.addItem(client.getRaisonSociale());
            }
        } else if (Objects.equals(Flag, "PROSPECT")) {
            ArrayList<Prospect> prospectArrayList = ControleurAccueil.addProspectInSelectSociete();
            for (Prospect prospect : prospectArrayList) {
                selectSociete.addItem(prospect.getRaisonSociale());
            }
        }
    }
    private void isEmpty(){
        if (Objects.equals(selectedValue, "-")){
            modifierButton.setVisible(false);
            afficherButton.setVisible(true);
            supprimerButton.setVisible(false);
            creerButton.setVisible(true);
        }
        else{
            modifierButton.setVisible(true);
            afficherButton.setVisible(false);
            supprimerButton.setVisible(true);
            creerButton.setVisible(false);
        }
    }
    private void addPlaceholder() {
        selectSociete.addItem("-");
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
