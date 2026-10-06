
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author claudia
 * 
 * Subclasse d'AccesDesnivell que defineix la forma d'una escala.
 * 
 */
public class Escala extends AccesDesnivell implements Serializable {
    
    /**
     * Constructor amb paràmetres. Crida al constructor de la classe AccesDesnivell
     * @param desnivell float
     * @param nom String
     * @param estat boolean
     */
    public Escala (float desnivell, String nom, boolean estat) {
        super(desnivell, nom, estat);
    }
    
    
    /**
     * Retorna true si l'accés és accessible, i false si no ho és.
     * @return boolean
     */
    @Override
    public boolean isAccessibilitat() {
        return false;
    }

    
    /**
     * Retorna una cadena de text amb la informació de l'escala
     * @return String
     */
    @Override
    public String toString() {
        return "Escala " + super.toString();
    }
    
    
}
