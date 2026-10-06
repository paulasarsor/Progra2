
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author paula
 * 
 * Subclasse d'AccesNivell que defineix la forma d'un passadís. 
 * 
 */
public class Passadis extends AccesNivell implements Serializable {
    
    /**
     * Constructor amb paràmetres. Crida al constructor de la classe AccesNivell
     * @param longitud float
     * @param nom String
     * @param estat boolean
     */
    public Passadis(float longitud, String nom, boolean estat) {
        super(longitud, nom, estat);
    }
    
    
    /**
     * Retorna si l'accés permet accessibilitat o no. 
     * @return boolean
     */
    @Override
    public boolean isAccessibilitat() {
        return true;
    }
    
    
    /**
     * Retorna una cadena de text amb la informació del passadís
     * @return String
     */
    @Override
    public String toString() {
        return "Passadís " + super.toString();
    }
    
}