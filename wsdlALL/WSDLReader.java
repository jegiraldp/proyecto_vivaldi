import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.*;


public class WSDLReader{
    
    private Document document; 
    private String nameSpace;
    private String nameService;
    private Vector messages;
    private Vector portTypes;
    private Vector operations;
	
    public WSDLReader(String archivo){
	
        messages = new Vector();
        portTypes = new Vector();
        operations = new Vector();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(archivo));
            }catch(ParserConfigurationException e){
        System.err.println("No se ha podido crear una instancia de DocumentBuilder");
        }catch(SAXException e){
        System.err.println("Error SAX al parsear el archivo");
        System.err.println(e);
        }catch(IOException e){
        System.err.println("Se ha producido un error de entrada salida");
        }
    }
    
    /**
     *Este metodo permite obtener el nameSpace del servicio especificado en el WSDL
     *@return Cadena con el nameSpace del Servicio
     */
    public String getNameSpace(){
        return nameSpace;
    }
    
    /**
     *Este metodo permite obtener el nombre del servicio especificado en el WSDL
     *@return Cadena con el nombre del Servicio
     */
    public String getNameService(){
        return nameService;
    }
    
    /**
     *Este metodo permite obtener los mensajes del servicio especificados en el WSDL
     *@return vector con los mensajes especificados en el servicio
     */
    public Vector getMessages(){
        return messages;
    }
    
    /**
     *Este metodo permite obtener los portTypes del servicio especificados en el WSDL
     *@return vector con los porTypes especificados en el servicio
     */
    public Vector getPortTypes(){
        return portTypes;
    }
    
    /**
     *Este metodo permite obtener las operaciones del servicio especificados en el WSDL
     *@return vector con las operaciones especificados en el servicio
     */
    public Vector getOperations(){
        return operations;
    }
    
    /**
     *Este metodo lee el archivo WSDL, debe de usarse antes de invocar cualquier otro metodo de la
     *clase
     */
    public void leerWSDL(){
        listChildren(document,0);
    }

    private void print(String s, int level){
        for(;level>0;level--)
            System.out.print("\t");
        System.out.println(s);
    }
    
    private void listChildren(Node e, int level){
        if(e.getNodeName().equals("wsdl:definitions")){
            //Se extrae el valor del NameSpace
            System.out.println("NameSpace: " + ((Element) e).getAttribute("targetNamespace"));
            nameSpace = ((Element) e).getAttribute("targetNamespace");
        }else if(e.getNodeName().equals("wsdl:message")){
            //Se extrae los mensajes contenidos en el documento wsdl
            System.out.println("Nuevo mensaje encontrado: " + ((Element) e).getAttribute("name"));
            String message = ((Element) e).getAttribute("name");
            messages.addElement(message);
        }else if(e.getNodeName().equals("wsdl:portType")){
            //Se obtiene el nombre y las operaciones asociadas al portType
            System.out.println("Nuevo portType encontrado: " + ((Element) e).getAttribute("name"));
            String port = ((Element) e).getAttribute("name");
            portTypes.addElement(port);
        }else if(e.getNodeName().equals("wsdl:operation")){
            //Se obtiene el nombre y las operaciones asociadas al portType
            System.out.println("Nuevo operation encontrado: " + ((Element) e).getAttribute("name"));
            String operacion = ((Element) e).getAttribute("name");
            operations.addElement(operacion);
        }else if(e.getNodeName().equals("wsdl:service")){
            //Se obtiene el nombre y las operaciones asociadas al portType
            System.out.println("Nombre del servicio: " + ((Element) e).getAttribute("name"));
            nameService = ((Element) e).getAttribute("name");
        }
        //print("[ "+e.getNodeName()+" ]",level);
        level++;
    
        for(Node node = e.getFirstChild(); node!=null; node = node.getNextSibling()){
            listChildren(node, level);
        }
    }

    public static void main(String args[]){
        //RecolectorOwls recolector = new RecolectorOwls("BravoAirProcess.owl");
        WSDLReader reader = new WSDLReader("DictionaryProcess.wsdl");
        System.out.println("Documento bien formado");
        reader.leerWSDL();    
    }
	
}