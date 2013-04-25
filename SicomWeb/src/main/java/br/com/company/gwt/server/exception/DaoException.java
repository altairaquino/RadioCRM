package br.com.company.gwt.server.exception;

/**
 *
 * @author andre
 */
public class DaoException extends RuntimeException{
    private static final long serialVersionUID = 2457552904697421443L;

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException() {
    }
}
