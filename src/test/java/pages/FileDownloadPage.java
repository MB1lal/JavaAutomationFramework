package pages;

import net.serenitybdd.core.pages.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileDownloadPage extends PageObject {
    private final Logger logger = LogManager.getLogger(FileDownloadPage.class);
    public void downloadFile(String fileName) {
        String fileURL = this.getDriver().findElement(By.linkText(fileName)).getAttribute("href");
        String downloadDir = System.getProperty("user.dir") + "/src/test-output/downloads";
        String command = "wget -P " + downloadDir + " " + fileURL;
        try {
            // Start a new process for the command
            Process process = new ProcessBuilder(command.split(" "))
                    .redirectErrorStream(true)
                    .start();
            // Read and print the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                logger.debug(line);
            }
            // Wait for the process to complete
            int exitCode = process.waitFor();
            logger.info("Command exited with code: " + exitCode);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
