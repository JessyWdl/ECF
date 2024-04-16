package com.jessy.entity.controleurs;

import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.dao.DAOProspect;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;
import com.jessy.entity.vues.Accueil;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class serves as the controller for the home screen.
 */
public class ControleurAccueil{
    public static void NewAccueil() {
        /**
         * Opens the home screen view.
         */
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }
    /**
     * Retrieves all Clients from the database to populate a selection box.
     *
     * @return An ArrayList containing all Clients.
     * @throws Exception if an error occurs while accessing the database.
     */
    public static ArrayList<Client> addClientInSelectSociete() throws Exception {
        return DAOClient.findAll();
    }
    /**
     * Retrieves all Prospects from the database to populate a selection box.
     *
     * @return An ArrayList containing all Prospects.
     * @throws Exception if an error occurs while accessing the database.
     */
    public static ArrayList<Prospect> addProspectInSelectSociete() throws Exception {
        return DAOProspect.findAll();
    }
}
