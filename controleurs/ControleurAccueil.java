package com.jessy.entity.controleurs;

import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.dao.DAOProspect;
import com.jessy.entity.entites.Client;
import com.jessy.entity.entites.Prospect;
import com.jessy.entity.vues.Accueil;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Controlleur de l'accueil
 */
public class ControleurAccueil{
    public static void NewAccueil() {
        /**
         * Initialisation de la vue
         */
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }
    /**
     * Récupère tout les clients de la BDD pour les ajouters à une combo box
     *
     * @return Une ArrayList avec client
     * @throws Exception Si jamais une erreur se produit au niveau de la BDD
     */
    public static ArrayList<Client> addClientInSelectSociete() throws Exception {
        return DAOClient.findAll();
    }
    /**
     * Récupère tout les prospect de la BDD pour les ajouters à une combo box
     *
     * @return Une ArrayList avec prospect
     * @throws Exception Si jamais une erreur se produit au niveau de la BDD
     */
    public static ArrayList<Prospect> addProspectInSelectSociete() throws Exception {
        return DAOProspect.findAll();
    }
}
