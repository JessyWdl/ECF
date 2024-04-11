package com.jessy.entity.controleurs;

import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.dao.DAOProspect;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;
import com.jessy.entity.vues.Accueil;

import java.util.ArrayList;
import java.util.Objects;

public class ControleurAccueil{
    public static void NewAccueil() {
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }
    //Récupère les Raison Sociale de chaque client ou Prospect en fonction du JButton
    public static ArrayList<Client> addClientInSelectSociete() throws Exception {
        return DAOClient.findAll();
    }
    public static ArrayList<Prospect> addProspectInSelectSociete() throws Exception {
        return DAOProspect.findAll();
    }
}
