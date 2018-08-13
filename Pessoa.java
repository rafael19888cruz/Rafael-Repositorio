/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author fael
 */
public class Pessoa {

    /**
     * @param args the command line arguments
     */
    public static int main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner dados = new Scanner(System.in);
 
    String nome;
    char escolha;
    int nu=0,c=0,j=0;
 
    System.out.printf("Para inserir nome digite --> N\n");
    System.out.printf("Para listar nomes digite --> L\n");
    System.out.println("Para sair digite 0");
    escolha = (char)System.in.read();
    
    if((escolha == 'N') || (escolha == 'n')){
    //System.out.println("Informe a quantidade que deseja inserir: \n");
    //
    for(c=0;c<nu;c++){
    System.out.printf("Informe um nome:\n");
    nome = dados.nextLine();
    System.out.println("Deseja inserir mais um nome --> N ou 0 para sair");
    escolha = (char)System.in.read();
        }
    }
    if((escolha == 'L') || (escolha == 'l')){
        for(j=0;j<nu;j++){
        System.out.printf("%S \n",+ nome[j]);
            }
        }
    if (escolha == '0'){
        return 0;
        }
    }
    
}
