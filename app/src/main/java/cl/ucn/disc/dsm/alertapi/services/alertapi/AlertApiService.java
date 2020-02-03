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
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
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
   * AlertAPI.
   */
  private final AlertAPI alertAPI;

  /**
   * Constructor.
   */
  public AlertApiService() {

    // Logging with slf4j
    final HttpLoggingInterceptor loggingInterceptor =
        new HttpLoggingInterceptor(log::debug).setLevel(Level.BODY);

    // Web Client
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
        // Retrofit's build and gets the AlertAPI
        .build()
        .create(AlertAPI.class);
  }

  /**
   * Get the Seismic from the Call.
   *
   * @param select - Filter.
   * @return - The {@link List} of {@link Seismic}.
   */
  @Override
  public List<Seismic> getSelect(String select) {

    // Call
    final Call<AlertApiResult> call = alertAPI.getSelect(select);

    // Call process
    return getSelectFromCall(call);
  }

  /**
   * Process the call from the "getSelect" Method
   *
   * @param call - To use.
   * @return the {@link List} of {@link Seismic}.
   */
  private static List<Seismic> getSelectFromCall(final Call<AlertApiResult> call) {

    try {
      // Response
      final Response<AlertApiResult> response = call.execute();
      log.debug("Response = {}", response);

      // UnSuccessful
      if (!response.isSuccessful()) {
        // Error
        throw new AlertAPIException("Can't get the AlertResult, code: " + response.code(),
            new HttpException(response)
        );
      }

      // Result
      final AlertApiResult result = response.body();

      // No body
      if (result == null) {
        throw new AlertAPIException("AlertResult was null");
      }

      // Null Metadata
      if (result.metadata == null) {
        throw new AlertAPIException("Metadata in AlertResult was null");
      }
      log.debug("Status = {}, Submitted = {}", result.metadata.status, result.metadata.submitted);
      log.debug("Request = {}, User = {}", result.metadata.request, result.metadata.user);
      log.debug("COUNTRY = {}, Limit = {}", result.metadata.country, result.metadata.limit);

      // Null Seismic
      if(result.ultimos_sismos == null) {
        throw new AlertAPIException("NULL");
      }

      // Get the seismic data
      return result.ultimos_sismos;

    } catch (final IOException ex){
      throw new AlertAPIException("Can't get the AlertResult", ex);
    }
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