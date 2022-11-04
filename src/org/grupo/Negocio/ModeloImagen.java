package org.grupo.Negocio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ModeloImagen {
    private LinkedList<Imagen> listaDeImagenes;

    public ModeloImagen(LinkedList<Imagen> listaDeImagenes) {
        this.listaDeImagenes = listaDeImagenes;
    }

    public LinkedList<Imagen> getListaDeImagenes() {
        return listaDeImagenes;
    }

    public ModeloImagen() {
        this.listaDeImagenes = new LinkedList<>();
    }

    public void addImagen(Imagen i) {
        listaDeImagenes.add(i);
    }

    public void eliminarImagen(Imagen i) {
        listaDeImagenes.remove(i);
    }

    public Sucursal buscarSucursalXY(int x, int y) {
        for (Imagen image : this.listaDeImagenes) {
            if (image.getSucur().getCoordenada().getX() == x && image.getSucur().getCoordenada().getX() == y) {
                return image.getSucur();
            }
        }
        return new Sucursal();
    }

    public LinkedList<Imagen> CargaImagenes(HashMap<String, Sucursal> macheo) {
        for (Map.Entry<String, Sucursal> suc : macheo.entrySet()) {
            listaDeImagenes.add(new Imagen("/IconoPlace.png", suc.getValue(), false));
        }
        return listaDeImagenes;
    }

    public void setListaDeImagenes(LinkedList<Imagen> listaDeImagenes) {
        this.listaDeImagenes = listaDeImagenes;
    }

}
