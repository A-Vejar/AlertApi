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

/**
 * UltimosSismo class.
 */
public class UltimosSismo {

  /**
   * State.
   */
  public Integer state;

  /**
   * UTC Time.
   */
  public String utcTime;

  /**
   * Local Time.
   */
  public String localTime;

  /**
   * Local Time (Chile).
   */
  public String chileanTime;

  /**
   * Reference.
   */
  public String reference;

  /**
   * Magnitude.
   */
  public double magnitude;

  /**
   * Scale.
   */
  public String scale;

  /**
   * Latitude.
   */
  public double latitude;

  /**
   * Longitude.
   */
  public double longitude;

  /**
   * Depth.
   */
  public double depth;

  /**
   * ID.
   */
  public String id;

  /**
   * URL.
   */
  public String url;

  /**
   * Source.
   */
  public String source;
}