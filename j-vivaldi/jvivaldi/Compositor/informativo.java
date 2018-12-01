
package Compositor;

import java.net.*;
import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.IOException;


/**
 * Esta clase determina si el servicio analizado es o no de tipo Informativo o de lo contrario cambia el mundo
 * @author Universidad Nacional de Colombia, Escuela de Sistemas- GIDIA
 */
public class informativo {
			
			public String captura;
			
	
			/**
			 * @param basedatos base de datos
			 */
			public void verificar(String basedatos){
		
		  	String linea;
		  	FileWriter fw;
		  	PrintWriter pw;
		  	Document document = null;
		  	String metodo="consulta";
		  	      
		   try{
		  	   
		   URL url = new URL("http://localhost:8080/axis/estado.jws?method=consulta&bd="+basedatos);
		    
		   BufferedReader htmlPage = new BufferedReader(new InputStreamReader(url.openStream())); 
		 	fw = new FileWriter (".\\temporales\\axis.xml");
		    pw = new PrintWriter (fw);
		      
		    while((linea = htmlPage.readLine()) != null) { 
		      pw.println(linea);
		      pw.close();
		   } //fin while 
		   htmlPage.close();
		    
		   }catch(Exception e) { 
		   e.printStackTrace(); 
		 }//fin catch
		 
		 		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				try{
					DocumentBuilder builder = factory.newDocumentBuilder();
					document = builder.parse(new File(".\\temporales\\axis.xml"));
				}catch(ParserConfigurationException e){
					System.err.println("No se ha podido crear una instancia de DocumentBuilder");
				}catch(SAXException e){
					System.err.println("Error SAX al parsear el archivo");
					System.err.println(e);
				}catch(IOException e){
					System.err.println("Se ha producido un error de entrada  / salida");
				}
		 
				Node n = document.getLastChild();
				getProcesos(n, metodo);
		 
		 } // verificar
		 
			/**
			 * @param node nodo
			 * @param m metodo
			 */
		   public void getProcesos(Node node, String m){
				
				String m1 = m;
				int nodeType = node.getNodeType();
				NodeList hijos;
				String hijo_response = m1+"Response";
				String hijo_return = m1+"Return";
										
				switch(nodeType){
				
					case Node.DOCUMENT_NODE:
					hijos = (node.getFirstChild()).getChildNodes();
					if(hijos != null){
							for(int i=0;i<hijos.getLength();i++)
							getProcesos(hijos.item(i), m1);			
					}  
					break;
					
					case 1:
					if((node.getNodeName()).equals("soapenv:Envelope")){
						hijos = node.getChildNodes();
						if(hijos != null){
						for(int i=0;i<hijos.getLength();i++){
						getProcesos(hijos.item(i), m1);
						}}}
						
						else if ((node.getNodeName()).equals("soapenv:Body")){
						hijos = node.getChildNodes();
						if(hijos != null){
						for(int i=0;i<hijos.getLength();i++){
						getProcesos(hijos.item(i), m1);}}}
						
						else if ((node.getNodeName()).equals(hijo_response)){
						hijos = node.getChildNodes();
						if(hijos != null){
						for(int i=0;i<hijos.getLength();i++){
						getProcesos(hijos.item(i), m1);}}}
						
						else if ((node.getNodeName()).equals(hijo_return)){
						hijos = node.getChildNodes();
						if(hijos != null){
						for(int i=0;i<hijos.getLength();i++){
						getProcesos(hijos.item(i), m1);}}}
						
					break;
					
					case Node.TEXT_NODE:
					
					Text expresion = (Text) node;
					captura = expresion.getData();
					//System.out.println ("Resultado del servicio = " + captura);
					
					break;
					
				
					default:
					break;
					
			}//switch		
			}//get procesos
		   	/**
			 * @return resultado
			 */
			public String getCaptura(){
				return captura;
			}
			
			}
	


