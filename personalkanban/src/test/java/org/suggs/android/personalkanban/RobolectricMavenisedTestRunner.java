package org.suggs.android.personalkanban;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xtremelabs.robolectric.RobolectricTestRunner;

/**
 * When running this from a maven multi-module build within Intellij, the classpath fails to find the manifest
 * containing directory.  This runner searches the classpath for the manifest file and then sets the root
 * <p/>
 * User: suggitpe Date: 11/10/11 Time: 07:30
 */

public class RobolectricMavenisedTestRunner extends RobolectricTestRunner {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( RobolectricMavenisedTestRunner.class );

    public RobolectricMavenisedTestRunner( Class<?> testClass ) throws InitializationError {
        super( testClass, AndroidBaseDirLocator.findBaseDirectory() );
    }

    private static class AndroidBaseDirLocator {

        public static File findBaseDirectory() {
            File manifestFile;
            try {
                manifestFile = new File( findCorrectManifestFromClasspath().toURI() );
            }
            catch ( URISyntaxException use ) {
                throw new RuntimeException( "Failed to create file from found URI", use );
            }

            if ( !manifestFile.exists() ) {
                throw new RuntimeException( "Failed to read in a manifest file from URL [" + manifestFile.getAbsolutePath() + "]" );
            }
            LOG.debug( "Seeding it all based on [" + manifestFile.getAbsolutePath() + "]" );
            return manifestFile.getParentFile();
        }

        private static URL findCorrectManifestFromClasspath() {
            Enumeration<URL> manifests;
            try {
                manifests = AndroidBaseDirLocator.class.getClassLoader().getResources( "AndroidManifest.xml" );
            }
            catch ( IOException ioe ) {
                throw new RuntimeException( "Failed to find AndroidManifest.xml", ioe );
            }
            while ( manifests.hasMoreElements() ) {
                URL url = manifests.nextElement();
                if ( url.toString().startsWith( "file:" ) ) {
                    return url;
                }
            }
            return null;
        }
    }
}
