
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe que defineix la forma de la bitàcola.
 * 
 * @author claudia
 */
public class Bitacola implements InBitacola, Serializable {
    
    private ArrayList<PaginaBitacola> llistaPagines; // ArrayList amb les pàgines de la bitàcola
    
 
    /**
     * Constructor per defecte. Inicialitza la llista de pàgines com un ArrayList buit.
     */
    public Bitacola() {
        this.llistaPagines = new ArrayList<>();
    }
     
    
    /**
     * Afegeix una pàgina a la bitàcola.
     * @param p Pàgina de la bitàcola a inserir
     */
    @Override
    public void afegeixPagina(PaginaBitacola p) {
        llistaPagines.add(p);
    }

    
    /**
     * Obté una llista amb totes les pàgines d'incidències contingudes dins de la bitàcola.
     * @return List d'objectes de tipus PaginaIncidencies
     */
    @Override
    public List<PaginaIncidencies> getIncidencies() {
        List<PaginaIncidencies> llista = new ArrayList<>();
        
        // Recorrem la llista i anem afegint les pàgines d'incidències
        Iterator<PaginaBitacola> it = llistaPagines.iterator();
        while (it.hasNext()) {
            PaginaBitacola pag = it.next();
            if (pag instanceof PaginaIncidencies) {
                llista.add((PaginaIncidencies) pag);
            }
        }
        
        return llista;
    }
    
    
    /**
     * Retorna un String amb la informació de totes les pàgines de la bitàcola.
     * @return String 
     */
    @Override
    public String toString() {
        String missatge = "";
        
        // Recorrem la llista i anem afegint les pàgines
        Iterator<PaginaBitacola> it = llistaPagines.iterator();
        while (it.hasNext()) {
            PaginaBitacola pag = it.next();
            missatge += pag.toString() + "\n";
        }
        
        return missatge;
    }
}
