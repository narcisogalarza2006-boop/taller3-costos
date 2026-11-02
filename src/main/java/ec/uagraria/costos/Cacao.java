package ec.uagraria.costos;

/** Cultivo de cacao; el manejo orgánico necesita más jornales por hectárea. */
public record Cacao(String variedad, boolean organico) implements Cultivo {

    @Override
    public String nombreComun() {
        return "Cacao " + variedad;
    }
}
