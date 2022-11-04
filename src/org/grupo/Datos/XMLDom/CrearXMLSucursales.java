package org.grupo.Datos.XMLDom;

import org.grupo.Negocio.ModeloTableModelSucursal;
import org.grupo.Negocio.Sucursal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Map;

public class CrearXMLSucursales {
        public static final String xmlFilePath = "Sucursales.xml";
        public static  void creaXML(ModeloTableModelSucursal modelo) {

            try {
                File myfile = new File(xmlFilePath);
                myfile.delete();
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();
                Element root = document.createElement("Sucursales");
                document.appendChild(root);
                for (Map.Entry<String, Sucursal> sucursal : modelo.getListaDeSucursales().entrySet()) {
                    // employee element
                    Element sucur = document.createElement("Sucursal");
                    Sucursal su = sucursal.getValue();
                    root.appendChild(sucur);

                    //you can also use staff.setAttribute("id", "1") for this

                    // firstname element
                    Element codigo = document.createElement("codigo");
                    codigo.appendChild(document.createTextNode(su.getCodigo()));
                    sucur.appendChild(codigo);

                    Element nombreCorto = document.createElement("nombreCorto");
                    nombreCorto.appendChild(document.createTextNode(su.getNombreCorto()));
                    sucur.appendChild(nombreCorto);

                    // direccion element
                    Element direccion = document.createElement("direccion");
                    direccion.appendChild(document.createTextNode(su.getDireccion()));
                    sucur.appendChild(direccion);

                    // zonaje element
                    Element zonaje = document.createElement("zonaje");
                    zonaje.appendChild(document.createTextNode(String.valueOf(su.getPorcentajeDeZonaje())));
                    sucur.appendChild(zonaje);

                    // coodernada x element
                    Element coorx = document.createElement("x");
                    coorx.appendChild(document.createTextNode(String.valueOf(su.getCoordenada().getX())));
                    sucur.appendChild(coorx);

                    // coodernada y element
                    Element coory = document.createElement("y");
                    coory.appendChild(document.createTextNode(String.valueOf(su.getCoordenada().getY())));
                    sucur.appendChild(coory);
                }


                // create the xml file
                //transform the DOM Object to an XML File
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));

                // If you use
                // StreamResult result = new StreamResult(System.out);
                // the output will be pushed to the standard output ...
                // You can use that for debugging

                transformer.transform(domSource, streamResult);

                System.out.println("Done creating XML File");

            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }
    }

