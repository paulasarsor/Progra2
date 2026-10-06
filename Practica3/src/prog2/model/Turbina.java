
package prog2.model;

import java.io.Serializable;

/**
 * Classe que defineix la forma de la turbina. Implementa la interfície InComponent. 
 * 
 * @author claudia
 */
public class Turbina implements InComponent, Serializable {

    private boolean activat; // Cert si la turbina està activada, fals altrament
    
    /**
     * Constructor per defecte. La turbina comença estant desactivada.
     */
    public Turbina() {
        this.activat = false;
    }

    
    /**
     * Retorna true si la turbina està activada.
     * @return boolean 
     */
    public boolean isActivat() {
        return activat;
    }
    
    
    /**
     * Activa el component. 
     */
    @Override
    public void activa() {
        activat = true;
    }
      
    
    /**
     * Desactiva el component.
     */
    @Override
    public void desactiva() {
        activat = false;
    }
    
    
    /**
     * Revisa el component. La turbina no genera cap incidència.
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa (PaginaIncidencies p) {
        
    }
    
    
    /**
     * Obté el cost operatiu del component. 
     * @return float 
     */
    @Override
    public float getCostOperatiu() {
        if (activat) return 20;
        else return 0;
    }
    
    
    /**
     * Calcula l'output del component donat l'input. 
     * @param input Input que rep el component.
     * @return int 
     */
    @Override
    public float calculaOutput(float input) {
        if (activat && input >= 100) return input * 2;
        else return 0;
    }
    
}
