import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.*;


public class WSDLReaderYO{
    
    private Document document; 
    private String nameSpace;
    private String nameService;
    private Vector messages;
    private Vector portTypes;
    private Vector operations;
    String mensaje;
	
    public WSDLReaderYO(String archivo){
	
	
		
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
    
    
    public void leerWSDL(){
        Node n = document.getLastChild();
        getProcesos(n);
    }

   
    
    private void getProcesos(Node node){
    	
    		NodeList hijos;
		
    		int nodeType = node.getNodeType();	
		
			switch(nodeType){
		
			// de tipo docuemnto
			case Node.DOCUMENT_NODE:
			//se analiza cada hijo
			hijos = (node.getFirstChild()).getChildNodes();
			if(hijos != null){
					for(int i=0;i<hijos.getLength();i++)
					getProcesos(hijos.item(i));			
			}  
			break;
			
			//case Node.ELEMENT_NODE:
			case 1:
			if(node.getNodeName().equals("wsdl:definitions")){
            //Se extrae el valor del NameSpace
            System.out.println("NameSpace: " + ((Element) node).getAttribute("targetNamespace"));
            nameSpace = ((Element) node).getAttribute("targetNamespace");
            
            //se analiza cada hijo
            hijos = node.getChildNodes();
				
				if(hijos != null){
				for(int i=0;i<hijos.getLength();i++){
				getProcesos(hijos.item(i));
				}
				}//fin if
            }
            
            //Captura Mensajes
            else if(node.getNodeName().equals("wsdl:message")){
            System.out.println("Mensaje : " + ((Element) node).getAttribute("name"));
            mensaje = ((Element) node).getAttribute("name");//por cada mensaje una posicion de m[]
            }
            
            //Captura portType          
            else if(node.getNodeName().equals("wsdl:portType")){
            System.out.println("PortType : " + ((Element) node).getAttribute("name"));
            String portType = ((Element) node).getAttribute("name");
            
            //se analiza cada hijo
            hijos = node.getChildNodes();
				
				if(hijos != null){
				for(int i=0;i<hijos.getLength();i++){
				getProcesos(hijos.item(i));
				}
				}//fin if
            }
            
            //Captura Operation
            else if(node.getNodeName().equals("wsdl:operation")){
            System.out.println("Operation : " + ((Element) node).getAttribute("name"));
            String operation = ((Element) node).getAttribute("name");
            
            //se analiza cada hijo entrada
            hijos = node.getChildNodes();
          				
				if(hijos != null){
				for(int i=0;i<hijos.getLength();i++){
				getProcesos(hijos.item(i));
				}
				}//fin if
				
            }
            
            //Captura entradas 
            else if(node.getNodeName().equals("wsdl:input")){
            System.out.println("Entrada : " + ((Element) node).getAttribute("name"));
            String inWS = ((Element) node).getAttribute("name");}
            
            //Captura Salidas
            else if(node.getNodeName().equals("wsdl:output")){
            System.out.println("Salida : " + ((Element) node).getAttribute("name"));
            String outWS = ((Element) node).getAttribute("name");}
            
            
             //Captura PartnerLinType 
            else if(node.getNodeName().equals("patternLinkType")){
            System.out.println("PartnerLT : " + ((Element) node).getAttribute("name"));
            String PLT = ((Element) node).getAttribute("name");}
            
            
            
            break;
            
            }//switch
		
	}//get procesos
 
     

    public static void main(String args[]){
        
        WSDLReaderYO reader = new WSDLReaderYO("Calculadora.wsdl");
        System.out.println("Documento bien formado");
        reader.leerWSDL(); 
                   
    }
	
}