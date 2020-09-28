
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mariana
 */
public class FakeBancoDeDados {
    
    private static Vector<Produto> produtos;
    
    //Leitura das informações do arquivo excel
    private static void cargaArquivo(){
        
        
        //Ajuste na criação do vetor de produto static
        if(produtos == null){
            produtos = new Vector<>();
        }else{
            produtos.clear();
        }
        
        File arquivoCsv = new File("C:\\Users\\Mariana\\Documents\\IFMG - 2020\\Desenvolvimento de Tópicos de Software\\Exercício Avaliativo 2\\produtos.csv");
        
        
        try{
            //Estruturas de leitura do arquivo
              FileReader marcaLeitura = new FileReader(arquivoCsv);
              
              BufferedReader bufLeitura = new BufferedReader(marcaLeitura);
              
              //**************ler cada linha**************************
              //lendo primeira linha do arquivo(cabeçalho) - descartar 
              bufLeitura.readLine();
              String linha = bufLeitura.readLine();
              
              while(linha != null){
                  //linhas seguintes até o final do arquivo
                  String infos[] = linha.split(";");
                  
                  int cod = Integer.parseInt(infos[0]);
                  String nome = infos[1];
                  double prec = Double.parseDouble(infos[2]);
                  int quant = Integer.parseInt(infos[3]);
                  
                  
                  //add dos produtos para o vetor dinâmico
                  produtos.add(new Produto(cod, nome, prec, quant));
                  
                  //a próxima linha do arquivo
                  linha = bufLeitura.readLine();
                 
              }
              
              //liberando o arquivo para outros processos
              bufLeitura.close();
              
        }catch(FileNotFoundException ex){
            System.err.println("Arquivo especificado não existe");
        }catch(IOException e){
            System.err.println("Arquivo corrompido");
        }
      
        
    }
    
    public static Produto consultaProdutCod(int cod){
        //Se o arquivo ainda não foi carregado precisamos carregar 
        if(produtos == null){
            cargaArquivo();
        }
        //Busca pelo produto com o código especificado 
        for(Produto prodI : produtos){
            if(prodI.getCodigo() == cod){
                return prodI;
            }
        }
        //Não existe produto com o código especificado no parâmetro 
        return null;
    }
    
    
    public static void atualizaArquivo(){
        File arquivo = new File("C:\\Users\\Mariana\\Documents\\IFMG - 2020\\Desenvolvimento de Tópicos de Software\\Exercício Avaliativo 2\\produtos.csv");
        try {
            FileWriter escritor = new FileWriter(arquivo);
            
            BufferedWriter bufEscrita = new BufferedWriter(escritor);
            
            for(int i = 0; i < produtos.size(); i++){
                bufEscrita.write(produtos.get(i) + "\n");
            }
            
            bufEscrita.flush();
            bufEscrita.close();
            
        } catch (IOException ex) {
            System.out.println("Dispositivo com falha");
        }
        
    }
}
