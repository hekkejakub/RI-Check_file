/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ri_check_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
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

        MessageDigest md = MessageDigest.getInstance("MD5");
        try (InputStream is = Files.newInputStream(Paths.get(path));
                DigestInputStream dis = new DigestInputStream(is, md)) {
        }
        byte[] digest = md.digest();
        if (!String.valueOf(digest).equals(checksum)){
            System.out.println("Checksum doesn't match. hash function of file: " + digest);
            System.exit(1);
        } 
    }

}
