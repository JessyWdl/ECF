package com.jessy.entity.dao;

import com.jessy.entity.entites.Prospect;
import com.jessy.entity.exception.MonException;

import java.sql.*;
import java.util.ArrayList;

import static com.jessy.entity.logs.Logs.LOGGER;

public class DAOProspect {
    public static ArrayList<Prospect>  findAll() throws Exception {
        Connection con = DatabaseConnection.con();
        Statement stmt = null;
        String query = "select * from prospect";
        ArrayList<Prospect> prospectArrayList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Prospect prospect = new Prospect();
                prospect.setID(rs.getInt("ID"));
                prospect.setRaisonSociale(rs.getString("RaisonSociale"));
                prospect.setNumRue(rs.getString("NumRue"));
                prospect.setNomRue(rs.getString("NomRue"));
                prospect.setCodePostal(rs.getString("CodePostal"));
                prospect.setVille(rs.getString("Ville"));
                prospect.setTel(rs.getString("Tel"));
                prospect.setEmail(rs.getString("Email"));
                prospect.setDateProspect(rs.getDate("DateProspect").toLocalDate());
                prospect.setProspectInteresse(rs.getString("ProspectInteresse"));
                prospect.setCommentaire(rs.getString("Commentaire"));

                prospectArrayList.add(prospect);
            }
        } catch (SQLException e) {
            LOGGER.info("Database closed");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return prospectArrayList;
    }

    public static ArrayList<Prospect> findByName(String where) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "select * from client where RaisonSociale=?";
        ArrayList<Prospect> prospectArrayList = new ArrayList<>();
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, where);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prospect prospect = new Prospect();
                prospect.setID(rs.getInt("ID"));
                prospect.setRaisonSociale(rs.getString("RaisonSociale"));
                prospect.setNumRue(rs.getString("NumRue"));
                prospect.setNomRue(rs.getString("NomRue"));
                prospect.setCodePostal(rs.getString("CodePostal"));
                prospect.setVille(rs.getString("Ville"));
                prospect.setTel(rs.getString("Tel"));
                prospect.setEmail(rs.getString("Email"));
                prospect.setDateProspect(rs.getDate("DateProspect").toLocalDate());
                prospect.setProspectInteresse(rs.getString("ProspectInteresse"));
                prospect.setCommentaire(rs.getString("Commentaire"));

                prospectArrayList.add(prospect);
            }
        } catch (SQLException e) {
            LOGGER.info("Database closed");
        } catch (MonException e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return prospectArrayList;
    }

    public static void create(Prospect prospect) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "Insert into prospect (RaisonSociale,NumRue,NomRue,CodePostal,Ville," +
                "Tel,Email,DateProspect,ProspectInteresse," +
                "Commentaire) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, prospect.getRaisonSociale());
            stmt.setString(2, prospect.getNumRue());
            stmt.setString(3, prospect.getNomRue());
            stmt.setString(4, prospect.getCodePostal());
            stmt.setString(5, prospect.getVille());
            stmt.setString(6, prospect.getTel());
            stmt.setString(7, prospect.getEmail());
            stmt.setDate(8, Date.valueOf(prospect.getDateProspect()));
            stmt.setString(9, prospect.getProspectInteresse());
            stmt.setString(10, prospect.getCommentaire());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Database closed");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public Prospect update(Prospect prospect, String where) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "UPDATE `prospect` SET `Raison sociale` = ?, `NumRue` = ?, `NomRue` = ? `CodePostal` = ?," +
                "`Ville` = ?, `Tel` = ?, `Email` = ?, `DateProspect` = ?, `ProspectInteresse` = ?, `Commentaire`= ? " +
                "WHERE `client`.`RaisonSociale` = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, prospect.getRaisonSociale());
            stmt.setString(2, prospect.getNumRue());
            stmt.setString(3, prospect.getNomRue());
            stmt.setString(4, prospect.getCodePostal());
            stmt.setString(5, prospect.getVille());
            stmt.setString(6, prospect.getTel());
            stmt.setString(7,prospect.getEmail());
            stmt.setDate(8, Date.valueOf(prospect.getDateProspect()));
            stmt.setString(9, prospect.getProspectInteresse());
            stmt.setString(10, prospect.getCommentaire());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Database closed");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return prospect;
    }

    public static void delete(Connection con, String where) throws Exception {
        PreparedStatement stmt = null;
        String query = "Delete from client where RaisonSociale=?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, where);

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Database closed");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
