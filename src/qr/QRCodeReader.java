package qr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRCodeReader {

    /**
     * QRコード画像から facility_id と child_id を取得するメソッド
     *
     * @param filePath QRコード画像のファイルパス
     * @return facility_id と child_id を含む文字列の配列
     * @throws IOException ファイル読み込みエラーが発生した場合
     * @throws NotFoundException QRコードが画像内に見つからない場合
     * @throws IllegalArgumentException QRコードの内容が不正な形式の場合
     */
    public String[] decodeQRCode(String filePath) throws IOException, NotFoundException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("QRコード画像ファイルが見つかりません: " + filePath);
        }

        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImageLuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

        Result result = new MultiFormatReader().decode(binaryBitmap);
        String qrText = result.getText();

        // QRコードテキストを分解し、facility_idとchild_idを配列に格納
        String[] qrData = qrText.split(",", 2);
        if (qrData.length != 2) {
            throw new IllegalArgumentException("QRコードの形式が正しくありません。facility_id, child_idが含まれている必要があります。");
        }

        return qrData;  // [facility_id, child_id]
    }

    // メインメソッド（テスト用）
    public static void main(String[] args) {
        try {
            QRCodeReader qrReader = new QRCodeReader();
            String[] qrData = qrReader.decodeQRCode("./WebContent/sysshin/MyQRCode.png");

            System.out.println("Facility ID: " + qrData[0]);
            System.out.println("Child ID: " + qrData[1]);
        } catch (IOException e) {
            System.err.println("ファイルエラー: " + e.getMessage());
        } catch (NotFoundException e) {
            System.err.println("QRコードが見つかりません: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("QRコードの内容が不正です: " + e.getMessage());
        }
    }
}



