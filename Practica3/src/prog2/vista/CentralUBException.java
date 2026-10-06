
package prog2.vista;

/**
 * Classe per a gestionar les excepcions de la central. Hereda de la classe Exception.
 * 
 * @author claudia
 */
public class CentralUBException extends Exception {
    
    /**
     * Constructor per defecte.
     */
    public CentralUBException() {
        super();
    }
    
    /**
     * Constructor amb paràmetres.
     * @param message String
     */
    public CentralUBException(String message) {
        super(message);
    }
    
}
