
package prog2.model;

import java.io.Serializable;
import prog2.vista.CentralUBException;

/**
 * Classe que defineix la forma de les bombes refrigerants. Implementa la interfície InBombaRefrigerant.
 * 
 * @author paula
 */
public class BombaRefrigerant implements InBombaRefrigerant, Serializable {

    private int id; // Identificador numèric
    private boolean activat; // Cert si la bomba està activa, fals altrament
    private boolean foraDeServei; // Cert si la bomba està fora de servei, fals altrament
    private VariableUniforme variableUniforme; 
    
    /**
     * Constructor amb paràmetres. Per defecte, la bomba està desactivada.
     * @param id Identificador de la bomba refrigerant
     * @param var VariableUniforme
     */
    public BombaRefrigerant(VariableUniforme var, int id) {
        this.id = id;
        this.variableUniforme = var;
        this.activat = false;
        this.foraDeServei = false;
    }
    
    
    /**
     * Retorna l'identificador numèric de la bomba refrigerant.
     * @return int 
     */
    @Override
    public int getId() {
        return id;
    }

    
    /**
     * Retorna true si la bomba refrigerant està activada.
     * @return boolean 
     */
    @Override
    public boolean getActivat() {
        return activat;
    }
    
    
    /**
     * Retorna true si la bomba refrigerant està fora de servei.
     * @return boolean
     */
    @Override
    public boolean getForaDeServei() {
        return foraDeServei;
    }
    
    
    /**
     * Activa la bomba refrigerant. El mètode llançarà una excepció si la bomba 
     * està fora de servei i en aquest cas la bomba no es podrà activar.
     * @throws CentralUBException 
     */
    @Override
    public void activa() throws CentralUBException {
        // Comprovem que la bomba no està fora de servei
        if (foraDeServei) throw new CentralUBException("La bomba refrigerant " + id 
                                                        + " està fora de servei i no es pot activar.");
        
        // Activem la bomba
        activat = true;
    }

    
    /**
     * Desactiva la bomba refrigerant.
     */
    @Override
    public void desactiva() {
        activat = false;
    }


    /**
     * Revisa la bomba refrigerant. Es farà servir l'objecte de tipus VariableUniforme per determinar si la bomba es queda 
     * fora de servei. En cas afirmatiu, s'haurà de registrar la situació dins d'una pàgina d'incidències.
     * @param p Objecte de tipus PaginaIncidencies per a registrar si la bomba es queda fora de servei.
     */
    @Override
    public void revisa(PaginaIncidencies p) {
        // Crida al mètode seguentValor de VariableUniforme per a determinar, amb una probabilitat del 20% si la bomba es 
        // queda fora de servei durant el dia següent.
        int valor = variableUniforme.seguentValor(); // Ho interpretem com un percentatge

        if (valor < 20) {
            // Queda fora de servei
            foraDeServei = true;
            p.afegeixIncidencia("La bomba refrig. " + id + " està fora de servei");
        }
        
        // Desactivem la bomba si escau
        if (foraDeServei) desactiva();
    }

    
    /**
     * Retorna un String amb la informació de la bomba.
     * @return String 
     */
    @Override
    public String toString() {
        return "Id=" + id + ", Activat=" + activat + ", Fora de servei=" + foraDeServei;
    }
    
}
