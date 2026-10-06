
package prog2.model;

import java.io.Serializable;
import prog2.vista.CentralUBException;

/**
 * Classe que defineix la forma del reactor. Implementa la interfície InComponent.
 * 
 * @author claudia
 */
public class Reactor implements InComponent, Serializable {
    
    private boolean activat; // Cert si el reactor està activat, fals altrament
    private float temperatura; // Temperatura del reactos
    
    /**
     * Constructor per defecte. El reactor comença estant desactivat.
     */
    public Reactor() {
        this.activat = false;
        this.temperatura = 30; // Temperatura ambient
    }

    
    /**
     * Retorna true si el reactor està activat.
     * @return boolean
     */
    public boolean isActivat() {
        return activat;
    }

    
    /**
     * Retorna la temperatura del reactor.
     * @return float
     */
    public float getTemperatura() {
        return temperatura;
    }

    
    /** 
     * Estableix la temperatura del reactor.
     * @param temperatura Temperatura del reactor
     */
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }
    
    
    /**
     * Activa el component. 
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activa() throws CentralUBException {
        // Comprovem que no es supera la temperatura màxima de 1000 graus
        if (temperatura > 1000) throw new CentralUBException("No es pot activar el reactor.");
        
        // Activem el reactor
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
     * Revisa el component. Com a resultat de la revisió, podria podria sorgir 
     * una incidència que s'ha de registrar dins d'una pàgina d'incidències.
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa (PaginaIncidencies p) {
        // Comprovem si la temperatura supera els 1000 graus. 
        if (temperatura > 1000) {
            p.afegeixIncidencia("El reactor es va desactivar per superar la temperatura màxima");
            desactiva();
        }
    }
    
    
    /**
     * Obté el cost operatiu del component. 
     * @return float
     */
    @Override
    public float getCostOperatiu() {
        if (activat) return 30;
        else return 0;
    }
    
    
    /**
     * Calcula l'output del component donat l'input. 
     * @param input Input que rep el component.
     * @return float
     */
    @Override
    public float calculaOutput(float input) {
        if (activat) return temperatura + (100 - input) * 10;
        else return temperatura;
    }
    
}
