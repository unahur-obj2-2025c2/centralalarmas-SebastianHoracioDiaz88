package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.riesgos.RiesgoPromedio;
import ar.edu.unahur.obj2.observer.riesgos.excepcion.DominioException;

public class CentralTest {

    private Central central;
    private Entidad e1;
    private Entidad e2;

    @BeforeEach

    void setUp(){
        central = new Central();
        e1 = new Entidad("hospital");
        e2 = new Entidad("bomberos");
        central.agregarObsrevador(e1);
        central.agregarObsrevador(e2);
    }

    @Test
    void dadoElSetUp_alAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo() {
    
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);

        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e1.getAlertasRecibidas().getFirst().getNivelRiesgo());
        assertEquals("lluvia", e1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e1.getAlertasRecibidas().getLast().getNivelRiesgo());

        assertEquals("calor", e2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivelRiesgo());
        assertEquals("lluvia", e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e2.getAlertasRecibidas().getLast().getNivelRiesgo());
    }

    @Test

    void dadoElSetUp_alCambiarElComportamientoYAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo () {

        

        e1.setCriterioRiesgo(new RiesgoPromedio());

        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);

        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e1.getAlertasRecibidas().getFirst().getNivelRiesgo());
        assertEquals("lluvia", e1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e1.getAlertasRecibidas().getLast().getNivelRiesgo());

        assertEquals("calor", e2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivelRiesgo());
        assertEquals("lluvia", e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e2.getAlertasRecibidas().getLast().getNivelRiesgo());

        assertEquals(7, e1.riesgo());
        assertEquals(10, e2.riesgo());

    }

    @Test

    void dadoElSetUp_alAgregarAlertasQuitarUnaEntidadYAgregaNuevaAlerta_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo () {

        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);

        central.quitarObservador(e1);

        central.emitirAlerta("granizo", 7);

        assertEquals(2, e1.cantidadDeAlertasRecibdas());
        
        assertEquals(10, e1.riesgo());

        assertEquals(3, e2.cantidadDeAlertasRecibdas());

        assertEquals(7, e2.riesgo());

        assertEquals(5, central.cantidadNotificacionesEnviadas());
        
    }

    
    @Test

    void excepcion() {
         

       // Forma 1: validar solo que se lanza la excepción
        assertThrows(DominioException.class, () -> {
            central.emitirAlerta("granizo", 11);
        });


        
    }


    }
