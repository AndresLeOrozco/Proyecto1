package org.grupo.Datos.XMLDom;

import org.grupo.Negocio.Empleado;
import org.grupo.Negocio.ModeloTableModelSucursal;
import org.grupo.Negocio.Sucursal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class CargarXMLEmpleados {
    public static final String xmlFilePath = "Empleados.xml";

    public static HashMap<String, Empleado> LeerXML(ModeloTableModelSucursal sucursales) throws IOException, SAXException, ParserConfigurationException {
        File xmlFile = new File(xmlFilePath);
        HashMap<String, Empleado> resultado = new HashMap<String, Empleado>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        if(!xmlFile.exists())
            return new HashMap<String, Empleado>();
        Document doc = builder.parse(xmlFile);
        NodeList empleadosNodes = doc.getElementsByTagName("Empleado");
        for(int i = 0; i < empleadosNodes.getLength(); i++) {
            Node empleadosNode = empleadosNodes.item(i);
            if(empleadosNode.getNodeType() == Node.ELEMENT_NODE) {
                Element empleadoElement = (Element) empleadosNode;
                String cedula = empleadoElement.getElementsByTagName("cedula").item(0).getTextContent();
                String nombre = empleadoElement.getElementsByTagName("nombre").item(0).getTextContent();
                String telefono = empleadoElement.getElementsByTagName("telefono").item(0).getTextContent();
                String salarioBase = empleadoElement.getElementsByTagName("salario").item(0).getTextContent();
                String codigo = empleadoElement.getElementsByTagName("sucursal").item(0).getTextContent();
                Sucursal temp = (Sucursal) sucursales.getListaDeSucursales().get((String)codigo);
                Empleado empleado = new Empleado(cedula,nombre,telefono,Double.parseDouble(salarioBase),temp);
                resultado.put(cedula,empleado);
            }
        }
        return resultado;
    }

}
