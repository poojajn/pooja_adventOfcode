package adventofcode;

import java.util.Scanner;

public class CircularSum {
	public static void main(String[] args) {
		int sum = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		int l = input.length();

		if (input.charAt(l - 1) == input.charAt(0)) {
			sum = sum + Integer.parseInt(String.valueOf(input.charAt(l - 1)));
		}

		for (int i = 0; i < (l - 1); i++) {

			if (input.charAt(i) == input.charAt(i + 1)) {
				sum = sum + Integer.parseInt(String.valueOf(input.charAt(i)));
			}
		}
		System.out.println(sum);
	}
}
