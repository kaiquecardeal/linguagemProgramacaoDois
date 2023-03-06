import java.util.Scanner;

public class tarefaUm {
    public static void main(String[] args) {

       int n1 = 1, n2 = 2, n3 = 3;

       System.out.println(n3);
       System.out.println(n2);
       System.out.println(n1);
       System.out.println("\n");
       //Exercicio 2 abaixo;
       System.out.println("A = " + n3);
       System.out.println("B = " + n1);
       System.out.println("C = " + n2);
    }

    public static void ex3(){
        Scanner sc1 = new Scanner(System.in);

        System.out.println("Olá eu sou o Nº 1. Como é seu nome?");
        String nome = sc1.next();
        System.out.println("Bem-vindo ao clube" + nome);
    }

    public static void ex4(){

        Scanner sc1 = new Scanner(System.in);

        System.out.println("Olá eu sou o Nº 1. Como é seu nome?");
        String nome = sc1.next();

        System.out.println("e qual a sua idade?");
        int n1 = sc1.nextInt();

        int soma = n1 * 365;
        System.out.println("você tem aproximadamente "+soma+" dias de vida.");
    }

    public static void ex5(){

        Scanner sc1 = new Scanner(System.in);

        System.out.print("Digite o raio do círculo: ");
        double raio = sc1.nextDouble();

        double area = Math.PI * Math.pow(raio, 2);

        System.out.println("A área do círculo é: " + area);
    }

    public static void ex6(){
        Scanner sc1 = new Scanner(System.in);

        // pedir as medidas da parede
        System.out.print("Digite a largura da parede em metros: ");
        double largura = sc1.nextDouble();

        System.out.print("Digite a altura da parede em metros: ");
        double altura = sc1.nextDouble();

        // calcular a área da parede em metros quadrados
        double area = largura * altura;

        // calcular a quantidade de tinta necessária em litros
        double litrosDeTinta = area * 0.3;

        // calcular a quantidade de latas de tinta necessária
        int latasDeTinta = (int) Math.ceil(litrosDeTinta / 2.0);

        // imprimir o resultado
        System.out.println("Você precisa de " + latasDeTinta + " latas de tinta.");
    }


}