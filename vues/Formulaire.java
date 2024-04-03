package com.jessy.entity.vues;

import com.jessy.entity.controleurs.ControleurAccueil;
import com.jessy.entity.controleurs.ControleurFormulaire;
import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;
import com.sun.jdi.Value;

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
    private JLabel IdLabel;
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
        if (Objects.equals(Type, "SUPPRIMER")){
            RaisonSociale.setEnabled(false);
            NumRue.setEnabled(false);
            NomRue.setEnabled(false);
            CodePostal.setEnabled(false);
            Ville.setEnabled(false);
            Tel.setEnabled(false);
            Email.setEnabled(false);
            ChiffreDate.setEnabled(false);
            NbEmployes.setEnabled(false);
            Commentaire.setEnabled(false);
        } else if (Objects.equals(Type, "CREER")) {
            ID.setEnabled(false);
            ID.setVisible(false);
            IdLabel.setVisible(false);
        }
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ControleurAccueil.NewAccueil();
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
                dispose();
                String RS = RaisonSociale.getText();
                String StreetNumber = NumRue.getText();
                String StreetName = NomRue.getText();
                String CP = CodePostal.getText();
                String City = Ville.getText();
                String Phone = Tel.getText();
                String Mail = Email.getText();
                String Com = Commentaire.getText();
                if(Objects.equals(Type, "CREER")){
                    if (Objects.equals(Flag, "CLIENT")) {
                        try {
                            ControleurFormulaire.CreateFormClient(RS, StreetNumber, StreetName,
                                    CP, City, Phone, Mail, Double.parseDouble(ChiffreDate.getText()), Integer.parseInt(NbEmployes.getText()), Com);
                            JOptionPane.showMessageDialog(null, "Création du client terminé");
                            ControleurAccueil.NewAccueil();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (Objects.equals(Type, "MODIFIER")) {
                    if (Objects.equals(Flag, "CLIENT")) {
                        try {
                            ControleurFormulaire.UpdateFormClient(Integer.parseInt(ID.getText()), RS, StreetNumber, StreetName,
                                    CP, City, Phone, Mail, Double.parseDouble(ChiffreDate.getText()), Integer.parseInt(NbEmployes.getText()), Com);
                            JOptionPane.showMessageDialog(null, "Modification du client terminé");
                            ControleurAccueil.NewAccueil();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    else{

                    }
                }else if(Objects.equals(Type, "SUPPRIMER")){
                    try {
                        ControleurFormulaire.DeleteFormClient(Integer.parseInt(ID.getText()));
                        JOptionPane.showMessageDialog(null, "Suppression du client terminé");
                        ControleurAccueil.NewAccueil();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
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
    public void fillFormClient(Client client) {
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
    public void fillFormProspect(Prospect prospect){
        ID.setText(String.valueOf(prospect.getID()));
        ID.setEnabled(false);
        RaisonSociale.setText(prospect.getRaisonSociale());
        NumRue.setText(prospect.getNumRue());
        NomRue.setText(prospect.getNomRue());
        CodePostal.setText(prospect.getCodePostal());
        Ville.setText(prospect.getVille());
        Tel.setText(prospect.getTel());
        Email.setText(prospect.getEmail());
        ChiffreDate.setText(String.valueOf(prospect.getDateProspect()));
        NbEmployes.setText(String.valueOf(prospect.getProspectInteresse()));
        if (Objects.equals(prospect.getProspectInteresse(), "oui")){
            ouiRadioButton.setSelected(true);
        } else if (Objects.equals(prospect.getProspectInteresse(), "non")){
            nonRadioButton.setSelected(true);
            }
        Commentaire.setText(prospect.getCommentaire());
    }
}