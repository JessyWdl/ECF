package com.jessy.entity;

import com.formdev.flatlaf.FlatDarkLaf;
import com.jessy.entity.dao.DAOClient;
import com.jessy.entity.exception.MonException;
import com.jessy.entity.logs.FormatterLogs;
import com.jessy.entity.logs.Logs;
import com.jessy.entity.vues.Accueil;

import javax.swing.*;
import java.util.logging.FileHandler;

public class Main {
    private static FileHandler fh = null;

    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(new FlatDarkLaf());

        Accueil accueil = new Accueil();
        accueil.setVisible(true);

        fh = new FileHandler("logReverso.log", true);
        Logs.LOGGER.setUseParentHandlers(false);
        Logs.LOGGER.addHandler(fh);

        fh.setFormatter(new FormatterLogs());
    }
}