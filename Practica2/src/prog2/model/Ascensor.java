
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author paula
 * 
 * Subclasse d'AccesDesnivell que defineix la forma d'un ascensor.
 * 
 */
public class Ascensor extends AccesDesnivell implements Serializable {
    
    /**
     * Pes màxim de la càrrega
     */
    private float pes_; 
    
    
    /**
     * Constructor amb paràmetres. Crida al constructor de la classe AccesDesnivell
     * @param pes float
     * @param desnivell float
     * @param nom String
     * @param estat boolean
     */
    public Ascensor(float pes, float desnivell, String nom, boolean estat) {
        super(desnivell, nom, estat);
        this.pes_ = pes;
    }
    
    
    /**
     * Retorna el pes màxim de la càrrega
     * @return float
     */
    public float getPes() {
        return pes_;
    }
    
    /**
     * Estableix el pes màxim de la càrrega
     * @param pes float
     */
    public void setPes(float pes) {
        this.pes_ = pes;
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
     * Retorna una cadena de text amb la informació de l'ascensor
     * @return String
     */
    @Override
    public String toString() {
        return "Ascensor " + super.toString() + ", pes màxim de càrrega = " + pes_ + " Kg";
    }
    
}