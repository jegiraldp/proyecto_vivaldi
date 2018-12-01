package TraductorA;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;

import java.io.IOException;
import java.io.*;
import java.io.FileWriter;

/**
 * Recolecta información por cada servicio descrito en OWL
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
public class RecolectorOWLS {
	
	File temp;
	public PrintWriter escritor,escritor2;
	Document document;
	String nombreServicio,nombreServicio2,entradas,salidas;
	BufferedReader entrada; 
	boolean nuevaLinea = true;
	FileWriter fw,fw2;
	int contadorEntradas=0;
	public Text texto;
	
	
	/**
	 * Constructor de la clase
	 * @param archivo Archivo a analizar
	 */
	public RecolectorOWLS(String archivo){
		
		try{
			temp = new File(".\\temporales\\TemporalDominio.txt");
			fw2 = new FileWriter(".\\temporales\\TemporalPredicados.txt",nuevaLinea);
			escritor2 = new PrintWriter(fw2);
			escritor = new PrintWriter(new FileWriter(temp,nuevaLinea));
			entrada = new BufferedReader(new FileReader(temp));	
		}catch(IOException ioex){System.out.println("No se puede crear archivo");}	
		
		
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
			System.err.println("Se ha producido un error de entrada  / salida");}
		
		Node n = document.getLastChild();
		try{
        analizar(n);
		}catch(Exception e) { 
   			e.printStackTrace();}}
	
	/**
	 * @param node Nodo a analizar
	 */
	public void analizar(Node node){
		
		
		NodeList hijos;
		int nodeType = node.getNodeType();	
			
		switch(nodeType){
		// de tipo docuemnto
		case Node.DOCUMENT_NODE:
		break;
				
		case 1:
			if(node.getNodeName().equals("rdf:RDF")){
				hijos = node.getChildNodes();
				if(hijos != null){
				for(int i=0;i<hijos.getLength();i++){
				analizar(hijos.item(i));
				}
				}//fin if
			}
			else if(node.getNodeName().equals("process:AtomicProcess")){
				nombreServicio=((Element) node).getAttribute("rdf:ID"); 
				escritor.println("");
				escritor.println("(:action "+nombreServicio);
				escritor.flush();				
				hijos = node.getChildNodes();
				if(hijos != null){
				for(int i=0;i<hijos.getLength();i++){
				analizar(hijos.item(i));}
				escritor.println(")");
				escritor.flush();
				}//fin if
				
			}else if(node.getNodeName().equals("process:Input")){
				entradas=((Element) node).getAttribute("rdf:ID"); 
				hijos = node.getChildNodes();
				if(hijos != null){
				for(int i=0;i<hijos.getLength();i++){
				analizar(hijos.item(i));}
				contadorEntradas++;
				}//fin if
				
			}else if(node.getNodeName().equals("service:Service")){
				nombreServicio2=((Element) node).getAttribute("rdf:ID"); 	
					
					
			}else if(node.getNodeName().equals("rdfs:label")){
				hijos = node.getChildNodes();
				if (contadorEntradas == 0){
				escritor.print(":parameters ");
				if(hijos != null){
					escritor.print("(?obj)");
					escritor.flush();
					}}				
				}else if((node.getNodeName()).equals("process:hasPrecondition")){
				hijos = node.getChildNodes();
				if(hijos != null){
					for(int i=0;i<hijos.getLength();i++){
						analizar(hijos.item(i));}}
				
			}else if((node.getNodeName()).equals("expr:KIF-Condition")){
				escritor.println("");
				escritor.println(":precondition");
				escritor2.println("precondiciones");
				escritor.flush();
				hijos = node.getChildNodes();
				if(hijos != null){
					for(int i=0;i<hijos.getLength();i++)
						analizar(hijos.item(i));
				}
			}else if((node.getNodeName()).equals("expr:expressionBody")){
				hijos = node.getChildNodes();
				if(hijos != null){
					for(int i=0;i<hijos.getLength();i++){
						analizar(hijos.item(i));
						}
			}
			}else if((node.getNodeName()).equals("process:hasResult")){
				hijos = node.getChildNodes();
				if(hijos != null){
					for(int i=0;i<hijos.getLength();i++)
						analizar(hijos.item(i));
				}
			}else if((node.getNodeName()).equals("process:Result")){
				hijos = node.getChildNodes();
				if(hijos != null){
					for(int i=0;i<hijos.getLength();i++)
						analizar(hijos.item(i));
				}
			}else if((node.getNodeName()).equals("process:hasEffect")){
				escritor.println("");
				escritor.print(":effect");
				escritor.flush();
				escritor2.println("efectos");
				escritor.flush();
				hijos = node.getChildNodes();
				if(hijos != null){
					for(int i=0;i<hijos.getLength();i++)
						analizar(hijos.item(i));
				}
			}else if((node.getNodeName()).equals("expr:KIF-Expression")){
				hijos = node.getChildNodes();
				if(hijos != null){
					for(int i=0;i<hijos.getLength();i++)
						analizar(hijos.item(i));
				}}
			else if((node.getNodeName()).equals("process:Output")){
				salidas=((Element) node).getAttribute("rdf:ID"); 
				hijos = node.getChildNodes();
				if(hijos != null){
					for(int i=0;i<hijos.getLength();i++)
						analizar(hijos.item(i));
				}}
			
					
			break;	
			
		case Node.TEXT_NODE:
			
			texto = (Text) node;
			if(!(((texto.getData()).trim()).equals(""))){
				escritor.print(texto.getData());			
				escritor.flush();
				escritor2.println(texto.getData());
				escritor2.flush();
				}
									
			break;
			
			default:

			break;
		}
		
	}//analizar
	
	/**
	 * @return nombreServicio
	 */
	public String getNombreServicio(){
		return nombreServicio;}
	/**
	 * @return nombreServicio2
	 */
	public String getNombreServicio2(){
		return nombreServicio2;}
	/**
	 * @return nombreServicio
	 */
	public String get(){
		return nombreServicio;}
	/**
	 * @return texto
	 */	
	public String getefecto(){
		return texto.toString();}
	/**
	 * @return entradas
	 */
	public String getentradas(){
		return entradas;}
	/**
	 * @return salidas
	 */
	public String getsalidas(){
		return salidas;}
	
}
