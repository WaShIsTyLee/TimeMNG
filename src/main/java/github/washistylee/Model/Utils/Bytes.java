package github.washistylee.Model.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Bytes {

    public static byte[] convertImageToBytes(File imageFile) {
        byte[] imageBytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             FileInputStream fis = new FileInputStream(imageFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            imageBytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytes;
    }

}
