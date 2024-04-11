package com.jessy.entity.controleurs;

import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.dao.DAOProspect;
import com.jessy.entity.entites.Client;
import com.jessy.entity.vues.Afficher;
import com.jessy.entity.vues.Formulaire;

import java.util.ArrayList;
import java.util.Objects;

public class ControleurAfficher {
    public static void Afficher(String Flag) throws Exception {
            Afficher a = new Afficher(Flag);
            a.setVisible(true);
    }
    //Permet de réucpérer toute les valeurs de la table client ou prospect pour les ajouters à la JTable
    public static ArrayList fillAfficher(String Flag) throws Exception {
        if (Objects.equals(Flag, "CLIENT")) {
            return DAOClient.findAll();
        }else {
            return DAOProspect.findAll();
        }
    }
}
