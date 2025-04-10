package dto;

public class OpcionComboDTO {
    private int id;
    private String nombre;

    public OpcionComboDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;  // Esto es lo que se verá en el JComboBox
    }
}

