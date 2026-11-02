package ec.uagraria.costos;

/** Cultivo de banano; se distingue por su variedad (por ejemplo, Cavendish). */
public record Banano(String variedad) implements Cultivo {

    @Override
    public String nombreComun() {
        return "Banano " + variedad;
    }
}
