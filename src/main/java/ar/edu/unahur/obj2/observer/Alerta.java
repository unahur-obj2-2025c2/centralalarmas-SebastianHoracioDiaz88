package ar.edu.unahur.obj2.observer;

public class Alerta {

    private String tipo;
    private Integer nivelRiesgo;
    

    public Alerta(String tipo, Integer nivelRiesgo) {
        this.tipo = tipo;
        this.nivelRiesgo = nivelRiesgo;        
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getNivelRiesgo() {
        return nivelRiesgo;
    }

    public Boolean getEsCritica() {
        return Boolean.valueOf(nivelRiesgo >= 8);
    }  
    

}
