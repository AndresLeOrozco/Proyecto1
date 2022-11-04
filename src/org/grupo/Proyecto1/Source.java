package org.grupo.Proyecto1;

import org.grupo.Presentacion.Vista.Controlador.Controlador;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class Source {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        //cada vez que se quiera realizar una edicion, se le debe dar guardar para salvar los cambios al cerrar la ventana
        Controlador controller = new Controlador();
        controller.startApplication();
    }
}
