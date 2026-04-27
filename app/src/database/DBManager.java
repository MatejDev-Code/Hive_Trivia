/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/26/2026
 */
import java.sql.Connection;

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
            if (c!= null && !connect.isClosed()){
                c.close();
            }
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

}