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

package cl.ucn.disc.dsm.alertapi.services.alertapi;

import cl.ucn.disc.dsm.alertapi.model.Seismic;
import cl.ucn.disc.dsm.alertapi.services.AlertService;
import cl.ucn.disc.dsm.alertapi.services.Transform;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlertApiService implements AlertService {

  /**
   * Logger.
   */
  private static final Logger log =
      LoggerFactory.getLogger(AlertApiService.class);

  /**
   * AlertAPI
   */
  private final AlertAPI alertAPI;

  public AlertApiService() {

    // Logging with slf4j.
    final HttpLoggingInterceptor loggingInterceptor =
        new HttpLoggingInterceptor(log::debug).setLevel(Level.BODY);

    // Web Client.
    final OkHttpClient httpClient = new Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .callTimeout(5, TimeUnit.SECONDS)
        .addNetworkInterceptor(loggingInterceptor)
        .build();

    // Retrofit implement
    alertAPI = new Retrofit.Builder()
        // URL
        .baseUrl(AlertAPI.BASE_URL)
        // JSON to POJO
        .addConverterFactory(GsonConverterFactory.create())
        // Interface validates
        .validateEagerly(true)
        // Retrofits build and gets the AlertAPI
        .build()
        .create(AlertAPI.class);
  }

  /**
   * Get the Seismic from the Call.
   *
   * @param theCall - To use.
   * @return the {@link List} of {@link Seismic}.
   */
  private static List<Seismic> getSelectFromCall(final Call<AlertApiResult> theCall) {

    try {
      // Get the result from the call
      final Response<AlertApiResult> response = theCall.execute();
      log.debug("Response = {}", response);

      // UnSuccessful !
      if (!response.isSuccessful()) {

        // Error!
        throw new AlertAPIException("Can't get the AlertResult, code: " + response.code(),
            new HttpException(response)
        );
      }

      final AlertApiResult theResult = response.body();

      // No body
      if (theResult == null) {
        throw new AlertAPIException("AlertResult was null");
      }

      // ---------------------------------------------------------------------------------------------------
      if (theResult.metadata == null) {
        throw new AlertAPIException("Metadata in AlertResult was null");
      }
      log.debug("STATUS = {}", theResult.metadata.status);
      log.debug("Request = {}, User = {}", theResult.metadata.request, theResult.metadata.user);
      log.debug("Submitted = {}", theResult.metadata.submitted);
      log.debug("COUNTRY = {}, Limit = {}", theResult.metadata.country, theResult.metadata.limit);
      // ---------------------------------------------------------------------------------------------------

      if(theResult.ultimos_sismos == null) {
        throw new AlertAPIException("NULL");
      }

      return theResult.ultimos_sismos;

    } catch (final IOException ex){
      throw new AlertAPIException("Can't get the AlertResult", ex);
    }
  }

  @Override
  public List<Seismic> getSelect(String select) {

    // Call
    final Call<AlertApiResult> call = alertAPI.getSelect(select);

    // Call process
    return getSelectFromCall(call);
  }

  @Override
  public List<Seismic> getExample() {

    // Call
    final Call<AlertApiResult> call = alertAPI.getExample();

    // Call process
    return getSelectFromCall(call);
  }

  /**
   * Inner class - Exception.
   */
  public static final class AlertAPIException extends RuntimeException {

    /**
     * Exception.
     * @param message - The exception message.
     */
    public AlertAPIException(final String message) {
      super(message);
    }

    /**
     * Exception.
     * @param message - The exception message.
     * @param cause - The exception cause.
     */
    public AlertAPIException(final String message, final Throwable cause) {
      super(message, cause);
    }
  }
}