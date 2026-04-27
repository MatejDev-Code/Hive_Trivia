import java.sql.Connection;

public  class DBManager {
    private static final String DB_URL_String = "Jdbc:SQLite:app.db";
    private Connection c;

    public DBManager() {
        try {
            c = DriverManager.getConnection(DB_URL_String);
            System.out.println("Connected to database successfully");
            createTables();

        }catch( SQLException e ){
            System.err.println("connection failed e.getMsg");
        }
    }
    public void close(){
        try{
            if (connection!= null && !connect.isClosed()){
                connection.close();
            }
        }
    }
    private void createTables(){
        String UsersSQL = "Create Table if onot exists Users(\n" +
                "ID serial primary key\n" +
                "Username varchar(20) not null\n" +
                "Password varchar(20) not null\n" +
                ");";
        String CategoriesSQL = ""
    }

}