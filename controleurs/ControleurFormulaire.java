package com.jessy.entity.controleurs;

import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.dao.DAOProspect;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;
import com.jessy.entity.vues.Accueil;
import com.jessy.entity.vues.Formulaire;
import com.sun.jdi.Value;

import java.nio.file.WatchEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class serves as the controller for managing form-related operations.
 */
public class ControleurFormulaire {
    public static String Type;
    //Appel d'un nouveau form avec comme type CREER qui sera utilisé pour la détection de l'action du bouton "confirmer"
    /**
     * Opens a form for creating a new entity (Client or Prospect).
     *
     * @param Flag Indicates whether the entity is a Client or a Prospect.
     */
    public static void ajouter(String Flag){
        Type = "CREER";
        if (Objects.equals(Flag, "CLIENT")) {
            Formulaire f = new Formulaire(Flag, Type);
            f.setVisible(true);
        } else if (Objects.equals(Flag, "PROSPECT")) {
            Formulaire f = new Formulaire(Flag, Type);
            f.setVisible(true);
        }
    }

    /**
     * Opens a form for modifying an existing entity (Client or Prospect).
     *
     * @param Flag  Indicates whether the entity is a Client or a Prospect.
     * @param Value The value of the entity to be modified.
     * @throws Exception if an error occurs while accessing the database.
     */
    public static void modifier(String Flag, Object Value) throws Exception {
        Type = "MODIFIER";
        if (Objects.equals(Flag, "CLIENT")) {
            Client client = DAOClient.findByName(Value);
            Formulaire f = new Formulaire(Flag, Type);
            f.fillFormClient(client);
            f.setVisible(true);
        } else if (Objects.equals(Flag, "PROSPECT")) {
            Prospect prospect = DAOProspect.findByName(Value);
            Formulaire f = new Formulaire(Flag, Type);
            f.fillFormProspect(prospect);
            f.setVisible(true);
        }
    }
    /**
     * Opens a form for deleting an existing entity (Client or Prospect).
     *
     * @param Flag  Indicates whether the entity is a Client or a Prospect.
     * @param Value The value of the entity to be deleted.
     * @throws Exception if an error occurs while accessing the database.
     */
    public static void supprimer(String Flag, Object Value) throws Exception {
        Type = "SUPPRIMER";
        if (Objects.equals(Flag, "CLIENT")) {
            Client client = DAOClient.findByName(Value);
            Formulaire f = new Formulaire(Flag, Type);
            f.fillFormClient(client);
            f.setVisible(true);
        } else if (Objects.equals(Flag, "PROSPECT")) {
            Prospect prospect = DAOProspect.findByName(Value);
            Formulaire f = new Formulaire(Flag, Type);
            f.fillFormProspect(prospect);
            f.setVisible(true);
        }
    }

    //Méthode utilisé pour la création d'un client ou d'un prospect
    public static void CreateFormClient(String RaisonSociale, String NumRue, String NomRue, String CodePostal,
                                        String Ville, String Tel, String Email, double ChiffreAffaire, int NbEmployes, String Commentaire)
            throws Exception {
        Client client = new Client(RaisonSociale, NumRue, NomRue, CodePostal, Ville, Tel, Email, ChiffreAffaire, NbEmployes, Commentaire);
        DAOClient.create(client);
    }
    public static void CreateFormProspect(String RaisonSociale, String NumRue, String NomRue, String CodePostal,
                                        String Ville, String Tel, String Email, LocalDate DateProspect, String ProspectInteresse, String Commentaire)
            throws Exception {
        Prospect prospect = new Prospect(RaisonSociale, NumRue, NomRue, CodePostal, Ville, Tel, Email, DateProspect, ProspectInteresse, Commentaire);
        DAOProspect.create(prospect);
    }

    //Méthode utilisé pour la modification d'un client ou d'un prospect
    public static void UpdateFormClient(int ID, String RaisonSociale, String NumRue, String NomRue, String CodePostal,
                                        String Ville, String Tel, String Email, double ChiffreAffaire, int NbEmployes, String Commentaire)
            throws Exception {
        Client client = new Client(RaisonSociale, NumRue, NomRue, CodePostal, Ville, Tel, Email, ChiffreAffaire, NbEmployes, Commentaire);
        DAOClient.update(ID, client);
    }
    public static void UpdateFormProspect(int ID, String RaisonSociale, String NumRue, String NomRue, String CodePostal,
                                        String Ville, String Tel, String Email, LocalDate DateProspect, String ProspectInteresse, String Commentaire)
            throws Exception {
        Prospect prospect = new Prospect(RaisonSociale, NumRue, NomRue, CodePostal, Ville, Tel, Email, DateProspect, ProspectInteresse, Commentaire);
        DAOProspect.update(ID, prospect);
    }

    //Méthode utilisé pour la suppression d'un client ou d'un prospect en fonction de son ID
    public static void DeleteFormClient(int ID) throws Exception {
        DAOClient.delete(ID);
    }
    public static void DeleteFormProspect(int ID) throws Exception {
        DAOProspect.delete(ID);
    }
}

