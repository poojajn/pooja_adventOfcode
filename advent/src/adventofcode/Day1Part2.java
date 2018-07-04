package adventofcode;

import java.util.Scanner;

public class Day1Part2 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		int l = input.length();
		int n = l / 2;
		int sum = 0;
		for (int i = 0; i < l; i++) {
			if ((i + n) <= l - 1) {

				if (input.charAt(i) == input.charAt(i + n)) {
					sum = sum + Integer.parseInt(String.valueOf(input.charAt(i)));
				}
			} else if ((i + n) == l && input.charAt(i) == input.charAt(0)) {
				sum = sum + Integer.parseInt(String.valueOf(input.charAt(i)));
			} else if ((i + n) > l && input.charAt(i) == input.charAt((i + n) - l)) {
				sum = sum + Integer.parseInt(String.valueOf(input.charAt(i)));
			}
		}

		System.out.println(sum);

	}
}