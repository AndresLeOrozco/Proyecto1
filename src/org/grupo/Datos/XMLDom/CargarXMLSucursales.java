package org.grupo.Datos.XMLDom;

import org.grupo.Negocio.Coordenada;
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

public class CargarXMLSucursales {
    public static final String xmlFilePath = "Sucursales.xml";

    public static HashMap<String, Sucursal> LeerXML() throws IOException, SAXException, ParserConfigurationException {
        File xmlFile = new File(xmlFilePath);
        HashMap<String,Sucursal> resultado = new HashMap<String,Sucursal>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        if(!xmlFile.exists())
            return new HashMap<String, Sucursal>();
        Document doc = builder.parse(xmlFile);
        NodeList sucursalNodes = doc.getElementsByTagName("Sucursal");
        for(int i = 0; i < sucursalNodes.getLength(); i++)
        {
            Node sucursalNode = sucursalNodes.item(i);
            if(sucursalNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element surcursalElement = (Element) sucursalNode;
                String codigo = surcursalElement.getElementsByTagName("codigo").item(0).getTextContent();
                String nombreCorto = surcursalElement.getElementsByTagName("nombreCorto").item(0).getTextContent();
                String direccion = surcursalElement.getElementsByTagName("direccion").item(0).getTextContent();
                String zonaje = surcursalElement.getElementsByTagName("zonaje").item(0).getTextContent();
                String x = surcursalElement.getElementsByTagName("x").item(0).getTextContent();
                String y = surcursalElement.getElementsByTagName("y").item(0).getTextContent();
                Sucursal sucur = new Sucursal(codigo,nombreCorto,direccion,new Coordenada(Integer.parseInt(x),Integer.parseInt(y))," "," "," ");
                sucur.setPorcentajeDeZonaje(Double.parseDouble(zonaje));
                resultado.put(codigo,sucur);
            }
        }
        return resultado;
    }

}

