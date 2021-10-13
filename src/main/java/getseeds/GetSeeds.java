package getseeds;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class GetSeeds {
	
	private static final int MIN = 0;
	private static final int MAX = 2048;
	private static final int WORDS = 23;

	public static void main(String[] args) {
		
		final long seed = generateSeed();

		System.out.println("Calculando...");
		
		final List<String> mnemonics = getMnemonics(seed);
		
		System.out.println(mnemonics.toString().replace(",", ""));
		
		printTable(mnemonics);
		
		System.out.println("Calcule a 24a palavra em https://seedpicker.net/calculator/last-word.html");
	}

	private static void printTable(final List<String> mnemonics) {
		int pos = 1;
		for (final String string : mnemonics) {
			System.out.println(String.format("%d - %s", pos++, string));
		}
	}

	private static List<String> getMnemonics(final long seed) {
		final List<String> words = readWords();
		final SecureRandom sRand = new SecureRandom();
		sRand.setSeed(seed);
		final List<String> mnemonics = new ArrayList<>(WORDS);
		for (int i = 0; i < WORDS; i++) {
			mnemonics.add(words.get(sRand.nextInt(MAX - MIN) + MIN));
		}
		return mnemonics;
	}

	private static long generateSeed() {

		final Point location = MouseInfo.getPointerInfo().getLocation();
		double mouseX = location.getX();
		double mouseY = location.getY();
		int samples = 250;
		long seed = Long.MAX_VALUE - System.nanoTime();

		System.out.println("Mova o mouse por alguns segundos");

		while (samples > 0) {

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			final Point newLocation = MouseInfo.getPointerInfo().getLocation();
			double newMouseX = newLocation.getX();
			double newMouseY = newLocation.getY();

			if (newMouseX != mouseX || newMouseY != mouseY) {
				mouseX = newMouseX;
				mouseY = newMouseY;
				seed -= (long) ((mouseX * mouseY));
				samples--;
			}
		}
		return seed;
	}

	private static List<String> readWords() {
		final List<String> words = new ArrayList<>();
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(GetSeeds.class.getClassLoader().getResourceAsStream("bip-0039.txt")))) {
			while (reader.ready()) {
				words.add(reader.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}
}
