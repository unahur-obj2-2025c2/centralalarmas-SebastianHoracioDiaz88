package ar.edu.unahur.obj2.observer;


import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.Set;

import ar.edu.unahur.obj2.observer.excepcion.DominioException;

public class Central implements Observable{

    private Set<Observador> observadores = new HashSet<>();
    private Map<Alerta,Integer> registroAlertas = new HashMap<>();

    public void emitirAlerta (String tipo, Integer nivelRiesgo) {
        if (nivelRiesgo < 1 || nivelRiesgo > 10) {
            throw new DominioException("nivel de Alerta Incorrecto");
        }
        
        Alerta alerta = new Alerta(tipo,nivelRiesgo);
        registroAlertas.put(alerta,observadores.size());
        notificar(alerta);
    }

    @Override
    public void agregarObsrevador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void quitarObservador(Observador observador) {
       observadores.remove(observador);
    }

    @Override
    public void notificar(Alerta alerta) {
        observadores.stream().forEach(o->o.actualizacion(alerta));;
    }

    public Integer cantidadNotificacionesEnviadas(){
        return registroAlertas.values().stream().mapToInt(Integer::intValue).sum();
    }
 
}
