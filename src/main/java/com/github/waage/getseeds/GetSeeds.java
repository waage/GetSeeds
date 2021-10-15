package com.github.waage.getseeds;

import java.awt.MouseInfo;
import java.awt.Point;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import io.github.novacrypto.bip39.MnemonicGenerator;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;

public class GetSeeds {
	
	public static void main(String[] args) {

		final long seed = generateSeed();
		
		System.out.println("Calculando... \n");
		
		final StringBuilder sb = new StringBuilder();
		final byte[] entropy = new byte[Words.TWENTY_FOUR.byteLength()];
		final SecureRandom sr = new SecureRandom();
		sr.setSeed(seed);
		sr.nextBytes(entropy);
		
		new MnemonicGenerator(English.INSTANCE).createMnemonic(entropy, sb::append);
		
		final String mnemonics = sb.toString();
		System.out.println(mnemonics);
		printTable(Arrays.asList(mnemonics.split(" ")));
	}

	private static void printTable(final List<String> mnemonics) {
		int pos = 1;
		for (final String string : mnemonics) {
			System.out.println(String.format("%d - %s", pos++, string));
		}
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
			final double newMouseX = newLocation.getX();
			final double newMouseY = newLocation.getY();

			if (newMouseX != mouseX || newMouseY != mouseY) {
				mouseX = newMouseX;
				mouseY = newMouseY;
				seed -= (long) ((mouseX * mouseY));
				samples--;
			}
		}
		return seed;
	}

}
