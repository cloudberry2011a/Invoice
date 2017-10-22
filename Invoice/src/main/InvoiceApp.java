package main;

import java.util.Scanner;

public class InvoiceApp {

	public static void main(String[] args) 
	{
		System.out.println("Welcome to the Invoice Total Calculator");
        System.out.println();
        
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        
        String choice = "y";
        while (choice.equalsIgnoreCase("y"))
        {
        		System.out.print("Enter subtotal: ");
        		double subtotal = scanner.nextDouble();
        		
        		double discountPercent = determineDiscount(subtotal);
        		double discountAmount = calculateDiscount(subtotal, discountPercent);
        		double total = subtotal - discountAmount;
        		
        		String message = "Discount percent: " + discountPercent + "\n"
                        + "Discount amount:  " + discountAmount + "\n"
                        + "Invoice total:    " + total + "\n";            
        		System.out.println(message);

         // See if the user wants to continue
         System.out.print("Continue? (y/n): ");
         choice = scanner.next();
         System.out.println();
        }
	}

	private static double calculateDiscount(double subtotal, double discountPercent) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static double determineDiscount(double subtotal) {
		// TODO Auto-generated method stub
		return 0;
	}

}
