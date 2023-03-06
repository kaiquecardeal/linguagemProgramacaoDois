import java.util.Scanner;

public class tarefaDois {

    public static void ex1(){
        Scanner sc1 = new Scanner(System.in);

        System.out.print("Digite a temperatura em graus Fahrenheit: ");
        double fahrenheit = sc1.nextDouble();

        double celsius = (5.0 / 9.0) * (fahrenheit - 32);

        System.out.printf("%.2f graus Fahrenheit equivalem a %.2f graus Celsius.", fahrenheit, celsius);
    }

    public static void ex2(){
        Scanner sc1 = new Scanner(System.in);

        System.out.print("Digite a pressão do pneu (em PSI): ");
        double p = sc1.nextDouble();

        System.out.print("Digite o volume do pneu (em m³): ");
        double v = sc1.nextDouble();

        System.out.print("Digite a temperatura do pneu (em °C): ");
        double t = sc1.nextDouble();

        // Converter temperatura de Celsius para Kelvin
        t += 273.15;

        // Calcular a massa de ar
        double m = (p * v) / (0.37 * t);

        System.out.printf("A massa de ar no pneu é de %.2f kg.", m);

        sc1.close();
    }

    public static void ex3(){
        Scanner sc1 = new Scanner(System.in);

        System.out.print("Digite o primeiro termo da PA: ");
        int primeiroTermo = sc1.nextInt();

        System.out.print("Digite a razão da PA: ");
        int razao = sc1.nextInt();

        System.out.print("Digite o número do termo que deseja calcular: ");
        int n = sc1.nextInt();

        // Calcular o n-ésimo termo da PA
        int nEsimoTermo = primeiroTermo + (n - 1) * razao;

        System.out.printf("O %d-ésimo termo da PA é %d.", n, nEsimoTermo);

        sc1.close();
    }
}
