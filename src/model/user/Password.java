package model.user;

import java.sql.Date;

public class Password {
    private int userID;
    private String passwordHash; 
    private String passwordSalt; 
    private Date modifiedDate; 
}
