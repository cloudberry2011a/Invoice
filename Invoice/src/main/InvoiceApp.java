package main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Scanner;

public class InvoiceApp {

	public static void main(String[] args) 
	{
        final BigDecimal SALES_TAX_PCT = new BigDecimal(".05");

		System.out.println("Welcome to the Invoice Total Calculator");
        System.out.println();
        
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        
        String choice = "y";
        while (choice.equalsIgnoreCase("y"))
        {
				System.out.print("Enter customer type ((r)egular/(s)pecial: ");
				String customerType = scanner.next();
        		System.out.print("Enter subtotal: ");
        		String subtotalString = scanner.next();
        		BigDecimal subtotal = new BigDecimal(subtotalString);
        		BigDecimal discountPercent = determineDiscount(subtotal, customerType);
        		
        		BigDecimal discountAmount = calculateDiscount(subtotal, discountPercent);
        		BigDecimal totalBeforeTax = subtotal.subtract(discountAmount);
        		BigDecimal salesTax = totalBeforeTax.multiply(SALES_TAX_PCT).setScale(2, RoundingMode.HALF_UP);
        		BigDecimal total = totalBeforeTax.add(salesTax);
        		
        		formatResult(discountPercent, discountAmount, totalBeforeTax, salesTax, total);


        		// See if the user wants to continue
        		System.out.print("Continue? (y/n): ");
        		choice = scanner.next();
        		System.out.println();
        }
	}

	private static void formatResult(BigDecimal discountPercent, BigDecimal discountAmount, BigDecimal totalBeforeTax,
			BigDecimal salesTax, BigDecimal total) {
		// format and display the results
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		NumberFormat percent = NumberFormat.getPercentInstance();
		String message = 
		    "Discount percent: " + percent.format(discountPercent) + "\n"
		  + "Discount amount:  " + currency.format(discountAmount) + "\n"
		  + "Total before tax: " + currency.format(totalBeforeTax) + "\n"
		  + "Sales tax:        " + currency.format(salesTax) + "\n"
		  + "Invoice total:    " + currency.format(total) + "\n";
		System.out.println(message);
	}

	private static BigDecimal calculateDiscount(BigDecimal subtotal, BigDecimal discountPercent) 
	{
		BigDecimal discountAmount = subtotal.multiply(discountPercent).setScale(2, RoundingMode.HALF_UP);
		return discountAmount;
	}

	private static BigDecimal determineDiscount(BigDecimal subtotal, String customerType) 
	{
		BigDecimal discountPercent;
		double value = subtotal.doubleValue();
		
		switch(customerType)
		{
			case "r":
			case "R":	
				if(value < 100)
					discountPercent = new BigDecimal("0.0");
				else if (value > 100 && value < 250)
					discountPercent = new BigDecimal("0.1");
				else
					discountPercent = new BigDecimal("0.2");
				break;
			case "s":
			case "S":
				if(value < 100)
					discountPercent = new BigDecimal("0.0");
				else if (value > 100 && value < 250)
					discountPercent = new BigDecimal("0.2");
				else
					discountPercent = new BigDecimal("0.25");
				break;
			default:
				discountPercent =  new BigDecimal("0.0");
				break;
		}
		
		return discountPercent;
	}

	@SuppressWarnings("unused")
	private static double calculateDiscount(double subtotal, double discountPercent) 
	{
		return subtotal * discountPercent;
	}

	@SuppressWarnings("unused")
	private static double determineDiscount(double subtotal) 
	{
		if (subtotal >= 200)
		{
			return 0.2;
		}
		else if (subtotal >= 100)
		{
			return 0.1;
		}
		else
		{
			return 0.0;
		}
	}
}