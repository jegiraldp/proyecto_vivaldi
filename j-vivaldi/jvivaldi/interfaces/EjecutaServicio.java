package interfaces;

import java.net.*;
import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.IOException;

/**
 * Esta clase ejecuta servicios sobre axis
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
class  EjecutaServicio{
	
	
  public static void main( String[] args ) {
  	
  	String linea;
  	FileWriter fw;
  	PrintWriter pw;
  	Document document = null;
  	
    String method = "saludo";
    String entradas = "&name=JORGE";
    String servicio = "jgservice";
    
   try{
  //http://localhost:8080/active-bpel/services/jgservice?method=saludo&name=JORGE	
   
   URL url = new URL("http://localhost:8080/axis/Calculadora.jws?method=mul&x=8&y=5");
   //URL url = new URL("http://localhost:8080/active-bpel/services/"+servicio+"?method="+ method + entradas);
    
   BufferedReader htmlPage = new BufferedReader(new InputStreamReader(url.openStream())); 
 	fw = new FileWriter (".\\axis.xml");
    pw = new PrintWriter (fw);
      
    while((linea = htmlPage.readLine()) != null) { 
      System.out.println(linea);
      
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
			document = builder.parse(new File(".\\axis.xml"));
		}catch(ParserConfigurationException e){
			System.err.println("No se ha podido crear una instancia de DocumentBuilder");
		}catch(SAXException e){
			System.err.println("Error SAX al parsear el archivo");
			System.err.println(e);
		}catch(IOException e){
			System.err.println("Se ha producido un error de entrada  / salida");
		}
 
		//Primero se crea el archivo ordenado con informacion de los procesos
		Node n = document.getLastChild();
		getProcesos(n, method, entradas);
 
 } // fin main
 


  /**
   * @param node Nodo a analizar
   * @param node m Metodo
   * @param node in Entrada
   */
   public static void getProcesos(Node node, String m, String in){
		
		//System.out.println("toy en getProcesos"+m+in);
		String m1 = m;
		String in1 = in;
		int nodeType = node.getNodeType();
		NodeList hijos;
		String captura;
		String hijo_response = m1+"Response";
		String hijo_return = m1+"Return";
		
				
				
		switch(nodeType){
		
			// de tipo docuemnto
			case Node.DOCUMENT_NODE:
			hijos = (node.getFirstChild()).getChildNodes();
			if(hijos != null){
					for(int i=0;i<hijos.getLength();i++)
					getProcesos(hijos.item(i), m1, in1);			
			}  
			break;
			
			//case Node.ELEMENT_NODE:
			case 1:
			//Para cada elemento obtenemos el tipo y procedemos
			if((node.getNodeName()).equals("soapenv:Envelope")){
				//System.out.println("toy en hijos ");
				hijos = node.getChildNodes();
				
				if(hijos != null){
				for(int i=0;i<hijos.getLength();i++){
					//System.out.println(hijos.item(i).getNodeName());
					getProcesos(hijos.item(i), m1, in1);
				}
				}
				
				}
				
				else if ((node.getNodeName()).equals("soapenv:Body")){
				//System.out.println("toy en hijos 2");
				hijos = node.getChildNodes();
				
				if(hijos != null){
				for(int i=0;i<hijos.getLength();i++){
				//System.out.println(hijos.item(i).getNodeName());
				getProcesos(hijos.item(i), m1, in1);}}}
				
				else if ((node.getNodeName()).equals(hijo_response)){
				//System.out.println("toy en hijos 3");
				hijos = node.getChildNodes();
				
				if(hijos != null){
				for(int i=0;i<hijos.getLength();i++){
				//System.out.println(hijos.item(i).getNodeName());
				getProcesos(hijos.item(i), m1, in1);}}}
				
				else if ((node.getNodeName()).equals("holaReturn")){
				//System.out.println("toy en hijos 4");
				hijos = node.getChildNodes();
				
				if(hijos != null){
				for(int i=0;i<hijos.getLength();i++){
				//System.out.println(hijos.item(i).getNodeName());
				getProcesos(hijos.item(i), m1, in1);	}}}
				
			break;
			
			case Node.TEXT_NODE:
			
			Text expresion = (Text) node;
			captura = expresion.getData();
			System.out.println ("Resultado del servicio = " + captura);
			break;
			
		
			default:
			break;
			
	}//switch		
	}//get procesos
 
 
 
 }


