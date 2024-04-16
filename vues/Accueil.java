package com.jessy.entity.vues;

import com.jessy.entity.controleurs.ControleurAccueil;
import com.jessy.entity.controleurs.ControleurAfficher;
import com.jessy.entity.controleurs.ControleurFormulaire;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;
import com.jessy.entity.exception.MonException;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;

import static com.jessy.entity.logs.Logs.LOGGER;
/**
 * Cette classe represente la dialogue principale pour l'application, affichant les différentes options qui permettent
 * de gérer un client ou un prospect elle extend JDialog
 */
public class Accueil extends JDialog {
    // Declaration des variables d'instance/UI components
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

    /**
     * Construire une nouvelle accueil en fonction des choix utilisateurs
     */
    public Accueil() {
        // Début du code et setup de l'UI
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

        buttonQuitter.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        //Définir le flag + remplir la combo box ici
        clientRadioButton.addActionListener(e -> {
            PanelSelectionSociete.setVisible(true);
            Flag = "CLIENT";
            try {
                clearComboBox();
                addPlaceholder();
                fillComboBox();
                isEmpty();
            }catch (MonException me) {
                JOptionPane.showMessageDialog(null, me.getMessage());
            }catch (Exception ex){
                LOGGER.log(Level.SEVERE, ex.getMessage());
                JOptionPane.showMessageDialog(null, "Un problème est survenu");
                System.exit(1);
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
            }catch (MonException me) {
                JOptionPane.showMessageDialog(null, me.getMessage());
            }catch (Exception ex){
                LOGGER.log(Level.SEVERE, ex.getMessage());
                JOptionPane.showMessageDialog(null, "Un problème est survenu");
                System.exit(1);
            }
        });

        creerButton.addActionListener(e -> {
            dispose();
                ControleurFormulaire.ajouter(Flag);
        });

        modifierButton.addActionListener(e -> {
            Value = selectSociete.getSelectedItem();
            dispose();
            try {
                ControleurFormulaire.modifier(Flag, Value);
            }catch (MonException me) {
                JOptionPane.showMessageDialog(null, me.getMessage());
            }catch (Exception ex){
                LOGGER.log(Level.SEVERE, ex.getMessage());
                JOptionPane.showMessageDialog(null, "Un problème est survenu");
                System.exit(1);
            }
        });
        supprimerButton.addActionListener(e -> {
            Value = selectSociete.getSelectedItem();
            dispose();
            try {
                ControleurFormulaire.supprimer(Flag, Value);
            }   catch (MonException me) {
                JOptionPane.showMessageDialog(null, me.getMessage());
            } catch (Exception ex){
                LOGGER.log(Level.SEVERE, ex.getMessage());
                JOptionPane.showMessageDialog(null, "Un problème est survenu");
                System.exit(1);
            }
        });
        afficherButton.addActionListener(e -> {
            dispose();
                ControleurAfficher.Afficher(Flag);
        });
        selectSociete.addActionListener(e -> {
            selectedValue = selectSociete.getSelectedItem();
            isEmpty();
        });
    }
    //Pour éviter qu'à chaque changement de valeur entre client et prospect, les valeurs s'additione
    private void clearComboBox(){
        selectSociete.removeAllItems();
    }
    //Fill la combo box
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
    //Check de la valeur si = à placeholder seulement affichage/creer
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
        dispose();
    }
}
