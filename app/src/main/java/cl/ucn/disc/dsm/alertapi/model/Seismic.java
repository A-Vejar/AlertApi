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
import org.threeten.bp.ZonedDateTime;

/**
 * Seismic class.
 */
public final class Seismic {

  /**
   * Zone.
   */
  public static final ZoneId ZONE_ID = ZoneId.of("-3");

  /**
   * Seismic State.
   */
  private Integer state;

  /**
   * Seismic UTC Time.
   */
  private ZonedDateTime dateUtc;
  //private final String utcTime;

  /**
   * Seismic Local Time.
   */
  private ZonedDateTime dateLocal;
  //private final String localTime;

  /**
   * Seismic Local Time (Chile).
   */
  private ZonedDateTime dateChile;
  //private final String chileanTime;

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
   * Constructor.
   *
   * @param state - State
   * @param dateUtc - UTC Time
   * @param dateLocal - Local Time
   * @param dateChile - Local Time (Chile)
   * @param reference - Reference
   * @param magnitude - Magnitude
   * @param scale - Scale
   * @param latitude - Latitude
   * @param longitude - Longitude
   * @param depth - Depth
   * @param id - ID
   * @param url - URL
   * @param source - Source
   */
  public Seismic(
      final Integer state,
      final ZonedDateTime dateUtc,
      final ZonedDateTime dateLocal,
      final ZonedDateTime dateChile,
      final String reference,
      final double magnitude,
      final String scale,
      final double latitude,
      final double longitude,
      final double depth,
      final Long id,
      final String url,
      final String source) {
    this.state = state;
    this.dateUtc = dateUtc;
    this.dateLocal = dateLocal;
    this.dateChile = dateChile;
    this.reference = reference;
    this.magnitude = magnitude;
    this.scale = scale;
    this.latitude = latitude;
    this.longitude = longitude;
    this.depth = depth;
    this.id = id;
    this.url = url;
    this.source = source;
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
  public ZonedDateTime getDateUtc() {
    return dateUtc;
  }

  /**
   * @return - Local Time.
   */
  public ZonedDateTime getDateLocal() {
    return dateLocal;
  }

  /**
   * @return - Local Time (Chile).
   */
  public ZonedDateTime getDateChile() {
    return dateChile;
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
   * @return - URL.
   */
  public String getSource() {
    return source;
  }

  // SETTERS
  public void setState(Integer state) {
    this.state = state;
  }

  public void setDateUtc(ZonedDateTime dateUtc) {
    this.dateUtc = dateUtc;
  }

  public void setDateLocal(ZonedDateTime dateLocal) {
    this.dateLocal = dateLocal;
  }

  public void setDateChile(ZonedDateTime dateChile) {
    this.dateChile = dateChile;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public void setMagnitude(double magnitude) {
    this.magnitude = magnitude;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public void setDepth(double depth) {
    this.depth = depth;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setSource(String source) {
    this.source = source;
  }
}
