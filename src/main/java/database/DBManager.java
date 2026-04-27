package database;
import User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/26/2026
 */
public  class DBManager {
    private static final String DB_URL_String = "Jdbc:SQLite:app.db";
    private Connection c;

    public DBManager() {
        try {
            c = DriverManager.getConnection(DB_URL_String);
            System.out.println("Connected to database successfully");
            createTables();

        }catch(SQLException e ){
            System.err.println("connection failed e.getMsg");
        }
    }
    public void close(){
        try{
            if (c!= null && !c.isClosed()){
                c.close();
            }
        } catch (SQLException e) {
            System.err.println("closed failed: "+ e.getMessage());
        }
    }
    private void createTables(){
        String UsersSQL = """
        CREATE TABLE IF NOT EXISTS Users(
            ID INT AUTO_INCREMENT PRIMARY KEY,
            Username VARCHAR(20) NOT NULL,
            Password VARCHAR(20) NOT NULL
         );
        """;
        String CategoriesSQL = "Create Table if not exists Categories(\n" +
                "ID serial primary key,\n" +
                "categoryName varchar(20) not null unique\n" +
                ");";
        String UserScoresSQL = "Create Table if not exists UserScores(\n" +
                "user_id int not null,\n" +
                "category_id int not null,\n" +
                "score int default 0,\n" +
                "primary key (user_id, category_id),\n" +
                "foreign key (user_id) references Users(ID),\n" +
                "foreign key (category_id) references Categories(ID)\n" +
                ");";
        String QuestionsSQL = """
        CREATE TABLE IF NOT EXISTS Questions(
            ID SERIAL PRIMARY KEY,
            question VARCHAR(200) NOT NULL,
            trueAns VARCHAR(50) NOT NULL,
            wrongAnsOne VARCHAR(50) NOT NULL,
            wrongAnsTwo VARCHAR(50),
            wrongAnsThr VARCHAR(50),
            correct int default NULl,
            category_id INT NOT NULL,
            FOREIGN KEY (category_id) REFERENCES Categories(ID) ON DELETE CASCADE
        );
        """;
        try(Statement s = c.createStatement()){
            s.execute("PRAGMA foreign_keys = ON;");//disclaimer from the author: no idea what this doies but if yt is to be trusted, it enables the use of foreign keys.
            s.execute(UsersSQL);
            s.execute(CategoriesSQL);
            s.execute(QuestionsSQL);
            s.execute(UserScoresSQL);
            System.out.println("Tables created successfully.");

        }catch(SQLException e){
            System.err.println("createTables Failded: "+ e.getMessage());
        }
    }
    public void insertUser(String uName, String pWord){
        String SQL = "INSERT INTO Users(username, password) VALUES (?,?);";
        try(PreparedStatement ps = c.prepareStatement(SQL)){
            ps.setString(1,uName);
            ps.setString(2,pWord);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String SQL = "SELECT * FROM Users;";

        try(Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(SQL)) {
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                users.add(new User(id, username, password));
            }
        } catch (SQLException e) {
            System.err.println("getAllUsers Failed: "+ e.getMessage());
        }
        return users;
    }
    public void updateUsername(int id, String newUsername) {
        String sql = "UPDATE Users SET Username = ? WHERE ID = ?";

        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, newUsername);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateUsername failed: " + e.getMessage());
        }
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM Users WHERE user_id = ?";

        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleteItem failed: " + e.getMessage());
        }
    }

}