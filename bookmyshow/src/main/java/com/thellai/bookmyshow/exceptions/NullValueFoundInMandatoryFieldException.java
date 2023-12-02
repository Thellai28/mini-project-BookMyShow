package com.thellai.bookmyshow.exceptions;

public class NullValueFoundInMandatoryFieldException extends RuntimeException{

    public NullValueFoundInMandatoryFieldException( String message ){
        super( message );
    }

}
