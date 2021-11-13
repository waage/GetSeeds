package com.github.waage.getseeds.display;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRWindow {

	private static final int SIZE = 400;

	public static void display(final String content) throws Exception {

		final BufferedImage myPicture = generateQRCodeImage(content);
		final JLabel picLabel = new JLabel(new ImageIcon(myPicture));

		final JPanel jPanel = new JPanel();
		jPanel.add(picLabel);

		final JFrame f = new JFrame("Seed");
		f.setSize(new Dimension(SIZE + 10, SIZE + 50));
		f.add(jPanel);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	private static BufferedImage generateQRCodeImage(final String barcodeText) throws Exception {
		final QRCodeWriter barcodeWriter = new QRCodeWriter();
		final BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, SIZE, SIZE);
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
}
