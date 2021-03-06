package com.kkbox.sqa.monkey;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class CalculatorTest {
    private UiDevice device =  UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    private static final String APP_PACKAGE = "com.skysoft.kkbox.android";
    private static final int TIMEOUT = 5000;

    private static final int LAUNCH_TIMEOUT = 10000;
    private static UiDevice mDevice;

//    @BeforeClass
//    public void login() {
//        mDevice = this.device;
//
//        // TODO: Uncomment below to using TestBuilder
//        // kkbox = new TestBuilder()
//        //        .loginViaEmail("demo171018@gmail.com", "1234")
//        //        .disableTrialMessage()
//        //        .disableTutorial()
//        //        .launch();
//
//        // FIXME: Refactor following to TestBuilder
//        // Trial Message Handler
////        registerTrialWatchers(mDevice);
//
//
//
//        // Start from the home screen
//        mDevice.pressHome();
//
//        // Wait for launcher
//        final String launcherPackage = getLauncherPackageName();
//        assertThat(launcherPackage, notNullValue());
//        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
//
//        // Launch the blueprint app
//        Context context = InstrumentationRegistry.getContext();
//        final Intent intent = context.getPackageManager()
//                .getLaunchIntentForPackage(APP_PACKAGE);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
//        context.startActivity(intent);
//
//        // Wait for the app to appear
//        mDevice.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
//
//        // Auto login
//        if(mDevice.wait(Until.hasObject(By.res(APP_PACKAGE, "button_login")), TIMEOUT)) {
//            // Login via Email
//            mDevice.wait(Until.findObject(By.res(APP_PACKAGE, "button_login")), TIMEOUT).click();
//            mDevice.wait(Until.findObject(By.res(APP_PACKAGE, "button_login_with_email")), TIMEOUT).click();
//
//            // Submit login form
//            mDevice.wait(Until.findObject(By.res(APP_PACKAGE, "text_uid")), TIMEOUT).setText("demo171018@gmail.com");
//            mDevice.wait(Until.findObject(By.res(APP_PACKAGE, "text_password")), TIMEOUT).setText("1234");
//            mDevice.wait(Until.findObject(By.res(APP_PACKAGE, "button_login")), TIMEOUT).click();
//            }
//    }

    @Test
    public void start() {
        // grant recording permission
        mDevice.wait(Until.findObject(By.res("android", "button1")), TIMEOUT).click();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        this.device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), TIMEOUT);

        // Launch the blueprint app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(APP_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        this.device.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), TIMEOUT);
    }

    /**
     * Uses package manager to find the package name of the device launcher. Usually this package
     * is "com.android.launcher" but can be different at times. This is a generic solution which
     * works on all platforms.`
     */
    private static String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}