package TraductorA;

import java.io.*;
import java.util.Hashtable;

import Compositor.pddlProblema;


/**
 * Esta clase el archivo dominio.pddl
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
public class pddlDominio {
		
		String servicios[];
		String servicios2[];
		FileWriter fw;
		boolean nuevaLinea=true;
		BufferedReader entrada, entrada2;
		pddlProblema obj_pddlProblema;  
				
		/**
		 * recupera archivos con extensión owl
		 */
		public void filtro (){
			File f1 = new File(".\\Repositorio");
			System.out.println("--- Servicios Identificados en Repositorio: ---");
			File f2 = new File(".\\Repositorio");
			FilenameFilter obj_FilenameFilter = new soloOWL();
			soloOWL objeto_soloOWL = new soloOWL ();
			servicios=objeto_soloOWL.listarOWL(f1,obj_FilenameFilter);
			servicios2=objeto_soloOWL.listarOWL(f2,obj_FilenameFilter);}
		
		
		/**
		 * @return servicios
		 */
		public String[] getServicios(){
			return servicios;
		}
		/**
		 * @return servicios2
		 */
		public String[] getServicios2(){
			return servicios2;
		}
		
		/**
		 * @param numero_condiciones numero de condiciones
		 */
		public void construir_Dominio(int numero_condiciones)throws IOException{
			int Numerocondiciones = numero_condiciones-1;
			String linea, predicados,linea2;
			entrada = new BufferedReader(new FileReader (".\\temporales\\TemporalDominio.txt"));
			entrada2 = new BufferedReader(new FileReader (".\\temporales\\TemporalPredicados.txt"));
			
			fw = new FileWriter (".\\Compositor\\ArchivoDominio.pddl");
			PrintWriter pw = new PrintWriter (fw);
			pw.println(";;CompositorSW-UN - 2006 Dominio-(Creado dinámicamente)");
			pw.println("(define (domain dominio)");
			pw.println("(:requirements :adl)");
			pw.println("(:predicates");
			linea2=entrada2.readLine();
			while((linea2=entrada2.readLine()) != null){
				if (linea2.startsWith("precondiciones")){
					linea2=entrada2.readLine();
					pw.println(linea2);
					pw.flush();
					
				}else if (linea2.startsWith("efectos")){
					linea2=entrada2.readLine();
					if (linea2.startsWith("(and")||linea2.startsWith("(not")){
					predicados=linea2.substring(linea2.indexOf('(')+4, linea2.indexOf(')')+1);
					pw.println(predicados);
					}else{pw.println(linea2);
					pw.flush();}
					//System.out.println(predicados);
				}
			}
			
			pw.flush();	
			pw.println(")");
			linea=entrada.readLine();
			while((linea=entrada.readLine()) != null){
			pw.println(linea);
			pw.flush();				
			}		
			pw.println(")");
			pw.close();
		}
			
			
			
			
		
	}//fin pddlDominio
/**
 *Clase que retorna solo los archivos con extensión owl 
 * 
 * */
//Clase que retorna solo los archivos con extensión owl
class soloOWL implements FilenameFilter{
	
	String s[];
	public boolean accept(File dir, String name){
	return name.endsWith(".owl");
	}

	/**
	 * @param f
	 * @param FilenameFilter
	 * @return s
	 */
	public String[] listarOWL(File f, FilenameFilter fnf){
 	
	s=f.list(fnf);
 	return s;
	
 	}//finaliza listarOWL*/

}
