package net.whatsbeef.showguide;

import android.app.Application;
import android.support.annotation.NonNull;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by k.
 */
public class SLTestRunner extends RobolectricTestRunner {

    public SLTestRunner (Class<?> testClass) throws InitializationError {
        super (testClass);
    }

    @Override
    public Config getConfig (Method method) {

        Config config = super.getConfig (method);

        if (config != null) {
            config = new DelegateConfig (config) {
                @Override
                public String manifest () {
                    return TestConfig.ANDROID_MANIFEST;
                }

                @Override
                public String resourceDir () {
                    return TestConfig.ANDROID_MERGED_RES_DIR;
                }

                @Override
                public int[] sdk () {
                    return new int[]{TestConfig.ANDROID_TEST_SDK_VERSION};
                }

            };
        }
        return config;
    }

    private abstract class DelegateConfig implements Config {

        final Config mDelegate;

        public DelegateConfig (@NonNull Config delegate) {
            this.mDelegate = delegate;
        }

        @Override
        public Class<? extends Annotation> annotationType () {
            return mDelegate.annotationType ();
        }

        @Override
        public int[] sdk () {
            return mDelegate.sdk ();
        }

        @Override
        public String manifest () {
            return mDelegate.manifest ();
        }

        @Override
        public Class<?> constants () {
            return mDelegate.constants ();
        }

        @Override
        public Class<? extends Application> application () {
            return mDelegate.application ();
        }

        @Override
        public String packageName () {
            return mDelegate.packageName ();
        }

        @Override
        public String qualifiers () {
            return mDelegate.qualifiers ();
        }

        @Override
        public String resourceDir () {
            return mDelegate.resourceDir ();
        }

        @Override
        public String assetDir () {
            return mDelegate.assetDir ();
        }

        @Override
        public Class<?>[] shadows () {
            return mDelegate.shadows ();
        }

        @Override
        public String[] libraries () {
            return mDelegate.libraries ();
        }

        @Override
        public String abiSplit () {
            return mDelegate.abiSplit ();
        }

        @Override
        public String buildDir () {
            return mDelegate.buildDir ();
        }

        @Override
        public String[] instrumentedPackages () {
            return mDelegate.instrumentedPackages ();
        }


    }
}

