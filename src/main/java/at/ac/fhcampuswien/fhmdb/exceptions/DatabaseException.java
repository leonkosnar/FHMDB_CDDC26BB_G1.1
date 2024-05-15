package at.ac.fhcampuswien.fhmdb.exceptions;

public class DatabaseException extends Exception{
    public DatabaseException(){
        super();
    }
    public DatabaseException(String errorMessage){
        super(errorMessage);
    }
    public DatabaseException(Throwable cause){
        super(cause);
    }
    public DatabaseException(String errorMessage, Throwable cause){
        super(errorMessage, cause);
    }
}
