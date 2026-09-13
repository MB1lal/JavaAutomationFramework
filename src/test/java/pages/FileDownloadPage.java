package pages;

import net.serenitybdd.core.pages.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FileDownloadPage extends PageObject {
    private final Logger logger = LogManager.getLogger(FileDownloadPage.class);

    /**
     * Downloads the first file listed on the page over plain HTTP instead of
     * driving the browser's download UI (or shelling out to wget, which isn't
     * available everywhere). The file list on the demo page changes over time,
     * so a hardcoded name goes stale — hence first-listed. Returns the name
     * of the file that was downloaded.
     */
    public String downloadFirstAvailableFile(String downloadDir) {
        List<WebElement> links = this.getDriver().findElements(By.cssSelector("#content a"));
        WebElement first = links.stream()
                .filter(link -> !link.getText().trim().isEmpty())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No files listed to download"));
        String fileName = first.getText().trim();
        String fileURL = first.getAttribute("href");
        try {
            Path dir = Paths.get(downloadDir);
            Files.createDirectories(dir);
            Path target = dir.resolve(fileName);
            try (InputStream input = new URI(fileURL).toURL().openStream()) {
                Files.copy(input, target, StandardCopyOption.REPLACE_EXISTING);
            }
            logger.info("Downloaded {} ({} bytes)", fileName, Files.size(target));
        } catch (Exception e) {
            throw new IllegalStateException("Could not download " + fileURL, e);
        }
        return fileName;
    }
}
