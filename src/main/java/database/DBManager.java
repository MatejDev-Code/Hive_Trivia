package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.User;

/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/26/2026
 */
public class DBManager {
    private static final String DB_URL_STRING = "jdbc:sqlite:app.db";
    private Connection connection;

    public DBManager() {
        try {
            connection = DriverManager.getConnection(DB_URL_STRING);
            System.out.println("Connected to database successfully");
            createTables();

        }catch(SQLException e ){
            System.err.println("connection failed: " + e.getMessage());
        }
    }
    public void close(){
        try{
            if (connection!= null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("closed failed: "+ e.getMessage());
        }
    }
    private void createTables(){
        String UsersSQL = """
        CREATE TABLE IF NOT EXISTS Users(
            ID INTEGER PRIMARY KEY AUTOINCREMENT,
            username VARCHAR(20) NOT NULL,
            password VARCHAR(20) NOT NULL
         );
        """;
        String CategoriesSQL = """
        CREATE TABLE IF NOT EXISTS Categories(
            ID INTEGER PRIMARY KEY AUTOINCREMENT,
            categoryName TEXT NOT NULL UNIQUE
        );
        """;
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
            ID INTEGER PRIMARY KEY AUTOINCREMENT,
            question VARCHAR(200) NOT NULL,
            trueAns VARCHAR(50) NOT NULL,
            wrongAnsOne VARCHAR(50) NOT NULL,
            wrongAnsTwo VARCHAR(50),
            wrongAnsThr VARCHAR(50),
            correct int default NULL,
            category_id INT NOT NULL,
            FOREIGN KEY (category_id) REFERENCES Categories(ID) ON DELETE CASCADE
        );
        """;
        try(Statement s = connection.createStatement()){
            s.execute("PRAGMA foreign_keys = ON;");//disclaimer from the author: no idea what this doies but if yt is to be trusted, it enables the use of foreign keys.
            s.execute(UsersSQL);
            System.out.println("users created successfully");
            s.execute(CategoriesSQL);
            System.out.println("Categories Executed successfully");
            s.execute(QuestionsSQL);
            System.out.println("Questions created successfully");
            s.execute(UserScoresSQL);
            System.out.println("UserScores created successfully.");

        }catch(SQLException e){
            System.err.println("createTables Failded: "+ e.getMessage());
        }
    }
    public void insertUser(User user){
        String SQL = "INSERT INTO Users(username, password) VALUES (?,?);";
        try(PreparedStatement ps = connection.prepareStatement(SQL)){
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("insertUser Failed: "+ e.getMessage());
        }
    }
    public int getCategory(String categoryName) {
        String selectSQL = "SELECT ID FROM Categories WHERE categoryName = ?";
        String insertSQL = "INSERT OR IGNORE INTO Categories (categoryName) VALUES (?)";

        try (
                PreparedStatement selectStmt = connection.prepareStatement(selectSQL);
                PreparedStatement insertStmt = connection.prepareStatement(insertSQL)
        ) {
            // 1. Try to find existing category
            selectStmt.setString(1, categoryName);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("ID");
            }

            // 2. Not found → try inserting (IGNORE avoids crash if duplicate)
            insertStmt.setString(1, categoryName);
            insertStmt.executeUpdate();

            // 3. Re-select to get ID
            ResultSet rs2 = selectStmt.executeQuery();
            if (rs2.next()) {
                return rs2.getInt("ID");
            }

        } catch (SQLException e) {
            System.err.println("getOrCreateCategory failed: " + e.getMessage());
        }

        return -1;
    }
    public void insertQuestion(Question q) {
        String[] wrongAnswers = q.getWrongans();

        if (wrongAnswers == null || wrongAnswers.length < 3) {
            throw new IllegalArgumentException("Question must have 3 wrong answers.");
        }

        String findCategorySQL = "SELECT ID FROM Categories WHERE categoryName = ?";
        String insertSQL = """
        INSERT INTO Questions (question, trueAns, wrongAnsOne wrongAnsTwo, wrongAnsThr, correct, category_id) 
        VALUES (?, ?, ?, ?, ?, ?, ?);
        """;

        try (PreparedStatement findStmt = connection.prepareStatement(findCategorySQL)) {
            findStmt.setString(1, q.getCategory());

            try (ResultSet rs = findStmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Category not found: " + q.getCategory());
                }
                int categoryId = rs.getInt("ID");
                try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                    insertStmt.setString(1, q.getQuestion());
                    insertStmt.setString(2, q.getCorrectAns());
                    insertStmt.setString(3, wrongAnswers[0]);
                    insertStmt.setString(4, wrongAnswers[1]);
                    insertStmt.setString(5, wrongAnswers[2]);
                    insertStmt.setNull(6, java.sql.Types.INTEGER); // correct
                    insertStmt.setInt(7, categoryId);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("insertQuestion failed: " + e.getMessage());
        }
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String SQL = "SELECT * FROM Users;";

        try(Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(SQL)) {
            while (rs.next()) {
                int id = rs.getInt("ID");
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
        String sql = "UPDATE Users SET username = ? WHERE ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newUsername);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateUsername failed: " + e.getMessage());
        }
    }
    public void updatePassword(int id, String newPass) {
        String sql = "UPDATE Users SET password = ? WHERE ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newPass);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateUsername failed: " + e.getMessage());
        }
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM Users WHERE ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleteItem failed: " + e.getMessage());
        }
    }

}