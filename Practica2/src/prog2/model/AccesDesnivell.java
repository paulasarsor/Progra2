
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author paula
 *
 *
 * Subclasse abstracta d'Acces que conté un nou atribut i és superclasse de tots els accessos amb desnivell.
 * 
 */
public abstract class AccesDesnivell extends Acces implements Serializable {
    
    /**
     * Alçada del desnivell
     */
    private float desnivell_; 
    
    
    /**
     * Constructor amb paràmetres. Crida al constructor de la classe Acces
     * @param desnivell float
     * @param nom String
     * @param estat boolean
     */
    public AccesDesnivell(float desnivell, String nom, boolean estat) {
        super(nom, estat);
        this.desnivell_ = desnivell;
    }

    
    /**
     * Retorna l'alçada del desnivell
     * @return float
     */
    public float getDesnivell() {
        return desnivell_;
    }

    /**
     * Estableix l'alçada del desnivell
     * @param desnivell float
     */
    public void setDesnivell(float desnivell) {
        this.desnivell_ = desnivell;
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
        return super.toString() + "alçada de desnivell = " + desnivell_ + " m";
    }

}
