package kickstart.year2020.roundA;

import org.junit.jupiter.api.Test;

import testBase.TestBase;

public class ProblemDTest extends TestBase {

	@Test
	public void case1() {
		check("2\n" + "2 2\n" + "KICK\n" + "START\n" + "8 2\n" + "G\n" + "G\n" + "GO\n" + "GO\n" + "GOO\n" + "GOO\n"
				+ "GOOO\n" + "GOOO", "Case #1: 0" + LF + "Case #2: 10");
	}

	@Test
	public void case2() {
		check("1\n" + "6 3\n" + "RAINBOW\n" + "FIREBALL\n" + "RANK\n" + "RANDOM\n" + "FIREWALL\n" + "FIREFIGHTER",
				"Case #1: 6");
	}
}
