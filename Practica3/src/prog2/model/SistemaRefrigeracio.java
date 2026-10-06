
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import prog2.vista.CentralUBException;

/**
 * Classe que defineix la forma del sistema de refrigeració. Implementa la interfície InComponent. 
 * 
 * @author paula
 */
public class SistemaRefrigeracio implements InComponent, Serializable {
    
    private ArrayList<BombaRefrigerant> llistaBombesRefrigerants; // Llista de les bombes refrigerants del sistema de refrigeració. 
    
    
    /**
     * Constructor per defecte. Inicialitza la llista de bombes refrigerants.
     */
    public SistemaRefrigeracio() {
        this.llistaBombesRefrigerants = new ArrayList<>();
    }

    
    /**
     * Retorna la llista de bombes refrigerants.
     * @return ArrayList d'objectes de tipus BombaRefrigerant
     */
    public ArrayList<BombaRefrigerant> getLlistaBombesRefrigerants() {
        return llistaBombesRefrigerants;
    }

    
    /**
     * Estableix la llista de bombes refrigerants.
     * @param llistaBombesRefrigerants ArrayList d'objectes de tipus BombaRefrigerant
     */
    public void setLlistaBombesRefrigerants(ArrayList<BombaRefrigerant> llistaBombesRefrigerants) {
        this.llistaBombesRefrigerants = llistaBombesRefrigerants;
    }
    
    
    /**
     * Activa les bombes del sistema de refrigeració. 
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activa() throws CentralUBException {
        // Recorrem la llista de bombes refrigerants i activem les bombes refrigerants
        Iterator<BombaRefrigerant> it = llistaBombesRefrigerants.iterator();
        while (it.hasNext()) {
            BombaRefrigerant b = it.next();
            
            b.activa(); // Podria llançar una excepció
        }
    }
      
    
    /**
     * Desactiva les bombes del sistema de refrigeració.
     */
    @Override
    public void desactiva() {
        // Recorrem la llista de bombes refrigerants i desactivem les bombes refrigerants
        Iterator<BombaRefrigerant> it = llistaBombesRefrigerants.iterator();
        while (it.hasNext()) {
            BombaRefrigerant b = it.next();
            
            b.desactiva();
        }
    }
    
    
    /**
     * Revisa les bombes del sistema de refrigeració. Com a resultat de la revisió, 
     * podria podria sorgir una incidència que s'ha de registrar dins d'una pàgina 
     * d'incidències.
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa (PaginaIncidencies p) {
        // Recorrem la llista de bombes refrigerants i revisem les bombes refrigerants. 
        Iterator<BombaRefrigerant> it = llistaBombesRefrigerants.iterator();
        while (it.hasNext()) {
            BombaRefrigerant bomba = it.next();
            
            // Cridem al mètode revisa de la classe BombaRefrigerant
            bomba.revisa(p);
        }
    }
    
    
    /**
     * Obté el cost operatiu del component. El cost operatiu depèn de si el 
     * component està activat. Si no està activat el cost és zero.
     * Si està activat, tindrà un cost de 125 per bomba.
     * @return float
     */
    @Override
    public float getCostOperatiu() {
        return 125 * comptaBombesActives();
    }
    
    
    /**
     * Calcula l'output del component donat l'input. 
     * @param input Input que rep el component.
     * @return float
     */
    @Override
    public float calculaOutput(float input) {
        return Math.min(input, 250 * comptaBombesActives());
    }
    
    
    /**
     * Afegeix una bomba refrigerant a la llista.
     * @param bomba Bomba refrigerant a afegir
     */
    public void afegirBomba(BombaRefrigerant bomba) {
        llistaBombesRefrigerants.add(bomba);
    }
    
    
    /**
     * Activa la bomba amb identificador rebut com a paràmetre.
     * @param id Id de la bomba a activar
     * @throws CentralUBException 
     */
    public void activaBomba (int id) throws CentralUBException{
        llistaBombesRefrigerants.get(id).activa();
    }
    
    
    /**
     * Desactiva la bomba amb identificador rebut com a paràmetre.
     * @param id Id de la bomba a desactivar
     */
    public void desactivaBomba (int id) {
        llistaBombesRefrigerants.get(id).desactiva();
    }
    
    
    /**
     * Mètode de suport que retorna el nombre de bombes refrigerants actives al sistema de refrigeració.
     * @return int 
     */
    private int comptaBombesActives() {
        int N = 0;
        
        // Recorrem la llista de bombes refrigerants
        Iterator<BombaRefrigerant> it = llistaBombesRefrigerants.iterator();
        while (it.hasNext()) {
            BombaRefrigerant bomba = it.next();
            
            if (bomba.getActivat()) N++;
        }
        
        return N;
    }
    
    
    /**
     * Retorna un String amb la informació de l'estat de les bombes.
     * @return String
     */
    public String mostraEstatBombes() {
        String missatge = "";
        
        // Recorrem la llista de bombes refrigerants
        Iterator<BombaRefrigerant> it = llistaBombesRefrigerants.iterator();
        while (it.hasNext()) {
            BombaRefrigerant bomba = it.next();
            
            missatge += bomba.toString() + "\n";
        }

        return missatge;
    }
}
