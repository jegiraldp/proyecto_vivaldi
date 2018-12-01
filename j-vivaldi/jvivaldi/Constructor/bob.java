package Constructor;

import java.util.Hashtable;
import java.io.*;

import TraductorA.RecolectorOWLS;


/**
 * Esta clase ayuda a la construcción el BPEL abstracto
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
public class bob {
	
	int numeroCondiciones;
	int contadorServicios=-1;
	int contadorServicios2=-1;
	public String pre;
	BufferedReader entrada,entrada2;
	File herramientaBob, herramientaBob2;
	RecolectorOWLS obj_RecolectorOWLS;
	PrintWriter pw;
	FileWriter fw;
	boolean nuevaLinea = true; 
	
	
	/**
	 * @param num_obj numero objetivos
	 * @param num_condiciones numero condiciones
	 * @param objetivos Plantilla Objetivos
	 * @param serWeb array de servicios 
	 */
public void inicio(int num_obj, int num_condiciones,Hashtable objetivos,String[] serWeb)throws IOException{
		String linea,linea2,ayuda,ayuda2,efecto,efecto2,efecto2A,objetivo1,objetivo2,ayudapre;
		String[] serWebArray,serWebArray1,serWebArray2;
		fw = new FileWriter (".\\plan.txt");
		pw= new PrintWriter(fw);
	try{
		numeroCondiciones=num_condiciones-1;
		herramientaBob = new File(".\\temporales\\TemporalDominio.txt");
		herramientaBob2 = new File(".\\Compositor\\ArchivoProblema.pddl");
		entrada = new BufferedReader(new FileReader(herramientaBob));
		entrada2 = new BufferedReader(new FileReader(herramientaBob2));
	    }catch(IOException ioex){System.out.println("No se puede crear archivo");}	
		
		if(num_obj==1){
			ayuda=objetivos.get("objetivo1").toString();
			ayuda="("+ayuda+")";
			efecto2=ayuda.trim();
			linea=entrada.readLine();
			while((linea=entrada.readLine()) != null){
				if (linea.startsWith(":precondition")){
					contadorServicios2++;
					pre=entrada.readLine();
					if(pre.startsWith("(and")){
						ayudapre=pre.substring(pre.indexOf('(')+5,pre.indexOf(')'));
							if(ayudapre.equals(efecto2)){
							ayuda=serWeb[contadorServicios];
							serWebArray=ayuda.split("\\.");
							System.out.println(serWebArray[0]);
						}
					}
				}
				if (linea.startsWith(":effect")){
					contadorServicios++;
					ayuda2=linea.substring(linea.indexOf(':')+7, linea.indexOf(')')+1);
					efecto=ayuda2.trim();
						if(efecto.equals(efecto2)){
							ayuda=serWeb[contadorServicios];
							serWebArray=ayuda.split("\\.");
							ayuda=serWeb[contadorServicios-1];
							serWebArray1=ayuda.split("\\.");
							ayuda=serWeb[contadorServicios-2];
							serWebArray2=ayuda.split("\\.");
							pw.println("Orden de ejecución -- CompositorSW-UN 2006 ..Creado online");
							pw.println(serWebArray2[0]);
							pw.println(serWebArray1[0]);
							pw.println(serWebArray[0]);
							pw.flush();
						}
				}
				
			}
		}
			
		if(num_obj==2){
			linea2=entrada2.readLine();
			while((linea2=entrada2.readLine()) != null){
				if (linea2.startsWith("(:goal")){
					linea2=entrada2.readLine();
					if(linea2.startsWith("(and")){//define un censado
						objetivo1=objetivos.get("objetivo1").toString();
						efecto2=objetivo1.trim();
						objetivo2=objetivos.get("objetivo2").toString();
						efecto2A=objetivo2.trim();
						linea=entrada.readLine();
						while((linea=entrada.readLine()) != null){
							if (linea.startsWith(":effect")){
								contadorServicios++;
								ayuda2=linea.substring(linea.indexOf(':')+7, linea.indexOf(')')+1);
								efecto=ayuda2.trim();
								if(efecto.equals("("+efecto2+")")){
									ayuda=serWeb[contadorServicios];
									serWebArray=ayuda.split("\\.");
									pw.println(serWebArray[0]);
									pw.flush();}
									
								if(efecto.equals("("+efecto2A+")")){
									ayuda=serWeb[contadorServicios];
									serWebArray=ayuda.split("\\.");
									pw.println(serWebArray[0]);
									pw.flush();}
							}
						}
					}
				}
			}
		}
			
		pw.close();
	}//metodo
}//clase



