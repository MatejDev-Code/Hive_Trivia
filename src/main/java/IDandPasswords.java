import java.util.HashMap;

/**
 IDandPasswords.java
 * Contains hashMap of userID and password combinations
 @author Matteo Ristoski
 @version 0.1.0
 @since 4/22/2026
 */

public class IDandPasswords {

    HashMap<String, String> loginInfo = new HashMap<String, String>();

    IDandPasswords(){

        loginInfo.put("John", "password123");
        loginInfo.put("Tom", "SafePassword");
        loginInfo.put("Jerry", "abc123");

    }

    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(HashMap<String, String> loginInfo) {
        this.loginInfo = loginInfo;
    }
}
