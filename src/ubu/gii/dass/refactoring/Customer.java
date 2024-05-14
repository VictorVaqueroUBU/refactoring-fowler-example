package ubu.gii.dass.refactoring;

/**
* Tema  Refactorizaciones 
*
* Ejemplo de aplicaci�n de refactorizaciones. Actualizado para colecciones gen�ricas de java 1.5
*
* @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos L�pez</A>
* @version 1.1
* @see java.io.File
*
*/
import java.util.*;

public class Customer {
	private String _name;
	private List<Rental> _rentals;

	public Customer(String name) {
		_name = name;
		_rentals = new ArrayList<Rental>();

	};

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	public String getName() {
		return _name;
	};

	public String statement(boolean generateInHTML) {
		double totalAmount = 0;
		String result = "";
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = _rentals.iterator();

		if (generateInHTML) {
			result += "<h1>";
		}
		result = "Rental Record for " + getName() + "\n";

		if (generateInHTML) {
			result += "</h1>";
		} else {
			result += "\n";
		}

		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental rental = rentals.next();
			thisAmount += rental.calculateAmount();
			frequentRenterPoints += rental.increaseFrequentRenterPoints();
			// show figures for this rental
			if (generateInHTML) {
				result += "<H2>" + rental.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "</H2>";
			} else {
				result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
			}

			totalAmount += thisAmount;
		}
		// add footer lines
		if (generateInHTML) {
			result += "<p> Amount owed is " + String.valueOf(totalAmount) + "</p>";
			result += "<p> You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points </p>";
		} else {
			result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
			result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		}

		return result;
	}
}
