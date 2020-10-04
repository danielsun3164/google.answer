package kickstart.year2020.roundA;

import org.junit.jupiter.api.Test;

import testBase.TestBase;

public class ProblemATest extends TestBase {

	@Test
	public void case1() {
		check("3\n" + "4 100\n" + "20 90 40 90\n" + "4 50\n" + "30 30 10 10\n" + "3 300\n" + "999 999 999",
				"Case #1: 2" + LF + "Case #2: 3" + LF + "Case #3: 0");
	}
}
