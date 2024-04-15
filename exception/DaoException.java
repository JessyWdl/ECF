package com.jessy.entity.exception;

import java.util.logging.Level;

public class DaoException extends Exception{
    public DaoException(String Message, Level Gravite) {
        super(Message);
    }
}
