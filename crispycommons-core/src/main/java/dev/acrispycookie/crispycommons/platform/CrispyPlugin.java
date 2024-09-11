package dev.acrispycookie.crispycommons.platform;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

public interface CrispyPlugin {
    File getDataFolder();
    InputStream getResource(String resourceName);
    Logger getLogger();
    String getName();
}
