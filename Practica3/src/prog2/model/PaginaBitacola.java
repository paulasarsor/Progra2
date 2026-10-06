
package prog2.model;

import java.io.Serializable;

/**
 * Classe abstracta que fa de classe mare de totes les pàgines de la bitàcola. 
 * 
 * @author claudia
 */
public abstract class PaginaBitacola implements Serializable {
    
    private int dia; // Dia al que es refereix la pàgina

    
    /**
     * Constructor amb paràmetres.
     * @param dia 
     */
    public PaginaBitacola(int dia) {
        this.dia = dia;
    }

    
    /**
     * Retorna el dia de la pàgina de la bitàcola.
     * @return int
     */
    public int getDia() {
        return dia;
    }

    
    /**
     * Estableix el dia de la pàgina de la bitàcola.
     * @param dia Dia al que es refereix la pàgina
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

}
