package Compositor;
import java.io.*;

import TraductorA.pddlDominio;
import java.util.*;
import TraductorA.RecolectorOWLS;
import Constructor.bob;
import Constructor.plantilla;

/**
 * Esta clase ejecuta el planificador y crea las plantillas por cada servicio
 * @author Universidad Nacional de Colombia, Escuela de Sistemas- GIDIA
 */
public class Planificar {
	
	public Hashtable plantilla1 = new Hashtable();
	public Hashtable plantilla2 = new Hashtable();
	public Hashtable plantilla3 = new Hashtable();
	public Hashtable plantilla4 = new Hashtable();
	public String servicio1,servicio2,servicio3,servicio4;
	public String[] servicioArray;
	public FileWriter fwLimpiar;
	public pddlDominio obj_pddlDominio = new pddlDominio();
	public pddlProblema obj_pddlProblema = new pddlProblema();
	public informativo obj_informativo = new informativo();
	public plantilla objeto_plantilla = new plantilla();
	public bob elconstructor = new bob();//CompositorSW-UN-2006
	public RecolectorOWLS objeto_recolector;
	public String[] serviciosThis; 
	 

	/**
	 * Ejecuta .bat 
	 */
	public void ejecutarBAT() throws IOException{
	System.out.println("Ejecutando SAPA");
	Process p= Runtime.getRuntime().exec ("cmd /c .\\ejecutarPlanificador.bat");
	BufferedReader br = new BufferedReader (new InputStreamReader (p.getInputStream()));
    String aux = br.readLine();
    
    while (aux!=null){
    
    System.out.println (aux);
    aux = br.readLine();
     }
	}
	
	public void recolectarInformacionProceso(int num_obj, int num_condiciones,String[] s,Hashtable objetivos){
		int num_servicios=0;
		System.out.println("--- generando Plantillas de servicios ---");
		if (s.length==1){}
		if (s.length==2){}
		if (s.length==3){
			num_servicios=s.length;
			servicio1=s[0];
			servicioArray=servicio1.split("\\.");
			obj_informativo.verificar(servicioArray[0]);
			plantilla1.put("nombre",servicioArray[0]);
			plantilla1.put("informativo",obj_informativo.getCaptura());
					
			servicio2=s[1];
			servicioArray=servicio2.split("\\.");
			obj_informativo.verificar(servicioArray[0]);
			plantilla2.put("nombre",servicioArray[0]);
			plantilla2.put("informativo",obj_informativo.getCaptura());
						
			servicio3=s[2];
			servicioArray=servicio3.split("\\.");
			obj_informativo.verificar(servicioArray[0]);
			plantilla3.put("nombre",servicioArray[0]);
			plantilla3.put("informativo",obj_informativo.getCaptura());}
		
		if (s.length==4){
			num_servicios=s.length;
			servicio1=s[0];
			servicioArray=servicio1.split("\\.");
			obj_informativo.verificar(servicioArray[0]);
			plantilla1.put("nombre",servicioArray[0]);
			plantilla1.put("informativo",obj_informativo.getCaptura());
					
			servicio2=s[1];
			servicioArray=servicio2.split("\\.");
			obj_informativo.verificar(servicioArray[0]);
			plantilla2.put("nombre",servicioArray[0]);
			plantilla2.put("informativo",obj_informativo.getCaptura());
						
			servicio3=s[2];
			servicioArray=servicio3.split("\\.");
			obj_informativo.verificar(servicioArray[0]);
			plantilla3.put("nombre",servicioArray[0]);
			plantilla3.put("informativo",obj_informativo.getCaptura());
			
			servicio4=s[3];
			servicioArray=servicio4.split("\\.");
			obj_informativo.verificar(servicioArray[0]);
			plantilla4.put("nombre",servicioArray[0]);
			plantilla4.put("informativo",obj_informativo.getCaptura());}
		try{
		elconstructor.inicio(num_obj,num_condiciones,objetivos,s);
		objeto_plantilla.construirplantilla(plantilla1,plantilla2,plantilla3,plantilla4,num_servicios);
		}catch(IOException ioex){};
	}
	
	}
	