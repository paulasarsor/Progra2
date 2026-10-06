
package prog2.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que defineix la forma de les pàgines d'incidències de la bitàcola. Hereta de PaginaBitacola.
 * 
 * @author paula
 */
public class PaginaIncidencies extends PaginaBitacola {
    
    private ArrayList<String> llistaIncidencies; // Llista de les incidències 
    
    
    /**
     * Constructor amb paràmetres. Inicialitza la llista d'incidències com un ArrayList buit.
     * @param dia Dia de la pàgina
     */
    public PaginaIncidencies(int dia) {
        super(dia);
        this.llistaIncidencies = new ArrayList<>();
    }
    
    
    /**
     * Afegeix una incidència a la llista d'incidències.
     * @param descIncidencia Descripció de la incidència a afegir
     */
    public void afegeixIncidencia(String descIncidencia) {
        llistaIncidencies.add(descIncidencia);
    }
    
    
    /**
     * Retorna un String amb la informació de la pàgina d'incidències.
     * @return String
     */
    @Override
    public String toString() {
        String missatge = "# Pàgina Incidències \n" 
                           + "- Dia: " + this.getDia() + "\n";
        
        // Recorrem la llista d'incidències i afegim les descripcions
        Iterator<String> it = llistaIncidencies.iterator(); 
        while (it.hasNext()) {
            String in = it.next();
            missatge += "- Descripció Incidència: " + in + "\n";
        }
        
        return missatge;
    }
}
