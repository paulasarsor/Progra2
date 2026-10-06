
package prog2.model;

import java.util.List;

/**
 * Interfície que defineix la bitàcola.
 * 
 * @author dortiz
 */
public interface InBitacola {
    /**
     * Afegeix una pagina a la bitàcola.
     * @param p Objecte de tipus PaginaBitacola
     */
    public void afegeixPagina(PaginaBitacola p);
    
    /**
     * Obté una llista amb totes les pàgines d'incidències contingudes dins 
     * de la bitàcola
     * @return 
     */
    public List<PaginaIncidencies> getIncidencies();
}
