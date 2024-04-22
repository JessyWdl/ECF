package com.jessy.entity.vues;

import com.jessy.entity.controleurs.ControleurAccueil;
import com.jessy.entity.controleurs.ControleurFormulaire;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;
import com.jessy.entity.exception.DaoException;
import com.jessy.entity.exception.MonException;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jessy.entity.logs.Logs.LOGGER;

/**
 * Cette classe représente un formulaire qui permet la création, modification et suppression d'un client
 * ou d'un prospect elle extend JDialog
 */
public class Formulaire extends JDialog {
    // Declaration des variables d'instance/UI components
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
    private static String TypeButton;

    /**
     * Construire un nouveau Formulaire en utilisant le Flag et Type
     *
     * @param Flag Le flag permet d'indiquer au programme si c'est un Client ou un Prospect que l'on va traiter
     * @param Type Le type d'action que l'on va effectuer sur l'entité (e.g., CREER, MODIFIER, SUPPRIMER).
     */
    public Formulaire(String Flag, String Type) {
        // Début du code et setup de l'UI

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
        if (Objects.equals(Type, "SUPPRIMER")) {
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
            ouiRadioButton.setSelected(false);
            nonRadioButton.setEnabled(false);
        } else if (Objects.equals(Type, "CREER")) {
            ID.setEnabled(false);
            ID.setVisible(false);
            IdLabel.setVisible(false);
        }
        buttonOK.addActionListener(e -> {
            dispose();
            ControleurAccueil.NewAccueil();
        });
        buttonCancel.addActionListener(e -> onCancel());
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        confirmerButton.addActionListener(e -> {
            //Permet de récup' les valeurs des différents champs
            String RS = RaisonSociale.getText();
            String StreetNumber = NumRue.getText();
            String StreetName = NomRue.getText();
            String CP = CodePostal.getText();
            String City = Ville.getText();
            String Phone = Tel.getText();
            String Mail = Email.getText();
            String Com = Commentaire.getText();
            //Créer un Client/Prospect
            try {
                if (Objects.equals(Type, "CREER")) {
                    if (Objects.equals(Flag, "CLIENT")) {
                        ControleurFormulaire.CreateFormClient(RS, StreetNumber, StreetName,
                                CP, City, Phone, Mail, Double.parseDouble(ChiffreDate.getText()), Integer.parseInt(NbEmployes.getText()), Com);
                        JOptionPane.showMessageDialog(null, "Création du " + Flag + " terminé");
                        dispose();
                        ControleurAccueil.NewAccueil();
                    } else {
                        if (Objects.equals(Flag, "PROSPECT")) {
                            ControleurFormulaire.CreateFormProspect(RS, StreetNumber, StreetName,
                                    CP, City, Phone, Mail, LocalDate.parse(ChiffreDate.getText()), TypeButton, Com);
                            JOptionPane.showMessageDialog(null, "Création du " + Flag + " terminé");
                            dispose();
                            ControleurAccueil.NewAccueil();
                        }
                    }
                    //Modifier un Client/Prospect
                } else if (Objects.equals(Type, "MODIFIER")) {
                    if (Objects.equals(Flag, "CLIENT")) {
                        ControleurFormulaire.UpdateFormClient(Integer.parseInt(ID.getText()), RS, StreetNumber, StreetName,
                                CP, City, Phone, Mail, Double.parseDouble(ChiffreDate.getText()), Integer.parseInt(NbEmployes.getText()), Com);
                        JOptionPane.showMessageDialog(null, "Modification du " + Flag + " terminé");
                        dispose();
                        ControleurAccueil.NewAccueil();
                    } else {
                        if (Objects.equals(Flag, "PROSPECT")) {
                            ControleurFormulaire.UpdateFormProspect(Integer.parseInt(ID.getText()), RS, StreetNumber, StreetName,
                                    CP, City, Phone, Mail, LocalDate.parse(ChiffreDate.getText()), TypeButton, Com);
                            JOptionPane.showMessageDialog(null, "Modification du " + Flag + " terminé");
                            dispose();
                            ControleurAccueil.NewAccueil();
                        }
                    }
                    //Supprimer un Client/Prospect
                } else if (Objects.equals(Type, "SUPPRIMER")) {
                    if (Objects.equals(Flag, "CLIENT")) {
                        ControleurFormulaire.DeleteFormClient(Integer.parseInt(ID.getText()));
                        JOptionPane.showMessageDialog(null, "Suppression du " + Flag + " terminé");
                        dispose();
                        ControleurAccueil.NewAccueil();
                    } else {
                        if (Objects.equals(Flag, "PROSPECT")) {
                            ControleurFormulaire.DeleteFormProspect(Integer.parseInt(ID.getText()));
                            JOptionPane.showMessageDialog(null, "Suppression du " + Flag + " terminé");
                            dispose();
                            ControleurAccueil.NewAccueil();
                        }
                    }
                }
            } catch (MonException me) {
                JOptionPane.showMessageDialog(null, me.getMessage());
            } catch (DaoException dex) {
                if (dex.getGravite() == Level.SEVERE) {
                    JOptionPane.showMessageDialog(null, dex.getMessage());
                }
                JOptionPane.showMessageDialog(null, dex.getMessage());
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
                JOptionPane.showMessageDialog(null, "Un problème est survenu");
                System.exit(1);
            }
        });

        ouiRadioButton.addActionListener(e -> {
            TypeButton = "oui";
        });
        nonRadioButton.addActionListener(e -> {
            TypeButton = "non";
        });
    }

    private void onCancel() {
        dispose();
    }

    /**
     * Remplir les champs du formulaire avec les données spécifié par l'entité client
     *
     * @param client L'entité dont les données vont être utilisé pour remplir le formulaire
     */
    public void fillFormClient(Client client) {
        //Rempli le formulaire en fonction des paramètres du client
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

    /**
     * Remplir les champs du formulaire avec les données spécifié par l'entité prospect
     *
     * @param prospect L'entité dont les données vont être utilisé pour remplir le formulaire
     */
    public void fillFormProspect(Prospect prospect) {
        //Rempli le formulaire en fonction des paramètres du prospect
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
        if (Objects.equals(prospect.getProspectInteresse(), "oui")) {
            ouiRadioButton.setSelected(true);
            TypeButton = "oui";
        } else if (Objects.equals(prospect.getProspectInteresse(), "non")) {
            nonRadioButton.setSelected(true);
            TypeButton = "non";
        }
        Commentaire.setText(prospect.getCommentaire());
    }
}
