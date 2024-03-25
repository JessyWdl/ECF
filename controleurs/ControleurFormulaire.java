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
import java.util.ArrayList;
import java.util.Objects;

public class ControleurFormulaire {
    public static String Type;

    public static void ajouter(String Flag) throws Exception {
        Type = "CREER";
        if (Objects.equals(Flag, "CLIENT")) {
            Client client = new Client();
            Formulaire f = new Formulaire(Flag, Type);
            f.fillFormClient(client);
            f.setVisible(true);
        } else if (Objects.equals(Flag, "PROSPECT")) {
            ControleurFormulaire cf = new ControleurFormulaire();
        }
    }

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

    public static void UpdateFormClient(int ID, String RaisonSociale, String NumRue, String NomRue, String CodePostal,
                                        String Ville, String Tel, String Email, double ChiffreAffaire, int NbEmployes, String Commentaire)
            throws Exception {
        Client client = new Client(ID, RaisonSociale, NumRue, NomRue, CodePostal, Ville, Tel, Email, ChiffreAffaire, NbEmployes, Commentaire);
        DAOClient.update(ID, client);
    }
}

