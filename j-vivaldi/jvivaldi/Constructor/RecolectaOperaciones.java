package Constructor;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;


/**
 * Esta clase Recolecta las operaciones por cada servicio
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
public class RecolectaOperaciones {
	
		String operacion;
		Document document; 
		
		public RecolectaOperaciones(String archivo){
			
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
				System.err.println("Se ha producido un error de entrada  / salida en el WSDL");}
		
			Node n = document.getLastChild();
			try{
	        analizar(n);
			}catch(Exception e) {e.printStackTrace();}
		
		}
		
		/**
		 * @param node nodo a analizar
		 */	
		public void analizar (Node node) {
			
				NodeList hijos;
				int nodeType = node.getNodeType();
				
				switch(nodeType){
			
				case Node.DOCUMENT_NODE:
				hijos = (node.getFirstChild()).getChildNodes();
				if(hijos != null){
						for(int i=0;i<hijos.getLength();i++)
						analizar(hijos.item(i));			
				}  
				break;
				
				//case Node.ELEMENT_NODE:
				case 1:
				if(node.getNodeName().equals("wsdl:definitions")){
	            hijos = node.getChildNodes();
					if(hijos != null){
					for(int i=0;i<hijos.getLength();i++){
					analizar(hijos.item(i));}
					}//fin if
					}
	              
				else if(node.getNodeName().equals("wsdl:portType")){
		                hijos = node.getChildNodes();
		          		if(hijos != null){
						for(int i=0;i<hijos.getLength();i++){
						analizar(hijos.item(i));
						}}//fin if
						}
	            //Captura Operation
	            else if(node.getNodeName().equals("wsdl:operation")){
	            operacion = ((Element) node).getAttribute("name");
	            hijos = node.getChildNodes();
	          		if(hijos != null){
					for(int i=0;i<hijos.getLength();i++){
					analizar(hijos.item(i));
					}}//fin if
					}
	            
	            break;
	            }//switch
	            
		}//fin analizar
		
		//retorna operaciones
		/**
		 * @return operacion 
		 */	
		public String getOp (){
			return operacion;}
	
}
