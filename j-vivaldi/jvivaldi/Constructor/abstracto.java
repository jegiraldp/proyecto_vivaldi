package Constructor;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.util.*;
import TraductorB.toConcreto;


/**
 * Esta clase construye el BPEL abstracto
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
public class abstracto {
	
	String verificaPre,verificaPos;
	toConcreto objeto_to =new toConcreto();
	public Hashtable swthis1,swthis2,swthis3,swthis4;
	
	 /**
	 * @param servicio1 Plantilla servicio
	 * @param servicio2 Plantilla servicio
	 * @param servicio3 Plantilla servicio
	 * @param servicio4 Plantilla servicio
	 */
	public void definirPatronWF(Hashtable servicio1,Hashtable servicio2,Hashtable servicio3,Hashtable servicio4){
		swthis1=servicio1;swthis2=servicio2;swthis3=servicio3;swthis4=servicio4;
		verificaPre = swthis1.get("precondiciones").toString();
		if(verificaPre.startsWith("(and")){
			swthis1.put("operadorwfPre","sincronizacion");		
		}else swthis1.put("operadorwfPre","secuencia");
		verificaPos = swthis1.get("poscondiciones").toString();
		if(verificaPos.startsWith("(and")){
			swthis1.put("operadorwfPos","AndParalelo");		
		}else swthis1.put("operadorwfPos","secuencia");
		
		verificaPre = swthis2.get("precondiciones").toString();
		if(verificaPre.startsWith("(and")){
			swthis2.put("operadorwfPre","sincronizacion");		
		}else swthis2.put("operadorwfPre","secuencia");
		verificaPos = swthis2.get("poscondiciones").toString();
		if(verificaPos.startsWith("(and")){
			swthis2.put("operadorwfPos","AndParalelo");		
		}else swthis2.put("operadorwfPos","secuencia");
		
		verificaPre = swthis3.get("precondiciones").toString();
		if(verificaPre.startsWith("(and")){
			swthis3.put("operadorwfPre","sincronizacion");		
		}else swthis3.put("operadorwfPre","secuencia");
		verificaPos = swthis3.get("poscondiciones").toString();
		if(verificaPos.startsWith("(and")){
			swthis3.put("operadorwfPos","AndParalelo");		
		}else swthis3.put("operadorwfPos","secuencia");
				
	}//metodo
	
	
	/**
	 * @param bpelXML Documento construido
	 * @param nombreArchivo Nombre de archivo a crear
	 */
	public void construirBPELAbstracto(Document bpelXML, String nombreArchivo)throws Exception{
			
			    Source fuente = new DOMSource(bpelXML); 
		        Result result = new StreamResult(new java.io.File(nombreArchivo));
		        
		        Transformer tranformador = TransformerFactory.newInstance ().newTransformer();
		        tranformador.transform(fuente,result);
		        //tranformador.transform(fuente,new StreamResult(System.out));
		        System.out.println("--BPEL Abstracto generado---");
		        objeto_to.recorrerSecuencia(swthis1,swthis2,swthis3,swthis4);
		        
		        
	}//metodo
		
	 /**
	 * @param sw1 Plantilla servicio
	 * @param sw2 Plantilla servicio
	 * @param sw3 Plantilla servicio
	 * @param sw4 Plantilla servicio
	 * @return document Documento construido
	 */
		public Document contenido(Hashtable sw1,Hashtable sw2,Hashtable sw3,Hashtable sw4) throws Exception{
				
			String servicioA,servicioB,servicioC;
			servicioA=sw1.get("nombre").toString();
			servicioB=sw2.get("nombre").toString();
			servicioC=sw3.get("nombre").toString();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument("http://schemas.xmlsoap.org/ws/2003/03/business-process/","process",null);
			

//			crea el atributos
			Attr idsw1 = document.createAttribute("id");
	        idsw1.setValue(servicioA);
	        Attr idsw2 = document.createAttribute("id");
	        idsw2.setValue(servicioB);
	        Attr idsw3 = document.createAttribute("id");
	        idsw3.setValue(servicioC);
			
			//creacion de elementos
			Element proceso = document.getDocumentElement();
			Element sequence = document.createElement("sequence");
			Element flow = document.createElement("flow");
			Element actividad1=document.createElement("actividad1");
			Element actividad2=document.createElement("actividad2");
			Element actividad3=document.createElement("actividad3");
			
			//adiciona atributos
			actividad1.setAttributeNodeNS(idsw1);
			actividad2.setAttributeNodeNS(idsw2);
			actividad3.setAttributeNodeNS(idsw3);
			
//			agregar hijos a process
	        proceso.appendChild(sequence);
	        sequence.appendChild(flow);
	        flow.appendChild(actividad1);
	        flow.appendChild(actividad2);
	        sequence.appendChild(actividad3);
	        
	        return document;}			
		
	
}
