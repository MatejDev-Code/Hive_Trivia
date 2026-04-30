package model;
/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/26/2026
 */

public class User {
    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        //this one is for the db to make a list out of
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        //this is for devs to insert into the table.
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
