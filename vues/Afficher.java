package com.jessy.entity.vues;

import com.jessy.entity.controleurs.ControleurAccueil;
import com.jessy.entity.controleurs.ControleurAfficher;
import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;
import com.jessy.entity.exception.MonException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.net.IDN;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;

import static com.jessy.entity.logs.Logs.LOGGER;
/**
 * Cette classe permet l'affichage d'un client ou prospect dans un tableau elle extend JDialog
 */
public class Afficher extends JDialog {
    // Declaration des variables d'instance/UI components
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;

    /**
     * Constructs a new Afficher dialog with the specified flag indicating whether to display clients or prospects.
     *
     * @param Flag The flag indicating whether to display clients or prospects.
     */
    public Afficher(String Flag){
        // Début du code et setup de l'UI

        DefaultTableModel model = new DefaultTableModel();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(900, 400);
        setLocationRelativeTo(null);

        buttonOK.addActionListener(e -> {
            dispose();
            ControleurAccueil.NewAccueil();
        });

        model.addColumn("Id");
        model.addColumn("Raison sociale");
        model.addColumn("Numéro de rue");
        model.addColumn("Nom de rue");
        model.addColumn("Code postal");
        model.addColumn("Ville");
        model.addColumn("Téléphone");
        model.addColumn("Email");
        model.addColumn("Commentaire");
        try {
            //Remplir le tableau avec les info de Client
            if (Objects.equals(Flag, "CLIENT")) {
                model.addColumn("Chiffre d'Affaire");
                model.addColumn("Nombre d'Employés");
                ArrayList<Client> clients = ControleurAfficher.fillAfficher(Flag);
                for (Client client : clients) {
                    Object[] fillTableau = {
                            client.getID(),
                            client.getRaisonSociale(),
                            client.getNumRue(),
                            client.getNomRue(),
                            client.getCodePostal(),
                            client.getVille(),
                            client.getTel(),
                            client.getEmail(),
                            client.getCommentaire(),
                            client.getChiffreAffaire(),
                            client.getNbEmployes(),
                    };
                    model.addRow(fillTableau);
                    table1.setModel(model);
                }
                //Remplir le tableau avec les info du Prospect
            } else if (Objects.equals(Flag, "PROSPECT")) {
                model.addColumn("Date Prospection");
                model.addColumn("Intéret Prospection");
                ArrayList<Prospect> prospects = ControleurAfficher.fillAfficher(Flag);
                for (Prospect prospect : prospects) {
                    Object[] fillTableau = {
                            prospect.getID(),
                            prospect.getRaisonSociale(),
                            prospect.getNumRue(),
                            prospect.getNomRue(),
                            prospect.getCodePostal(),
                            prospect.getVille(),
                            prospect.getTel(),
                            prospect.getEmail(),
                            prospect.getCommentaire(),
                            prospect.getDateProspect(),
                            prospect.getProspectInteresse(),
                    };
                    model.addRow(fillTableau);
                    table1.setModel(model);
                }
            }
        }catch (MonException me) {
            JOptionPane.showMessageDialog(null, me.getMessage());
        }
        catch (Exception ex){
            LOGGER.log(Level.SEVERE, ex.getMessage());
            JOptionPane.showMessageDialog(null, "Un problème est survenu");
            System.exit(1);
        }

        buttonCancel.addActionListener(e -> onCancel());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // Method to handle cancellation action
        dispose();
    }
}
