/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az3eval2bourassamarieeve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TQY_06
 */
public class ModelVueArticles {

    private static String url = "jdbc:derby://localhost:1527/Az3Eval2Bourassa";
    private static String user = "username";
    private static String passwd = "password";

    private Connection connect = null;
    private String sqlString;
    private Statement stmt;
    private ResultSet resSet;

    public Connection connectToBD() {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion Obtenue");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }

    public void getArticles() {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
            stmt = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sqlString = "Select * from ARTICLES";
            resSet = stmt.executeQuery(sqlString);
            while (resSet.next()) {
                System.out.println(resSet.getString(2));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resSet.close();
                stmt.close();
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelVueArticles.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
        public void modifyArticles(String  codesArticles, String designationArticles, int codeCategories, double prixUnitaire) {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
            stmt = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sqlString = "Select * from Articles where Codes Articles = " + codesArticles;
            resSet = stmt.executeQuery(sqlString);
            resSet.next();
            resSet.updateString(2, designationArticles);
            resSet.updateInt(3, codeCategories);
            resSet.updateDouble(4, prixUnitaire);
            resSet.updateRow();
            System.out.println("Article successfully modified");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resSet.close();
                stmt.close();
                connect.close();
            } catch (Exception ex) {
                Logger.getLogger(ModelVueArticles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
     public void deleteArticles(String codeArticles) {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
            stmt = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sqlString = "Select * from Articles where CodeArticles = " + codeArticles;
            resSet = stmt.executeQuery(sqlString);
            resSet.next();
            resSet.deleteRow();
            System.out.println("You have properly deleted this article from the database");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resSet.close();
                stmt.close();
                connect.close();
            } catch (Exception ex) {
                Logger.getLogger(ModelVueArticles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addArticles(String  codesArticles, String designationArticles, int codeCategories, double prixUnitaire) {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
            stmt = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sqlString = "Select * from Articles ";
            resSet = stmt.executeQuery(sqlString);
            resSet.moveToInsertRow();
            resSet.updateString(1, codesArticles);
            resSet.updateString(2, designationArticles);
            resSet.updateInt(3, codeCategories);
            resSet.updateDouble(4, prixUnitaire);
            resSet.insertRow();
            System.out.println("New Article inserted into Database Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resSet.close();
                stmt.close();
                connect.close();
            } catch (Exception ex) {
                Logger.getLogger(ModelVueArticles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
