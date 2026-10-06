
package prog2.model;

/**
 * Classe que defineix la forma de les pàgines econòmiques de la bitàcola. Hereta de PaginaBitacola.
 * 
 * @author paula
 */
public class PaginaEconomica extends PaginaBitacola {
    
    private float beneficis; // Beneficis del dia
    private float penalitzacio; // Penalització en cas d'excedir la demanda de potencia
    private float costOperatiu; // Cost operatiu total dels components
    private float guanysAcumulats; // Guanys acumulats 

    
    /**
     * Constructor amb paràmetres.
     * @param beneficis Beneficis del dia
     * @param penalitzacio Penalització en cas d'excedir la demanda de potencia
     * @param costOperatiu Cost operatiu total dels components
     * @param guanysAcumulats Guanys acumulats 
     * @param dia Dia de la pàgina
     */
    public PaginaEconomica(float beneficis, float penalitzacio, float costOperatiu, float guanysAcumulats, int dia) {
        super(dia);
        this.beneficis = beneficis;
        this.penalitzacio = penalitzacio;
        this.costOperatiu = costOperatiu;
        this.guanysAcumulats = guanysAcumulats;
    }
    
    
    /**
     * Retorna un String amb la informació de la pàgina econòmica.
     * @return String
     */
    @Override
    public String toString() {
        return "# Pàgina Econòmica \n" 
                + "- Dia: " + this.getDia() + "\n"
                + "- Beneficis: " + beneficis + " Unitats Econòmiques \n"
                + "- Penalitzacó Excés Producció: " + penalitzacio + " Unitats Econòmiques \n"
                + "- Cost Operatiu: " + costOperatiu + " Unitats Econòmiques \n"
                + "- Guanys acumulats: " + guanysAcumulats + " Unitats Econòmiques \n";

    }
}
