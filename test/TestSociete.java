package com.jessy.entity.test;

import com.jessy.entity.entites.Societe;
import com.jessy.entity.exception.MonException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestSociete extends Societe {
    @Test
    public void testSetRaisonSociale() {
        TestSociete raisonSociale = new TestSociete();
        try {
            raisonSociale.setRaisonSociale("Raison Sociale Test");
            assertEquals("Raison Sociale Test", raisonSociale.getRaisonSociale());
        } catch (MonException e) {
            fail("L'exception ne devrait pas être lancée avec une raison sociale valide");
        }
        try {
            raisonSociale.setRaisonSociale(null);
            fail("Une exception devrait être lancée pour une valeur null");
        } catch (Exception e) {

        }
    }

    @Test
    public void testSetNumRue() {
        TestSociete numRue = new TestSociete();
        try {
            numRue.setNumRue("123");
            assertEquals("123", numRue.getNumRue());
        } catch (MonException e) {
            fail("L'exception ne devrait pas être lancée avec un numéro de rue valide.");
        }
        try{
            numRue.setNumRue(null);
            fail("Une exception devrait être lancée pour une valeur null");
        } catch (MonException e) {

        }
    }
    @Test
    public void testSetNomRue() {
        TestSociete nomRue = new TestSociete();
        try {
            nomRue.setNomRue("Nom Rue Test");
            assertEquals("Nom Rue Test", nomRue.getNomRue());
        } catch (MonException e) {
            fail("L'exception ne devrait pas être lancée avec un nom de rue valide.");
        }
        try {
            nomRue.setNomRue(null);
            fail("Une exception devrait être lancée pour une valeur null");
        }catch (MonException e){

        }
    }
    @Test
    public void testSetCodePostal() {
        TestSociete codePostal = new TestSociete();
        try {
            codePostal.setCodePostal("12345");
            assertEquals("12345", codePostal.getCodePostal());
        } catch (MonException e) {
            fail("L'exception ne devrait pas être lancée avec un code postal valide.");
        }
        try {
            codePostal.setCodePostal(null);
            fail("Une exception devrait être lancée pour une valeur null");
        }catch (MonException e){

        }
    }
    @Test
    public void testSetVille() {
        TestSociete ville = new TestSociete();
        try {
            ville.setVille("Ville Test");
            assertEquals("Ville Test", ville.getVille());
        } catch (MonException e) {
            fail("L'exception ne devrait pas être lancée avec une ville valide.");
        }
        try {
            ville.setVille(null);
            fail("Une exception devrait être lancée pour une valeur null");
        } catch (MonException e){

        }
    }
    @Test
    public void testSetTel() {
        TestSociete tel = new TestSociete();
        try {
            tel.setTel("0123456789");
            assertEquals("0123456789", tel.getTel());
        } catch (MonException e) {
            fail("L'exception ne devrait pas être lancée avec un numéro de téléphone valide.");
        }
        try {
            tel.setTel(null);
            fail("Une exception devrait être lancée pour une valeur null");
        }catch (MonException e){

        }
    }
    @Test
    public void testSetEmailWithValidEmail() {
        TestSociete email = new TestSociete();
        try {
            email.setEmail("test@example.com");
        } catch (MonException e) {
            fail("L'exception ne devrait pas être lancée avec un email valide.");
        }
        try{
            email.setEmail("invalid-email");
            fail("Une exception devrait être lancée pour un email non conforme");

        }catch (MonException e){
        }
    }

}
