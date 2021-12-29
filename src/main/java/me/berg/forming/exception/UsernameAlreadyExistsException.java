package me.berg.forming.exception;

import java.sql.SQLException;

public class UsernameAlreadyExistsException extends SQLException {

    public UsernameAlreadyExistsException(String msg) {
        super(msg);
    }
}
