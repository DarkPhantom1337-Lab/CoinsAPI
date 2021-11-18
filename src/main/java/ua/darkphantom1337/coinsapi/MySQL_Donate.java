package ua.darkphantom1337.coinsapi;

import com.google.common.primitives.Booleans;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQL_Donate {
	
	 public static Connection conn;
	 public static Statement statmt;

    public static void connectToBase(String url, String dbName, String user, String pass) {
        try {
            conn = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://" + url + "/" + dbName + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=UTC", user, pass);
            statmt = conn.createStatement();
            statmt.execute(
                    "CREATE TABLE IF NOT EXISTS coinsapi_donate (username varchar(32) PRIMARY KEY, "
                            + "rank varchar(99), keep int, fly int, akill int, " +
                            "rank_end_date varchar(30), keep_end_date varchar(30)," +
                            "fly_end_date varchar(30), isBuyRank int, isBuyKeep int, isBuyFly int);");
            statmt.close();
           // statmt.cancel();
            System.out.println("[CoinsAPI] [" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "] [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [INFO] -> Successful connect to DataBase!");
        } catch (Exception e) {
            Main.getInstance().isMySQL = false;
            System.out.println("[CoinsAPI] [" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "] [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [INFO] -> Error in connect to DataBase!");
            e.printStackTrace();
        }
    }
    
    public static void setRank(String username, String rank) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_donate (username,rank) VALUES (?,?) ON DUPLICATE KEY UPDATE rank = ?;");
        ) {
            e.setString(1, username);
            e.setString(2, rank);
            e.setString(3, rank);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setRankEndDate(String username, String rank_end_date) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_donate (username,rank_end_date) VALUES (?,?) ON DUPLICATE KEY UPDATE rank_end_date = ?;");
        ) {
            e.setString(1, username);
            e.setString(2, rank_end_date);
            e.setString(3, rank_end_date);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setKeepEndDate(String username, String keep_end_date) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_donate (username,keep_end_date) VALUES (?,?) ON DUPLICATE KEY UPDATE keep_end_date = ?;");
        ) {
            e.setString(1, username);
            e.setString(2, keep_end_date);
            e.setString(3, keep_end_date);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFlyEndDate(String username, String fly_end_date) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_donate (username,fly_end_date) VALUES (?,?) ON DUPLICATE KEY UPDATE fly_end_date = ?;");
        ) {
            e.setString(1, username);
            e.setString(2, fly_end_date);
            e.setString(3, fly_end_date);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setKeepInventory(String username, Boolean keep) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_donate (username,keep) VALUES (?,?) ON DUPLICATE KEY UPDATE keep = ?;");
        ) {
            e.setString(1, username);
            e.setBoolean(2, keep);
            e.setBoolean(3, keep);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAnimationForKill(String username, Boolean akill) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_donate (username,akill) VALUES (?,?) ON DUPLICATE KEY UPDATE akill = ?;");
        ) {
            e.setString(1, username);
            e.setBoolean(2, akill);
            e.setBoolean(3, akill);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setIsBuyKeep(String username, Boolean keep) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_donate (username,isBuyKeep) VALUES (?,?) ON DUPLICATE KEY UPDATE isBuyKeep = ?;");
        ) {
            e.setString(1, username);
            e.setBoolean(2, keep);
            e.setBoolean(3, keep);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setIsBuyRank(String username, Boolean rank) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_donate (username,isBuyRank) VALUES (?,?) ON DUPLICATE KEY UPDATE isBuyRank = ?;");
        ) {
            e.setString(1, username);
            e.setBoolean(2, rank);
            e.setBoolean(3, rank);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setIsBuyFly(String username, Boolean fly) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_donate (username,isBuyFly) VALUES (?,?) ON DUPLICATE KEY UPDATE isBuyFly = ?;");
        ) {
            e.setString(1, username);
            e.setBoolean(2, fly);
            e.setBoolean(3, fly);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFly(String username, Boolean fly) {
        try (PreparedStatement e = conn.prepareStatement(
                "INSERT INTO coinsapi_donate (username,fly) VALUES (?,?) ON DUPLICATE KEY UPDATE fly = ?;");
        ) {
            e.setString(1, username);
            e.setBoolean(2, fly);
            e.setBoolean(3, fly);
            e.executeUpdate();
            e.close();
            e.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getRank(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "rank" + " FROM coinsapi_donate WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                String balance = e.getString("rank");
                e.close();
                return balance == null || balance.isEmpty() ? "DarkNotSet" : balance;
            }
            return "DarkNotSet";
        } catch (Exception e) {

        }
        return "DarkNotSet";
    }

    public static String getRankEndDate(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "rank_end_date" + " FROM coinsapi_donate WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                String balance = e.getString("rank_end_date");
                e.close();
                return balance == null || balance.isEmpty() ? "DarkNotSet" : balance;
            }
            return "DarkNotSet";
        } catch (Exception e) {

        }
        return "DarkNotSet";
    }

    public static String getKeepEndDate(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "keep_end_date" + " FROM coinsapi_donate WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                String balance = e.getString("keep_end_date");
                e.close();
                return balance == null || balance.isEmpty() ? "DarkNotSet" : balance;
            }
            return "DarkNotSet";
        } catch (Exception e) {

        }
        return "DarkNotSet";
    }

    public static String getFlyEndDate(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "fly_end_date" + " FROM coinsapi_donate WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                String balance = e.getString("fly_end_date");
                e.close();
                return balance == null || balance.isEmpty() ? "DarkNotSet" : balance;
            }
            return "DarkNotSet";
        } catch (Exception e) {

        }
        return "DarkNotSet";
    }

    public static Boolean getKeepInventory(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "keep" + " FROM coinsapi_donate WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                Boolean balance = e.getBoolean("keep");
                e.close();
                return balance;
            }
            return false;
        } catch (Exception e) {

        }
        return false;
    }

    public static Boolean getAnimationForKill(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "akill" + " FROM coinsapi_donate WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                Boolean balance = e.getBoolean("akill");
                e.close();
                return balance;
            }
            return false;
        } catch (Exception e) {

        }
        return false;
    }

    public static Boolean getIsBuyKeep(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "isBuyKeep" + " FROM coinsapi_donate WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                Boolean balance = e.getBoolean("isBuyKeep");
                e.close();
                return balance;
            }
            return false;
        } catch (Exception e) {

        }
        return false;
    }

    public static Boolean getIsBuyRank(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "isBuyRank" + " FROM coinsapi_donate WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                Boolean balance = e.getBoolean("isBuyRank");
                e.close();
                return balance;
            }
            return false;
        } catch (Exception e) {

        }
        return false;
    }

    public static Boolean getIsBuyFly(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "isBuyFly" + " FROM coinsapi_donate WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                Boolean balance = e.getBoolean("isBuyFly");
                e.close();
                return balance;
            }
            return false;
        } catch (Exception e) {

        }
        return false;
    }

    public static Boolean getFly(String username) {
        try (PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT " + "fly" + " FROM coinsapi_donate WHERE username = ?;");
        ) {
            preparedStatement.setString(1, username);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                Boolean balance = e.getBoolean("fly");
                e.close();
                return balance;
            }
            return false;
        } catch (Exception e) {

        }
        return false;
    }

    public static List<String> getIsBuyRankUsers() {
        String sql = "SELECT username FROM coinsapi_donate WHERE isBuyRank = ?;";
        String result = "";
        List<String> res = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setBoolean(1, true);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("username");
                res.add(result);
                rs.close();
                break;
            }
            return res;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static List<String> getIsBuyKeepUsers() {
        String sql = "SELECT username FROM coinsapi_donate WHERE isBuyKeep = ?;";
        String result = "";
        List<String> res = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setBoolean(1, true);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("username");
                res.add(result);
                rs.close();
                break;
            }
            return res;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static List<String> getIsBuyFlyUsers() {
        String sql = "SELECT username FROM coinsapi_donate WHERE isBuyFly = ?;";
        String result = "";
        List<String> res = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setBoolean(1, true);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("username");
                res.add(result);
                rs.close();
                break;
            }
            return res;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
