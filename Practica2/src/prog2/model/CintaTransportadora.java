
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author paula
 * 
 * Subclasse d'AccesNivell que defineix la forma d'una cinta transportadora.
 * 
 */
public class CintaTransportadora extends AccesNivell implements Serializable {
    
    /**
     * Velocitat de la cinta transportadora
     */
    private float velocitat_; 
    
    
    /**
     * Constructor amb paràmetres. Crida al constructor de la classe AccesNivell
     * @param velocitat float
     * @param longitud float
     * @param nom String
     * @param estat boolean
     */
    public CintaTransportadora(float velocitat, float longitud, String nom, boolean estat) {
        super(longitud, nom, estat);
        this.velocitat_ = velocitat;
    }

    
    /**
     * Retorna la velocitat de la cinta
     * @return float
     */
    public float getVelocitat() {
        return velocitat_;
    }

    /**
     * Estableix la velocitat de la cinta
     * @param velocitat float
     */
    public void setVelocitat(float velocitat) {
        this.velocitat_ = velocitat;
    }
    
    
    /**
     * Retorna si l'accés permet accessibilitat o no. 
     * @return boolean
     */
    @Override
    public boolean isAccessibilitat() {
        return false;
    }
    
    
    /**
     * Retorna una cadena de text amb la informació de la cinta
     * @return String
     */
    @Override
    public String toString() {
        return "Cinta transportadora " + super.toString() + ", velocitat = " + velocitat_ + " m/s";
    }
    
}
     