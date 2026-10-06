
package prog2.model;

import java.io.Serializable;
import java.util.List;
import prog2.vista.CentralUBException;

/**
 * Classe principal del paquet model que conté i gestiona totes les dades de la central. Implementa la interfície InDades.
 * 
 * @author dortiz
 */
public class Dades implements InDades, Serializable {
    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 200;
    
    private VariableUniforme variableUniforme;
    private float insercioBarres;
    private Reactor reactor;
    private SistemaRefrigeracio sistemaRefrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;
    private Bitacola bitacola;
    private int dia;
    private float guanysAcumulats;

    
    /**
     * Constructor per defecte. Inicialitza les dades.
     */
    public Dades() {
        // Inicialitza atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;
        
        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);
        
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);
    }
    
    
    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a 
     * partir de la demanda de potència actual. Aquesta pàgina econòmica inclou 
     * beneficis, penalització per excès de potència, costos operatius i 
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia) {
        float potencia = calculaPotencia(), beneficis, penalitzacio = 0, costOperatiu;
        
        //Calculem els beneficis i la penalització per excés de potència
        if (potencia <= demandaPotencia) {
            beneficis = potencia * PREU_UNITAT_POTENCIA;
        } else {
            beneficis = demandaPotencia * PREU_UNITAT_POTENCIA;
            penalitzacio = PENALITZACIO_EXCES_POTENCIA;
        }
        
        //Calculem el cost operatiu
        costOperatiu = reactor.getCostOperatiu() + sistemaRefrigeracio.getCostOperatiu() 
                        + generadorVapor.getCostOperatiu() + turbina.getCostOperatiu();
        
        //Calculem els guanys acumulats
        guanysAcumulats += beneficis - penalitzacio - costOperatiu ;
        
        // Creem una pàgina econòmica amb aquesta informació
        PaginaEconomica pagina = new PaginaEconomica(beneficis, penalitzacio, costOperatiu, guanysAcumulats, dia);
        
        return pagina;
    }
    
    
    /**
     * Actualitza l'estat de la central. El mètode ha de establir la nova
     * temperatura del reactor i revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void actualitzaEstatCentral(PaginaIncidencies paginaIncidencies) {
        // Actualitzem la temperatura del reactor
        float outputReactor = reactor.calculaOutput(insercioBarres);
        float outputSistemaRefrigeracio = sistemaRefrigeracio.calculaOutput(outputReactor);
        float temperatura = outputReactor - outputSistemaRefrigeracio;
        
        // Comprovem que la temperatura és correcte
        if (temperatura < 30) {
            reactor.setTemperatura(30);
        } else {
            reactor.setTemperatura(temperatura);
        }
        
        // Revisem els components
        reactor.revisa(paginaIncidencies);
        sistemaRefrigeracio.revisa(paginaIncidencies);
        generadorVapor.revisa(paginaIncidencies);
        turbina.revisa(paginaIncidencies);
    }
    
    
    /**
     * Duu a terme les accions relacionades amb la finalització d'un dia. Per això 
     * és necessari coneixer la demanda de potència actual per al dia en curs.
     * @param demandaPotencia Demanda de potència actual de la central.
     * @return Bitacola Bitàcola del dia actual.
     */
    @Override
    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);
        
        // Genera pàgina d'estat
        PaginaEstat paginaEstat = mostraEstat(demandaPotencia);

        // Actualitza estat central i registra incidències
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        actualitzaEstatCentral(paginaIncidencies);
        

        // Incrementa dia
        dia += 1;
        
        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);
        
        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }
    
    
    /**
     * Retorna el grau d'inserció de les barres de control en percentatge.
     * @return float 
     */
    @Override
    public float getInsercioBarres() {
        return insercioBarres;
    } 
    
    /**
     * Estableix el grau d'inserció de les barres de control en percentatge. 
     * Llança una excepció si el grau és incorrecte.
     * @param insercioBarres Percentatge d'inserció de les barres de control.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        // Comprovem que el grau és un percentatge
        if (insercioBarres < 0 || insercioBarres > 100) {
            throw new CentralUBException("El grau d'inserció de les barres de control és incorrecte.");
        }
        
        this.insercioBarres = insercioBarres;
    }
     
    /**
     * Activa el reactor de la central.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activaReactor() throws CentralUBException {
        reactor.activa();
    }

    /**
     * Desactiva el reactor de la central.
     */
    @Override
    public void desactivaReactor() {
        reactor.desactiva();
    }
    
    /**
     * Retorna l'objecte que contè el reactor de la central.
     * @return Reactor
     */
    @Override
    public Reactor mostraReactor() {
        return reactor;
    }
    
    /**
     * Activa una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activaBomba(int id) throws CentralUBException {
        sistemaRefrigeracio.activaBomba(id);
    }
    
    /**
     * Desactiva una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     */
    @Override
    public void desactivaBomba(int id) {
        sistemaRefrigeracio.desactivaBomba(id);
    }
    
    /**
     * Retorna l'objecte que contè el sistema de refrigeració de la central.
     * @return SistemaRefrigeracio
     */
    @Override
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }
    
    /**
     * Retorna la potència generada per la central. Aquesta potència és 
     * l'output de la turbina. 
     * @return float
     */
    @Override
    public float calculaPotencia() {
        float potencia = insercioBarres;
        
        potencia = reactor.calculaOutput(potencia);
        potencia = sistemaRefrigeracio.calculaOutput(potencia);
        potencia = generadorVapor.calculaOutput(potencia);
        potencia = turbina.calculaOutput(potencia);
        
        return potencia;
    }
    
    /**
     * Retorna una pàgina de estat per a la configuració actual de la central. 
     * Amb aquest propòsit és necessari coneixer la demanda de potència actual.
     * @param demandaPotencia Demanda de potència actual.
     * @return PaginaEstat
     */
    @Override
    public PaginaEstat mostraEstat(float demandaPotencia) {
        float outputReactor, outputSistemaRefrigeracio, outputGeneradorVapor, outputTurbina; 
        
        // Calculem els outputs
        outputReactor = reactor.calculaOutput(insercioBarres);
        outputSistemaRefrigeracio = sistemaRefrigeracio.calculaOutput(outputReactor);
        outputGeneradorVapor = generadorVapor.calculaOutput(outputSistemaRefrigeracio);
        outputTurbina = turbina.calculaOutput(outputGeneradorVapor);
        
        // Creem una pàgina d'estat amb aquesta informació
        PaginaEstat pagina = new PaginaEstat(demandaPotencia, insercioBarres, outputReactor, outputSistemaRefrigeracio, outputGeneradorVapor, outputTurbina , dia);
       
        return pagina;
    }
      
    /**
     * Retorna la bitàcola de la central.
     * @return Bitacola
     */
    @Override
    public Bitacola mostraBitacola() {
        return bitacola;
    }
    
    /**
     * Retorna una llista amb totes les pàgines d'incidències de la bitàcola de la central.
     * @return List d'objectes de tipus PaginaIncidencies
     */
    @Override
    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();
    }
}
