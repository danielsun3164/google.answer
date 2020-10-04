package kickstart.year2020.roundA;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc7/00000000001d3f5b
 */
public class ProblemC {

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
		int n = scanner.nextInt(), k = scanner.nextInt();
		int[] diff = new int[n - 1];
		IntStream.range(0, n - 1).reduce(scanner.nextInt(), (prev, i) -> {
			int now = scanner.nextInt();
			diff[i] = now - prev;
			return now;
		});
		int left = 0, right = Arrays.stream(diff).max().getAsInt();
		while (left < right - 1) {
			int middle = (left + right) / 2;
			if (Arrays.stream(diff).map(mi -> (mi + middle - 1) / middle - 1).sum() <= k) {
				right = middle;
			} else {
				left = middle;
			}
		}
		return right;
	}
}
