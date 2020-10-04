package kickstart.year2020.roundA;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc7/00000000001d40bb
 *
 * source code following analysis
 */
public class ProblemB {

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
		int n = scanner.nextInt(), k = scanner.nextInt(), p = scanner.nextInt();
		int[][] sum = new int[n + 1][k + 1], dp = new int[n + 1][p + 1];
		IntStream.rangeClosed(0, n).forEach(i -> {
			sum[i][0] = 0;
			Arrays.fill(dp[i], 0);
		});
		IntStream.rangeClosed(1, n)
				.forEach(i -> IntStream.rangeClosed(1, k).forEach(j -> sum[i][j] = sum[i][j - 1] + scanner.nextInt()));
		IntStream.rangeClosed(1, n)
				.forEach(i -> IntStream.rangeClosed(0, p).forEach(j -> IntStream.rangeClosed(0, Math.min(k, j))
						.forEach(x -> dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - x] + sum[i][x]))));
		return dp[n][p];
	}
}
