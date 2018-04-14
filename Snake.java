package combinatorial;

import java.util.HashSet;
import java.util.Scanner;

public class Snake {
	public static char[] elements;
	public static HashSet<String> visitedCells = new HashSet<String>();
	public static HashSet<String> generatedSnake = new HashSet<String>();
	public static HashSet<String> allPossibleSnakes = new HashSet<String>();

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = Integer.parseInt(console.nextLine());
		elements = new char[n];
		generate(0, 0, 0, 'S');
		printSnake();
		console.close();
	}

	private static void generate(int index, int row, int col, char direction) {
		if (index == elements.length) {
			markSnake();
		} else {

			String cell = row + "" + col;

			if (!visitedCells.contains(cell)) {

				elements[index] = direction;

				visitedCells.add(cell);

				generate(index + 1, row, col + 1, 'R');
				generate(index + 1, row + 1, col, 'D');
				generate(index + 1, row, col - 1, 'L');
				generate(index + 1, row - 1, col, 'U');

				visitedCells.remove(cell);
			}

		}
	}

	private static void markSnake() {
		String normalSnake = new String(elements);

		if (allPossibleSnakes.contains(normalSnake)) {
			return;
		}

		generatedSnake.add(normalSnake);

		String flippedSnake = flip(normalSnake);
		String reversedSnake = reverse(normalSnake);
		String reversedFlippedSnake = flip(reversedSnake);

		for (int i = 0; i < 4; i++) {
			allPossibleSnakes.add(normalSnake);
			normalSnake = rotate(normalSnake);

			allPossibleSnakes.add(flippedSnake);
			flippedSnake = rotate(flippedSnake);

			allPossibleSnakes.add(reversedSnake);
			reversedSnake = rotate(reversedSnake);

			allPossibleSnakes.add(reversedFlippedSnake);
			reversedFlippedSnake = rotate(reversedFlippedSnake);
		}
	}

	private static String rotate(String snk) {
		char[] newSnake = new char[snk.length()];
		char[] snake = snk.toCharArray();
		for (int i = 0; i < snake.length; i++) {
			switch (snake[i]) {
			case 'R':
				newSnake[i] = 'D';
				break;
			case 'D':
				newSnake[i] = 'L';
				break;
			case 'L':
				newSnake[i] = 'U';
				break;
			case 'U':
				newSnake[i] = 'R';
				break;
			default:
				newSnake[i] = snake[i];
				break;
			}
		}

		return new String(newSnake);
	}

	private static String reverse(String snk) {
		char[] newSnake = new char[snk.length()];
		char[] snake = snk.toCharArray();
		newSnake[0] = 'S';

		for (int i = 1; i < snake.length; i++) {
			newSnake[snake.length - i] = snake[i];
		}

		return new String(newSnake);
	}

	private static String flip(String snk) {
		char[] newSnake = new char[snk.length()];
		char[] snake = snk.toCharArray();

		for (int i = 0; i < snake.length; i++) {
			switch (snake[i]) {
			case 'U':
				newSnake[i] = 'D';
				break;
			case 'D':
				newSnake[i] = 'U';
				break;
			default:
				newSnake[i] = snake[i];
				break;
			}
		}

		return new String(newSnake);
	}

	private static void printSnake() {
		for (String snake : generatedSnake) {
			System.out.println(snake);
		}

		System.out.printf("Snakes count = %d", generatedSnake.size());
	}

}