package org.grupo.Negocio;

import org.grupo.Negocio.Coordenada;

public class Sucursal {
    private String codigo;
    private String nombreCorto;
    private String direccion;

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String distrito) {
        Distrito = distrito;
    }

    private String Distrito;

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    private String Provincia;

    public String getCanton() {
        return Canton;
    }

    public void setCanton(String canton) {
        Canton = canton;
    }

    private String Canton;
    private double porcentajeDeZonaje;
    private Coordenada coordenada;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPorcentajeDeZonaje() {
        return porcentajeDeZonaje;
    }

    public void setPorcentajeDeZonaje(double porcentajeDeZonaje) {
        this.porcentajeDeZonaje = porcentajeDeZonaje;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Sucursal() {
        this.codigo = " ";
        this.nombreCorto = " ";
        this.direccion = " ";
        this.porcentajeDeZonaje = 0.0;
        this.Canton = " ";
        this.Distrito = " ";
        this.Provincia = " ";
        this.coordenada = new Coordenada();
    }

    public Sucursal(String codigo, String nombreCorto, String direccion, Coordenada coordenada,String Can,String Dis,String Pro) {
        this.codigo = codigo;
        this.nombreCorto = nombreCorto;
        this.direccion = direccion;
        this.porcentajeDeZonaje = 0.0;
        this.coordenada = coordenada;
        this.Provincia = Pro;
        this.Canton = Can;
        this.Distrito = Dis;
    }

    @Override
    public String toString() {
        return getNombreCorto();
    }

}