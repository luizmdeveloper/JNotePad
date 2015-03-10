package br.com.JNotePad.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;

public class EscreverTXT {

	public static boolean persistirArquivoTXT ( String arquivo, String conteudo) throws IOException {
		
		FileWriter fout  = new FileWriter(arquivo);
		PrintWriter pout = new PrintWriter(fout);
		
		pout.println(conteudo);
		fout.close();
		pout.close();
		
		return true;
	}
	
	public static String abrirArquivoTXT (String arquivo) throws FileNotFoundException, IOException {
		String retorno = "";
		FileReader pint    = new FileReader(arquivo);
		BufferedReader bin = new BufferedReader(pint);
		
		String linha = bin.readLine();
		while (linha != null){
			retorno += linha+"\n";
			linha = bin.readLine();
		}
		pint.close();
		bin.close();
		return retorno;
	}
}
