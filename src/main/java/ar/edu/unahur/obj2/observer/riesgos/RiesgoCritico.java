package ar.edu.unahur.obj2.observer.riesgos;

import java.util.List;

import ar.edu.unahur.obj2.observer.Alerta;

public class RiesgoCritico implements Criterio {

    @Override
    public Double obtenerRiesgo(List<Alerta> alertasRecibidas) {
        return alertasRecibidas.getLast().getEsCritica() ? 10.00 : alertasRecibidas.getLast().getNivelRiesgo();
    }


}
