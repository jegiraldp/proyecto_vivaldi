package TraductorB;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Ejecutor.activeBPEL;

/**
 * Construye BPEL final-concreto
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
public class bpel {
	
	activeBPEL objeto_activeBPEL = new activeBPEL();

	public void construirBPELActive(Document bpelXML, String nombreArchivo)throws Exception{
		
	    Source fuente = new DOMSource(bpelXML); 
        Result result = new StreamResult(new java.io.File(nombreArchivo));
        
        Transformer tranformador = TransformerFactory.newInstance ().newTransformer();
        tranformador.transform(fuente,result);
        //tranformador.transform(fuente,new StreamResult(System.out));
        System.out.println("--BPEL concreto generado---");
        objeto_activeBPEL.inicio();
	
	
	}//metodo construir
	/**
	 * Construye contenido del BPEL
	 * @param literal
	 * @return document
	 */
	public Document contenido(String literal) throws Exception{
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		Document document = implementation.createDocument("http://schemas.xmlsoap.org/ws/2003/03/business-process/","process",null);
		
		
		//crea el atributos
		Attr xmlnsbpws = document.createAttribute("xmlns:bpws");
		xmlnsbpws.setValue("http://schemas.xmlsoap.org/ws/2003/03/business-process/");
		Attr xmlnsns1 = document.createAttribute("xmlns:ns1");
		xmlnsns1.setValue("http://activebpel.org/swDef");
		Attr xmlnsxsd = document.createAttribute("xmlns:xsd");
		xmlnsxsd.setValue("http://www.w3.org/2001/XMLSchema");
		Attr name = document.createAttribute("name");
		name.setValue("sw");
		Attr suppressJoinFailure = document.createAttribute("suppressJoinFailure");
		suppressJoinFailure.setValue("yes");
		Attr targetNamespace = document.createAttribute("targetNamespace");
		targetNamespace.setValue("http://sw");
		Attr xmlns = document.createAttribute("xmlns");
		xmlns.setValue("http://schemas.xmlsoap.org/ws/2003/03/business-process/");
		
		Attr myRole = document.createAttribute("myRole");
		myRole.setValue("swRol");
        Attr nameRole = document.createAttribute("name");
        nameRole.setValue("swPL");
        Attr partnerLinkType = document.createAttribute("partnerLinkType");
        partnerLinkType.setValue("ns1:swLT");
        
        Attr messageTypeRq = document.createAttribute("messageType");
        messageTypeRq.setValue("ns1:swRequest");
        Attr nameVRq = document.createAttribute("name");
        nameVRq.setValue("swRequest");
        Attr messageTypeRs = document.createAttribute("messageType");
        messageTypeRs.setValue("ns1:swResponse");
        Attr nameVRs = document.createAttribute("name");
        nameVRs.setValue("swResponse");
        
        Attr createInstance = document.createAttribute("createInstance");
        createInstance.setValue("yes");
        Attr operation = document.createAttribute("operation");
        operation.setValue("sw");
        Attr partnerLinkA = document.createAttribute("partnerLink");
        partnerLinkA.setValue("swPL");
        Attr portType = document.createAttribute("portType");
        portType.setValue("ns1:swServicePT");
        Attr variable = document.createAttribute("variable");
        variable.setValue("swRequest");
        
        Attr expression = document.createAttribute("expression");
        expression.setValue("'"+literal+"'");
        
        Attr part = document.createAttribute("part");
        part.setValue("swReturn");
        Attr variableTo = document.createAttribute("variable");
        variableTo.setValue("swResponse");
        
        Attr operationReply = document.createAttribute("operation");
        operationReply.setValue("sw");
        Attr partnerLinkReply = document.createAttribute("partnerLink");
        partnerLinkReply.setValue("swPL");
        Attr portTypeReply = document.createAttribute("portType");
        portTypeReply.setValue("ns1:swServicePT");
        Attr variableReply = document.createAttribute("variable");
        variableReply.setValue("swResponse");
       
        //creacion elementos
		Element proceso = document.getDocumentElement();
		Element partnerLinks = document.createElement("partnerLinks");
		Element partnerLink = document.createElement("partnerLink");
		Element variables = document.createElement("variables");
		Element variable1 = document.createElement("variable");
		Element variable2 = document.createElement("variable");
		Element sequence = document.createElement("sequence");
		Element receive = document.createElement("receive");
		Element assign = document.createElement("assign");
		Element copy = document.createElement("copy");
		Element from = document.createElement("from");
		Element to = document.createElement("to");
		Element reply = document.createElement("reply");
		
//		adiciona atributos
		proceso.setAttributeNodeNS(xmlnsbpws);
		proceso.setAttributeNodeNS(xmlnsns1);
		proceso.setAttributeNodeNS(xmlnsxsd);
		proceso.setAttributeNodeNS(name);
		proceso.setAttributeNodeNS(targetNamespace);
		proceso.setAttributeNodeNS(xmlns);
		proceso.setAttributeNodeNS(suppressJoinFailure);
		
		partnerLink.setAttributeNode(myRole);
		partnerLink.setAttributeNode(nameRole);
		partnerLink.setAttributeNode(partnerLinkType);
		
		variable1.setAttributeNode(messageTypeRq);
		variable1.setAttributeNode(nameVRq);
		variable2.setAttributeNode(messageTypeRs);
		variable2.setAttributeNode(nameVRs);
		
		receive.setAttributeNode(createInstance);
		receive.setAttributeNode(operation);
		receive.setAttributeNode(partnerLinkA);
		receive.setAttributeNode(portType);
		receive.setAttributeNode(variable);
				
		from.setAttributeNode(expression);
		to.setAttributeNode(part);
		to.setAttributeNode(variableTo);
		
		reply.setAttributeNode(operationReply);
		reply.setAttributeNode(partnerLinkReply);
		reply.setAttributeNode(portTypeReply);
		reply.setAttributeNode(variableReply);
		
//		agregar hijos 
		proceso.appendChild(partnerLinks);
		partnerLinks.appendChild(partnerLink);
		proceso.appendChild(variables);
		variables.appendChild(variable1);
		variables.appendChild(variable2);
		proceso.appendChild(sequence);
		sequence.appendChild(receive);
		receive.appendChild(assign);
		assign.appendChild(copy);
		copy.appendChild(from);
		copy.appendChild(to);
		sequence.appendChild(reply);
		
        return document;}			
	

}

	
	
	

