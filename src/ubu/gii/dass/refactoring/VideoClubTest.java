package ubu.gii.dass.refactoring;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tema Refactorizaciones
 * 
 * Ejemplo de aplicación de refactorizaciones. Actualizado para colecciones
 * genéricas de java 1.5
 * 
 * @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos L�pez</A>
 * @version 1.1
 * 
 * 
 */
public class VideoClubTest {
	protected Movie m0, m11, m12, m2, m3;
	protected Customer c1;

	@Before
	public void setUp() {
		m11 = new Movie("Sky Captain", 1);
		m12 = new Movie("Alejandro Magno", 1);
		m0 = new Movie("Accion Mutante", 0);
		m2 = new Movie("Hermano Oso", 2);
		m3 = new Movie("Le llamaban Trinidad", 0);

		c1 = new Customer("Manuel");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAlquiler() {

		m12.setPriceCode(2);
		assertTrue("No modifica el precio", m12.getPriceCode() == 2);

		Rental r1 = new Rental(m11, 5);
		Rental r2 = new Rental(m0, 1);
		Rental r3 = new Rental(m2, 10);
		Rental r4 = new Rental(m3, 10);

		c1.addRental(r1);
		c1.addRental(r2);
		c1.addRental(r3);
		c1.addRental(r4);

		String salida = c1.statement(false);

		String salidaEsperada = new String("Rental Record for Manuel\n" + "\tSky Captain\t15.0\n"
				+ "\tAccion Mutante\t2.0\n" + "\tHermano Oso\t12.0\n" + "\tLe llamaban Trinidad\t14.0\n"
				+ "Amount owed is 43.0\n" + "You earned 5 frequent renter points");

		assertTrue("Calcula mal el alquiler", salidaEsperada.equals(salida));

		salida = c1.statement(true);
		assertFalse("Misma cadena para html", salidaEsperada.equals(salida));
		salidaEsperada = "<h1>Rental Record for Manuel</h1>" + "<H2>Sky Captain	15.0</H2>"
				+ "<H2>Accion Mutante	2.0</H2>" + "<H2>Hermano Oso	12.0</H2>"
				+ "<H2>Le llamaban Trinidad	14.0</H2>" + "<p>Amount owed is 43.0</p>"
				+ "<p>You earned 5 frequent renter points </p>";
		assertTrue("Cadena incorrecta o no suma bien los puntos", salidaEsperada.equals(salida));
	}
}
