
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author claudia
 * 
 * Subclasse abstracta d’Acces que conté un nou atribut i és superclasse de tots els accessos amb desnivell.
 * 
 */
public abstract class AccesNivell extends Acces implements Serializable {
    
    /**
     * Longitud de l'accés
     */
    public float longitud_; 
    
    
    /**
     * Constructor amb paràmetres. Crida al constructor de la classe Acces
     * @param longitud float
     * @param nom String
     * @param estat boolean
     */
    public AccesNivell(float longitud, String nom, boolean estat) {
        super(nom, estat);
        this.longitud_ = longitud;
    }

    
    /**
     * Retorna la longitud de l'accés
     * @return float
     */
    public float getLongitud() {
        return longitud_;
    }

    /**
     * Estableix la longitud de l'accés
     * @param longitud float
     */
    public void setLongitud(float longitud) {
        this.longitud_ = longitud;
    }

    
    /**
     * Retorna si l'accés permet accessibilitat o no. 
     * @return boolean
     */
    @Override
    public abstract boolean isAccessibilitat();
    
    
    /**
     * Retorna una cadena de text amb la informació de l'accés
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + "longitud = " + longitud_ + " metres";
    }
    
}
