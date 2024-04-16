package com.jessy.entity.test;

import com.jessy.entity.entites.Client;
import com.jessy.entity.exception.MonException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TestClient extends Client {

    @ParameterizedTest
    @NullAndEmptySource
    void testRsThrows(String param) throws MonException {
        assertThrows(MonException.class, () -> setRaisonSociale(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Apple" })
    void testSetRaceNotThrow(String param) {
        assertDoesNotThrow(() -> setRaisonSociale(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testNumRueThrows(String param) throws MonException {
        assertThrows(MonException.class, () -> setNumRue(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "12" })
    void testNumRueNotThrow(String param) {
        assertDoesNotThrow(() -> setNumRue(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testNomRueThrows(String param) throws MonException {
        assertThrows(MonException.class, () -> setNomRue(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Rue de l'innovation" })
    void testNomRueNotThrow(String param) {
        assertDoesNotThrow(() -> setNomRue(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testCpThrows(String param) throws MonException {
        assertThrows(MonException.class, () -> setCodePostal(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "54200" })
    void testCpNotThrow(String param) {
        assertDoesNotThrow(() -> setCodePostal(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testVilleThrows(String param) throws MonException {
        assertThrows(MonException.class, () -> setVille(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Paris" })
    void testVilleNotThrow(String param) {
        assertDoesNotThrow(() -> setVille(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "6789" })
    void testTelephoneThrows(String param) throws MonException {
        assertThrows(MonException.class, () -> setTel(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "0845654434" })
    void testTelephoneNotThrow(String param) {
        assertDoesNotThrow(() -> setTel(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "dfsdf@" })
    void testMailThrows(String param) throws MonException {
        assertThrows(MonException.class, () -> setEmail(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "apple@contact.com" })
    void testMailNotThrow(String param) {
        assertDoesNotThrow(() -> setEmail(param));
    }


    @Test
    public void testChiffreAffaireThrow() {
        Client client = new Client();
        assertThrows(MonException.class, () -> client.setChiffreAffaire(100.0));
    }

    @Test
    public void testChiffreAffaireNotThrow() {
        Client client = new Client();
        assertDoesNotThrow(() -> client.setChiffreAffaire(5000.0));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testNbEmployesThrows(Integer param) throws MonException {
        assertThrows(MonException.class, () -> setNbEmployes(param));
    }

    @ParameterizedTest
    @ValueSource(ints = 23423)
    void testNbEmployesNotThrow(Integer param) {
        assertDoesNotThrow(() -> setNbEmployes(param));
    }
}