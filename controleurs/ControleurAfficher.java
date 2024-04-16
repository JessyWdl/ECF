package com.jessy.entity.controleurs;

import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.dao.DAOProspect;
import com.jessy.entity.entites.Client;
import com.jessy.entity.vues.Afficher;
import com.jessy.entity.vues.Formulaire;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class serves as the controller for displaying entities in a view.
 */
public class ControleurAfficher {
    /**
     * Opens a view for displaying entities of a specified type (Client or Prospect).
     *
     * @param Flag Indicates whether the entities to display are Clients or Prospects.
     */
    public static void Afficher(String Flag){
            Afficher a = new Afficher(Flag);
            a.setVisible(true);
    }
    /**
     * Retrieves all entities from the database of a specified type (Client or Prospect) to populate a table view.
     *
     * @param Flag Indicates whether to retrieve Clients or Prospects.
     * @return An ArrayList containing all entities of the specified type.
     * @throws Exception if an error occurs while accessing the database.
     */
    public static ArrayList fillAfficher(String Flag) throws Exception {
        if (Objects.equals(Flag, "CLIENT")) {
            return DAOClient.findAll();
        }else {
            return DAOProspect.findAll();
        }
    }
}
