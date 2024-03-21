package com.jessy.entity.vues;

import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.entites.Client;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

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
    private JTextField ID;

    protected static String Type;

    public Formulaire(String Flag, String Type) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);

        ButtonGroup table = new ButtonGroup();
        table.add(ouiRadioButton);
        table.add(nonRadioButton);

        ClientProspectLabel.setText(Flag);
        AjouterModifierSupprimerLabel.setText(Type);

//        LocalDate date = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
//        String text = date.format(formatter);
//        LocalDate parsedDate = LocalDate.parse(text, formatter);


        if (Objects.equals(Accueil.Flag, "CLIENT")) {
            ChiffreAffaireDateProspect.setText("Chiffre D'affaire");
            ProspectInteret.setVisible(false);
            ouiRadioButton.setVisible(false);
            nonRadioButton.setVisible(false);
        } else if (Objects.equals(Accueil.Flag, "PROSPECT")) {
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
        confirmerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Objects.equals(Type, "CREER")){

                } else if (Objects.equals(Type, "MODIFIER")) {
                    try {
                        DAOClient.update(Accueil.Value);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }else if(Objects.equals(Type, "SUPPRIMER")){

                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void fillForm(Client client) {
        ID.setText(String.valueOf(client.getID()));
        ID.setEnabled(false);
        RaisonSociale.setText(client.getRaisonSociale());
        NumRue.setText(client.getNumRue());
        NomRue.setText(client.getNomRue());
        CodePostal.setText(client.getCodePostal());
        Ville.setText(client.getVille());
        Tel.setText(client.getTel());
        Email.setText(client.getEmail());
        ChiffreDate.setText(String.valueOf(client.getChiffreAffaire()));
        NbEmployes.setText(String.valueOf(client.getNbEmployes()));
        Commentaire.setText(client.getCommentaire());
    }
}