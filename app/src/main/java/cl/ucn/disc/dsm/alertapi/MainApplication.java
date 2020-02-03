/*
 * Copyright 2019-2020 Ariel Vejar Martinez <ariel.vejar@live.cl>.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       https:www.apache.orglicensesLICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cl.ucn.disc.dsm.alertapi;

import android.app.Application;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import com.facebook.drawee.backends.pipeline.Fresco;
import org.acra.ACRA;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraMailSender;
import org.acra.annotation.AcraToast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main Application.
 */
@AcraCore(buildConfigClass = BuildConfig.class)
@AcraToast(resText = R.string.acra_crash_msg)
@AcraMailSender(mailTo = "ariel.vejar@live.cl")
public class MainApplication extends Application {

  /**
   * The Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

  /**
   * Called when the application is starting, before any activity, service, or receiver objects (excluding content
   * providers) have been created.
   */
  @Override
  public void onCreate() {
    super.onCreate();

    /*
     * Day and Night support.
     *
     * MODE_NIGHT_NO - Day mode.
     * MODE_NIGHT_YES - Night mode.
     * MODE_NIGHT_AUTO_BATTERY - Night mode if the save battery is activate.
     * MODE_NIGHT_FOLLOW_SYSTEM - Default mode by the device.
     */
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    // Facebook Fresco
    Fresco.initialize(this);

    log.debug("Initializing: Done.");
  }

  /**
   * @param base - Context.
   */
  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);

    log.debug("Initializing ACRA ..");
    ACRA.init(this);
    log.debug(".. ACRA initialized !!");
  }
}
