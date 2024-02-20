package in.jewelx.jewelxbackend.barcode;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class BarcodeGenerator {
	
	public static byte[] getBarCodeImage(String text, int width, int height ) {
		try {
			String path = "C:\\Users\\SONU\\OneDrive\\Desktop\\JewelxProject\\barcode\\barcode.jpg";
			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			Writer writer = new Code128Writer();
			BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.CODE_128, width, height);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		}catch(Exception e) {
			return null;
		}
	}
	
//		String text = "Kshitij";
//		String path = "C:\\Users\\SONU\\OneDrive\\Desktop\\JewelxProject\\barcode\\barcode.jpg";
	
	
}
