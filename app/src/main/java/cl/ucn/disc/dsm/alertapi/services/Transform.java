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

package cl.ucn.disc.dsm.alertapi.services;

import cl.ucn.disc.dsm.alertapi.model.Seismic;
import cl.ucn.disc.dsm.alertapi.services.alertapi.AlertApiService;
import cl.ucn.disc.dsm.alertapi.services.alertapi.UltimosSismo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import net.openhft.hashing.LongHashFunction;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeParseException;

public class Transform {

  /**
   * Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(Transform.class);

  static Seismic seismic;

  /**
   * UltimosSismosChile to Seismic.
   *
   * @param seismics - To transform.
   * @return - The Seismic.
   */
  public static List<Seismic> transform(List<Seismic> seismics) {

    throwingException(seismic);

    // Date - UTC
    seismic.setDateUtc(ZonedDateTime.parse(seismic.getDateUtc().toString()));

    // Date - Chile
    seismic.setDateChile(parseZonedDateTime(seismic.getDateChile().toString()).withZoneSameInstant(Seismic.ZONE_ID));

    // Date - Local
    seismic.setDateLocal(parseZonedDateTime(seismic.getDateChile().toString()).withZoneSameInstant(Seismic.ZONE_ID));

    // ID
    final Long id = LongHashFunction.xx().hashChars(seismic.getId().toString());

    return seismics;
  }

  /**
   * Exceptions.
   * @param seismic - The Seismic.
   */
  private static void throwingException(Seismic seismic) {

    // If its null
    if (seismic == null) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Host
    String host = getHost(seismic.getUrl());

    // State
    if (seismic.getState() == null) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Reference
    if (seismic.getReference() == null) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Magnitude
    if(seismic.getMagnitude() == 0.0d) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Scale
    if (seismic.getScale() == null) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Latitude
    if (seismic.getLatitude() == 0.0d) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Longitude
    if(seismic.getLongitude() == 0.0d) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Depth
    if (seismic.getDepth() == 0.0d) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Source
    if (seismic.getSource() == null) {

      if (host != null) {
        seismic.setSource(host);

      } else {
        seismic.setSource("*NO-SOURCE");
        log.warn("Seismic without source: {}", toString(seismic));
      }
    }
  }

  /**
   * Parse a date of {@link String} to {@link ZonedDateTime}.
   *
   * @param date - Date to parse.
   * @return the date.
   * @throws cl.ucn.disc.dsm.alertapi.services.alertapi.AlertApiService.AlertAPIException
   *         --> In case of no achieve the parse of the date.
   */
  private static ZonedDateTime parseZonedDateTime(final String date) {

    // Null date.
    if (date == null) {
      throw new AlertApiService.AlertAPIException("Can't parse null date");
    }

    try {
      // Try to parse the date ...
      return ZonedDateTime.parse(date);

    } catch (DateTimeParseException ex) {

      // Debug message.
      log.error("Can't parse date: ->{}<-. Error: ", date, ex);

      // Add a DateTimeParseException into a NewsTransformerException.
      throw new AlertApiService.AlertAPIException("Can't parse date: " + date, ex);
    }
  }

  /**
   * Get the host part of one url.
   *
   * @param url - To use.
   * @return - The host part (without the 'www').
   */
  private static String getHost(final String url) {

    try {

      final URI uri = new URI(url);
      final String hostname = uri.getHost();

      // to provide faultproof result, check if not null then return only hostname, without www.
      if (hostname != null) {
        return hostname.startsWith("www.") ? hostname.substring(4) : hostname;
      }
      return null;

    } catch (final URISyntaxException | NullPointerException ex) {
      return null;
    }
  }

  /**
   * Transform into String an object t showing its attributes.
   *
   * @param t - To convert.
   * @param <T> - Type of t.
   * @return - The object in String format.
   */
  public static <T> String toString(final T t) {
    return ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE);
  }
}
