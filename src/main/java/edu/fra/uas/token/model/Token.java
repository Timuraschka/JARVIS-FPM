package edu.fra.uas.token.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Token {
    private long userID;

    private UUID token;

    private LocalDateTime dateTime;

    public Token(long userID){
        this.userID= userID;
        this.token = UUID.randomUUID();
        this.dateTime = LocalDateTime.now();
    }

    public UUID getToken(){
        return this.token;
    }

    public long getUserID() {
        return userID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


}
