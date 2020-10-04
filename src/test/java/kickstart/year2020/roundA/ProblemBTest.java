package kickstart.year2020.roundA;

import org.junit.jupiter.api.Test;

import testBase.TestBase;

public class ProblemBTest extends TestBase {

	@Test
	public void case1() {
		check("2\n" + "2 4 5\n" + "10 10 100 30\n" + "80 50 10 50\n" + "3 2 3\n" + "80 80\n" + "15 50\n" + "20 10",
				"Case #1: 250" + LF + "Case #2: 180");
	}
}
