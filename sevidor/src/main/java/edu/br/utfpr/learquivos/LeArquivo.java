package edu.br.utfpr.learquivos;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.sql.*;
import java.net.*;

public class LeArquivo {
	
	private static File arquivo = null;
	private static FileReader leitor;
	private static BufferedReader leitorBuffer;
	
	public LeArquivo() {
		
		this.arquivo = new File(System.getProperty("user.dir")+"/src/main/java/edu/br/utfpr/learquivos/index.html");
	}
	public LeArquivo(File arquivo) {
		
		this.arquivo = arquivo;
	}
	
	
	//
	//public static ArrayList<String>
	public static ArrayList<String> leArquivo() throws IOException, ClassNotFoundException{
		
		ArrayList<String> pagina = new ArrayList<String>();
		
		leitor = new FileReader(arquivo);
		leitorBuffer = new BufferedReader(leitor);
		
		//String url = System.getProperty("user.dir");//getClass().getResource(System.getProperty("user.dir");//getClass().getSimpleName()+".class").getPath();
		//System.out.println("Caminho "+url);
				
		while(leitorBuffer.ready()) {
			StringBuilder aux = new StringBuilder();
			aux.append(leitorBuffer.readLine());
			
			pagina.add(aux.toString());
			
			System.out.println(aux.toString());
		}
		
		return pagina;
	}

}
