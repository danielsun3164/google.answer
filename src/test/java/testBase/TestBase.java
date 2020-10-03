package testBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class TestBase {

	private static InputStream systemIn = System.in;
	private static PrintStream systemOut = System.out;

	protected static StandardInputSnatcher in = new StandardInputSnatcher();
	protected static ByteArrayOutputStream out = new ByteArrayOutputStream();
	private static PrintStream mySystemOut;

	/** システムの改行コード */
	protected static String LF = System.lineSeparator();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setIn(in);
		System.setOut(mySystemOut = new PrintStream(out));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.setOut(systemOut);
		System.setIn(systemIn);
		mySystemOut.close();
		out.close();
	}

	@BeforeEach
	void clearInAndOut() throws IOException {
		out.reset();
		in.clear();
	}

	/**
	 * テストケースを実行した結果をチェック
	 * 
	 * @param expected 予定される結果
	 */
	protected void assertResultIs(String expected) {
		assertEquals(expected + LF, out.toString());
	}

	/**
	 * テストケースを実行した結果をチェック
	 * 
	 * @param expecteds 予定される結果の一覧
	 */
	protected void assertResultIn(String... expecteds) {
		String actualResult = out.toString();
		assertTrue(Arrays.stream(expecteds).filter(s -> (s + LF).equals(actualResult)).count() > 0,
				"result is " + actualResult);
	}

	/**
	 * テストケースを実行した結果をチェック
	 * 
	 * @param regexp 予定される結果の正規表現
	 */
	protected void assertResultMatches(String regexp) {
		assertTrue(out.toString().matches(regexp + LF), "result is " + out.toString());
	}

	/**
	 * テストケースを実行した結果をチェック
	 * 
	 * @param expected 予定される結果
	 */
	protected void assertResultIs(double expected) {
		assertEquals(expected, Double.parseDouble(out.toString()));
	}

	/**
	 * テストケースを実行した結果をチェック
	 * 
	 * @param expected  予定される結果
	 * @param tolerance 誤差範囲
	 */
	protected void assertResultIsAbout(double expected, double tolerance) {
		assertTrue(Math.abs(Double.parseDouble(out.toString()) - expected) < tolerance, "number is " + out.toString());
	}

	/**
	 * テスト対象のメソッドを実行
	 */
	protected void execute() {
		try {
			// テストクラス名から末尾の「Test」を取ったクラス名のクラスを取得し、mainメソッドを実行
			Class<?> clazz = Class.forName(this.getClass().getName().replaceFirst("Test$", ""));
			Method method = clazz.getDeclaredMethod("main", String[].class);
			method.invoke(null, (Object) null);
		} catch (ClassNotFoundException | SecurityException | IllegalArgumentException | NoSuchMethodException
				| IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * テストを実施する
	 * 
	 * @param input    入力文字列
	 * @param expected 予想される実行結果
	 */
	protected void check(String input, String expected) {
		in.input(input);
		execute();
		assertResultIs(expected);
	}

	/**
	 * テストを実施する
	 * 
	 * @param input    入力文字列
	 * @param expected 予想される実行結果の一覧
	 */
	protected void checkResultIn(String input, String... expected) {
		in.input(input);
		execute();
		assertResultIn(expected);
	}

	/**
	 * テストを実施する
	 * 
	 * @param input     入力文字列
	 * @param expected  予想される実行結果
	 * @param tolerance 誤差範囲
	 */
	protected void checkResultIsAbout(String input, double expected, double tolerance) {
		in.input(input);
		execute();
		assertResultIsAbout(expected, tolerance);
	}

	/**
	 * テストを実施する
	 * 
	 * @param input          入力文字列
	 * @param expectedRegexp 予想される実行結果の正規表現
	 */
	protected void checkResultMatches(String input, String expectedRegexp) {
		in.input(input);
		execute();
		assertResultMatches(expectedRegexp);
	}

	/**
	 * テストを実施する
	 * 
	 * @param input    入力文字列
	 * @param expected 予想される実行結果
	 */
	protected void check(String input, double expected) {
		in.input(input);
		execute();
		assertResultIs(expected);
	}

	/**
	 * テストケースを実行した結果をチェック
	 * 
	 * @param number    実行結果の数字形式の文字列
	 * @param expected  予定した結果
	 * @param tolerance 誤差範囲
	 */
	protected void assertNumberIsAbout(String number, double expected, double tolerance) {
		assertTrue(Math.abs(Double.parseDouble(number) - expected) < tolerance, "number is " + number);
	}

	/**
	 * 標準入力を代替するクラス
	 */
	protected static class StandardInputSnatcher extends InputStream {

		private StringBuilder buffer = new StringBuilder();

		/**
		 * 文字列を入力する。
		 * 
		 * @param str 入力文字列
		 */
		public void input(String str) {
			buffer.append(str).append(LF);
		}

		/**
		 * 数字を入力する。
		 * 
		 * @param num 入力数字
		 */
		public void input(Number num) {
			buffer.append(num).append(LF);
		}

		@Override
		public int read() throws IOException {
			if (buffer.length() == 0) {
				return -1;
			}
			int result = buffer.charAt(0);
			buffer.deleteCharAt(0);
			return result;
		}

		/**
		 * 未使用のバッファーをクリアする
		 */
		public void clear() {
			buffer.setLength(0);
		}
	}
}
