import java.util.Scanner;

public class Principal {
    private static final ExchangeRateService rateService = new ExchangeRateService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("\n=== CONVERSOR DE MONEDAS ===");
            System.out.println("1. Convertir de Dólar a Peso Chileno");
            System.out.println("2. Convertir de Peso Chileno a Dólar");
            System.out.println("3. Convertir de Euro a Real");
            System.out.println("4. Convertir de Real a Euro");
            System.out.println("5. Convertir de Euro a Peso Chileno");
            System.out.println("6. Convertir de Peso Chileno a Euro");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción de las anteriores: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> convert("USD", "CLP");
                case 2 -> convert("CLP", "USD");
                case 3 -> convert("EUR", "BRL");
                case 4 -> convert("BRL", "EUR");
                case 5 -> convert("EUR", "CLP");
                case 6 -> convert("CLP", "EUR");
                case 0 -> System.out.println("Gracias por usar el conversor.");
                default -> System.out.println("Opción inválida.");
            }
        } while (option != 0);

        scanner.close();
    }

    private static void convert(String from, String to) {
        System.out.printf("Ingrese monto en %s: ", from);
        double amount = scanner.nextDouble();

        double result = rateService.convert(from, to, amount);
        if (result >= 0) {
            System.out.printf("Resultado: %.2f %s = %.2f %s%n", amount, from, result, to);
        } else {
            System.out.println("Error al realizar la conversión.");
        }
    }
}
