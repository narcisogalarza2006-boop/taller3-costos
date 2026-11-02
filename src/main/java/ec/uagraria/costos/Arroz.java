package ec.uagraria.costos;

/** Cultivo de arroz; los jornales dependen de cuántos ciclos se siembran al año. */
public record Arroz(int ciclosPorAnio) implements Cultivo {

    @Override
    public String nombreComun() {
        return "Arroz (" + ciclosPorAnio + " ciclos/año)";
    }
}
