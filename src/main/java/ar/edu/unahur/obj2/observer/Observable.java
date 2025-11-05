package ar.edu.unahur.obj2.observer;

public interface Observable {

    void agregarObsrevador(Observador observador);

    void quitarObservador(Observador observador);

    void notificar(Alerta alerta);

}
