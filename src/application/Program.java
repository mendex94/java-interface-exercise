package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import model.entities.Contract;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter contract data");
        System.out.print("Number: ");

        Integer number = sc.nextInt();
        sc.nextLine();

        System.out.print("Date (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.nextLine(), formatter);

        System.out.print("Contract value: ");
        Double totalValue = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter number of installments: ");
        Integer installments = sc.nextInt();
        sc.nextLine();

        Contract contract = new Contract(totalValue, date, number);

        System.out.println("Installments:");
        ContractService contractService = new ContractService(new PaypalService());
        contractService.proccessContract(contract, installments);

        contract.getInstallments().forEach(System.out::println);
    }
}
