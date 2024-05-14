package ubu.gii.dass.refactoring;

/**
 * Tema Refactorizaciones
 * 
 * Ejemplo de aplicación de refactorizaciones. Actualizado para colecciones
 * genéricas de java 1.5.
 * 
 * @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos López</A>
 * @version 1.1
 * @see java.io.File
 * 
 */
public class Rental {
	private Movie _movie;
	private int _daysRented;

	public Rental(Movie movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}

	public int getDaysRented() {
		return _daysRented;
	}

	public Movie getMovie() {
		return _movie;
	}

	double calculateAmount() {
		double calculatedAmount = 0;
		// determine amounts for each line
		switch (getMovie().getPriceCode()) {
		case Movie.REGULAR:
			calculatedAmount += 2;
			if (getDaysRented() > 2)
				calculatedAmount += (getDaysRented() - 2) * 1.5;
			break;
		case Movie.NEW_RELEASE:
			calculatedAmount += getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			calculatedAmount += 1.5;
			if (getDaysRented() > 3)
				calculatedAmount += (getDaysRented() - 3) * 1.5;
			break;
		}
		return calculatedAmount;
	}

	int increaseFrequentRenterPoints(int frequentRenterPoints) {
		// add frequent renter points
		frequentRenterPoints++;
		// add bonus for a two day new release rental
		if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

}
