package ua.darkphantom1337.coinsapi;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySQL {
	
	 public static Connection conn;
	 public static Statement statmt;

    public static void connectToBase(String url, String dbName, String user, String pass) {
        try {
            conn = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://" + url + "/" + dbName + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=UTC", user, pass);
            statmt = conn.createStatement();
            statmt.execute(
                    "CREATE TABLE IF NOT EXISTS coinsapi_plugin (username varchar(32) PRIMARY KEY,"
                            + "level int, balance double)");
            statmt.close();
           // statmt.cancel();
            System.out.println("[CoinsAPI] [" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "] [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [INFO] -> Successful connect to DataBase!");
        } catch (Exception e) {
            Main.getInstance().isMySQL = false;
            System.out.println("[CoinsAPI] [" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "] [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [INFO] -> Error in connect to DataBase!");
            e.printStackTrace();
        }
    }
    
    public static void setBalance(String username, Double balance) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_plugin (username,balance) VALUES (?,?) ON DUPLICATE KEY UPDATE balance = ?;");
        ) {
            e.setString(1, username);
            e.setDouble(2, balance);
            e.setDouble(3, balance);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setLevel(String username, Integer level) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_plugin (username,level) VALUES (?,?) ON DUPLICATE KEY UPDATE level = ?;");
        ) {
            e.setString(1, username);
            e.setInt(2, level);
            e.setInt(3, level);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Double getBalance(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "balance" + " FROM coinsapi_plugin WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                Double balance = e.getDouble("balance");
                if (balance == null)
                    balance = 0.0;
                e.close();
                return balance;
            }
            return 0.0;
        } catch (Exception e) {

        }
        return 0.0;
    }

    public static Integer getLevel(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "level" + " FROM coinsapi_plugin WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                Integer balance = e.getInt("level");
                if (balance == null)
                    balance = 0;
                e.close();
                return balance;
            }
            return 0;
        } catch (Exception e) {

        }
        return 0;
    }

    public static void giveBalance(String username, Double amount){
        setBalance(username, getBalance(username)+amount);
    }

    public static void giveLevel(String username, Integer amount){
        setLevel(username, getLevel(username)+amount);
    }


    public static void takeBalance(String username, Double amount){
        setBalance(username, getBalance(username)-amount);
    }

    public static void takeLevel(String username, Integer amount){
        setLevel(username, getLevel(username)-amount);
    }

}
