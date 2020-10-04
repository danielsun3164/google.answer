package kickstart.year2020.roundA;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc7/00000000001d3f56
 */
public class ProblemA {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print(IntStream.rangeClosed(1, scanner.nextInt()).boxed()
					.reduce(new StringBuilder(),
							(sb, i) -> sb.append("Case #").append(i).append(": ").append(solve(scanner)).append("\n"),
							(x, y) -> x)
					.toString());
		}
	}

	private static int solve(Scanner scanner) {
		int n = scanner.nextInt(), b = scanner.nextInt();
		int[] a = IntStream.range(0, n).map(i -> scanner.nextInt()).sorted().toArray();
		int sum = 0, count = 0;
		for (int ai : a) {
			if (sum + ai > b) {
				break;
			}
			sum += ai;
			count++;
		}
		return count;
	}
}
