package qr;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

    // 保存するパスを直接指定
    private static final String QR_CODE_IMAGE_PATH = "/WebContent/sysshin/MyQRCode.png";

    // QRコード生成メソッド
    public String generateQRCode(String childId, String facilityId) throws WriterException, IOException {
    	System.out.println("aaaaaaaaaaaaaaaaaaa");
        String qrData = "child_id=" + childId + "&facility_id=" + facilityId;
        System.out.println("bbbbbbbbbbbbbbbbbb");
        String imagePath = generateQRCodeImage(qrData, 350, 350);
        System.out.println("cccccccccccccccccc");
        return imagePath;
    }

    // QRコード画像生成処理（プライベート）
    private String generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
    	System.out.println("ddddddddddddddddddddd");
        // 直接ファイルパスを指定
    	// 直接ファイルパスを指定
        String filePath = "/Webcontent/sysshin/MyQRCode.png";
        Path path = FileSystems.getDefault().getPath(filePath);
        System.out.println("eeeeeeeeeeeeeeeeeee");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        System.out.println("fffffffffffffffffff");
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        System.out.println("ggggggggggggggggggggggggg");

        // QRコードをPNG画像として保存
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("hhhhhhhhhhhhhhhhhhh");
        System.out.println("QRコードが生成され、保存されました: " + path.toAbsolutePath());
        System.out.println("iiiiiiiiiiiiiiiiiii");

        // 保存された画像のパスを返す
        return path.toString();
    }
}
