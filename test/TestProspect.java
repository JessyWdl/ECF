package com.jessy.entity.test;

import com.jessy.entity.entites.Prospect;
import com.jessy.entity.exception.MonException;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestProspect extends Prospect {

    @Test
    public void testSetDateProspectWithValidDate() {
        TestProspect prospect = new TestProspect();
        try {
            Date validDate = new Date(2024, 3, 7 ); // Date valide
            prospect.setDateProspect(validDate.toLocalDate());
            assertEquals(validDate, prospect.getDateProspect()); // Vérifie si la valeur a bien été affectée
        } catch (MonException e) {
            fail("L'exception ne devrait pas être lancée avec une date valide.");
        }
        try {
            prospect.setDateProspect(null);
            fail("Une exception devrait être lancée pour une date nulle.");
        } catch (MonException e) {
            assertEquals("Date invalide", e.getMessage());
        }
    }
    @Test
    public void testSetProspectInteresseWithValidValue() {
        TestProspect prospect = new TestProspect();
        try {
            String validValue = "Intéressé";
            prospect.setProspectInteresse(validValue);
            assertEquals(validValue, prospect.getProspectInteresse()); // Vérifie si la valeur a bien été affectée
        } catch (MonException e) {
            fail("L'exception ne devrait pas être lancée avec une valeur valide.");
        }
        try {
            prospect.setProspectInteresse(null);
            fail("Une exception devrait être lancée pour une valeur nulle.");
        } catch (MonException e) {
            assertEquals("Prospect intéressé ne peut être vide", e.getMessage());
        }
        try {
            prospect.setProspectInteresse("");
            fail("Une exception devrait être lancée pour une valeur vide.");
        } catch (MonException e) {
            assertEquals("Prospect intéressé ne peut être vide", e.getMessage());
        }
    }

}

