package org.grupo.Negocio;

public class Imagen {
    private String path;
    private Sucursal sucur;
    boolean activo;

    public Imagen() {
        this.path = " ";
        this.sucur = new Sucursal();
        this.activo = false;
    }

    public Imagen(String path, Sucursal sucur, boolean activo) {
        this.path = path;
        this.sucur = sucur;
        this.activo = activo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Sucursal getSucur() {
        return sucur;
    }

    public void setSucur(Sucursal sucur) {
        this.sucur = sucur;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String toString() {
        return sucur.getNombreCorto() + " " + sucur.getDireccion();
    }
}
