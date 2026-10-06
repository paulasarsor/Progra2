
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author claudia
 * 
 * Subclasse d'AccesDesnivell que defineix la forma d'una escala mecànica.
 * 
 */
public class EscalaMecanica extends AccesDesnivell implements Serializable {
    
    /**
     * Marca de l'escala mecànica
     */
    private String marca_; 
    
    
    /**
     * Constructor amb paràmetres. Crida al constructor de la classe AccesDesnivell
     * @param marca String
     * @param desnivell float
     * @param nom String
     * @param estat boolean
     */
    public EscalaMecanica(String marca, float desnivell, String nom, boolean estat) {
        super(desnivell, nom, estat);
        this.marca_ = marca;
    }

    
    /**
     * Retorna la marca de l'escala mecànica
     * @return String
     */
    public String getMarca() {
        return marca_;
    }

    /**
     * Estableix la marca de l'escala mecànica
     * @param marca String
     */
    public void setMarca(String marca) {
        this.marca_ = marca;
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
     * Retorna una cadena de text amb la informació de l'escala mecànica
     * @return String
     */
    @Override
    public String toString() {
        return "Escala mecànica " + super.toString() + ", marca = " + marca_;
    }
    
}
