package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entities.Produto;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		List<Produto> produtos = new ArrayList<>();
		List<String> bwlinhas = new ArrayList<>();

		String caminhoDoArquivo = "C:\\Users\\hades\\Desktop\\in.csv";
		String caminhoDaPasta = "C:\\Users\\hades\\Desktop";

		boolean sucesso = new File(caminhoDaPasta + "\\out").mkdir();
		System.out.println("Diretório criado com sucesso: " + sucesso);
		String saidaDoCaminhoDoArquivo = "C:\\Users\\hades\\Desktop\\out\\summary.csv";

		String nome, linha = null;
		double valor;
		int quantidade;
		String[] textoSeparado;

		// Ler o arquivo 
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoArquivo))) {
			linha = br.readLine();

			while (linha != null) {

				textoSeparado = linha.split(",");
				nome = textoSeparado[0];
				valor = Double.parseDouble(textoSeparado[1]);
				quantidade = Integer.parseInt(textoSeparado[2]);
				produtos.add(new Produto(nome, valor, quantidade));

				linha = br.readLine();
			}
			System.out.println("Lido com sucesso! ");
			String total = null;
			
			for (Produto prod : produtos) {
				total = prod.getNome() + "," + String.format("%.2f", prod.total());
				bwlinhas.add(total);
				
				// Cria e escreve o arquivo
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(saidaDoCaminhoDoArquivo))) {

					for (String bwlinha : bwlinhas) {
						bw.write(bwlinha);
						bw.newLine();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Concluído com sucesso! ");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
