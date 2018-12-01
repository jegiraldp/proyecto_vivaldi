package Constructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import TraductorA.RecolectorOWLS;
import Constructor.abstracto;
import Constructor.RecolectaOperaciones;

/**
 * Esta calse llena las plantillas para cada servicio
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
public class plantilla {
	
	BufferedReader elquelee, tambienlee;
	File herramientaBob,herramientaBob2;
	String si="si";
	String no="no";
	public RecolectorOWLS objeto;
	public abstracto bobAbstracto=new abstracto();
	public RecolectaOperaciones objeto_recolector;
	plantilla yosoy;
		
	/**
	 * @param plt1 plantilla de servicio
	 * @param plt2 plantilla de servicio
	 * @param plt3 plantilla de servicio
	 * @param plt4 plantilla de servicio
	 */
	public void construirplantilla(Hashtable plt1,Hashtable plt2,Hashtable plt3,Hashtable plt4,int cuantossomos)throws IOException{
		
		
		int contadorservicios=0;
		String guarda,guarda2;
		herramientaBob = new File(".\\temporales\\TemporalDominio.txt");
		herramientaBob2 = new File(".\\temporales\\TemporalPredicados.txt");
		elquelee = new BufferedReader(new FileReader(herramientaBob));
		tambienlee = new BufferedReader(new FileReader(herramientaBob2));
		if(cuantossomos==3){
			guarda = elquelee.readLine();
			while((guarda=elquelee.readLine()) != null){
				if (guarda.startsWith(":precondition")){
					guarda = elquelee.readLine();
					contadorservicios++;
					if(guarda.startsWith("(and")){
						if (contadorservicios==1){
							plt1.put("censado",si);
							}else if (contadorservicios==2){
								plt1.put("censado",no);
								plt2.put("censado",si);
							}else if (contadorservicios==3){
								plt1.put("censado",no);
								plt2.put("censado",no);
								plt3.put("censado",si);}
						
					}else{
						if (contadorservicios==1)plt1.put("censado",no);
						if (contadorservicios==2)plt2.put("censado",no);
						if (contadorservicios==3)plt3.put("censado",no);}
				}
			}
			objeto = new RecolectorOWLS(".\\Repositorio\\"+plt1.get("nombre").toString()+".owl");
			objeto_recolector = new RecolectaOperaciones(".\\Repositorio\\wsdl\\"+plt1.get("nombre").toString()+".wsdl");
			plt1.put("entradas",objeto.getentradas());
			plt1.put("operaciones",objeto_recolector.getOp());
			objeto = new RecolectorOWLS(".\\Repositorio\\"+plt2.get("nombre").toString()+".owl");
			objeto_recolector = new RecolectaOperaciones(".\\Repositorio\\wsdl\\"+plt2.get("nombre").toString()+".wsdl");
			plt2.put("entradas",objeto.getentradas());
			plt2.put("operaciones",objeto_recolector.getOp());
			objeto = new RecolectorOWLS(".\\Repositorio\\"+plt3.get("nombre").toString()+".owl");
			objeto_recolector = new RecolectaOperaciones(".\\Repositorio\\wsdl\\"+plt3.get("nombre").toString()+".wsdl");
			plt3.put("entradas",plt2.get("entradas").toString()+" "+objeto.getentradas());
			plt3.put("operaciones",objeto_recolector.getOp());
			
			objeto = new RecolectorOWLS(".\\Repositorio\\"+plt1.get("nombre").toString()+".owl");
			plt1.put("salidas",objeto.getsalidas());
			objeto = new RecolectorOWLS(".\\Repositorio\\"+plt2.get("nombre").toString()+".owl");
			plt2.put("salidas",objeto.getsalidas());
			objeto = new RecolectorOWLS(".\\Repositorio\\"+plt3.get("nombre").toString()+".owl");
			plt3.put("salidas",objeto.getsalidas());
		
		
			guarda2 = tambienlee.readLine();
			guarda2 = tambienlee.readLine();
			plt1.put("precondiciones",guarda2);
			guarda2 = tambienlee.readLine();
			guarda2 = tambienlee.readLine();
			plt1.put("poscondiciones",guarda2);
			guarda2 = tambienlee.readLine();
			guarda2 = tambienlee.readLine();
			plt2.put("precondiciones",guarda2);
			guarda2 = tambienlee.readLine();
			guarda2 = tambienlee.readLine();
			plt2.put("poscondiciones",guarda2);
			guarda2 = tambienlee.readLine();
			guarda2 = tambienlee.readLine();
			plt3.put("precondiciones",guarda2);
			guarda2 = tambienlee.readLine();
			guarda2 = tambienlee.readLine();
			plt3.put("poscondiciones",guarda2);
			}//3
		
		bobAbstracto.definirPatronWF(plt1,plt2,plt3,plt4);
		try{
		bobAbstracto.construirBPELAbstracto(bobAbstracto.contenido(plt1,plt2,plt3,plt4),".\\Constructor\\abstractobpel.xml");
		}catch(Exception e){e.printStackTrace();}//fin catch
	}
	
	

}
