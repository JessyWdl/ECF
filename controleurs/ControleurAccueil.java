package com.jessy.entity.controleurs;

import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.dao.DAOProspect;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;
import com.jessy.entity.vues.Accueil;
import com.jessy.entity.vues.Formulaire;

import java.util.ArrayList;
import java.util.Objects;

public class ControleurAccueil{
    public ControleurAccueil() {
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }
    public static ArrayList<Client> addClientInSelectSociete() throws Exception {
        return DAOClient.findAll();
    }
    public static ArrayList<Prospect> addProspectInSelectSociete() throws Exception {
        return DAOProspect.findAll();
    }
    public static void afficher(String Flag) {
        if (Objects.equals(Flag, "CLIENT")) {
            ControleurAfficher ca = new ControleurAfficher();
        } else if (Objects.equals(Flag, "PROSPECT")) {
            ControleurAfficher ca = new ControleurAfficher();
        }
    }
}
