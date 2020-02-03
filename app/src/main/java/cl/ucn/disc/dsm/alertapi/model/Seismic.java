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

package cl.ucn.disc.dsm.alertapi.model;

import org.threeten.bp.ZoneId;

/**
 * Seismic class.
 */
public class Seismic {

  /**
   * Zone.
   */
  public static ZoneId ZONE_ID = ZoneId.of("-3");

  /**
   * Seismic State.
   */
  private Integer state;

  /**
   * Seismic UTC Time.
   */
  //private ZonedDateTime utc_time;
  private String utc_time;

  /**
   * Seismic Local Time.
   */
  //private ZonedDateTime local_time;
  private String local_time;

  /**
   * Seismic Local Time (Chile).
   */
  //private ZonedDateTime chilean_time;
  private String chilean_time;

  /**
   * Seismic Reference.
   */
  private String reference;

  /**
   * Seismic Magnitude.
   */
  private double magnitude;

  /**
   * Seismic Scale.
   */
  private String scale;

  /**
   * Seismic Latitude.
   */
  private double latitude;

  /**
   * Seismic Longitude.
   */
  private double longitude;

  /**
   * Seismic Depth.
   */
  private double depth;

  /**
   * Seismic ID.
   */
  //private final String id;
  private Long id;

  /**
   * Seismic URL.
   */
  private String url;

  /**
   * Seismic Source.
   */
  private String source;

  /**
   * Seismic URL-Picture.
   */
  private String urlPic;

  /**
   * Constructor.
   *
   * @param state - State
   * @param utc_time - UTC Time
   * @param local_time - Local Time
   * @param chilean_time - Local Time (Chile)
   * @param reference - Reference
   * @param magnitude - Magnitude
   * @param scale - Scale
   * @param latitude - Latitude
   * @param longitude - Longitude
   * @param depth - Depth
   * @param id - ID
   * @param url - URL
   * @param source - Source
   * @param urlPic - URL-Picture
   */
  public Seismic(
      final Integer state,
      final String utc_time,
      final String local_time,
      final String chilean_time,
      final String reference,
      final double magnitude,
      final String scale,
      final double latitude,
      final double longitude,
      final double depth,
      final Long id,
      final String url,
      final String source,
      final String urlPic) {
    this.state = state;
    this.utc_time = utc_time;
    this.local_time = local_time;
    this.chilean_time = chilean_time;
    this.reference = reference;
    this.magnitude = magnitude;
    this.scale = scale;
    this.latitude = latitude;
    this.longitude = longitude;
    this.depth = depth;
    this.id = id;
    this.url = url;
    this.source = source;
    this.urlPic = urlPic;
  }

  // GETTERS
  /**
   * @return - State.
   */
  public Integer getState() {
    return state;
  }

  /**
   * @return - UTC Time.
   */
  public String getUtc_time() {
    return utc_time;
  }

  /**
   * @return - Local Time.
   */
  public String getLocal_time() {
    return local_time;
  }

  /**
   * @return - Local Time (Chile).
   */
  public String getChilean_time() {
    return chilean_time;
  }

  /**
   * @return - Reference.
   */
  public String getReference() {
    return reference;
  }

  /**
   * @return - Magnitude.
   */
  public double getMagnitude() {
    return magnitude;
  }

  /**
   * @return - Scale.
   */
  public String getScale() {
    return scale;
  }

  /**
   * @return - Latitude.
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * @return - Longitude.
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * @return - Depth.
   */
  public double getDepth() {
    return depth;
  }

  /**
   * @return - ID.
   */
  public Long getId() {
    return id;
  }

  /**
   * @return - URL.
   */
  public String getUrl() {
    return url;
  }

  /**
   * @return - Source.
   */
  public String getSource() {
    return source;
  }

  /**
   * @return - URL-Picture.
   */
  public String getUrlPic() {
    return urlPic;
  }

  // SETTERS

  /**
   * @param state - State.
   */
  public void setState(Integer state) {
    this.state = state;
  }

  /**
   * @param utc_time - UTC Date.
   */
  public void setUtc_time(String utc_time) {
    this.utc_time = utc_time;
  }

  /**
   * @param local_time - Local Time.
   */
  public void setLocal_time(String local_time) {
    this.local_time = local_time;
  }

  /**
   * @param chilean_time - Date of Chile.
   */
  public void setChilean_time(String chilean_time) {
    this.chilean_time = chilean_time;
  }

  /**
   * @param reference - Reference.
   */
  public void setReference(String reference) {
    this.reference = reference;
  }

  /**
   * @param magnitude - Magnitude.
   */
  public void setMagnitude(double magnitude) {
    this.magnitude = magnitude;
  }

  /**
   * @param scale - Scale.
   */
  public void setScale(String scale) {
    this.scale = scale;
  }

  /**
   * @param latitude - Latitude.
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * @param longitude - Longitude.
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /**
   * @param depth - Depth.
   */
  public void setDepth(double depth) {
    this.depth = depth;
  }

  /**
   * @param id - ID.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @param url - URL.
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @param source - Source.
   */
  public void setSource(String source) {
    this.source = source;
  }

  /**
   * @param urlPic - URL-Picture.
   */
  public void setUrlPic(String urlPic) {
    this.urlPic = urlPic;
  }
}
