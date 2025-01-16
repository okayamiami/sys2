package qr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

    // QRコード生成メソッド
    public String generateQRCode(String childId, String facilityId, ServletContext context) throws WriterException, IOException {
        String qrData = childId + "," + facilityId;
        String imagePath = generateQRCodeImage(qrData, 350, 350, context);
        return imagePath;
    }

    // QRコード画像生成処理（プライベート）
    private String generateQRCodeImage(String text, int width, int height, ServletContext context) throws WriterException, IOException {
        // WebContent/sysshin ディレクトリに保存する相対パスを指定
        String relativePath = "/sysshin/MyQRCode.png";
        String absolutePath = context.getRealPath(relativePath); // 絶対パスに変換

        // 画像保存先のディレクトリが存在しない場合は作成する
        File dir = new File(absolutePath).getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();  // ディレクトリを作成
        }

        Path path = Paths.get(absolutePath); // 修正: Paths.get()を使用

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        // QRコードをPNG画像として保存
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        // 保存された画像のパスを返す
        return context.getContextPath() + relativePath;
    }
}
