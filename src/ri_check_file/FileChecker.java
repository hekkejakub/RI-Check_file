/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ri_check_file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author james
 */
class FileChecker {

    private final String path;

    private final String checksum;

    public FileChecker(String input) {
        String[] result = input.split("\\s");
        path = result[1];                       //dlaczego 1 a nie od 0?
        checksum = result[2];
    }

    public void checkpath() throws IOException, NoSuchAlgorithmException {
        Path path_temp = Paths.get(path);
        if (!Files.exists(path_temp)) {
            System.out.println("File/directory does not exist");
            System.exit(1);
        } else {
            checksum();
        }

    }

    private void checksum() throws NoSuchAlgorithmException, IOException {
        InputStream imputfile = new FileInputStream(path);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("SHA1");
        int numRead;
        do {
            numRead = imputfile.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        imputfile.close();
        byte[] b = complete.digest();
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        System.out.println(result);
        System.out.println(checksum);
        if (!result.equals(checksum)) {
            System.out.println("Checksum does not match. Current file SHA-1 checksum: " + result);
            System.exit(1);
        }
    }

}
