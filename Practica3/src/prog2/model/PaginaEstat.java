
package prog2.model;

/**
 * Classe que defineix la forma de les pàgines d'estat de la bitàcola. Hereta de PaginaBitacola.
 * 
 * @author claudia
 */
public class PaginaEstat extends PaginaBitacola {
    
    private float demandaPotencia; // Demanda de potència elèctrica
    private float insercioBarres; // Percentatge del grau d'inserció de les barres 
    private float outputReactor; // Output del reactor
    private float outputSistemaRefrigeracio; // Output del sistema de refrigeració
    private float outputGeneradorVapor; // Output del generador de vapor 
    private float outputTurbina; // Output de la turbina
    private float demandaSatisfeta; // Percentatge de la demanda de potència satisfera
    
    /**
     * Constructor amb paràmetres.
     * @param demandaPotencia Demanda de potència elèctrica
     * @param insercioBarres Percentatge del grau d'inserció de les barres 
     * @param outputReactor Output del reactor
     * @param outputSistemaRefrigeracio Output del sistema de refrigeració
     * @param outputGeneradorVapor Output del generador de vapor 
     * @param outputTurbina Output de la turbina
     * @param dia Dia de la pàgina
     */
     public PaginaEstat(float demandaPotencia, float insercioBarres, float outputReactor, float outputSistemaRefrigeracio, float outputGeneradorVapor, float outputTurbina, int dia) {
        super(dia);
        this.demandaPotencia = demandaPotencia;
        this.insercioBarres = insercioBarres;
        this.outputReactor = outputReactor;
        this.outputSistemaRefrigeracio = outputSistemaRefrigeracio;
        this.outputGeneradorVapor = outputGeneradorVapor;
        this.outputTurbina = outputTurbina;
        this.demandaSatisfeta = (outputTurbina / demandaPotencia) * 100;
        if (demandaSatisfeta > 100) demandaSatisfeta = 100;
    }
    
    
    /**
     * Retorna un String amb la informació de la pàgina d'estat.
     * @return String
     */
    @Override
    public String toString() {
        return "# Pàgina Estat\n"
                + "- Dia: " + this.getDia() + "\n"
                + "- Demanda de potencia: " + demandaPotencia + "\n"
                + "- Inserció Barres: " + insercioBarres + " % \n"
                + "- Output Reactor: " + outputReactor + " Graus \n"
                + "- Output Sistema de Refrigeració: " + outputSistemaRefrigeracio + " Graus \n"
                + "- Output Generador de Vapor: " + outputGeneradorVapor + " Graus \n"
                + "- Output Turbina: " + outputTurbina + " Unitats de Potència \n"
                + "- Demanda de Potència Satisfeta: " + demandaSatisfeta + " % \n";
    }
    
}
