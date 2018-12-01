package Compositor;
import java.io.*;
import java.util.*;

/**
 * Esta clase construye el archivo problema.pddl
 * @author Universidad Nacional de Colombia, Escuela de Sistemas- GIDIA 
 */
public class pddlProblema {
	
		public Hashtable objetivos = new Hashtable();
		public Hashtable operadores = new Hashtable();
		public Hashtable condiciones = new Hashtable();
		
		FileWriter fw,fwLimpiar;
		PrintWriter pw;
				
		/**
		 * @param num_obj número objetivos
		 * @param num_condiciones número condiciones
		 */
		public void contruir_archivo(int num_obj, int num_condiciones)throws IOException{
			
		int Numerocondiciones = num_condiciones-1;
		int Numeroobjetivos = num_obj;
		String objetos, objetos2,objetos3,objetos4; 
		String[] objetos_condi1,objetos_condi2,objetos_condi3,objetos_condi4;
		
		fw = new FileWriter (".\\Compositor\\ArchivoProblema.pddl");
		pw = new PrintWriter (fw);
				
		pw.println(";;UN - 2006 (Creado dinámicamente)");
		pw.println("(define (problem problema)");
		pw.println("(:domain dominio)");
		if(Numerocondiciones==0)System.out.println("Debe existir almenos una condición inicial");
		if(Numerocondiciones==1){
			objetos=condiciones.get("condicion1").toString();
			objetos_condi1=objetos.split(" ");
			pw.print("(:objects "+objetos_condi1[1]+")");
			}
		if(Numerocondiciones==2){
			objetos=condiciones.get("condicion1").toString();
			objetos2=condiciones.get("condicion2").toString();
			objetos_condi1=objetos.split(" ");
			objetos_condi2=objetos2.split(" ");
			pw.print("(:objects "+objetos_condi1[1]+" "+objetos_condi2[1]+" - objeto)");
			}
		
		if(Numerocondiciones==3){
			objetos=condiciones.get("condicion1").toString();
			objetos2=condiciones.get("condicion2").toString();
			objetos3=condiciones.get("condicion3").toString();
			objetos_condi1=objetos.split(" ");
			objetos_condi2=objetos2.split(" ");
			objetos_condi3=objetos3.split(" ");
			pw.print("(:objects "+objetos_condi1[1]+" "+objetos_condi2[1]+" "+objetos_condi3[1]+" - objeto)");
			}
		if(Numerocondiciones==4){
			objetos=condiciones.get("condicion1").toString();
			objetos2=condiciones.get("condicion2").toString();
			objetos3=condiciones.get("condicion3").toString();
			objetos4=condiciones.get("condicion4").toString();
			objetos_condi1=objetos.split(" ");
			objetos_condi2=objetos2.split(" ");
			objetos_condi3=objetos3.split(" ");
			objetos_condi4=objetos4.split(" ");
			pw.print("(:objects "+objetos_condi1[1]+" "+objetos_condi2[1]+" "+objetos_condi3[1]+" "+objetos_condi4[1]+" - objeto)");
			}
					
		pw.println(""); 
		pw.println("(:init"); 
		
		for (int i=1;i<=Numerocondiciones;i++){
			pw.println("("+condiciones.get("condicion"+i)+")");}		
		pw.println(")");
		pw.println("(:goal");
		if(Numeroobjetivos==1)pw.println("("+objetivos.get("objetivo1")+"))");
		if(Numeroobjetivos==2){pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+objetivos.get("objetivo2")+"))");
		pw.println(")");}
		if(Numeroobjetivos==3){if(operadores.get("operador1").toString().equals(operadores.get(("operador2").toString()))){
			pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+objetivos.get("objetivo2")+")("+objetivos.get("objetivo3")+")))");
			}else pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+
					")("+operadores.get("operador2")+"("+objetivos.get("objetivo2")+")("+objetivos.get("objetivo3")+")))");
			pw.println(")");}
		
		if(Numeroobjetivos==4){if(operadores.get("operador1").toString().equals(operadores.get("operador2").toString())){
			if(operadores.get("operador2").toString().equals(operadores.get("operador3").toString())){
			pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+objetivos.get("objetivo2")+
					")("+objetivos.get("objetivo3")+")("+objetivos.get("objetivo4")+")))");
			}else pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+objetivos.get("objetivo2")+
					")("+operadores.get("operador3")+"("+objetivos.get("objetivo3")+")("+objetivos.get("objetivo4")+"))))");
			}else if(operadores.get("operador2").toString().equals(operadores.get("operador3").toString())){
				pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+operadores.get("operador2")+"("+
						objetivos.get("objetivo2")+")("+objetivos.get("objetivo3")+")("+objetivos.get("objetivo4")+")))");
			}else pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+operadores.get("operador2")+"("+
					objetivos.get("objetivo2")+")("+operadores.get("operador3")+"("+objetivos.get("objetivo3")+")("+objetivos.get("objetivo4")+")))))");
 
			}
		
		if(Numeroobjetivos==5){if(operadores.get("operador1").toString().equals(operadores.get("operador2").toString())){
			if(operadores.get("operador2").toString().equals(operadores.get("operador3").toString())){
				if(operadores.get("operador3").toString().equals(operadores.get("operador4").toString())){
					pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+objetivos.get("objetivo2")+
							")("+objetivos.get("objetivo3")+")("+objetivos.get("objetivo4")+")("+objetivos.get("objetivo5")+")))");
					}else pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+objetivos.get("objetivo2")+
					")("+objetivos.get("objetivo3")+")("+operadores.get("operador4")+"("+objetivos.get("objetivo4")+")("+objetivos.get("objetivo5")+"))))");
  
			}else if (operadores.get("operador3").toString().equals(operadores.get("operador4").toString())){
				pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+objetivos.get("objetivo2")+
						")("+operadores.get("operador3")+"("+objetivos.get("objetivo3")+")("+objetivos.get("objetivo4")+
						")("+objetivos.get("objetivo5")+"))))");	
			}else pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+objetivos.get("objetivo2")+
					")("+operadores.get("operador3")+"("+objetivos.get("objetivo3")+")("+operadores.get("operador4")+"("+
					objetivos.get("objetivo4")+")("+objetivos.get("objetivo5")+")))))");	
			}else if (operadores.get("operador2").toString().equals(operadores.get("operador3").toString())){
				if(operadores.get("operador3").toString().equals(operadores.get("operador4").toString())){
					pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+objetivos.get("objetivo2")+")("+
							operadores.get("operador2")+"("+objetivos.get("objetivo3")+")("+objetivos.get("objetivo4")+
							")("+objetivos.get("objetivo5")+"))))");
				}else pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+operadores.get("operador2")+
						"("+objetivos.get("objetivo2")+")("+objetivos.get("objetivo3")+")("+operadores.get("operador4")+"("+
						objetivos.get("objetivo4")+")("+objetivos.get("objetivo5")+")))))");
			}else pw.println("("+operadores.get("operador1")+"("+objetivos.get("objetivo1")+")("+operadores.get("operador2")+
					"("+objetivos.get("objetivo2")+")("+operadores.get("operador3")+"("+objetivos.get("objetivo3")+")("+
					operadores.get("operador4")+"("+ objetivos.get("objetivo4")+")("+objetivos.get("objetivo5")+"))))))");
					
			}
		pw.println(")");
		pw.close();	
		System.out.println("> Archivo problema.pddl creado <");
		fwLimpiar = new FileWriter (".\\temporales\\TemporalProblema.txt");
		
		}
		

		/**
		 * @param num_obj número objetivos
		 * @param num_condiciones número condiciones
		 */		
		public void RecuperarDatosInterfaz (int num_obj, int num_condiciones)throws IOException{
			String linea;
			BufferedReader entrada;
			entrada = new BufferedReader(new FileReader (".\\temporales\\TemporalProblema.txt"));
			linea=entrada.readLine();
			
				while((linea=entrada.readLine()) != null){
				
				if (linea.startsWith("objetivos")){
					
					if(num_obj==1){//cuando objetivo es uno
						objetivos.put("objetivo1",linea=entrada.readLine());}
					
						if(num_obj==2){//cuando objetivo es dos
						objetivos.put("objetivo1",linea=entrada.readLine());
						operadores.put("operador1",linea=entrada.readLine());
						objetivos.put("objetivo2",linea=entrada.readLine());}
					
					
					if(num_obj==3){//cuando objetivo es tres
						objetivos.put("objetivo1",linea=entrada.readLine());
						operadores.put("operador1",linea=entrada.readLine());
						objetivos.put("objetivo2",linea=entrada.readLine());
						operadores.put("operador2",linea=entrada.readLine());
						objetivos.put("objetivo3",linea=entrada.readLine());}
				
					if(num_obj==4){//cuando objetivo es cuatro
						objetivos.put("objetivo1",linea=entrada.readLine());
						operadores.put("operador1",linea=entrada.readLine());
						objetivos.put("objetivo2",linea=entrada.readLine());
						operadores.put("operador2",linea=entrada.readLine());
						objetivos.put("objetivo3",linea=entrada.readLine());
						operadores.put("operador3",linea=entrada.readLine());
						objetivos.put("objetivo4",linea=entrada.readLine());}
					
					if(num_obj==5){//cuando objetivo es cinco
						objetivos.put("objetivo1",linea=entrada.readLine());
						operadores.put("operador1",linea=entrada.readLine());
						objetivos.put("objetivo2",linea=entrada.readLine());
						operadores.put("operador2",linea=entrada.readLine());
						objetivos.put("objetivo3",linea=entrada.readLine());
						operadores.put("operador3",linea=entrada.readLine());
						objetivos.put("objetivo4",linea=entrada.readLine());
						operadores.put("operador4",linea=entrada.readLine());
						objetivos.put("objetivo5",linea=entrada.readLine());
					
						//System.out.println("Del hastable op4 =" + operadores.get("operador4"));						
						}
					
				
				}//fin linea.startsWith
				
				else if (linea.startsWith("condiciones")){
					if(num_condiciones==2){
						condiciones.put("condicion1",linea=entrada.readLine());}
					if(num_condiciones==3){//para cuando hay dos condiciones
						condiciones.put("condicion1",linea=entrada.readLine());
						condiciones.put("condicion2",linea=entrada.readLine());}
					if(num_condiciones==4){//para cuando hay tres condiciones
						condiciones.put("condicion1",linea=entrada.readLine());
						condiciones.put("condicion2",linea=entrada.readLine());
						condiciones.put("condicion3",linea=entrada.readLine());}
					if(num_condiciones==5){//para cuando hay cuatro condiciones
						condiciones.put("condicion1",linea=entrada.readLine());
						condiciones.put("condicion2",linea=entrada.readLine());
						condiciones.put("condicion3",linea=entrada.readLine());
						condiciones.put("condicion4",linea=entrada.readLine());}

				}
				}//fin while
			
			
				
		}
		
		/**
		 * @return condiciones condiciones
		 */	
		public Hashtable getCondiciones(){
			return condiciones;
			
		}
		/**
		 * @return objetivos objetivos
		 */	
		public Hashtable getObjetivos(){
			return objetivos;}
		
		/**
		 * @return operadores operadores
		 */	
		public Hashtable getOperadores(){
			return operadores;}
		
		
 }//finaliza class 
