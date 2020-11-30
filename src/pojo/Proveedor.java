package pojo;

public class Proveedor {
    private int id_prov;
    private String name_prov;
    private String doc_prov;
    private String direction_prov;
    private String correo_prov;
    private int telefono;

    public int getId_prov() {
        return id_prov;
    }

    public void setId_prov(int id_prov) {
        this.id_prov = id_prov;
    }

    public String getName_prov() {
        return name_prov;
    }

    public void setName_prov(String name_prov) {
        this.name_prov = name_prov;
    }

    public String getDoc_prov() {
        return doc_prov;
    }

    public void setDoc_prov(String doc_prov) {
        this.doc_prov = doc_prov;
    }

    public String getDirection_prov() {
        return direction_prov;
    }

    public void setDirection_prov(String direction_prov) {
        this.direction_prov = direction_prov;
    }

    public String getCorreo_prov() {
        return correo_prov;
    }

    public void setCorreo_prov(String correo_prov) {
        this.correo_prov = correo_prov;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
