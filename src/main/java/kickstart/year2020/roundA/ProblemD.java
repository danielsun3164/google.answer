package kickstart.year2020.roundA;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc7/00000000001d3ff3
 *
 * source code following analysis
 */
public class ProblemD {

	/** Alphabet size (# of symbols) */
	static final int ALPHABET_SIZE = 26;

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print(IntStream.rangeClosed(1, scanner.nextInt()).boxed()
					.reduce(new StringBuilder(),
							(sb, i) -> sb.append("Case #").append(i).append(": ").append(solve(scanner)).append("\n"),
							(x, y) -> x)
					.toString());
		}
	}

	private static long solve(Scanner scanner) {
		root = new TrieNode();
		int n = scanner.nextInt(), k = scanner.nextInt();
		IntStream.range(0, n).forEach(i -> insert(scanner.next()));
		return count(root, k);
	}

	/**
	 * https://www.geeksforgeeks.org/trie-insert-and-search/
	 */
	private static class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];

		int count = 0;
		// isEndOfWord is true if the node represents end of a word
		boolean isEndOfWord;

		TrieNode() {
			isEndOfWord = false;
			Arrays.fill(children, null);
		}
	}

	private static TrieNode root;

	/**
	 * If not present, inserts key into trie<br/>
	 * If the key is prefix of trie node, just marks leaf node
	 *
	 * @param key
	 */
	private static void insert(String key) {
		TrieNode pCrawl = root;

		for (char c : key.toCharArray()) {
			int index = c - 'A';
			if (pCrawl.children[index] == null) {
				pCrawl.children[index] = new TrieNode();
			}
			pCrawl = pCrawl.children[index];
			pCrawl.count++;
		}

		// mark last node as leaf
		pCrawl.isEndOfWord = true;
	}

	/**
	 * Returns true if key presents in trie, else false
	 *
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean search(String key) {
		TrieNode pCrawl = root;
		for (char c : key.toCharArray()) {
			int index = c - 'A';
			if (pCrawl.children[index] == null) {
				return false;
			}
			pCrawl = pCrawl.children[index];
		}

		return (pCrawl != null && pCrawl.isEndOfWord);
	}

	private static long count(TrieNode parrent, int k) {
		long sum = 0L;
		for (TrieNode child : parrent.children) {
			if (null != child) {
				sum += child.count / k;
				sum += count(child, k);
			}
		}
		return sum;
	}
}
