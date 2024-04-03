package com.jessy.entity.vues;

import com.jessy.entity.controleurs.ControleurAccueil;
import com.jessy.entity.controleurs.ControleurAfficher;
import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.net.IDN;
import java.util.ArrayList;
import java.util.Objects;

public class Afficher extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;

    public Afficher(String Flag){
        DefaultTableModel model = new DefaultTableModel();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(900, 400);
        setLocationRelativeTo(null);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ControleurAccueil.NewAccueil();
            }
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
        } catch (Exception e) {
            e.printStackTrace();
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
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
