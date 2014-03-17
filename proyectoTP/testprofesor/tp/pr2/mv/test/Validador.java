package tp.pr2.mv.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

import tp.pr2.mv.CPU;
import tp.pr2.mv.ProgramMV;
import tp.pr2.mv.cmdprompt.Prompt;

public class Validador {

	
	@Test
	public void test1()
	{
		genera("program1");
		compara("program1");
	}
	
	@Test
	public void test2()
	{
		genera("program2");
		compara("program2");
	}
	
	@Test
	public void test3()
	{
		genera("program3");
		compara("program3");
	}
	
	@Test
	public void test4()
	{
		genera("program4");
		compara("program4");
	}
	

	private void compara(String file) {
			System.out.println("Comparando "+file+".out (en raiz del proyecto) con la salida experada desde: /tp/pr2/mv/test/programs/"+file+".out");
			try {
			Scanner obtained = new Scanner(new File(file+".out"));
			
			Scanner expected = new Scanner(Validador.class.getResourceAsStream("/tp/pr2/mv/test/programs/"+file+".out"));

			while(expected.hasNext() && obtained.hasNext())
				assertEquals("Las lineas no coinciden",expected.nextLine(),obtained.nextLine());
			
			assertEquals("Ambos ficheros deben tener el mismo numero de lineas",expected.hasNext(),obtained.hasNext());
			
			System.out.println("Comparación correcta");
			
			obtained.close();
			expected.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
	}


	private static void genera(String file) {
		
		System.out.println("Programa:  /tp/pr2/mv/test/programs/"+file+".in");
		System.out.println("Comandos: /tp/pr2/mv/test/programs/"+file+".prompt");
		System.out.println("Generando salida en el directorio raiz del proyecto. Archivo: "+file+".out");
		
		PrintStream defaultOut = System.out;
		try {
			System.setOut(new PrintStream(file+".out"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(Validador.class.getResourceAsStream("/tp/pr2/mv/test/programs/"+file+".in"));
		ProgramMV programMV = new ProgramMV();
		programMV.readProgram(sc);
		sc.close();
	
		CPU cpu = new CPU();
		cpu.loadProgram(programMV);

		sc = new Scanner(Validador.class.getResourceAsStream("/tp/pr2/mv/test/programs/"+file+".prompt"));
		
		Prompt prompt = new Prompt();
		prompt.runPrompt(sc, cpu);
		sc.close();

		System.setOut(defaultOut);	
		
	}
	
}
