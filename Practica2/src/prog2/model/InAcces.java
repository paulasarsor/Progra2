
package prog2.model;

import prog2.vista.ExcepcioEstacio;

/**
 *
 * @author lauraigual
 */
public interface InAcces {
    
    /**
     * Afegeix una via rebuda com a paràmetre a la llista de vies de l'accés
     * @param via 
     * @throws prog2.vista.ExcepcioEstacio 
     */
    public void afegirVia(Via via) throws ExcepcioEstacio;
    
    /**
     * Canvia l'estat de l'accés a tancat
     */
    public void tancarAcces();
    
    /**
     * Canvia l'estat de l'accés a obert 
     */
    public void obrirAcces();
    
    /**
     * Retorna si l'accés permet accessibilitat o no. 
     * @return 
     */
    public boolean isAccessibilitat();
}
