
package prog2.vista;

/**
 * 
 * @author paula
 * 
 * Classe per gestionar les excepcions de l'estació. Hereda de la classe Exception.
 * 
 */
public class ExcepcioEstacio extends Exception {
    
    /**
     * Cosntructor per defecte.
     */
    public ExcepcioEstacio () {
        super();
    }
    
    /**
     * Constructor amb paràmetres.
     * @param message String
     */
    public ExcepcioEstacio(String message) {
        super(message);
    }
}
