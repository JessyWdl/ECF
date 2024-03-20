package com.jessy.entity.controleurs;

import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.entites.Client;
import com.jessy.entity.vues.Accueil;
import com.jessy.entity.vues.Formulaire;
import com.sun.jdi.Value;

import java.nio.file.WatchEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControleurFormulaire extends Formulaire {
    public ControleurFormulaire() {
        Formulaire formulaire = new Formulaire();
        formulaire.setVisible(true);
    }
    public static ArrayList<Client> MODIFIER() throws Exception {
        return DAOClient.findByName(Accueil.Value);
    }
}
