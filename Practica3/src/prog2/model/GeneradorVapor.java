
package prog2.model;

import java.io.Serializable;

/**
 * Classe que defineix la forma del generador de vapor. Implementa la interfície InComponent.
 * 
 * @author paula
 */
public class GeneradorVapor implements InComponent, Serializable {
    
    private boolean activat; // Cert si el generador està actiu, fals altrament
    
    /**
     * Constructor per defecte. El generador comença estant desactivat.
     */
    public GeneradorVapor() {
        this.activat = false;
    }

    
    /**
     * Retorna true si el generador de vapor està activat.
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
     * Revisa el component. El generador de vapor no genera cap incidència.
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
        if (activat) return 25;
        else return 0;
    }
    
    
    /**
     * Calcula l'output del component donat l'input. 
     * @param input Input que rep el component.
     * @return float
     */
    @Override
    public float calculaOutput(float input) {
        if (activat) return (float) (input * 0.8);
        else return 30;
    }
    
}
