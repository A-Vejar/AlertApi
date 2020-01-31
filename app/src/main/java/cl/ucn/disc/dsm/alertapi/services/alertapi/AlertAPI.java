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

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Alert-API Interface
 */
public interface AlertAPI {

  /**
   * URL.
   */
  String BASE_URL = "https://chilealerta.com/api/query/";

  /**
   * Username.
   */
  String API_USER = "WsaaP";

  @GET("?user=" + API_USER)
  Call<AlertApiResult> getSelect(@Query(("select")) String select);

  // ?user=demo&select=ultimos_sismos&country=chile
  @GET("?user=demo&select=ultimos_sismos&country=chile")
  Call<AlertApiResult> getExample();
}
