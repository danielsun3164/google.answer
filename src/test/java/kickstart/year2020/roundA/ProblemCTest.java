package kickstart.year2020.roundA;

import org.junit.jupiter.api.Test;

import testBase.TestBase;

public class ProblemCTest extends TestBase {

	@Test
	public void case1() {
		check("1\n" + "3 1\n" + "100 200 230", "Case #1: 50");
	}

	@Test
	public void case2() {
		check("3\n" + "5 2\n" + "10 13 15 16 17\n" + "5 6\n" + "9 10 20 26 30\n" + "8 3\n" + "1 2 3 4 5 6 7 10",
				"Case #1: 2" + LF + "Case #2: 3" + LF + "Case #3: 1");
	}
}
