package com.jessy.entity.vues;

import com.jessy.entity.controleurs.ControleurFormulaire;
import com.jessy.entity.entites.Client;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

import static com.jessy.entity.vues.Accueil.Flag;

public class Formulaire extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton confirmerButton;
    private JTextField CodePostal;
    private JTextField Tel;
    private JTextField Email;
    private JTextField ChiffreDate;
    private JTextField NbEmployes;
    private JLabel ClientProspectLabel;
    private JTextField RaisonSociale;
    private JRadioButton nonRadioButton;
    private JRadioButton ouiRadioButton;
    private JLabel ChiffreAffaireDateProspect;
    private JLabel NbEmployers;
    private JLabel ProspectInteret;
    private JLabel AjouterModifierSupprimerLabel;
    private JTextField Commentaire;
    private JTextField NumRue;
    private JTextField NomRue;
    private JTextField Ville;

    public Formulaire(){
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        ButtonGroup table = new ButtonGroup();
        table.add(ouiRadioButton);
        table.add(nonRadioButton);

        ClientProspectLabel.setText(Flag);
        AjouterModifierSupprimerLabel.setText(Accueil.Type);


        if (Objects.equals(Flag, "CLIENT")) {
            ChiffreAffaireDateProspect.setText("Chiffre D'affaire");
            ProspectInteret.setVisible(false);
            ouiRadioButton.setVisible(false);
            nonRadioButton.setVisible(false);
        } else if (Objects.equals(Flag, "PROSPECT")) {
            ChiffreAffaireDateProspect.setText("Date Prospect");
            NbEmployers.setVisible(false);
            NbEmployes.setVisible(false);
        }
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
    private void onOK() {
        // add your code here
        dispose();
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    public void Modification() throws Exception {
        if (Objects.equals(Accueil.Type, "MODIFIER")) {
            ArrayList<Client> clientArrayList = ControleurFormulaire.MODIFIER();
            for (Client client : clientArrayList) {
                RaisonSociale.setText(client.getRaisonSociale());
                NumRue.setText(client.getNumRue());
                NomRue.setText(client.getNumRue());
                CodePostal.setText(client.getNumRue());
                Ville.setText(client.getVille());
                Tel.setText(client.getTel());
                Email.setText(client.getEmail());
                double chiffreAffaire= client.getChiffreAffaire();
                ChiffreDate.setText(String.valueOf(chiffreAffaire));
                int nbEmployes = client.getNbEmployes();
                NbEmployes.setText(String.valueOf(nbEmployes));
                Commentaire.setText(client.getCommentaire());
            }
        }
    }
}
