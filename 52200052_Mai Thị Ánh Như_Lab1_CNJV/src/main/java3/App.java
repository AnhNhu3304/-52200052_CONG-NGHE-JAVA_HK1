

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class App {
    public static void main(String[] args) {
        // Code của bạn để xử lý URL và tải file
        if (args.length == 0) {
            System.out.println("Please specify an URL to a file.");
            return;
        }

        String urlString = args[0];
        UrlValidator urlValidator = new UrlValidator();

        if (!urlValidator.isValid(urlString)) {
            System.out.println("This is not a valid URL.");
            return;
        }

        try {
            URL url = new URL(urlString);
            String fileName = new File(url.getPath()).getName();
            File file = new File(fileName);
            FileUtils.copyURLToFile(url, file);
            System.out.println("File downloaded: " + fileName);
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
        }
    }
}

