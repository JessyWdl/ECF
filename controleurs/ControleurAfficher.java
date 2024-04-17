package com.jessy.entity.controleurs;

import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.dao.DAOProspect;
import com.jessy.entity.entites.Client;
import com.jessy.entity.vues.Afficher;
import com.jessy.entity.vues.Formulaire;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Cette classe est le controleur de la vue afficher qui a pour but de
 * renvoyer un tableau contenant toute les informations d'une table
 */
public class ControleurAfficher {
    /**
     * Appel de la vu Afficher qui renvois un tableau contenant les éléments de la table Client ou Prospect
     *
     * @param Flag Indique si l'entités à afficher est un Clients ou un Prospects
     */
    public static void Afficher(String Flag){
            Afficher a = new Afficher(Flag);
            a.setVisible(true);
    }
    /**
     * Récupère toute les entités de la BDD d'un type spécifique (Client ou Prospect) qui vont remplir le tableau
     *
     * @param Flag Indique si l'on récupère un Clients ou un Prospects
     * @return Une ArrayList qui contient les entités d'un type spécifique
     * @throws Exception Si une erreur se produit lors de l'accés à la BDD
     */
    public static ArrayList fillAfficher(String Flag) throws Exception {
        if (Objects.equals(Flag, "CLIENT")) {
            return DAOClient.findAll();
        }else {
            return DAOProspect.findAll();
        }
    }
}
