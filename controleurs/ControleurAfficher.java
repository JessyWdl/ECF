package com.jessy.entity.controleurs;

import com.jessy.entity.vues.Afficher;

public class ControleurAfficher extends Afficher {
    public static void NewAfficher(){
        Afficher afficher = new Afficher();
        afficher.setVisible(true);
    }
}
