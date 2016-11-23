package net.whatsbeef.showguide;

import android.os.Build;

/**
 * Created by k.
 */
public interface TestConfig {
    int ANDROID_TEST_SDK_VERSION = Build.VERSION_CODES.LOLLIPOP;

    /**
     * Use the resources of our current flavour/build type to run the tests
     */
    String ANDROID_MERGED_RES_DIR = "../../../app2/build/intermediates/res/merged/" + BuildConfig.FLAVOR + "/" + BuildConfig.BUILD_TYPE;

    /**
     * Location of the AndroidManifest to configure tests
     */
    String ANDROID_MANIFEST = "../app/src/main/AndroidManifest.xml";
}
