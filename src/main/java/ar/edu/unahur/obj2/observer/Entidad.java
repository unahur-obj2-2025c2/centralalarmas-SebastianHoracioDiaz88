package ar.edu.unahur.obj2.observer;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.observer.riesgos.Criterio;
import ar.edu.unahur.obj2.observer.riesgos.RiesgoCritico;

public class Entidad implements Observador {

    private String nombre;
    private List<Alerta> alertasRecibidas = new ArrayList<>();
    private Criterio criterioRiesgo = new RiesgoCritico();

    
    public Entidad(String nombre) {
        this.nombre = nombre;
    }

  
    public List<Alerta> getAlertasRecibidas() {
        return alertasRecibidas;
    }

   
    public Criterio getCriterioRiesgo() {
        return criterioRiesgo;
    }



    public void setCriterioRiesgo(Criterio criterioRiesgo) {
        this.criterioRiesgo = criterioRiesgo;
    }



    @Override
    public void actualizacion(Alerta alerta) {
        alertasRecibidas.add(alerta);
    }


    public Double riesgo() {
      return alertasRecibidas.isEmpty() ? 0.00 : criterioRiesgo.obtenerRiesgo(alertasRecibidas);
    }

    public Integer cantidadDeAlertasRecibdas() {
        return alertasRecibidas.size();
    }

}
