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
import cl.ucn.disc.dsm.alertapi.services.alertapi.UltimosSismosChile;
import java.net.URI;
import java.net.URISyntaxException;
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

  /**
   * UltimosSismosChile to Seismic.
   *
   * @param ultimosSismosChile - To transform.
   * @return - The Seismic.
   */
  public static Seismic transform(UltimosSismosChile ultimosSismosChile) {

    throwingException(ultimosSismosChile);

    // Date - UTC
    final ZonedDateTime utcTime = ZonedDateTime.parse(ultimosSismosChile.utcTime);

    // Date - Chile
    final ZonedDateTime chileanTime = parseZonedDateTime(ultimosSismosChile.chileanTime)
        .withZoneSameInstant(Seismic.ZONE_ID);

    // Date - Local
    final ZonedDateTime localTime = parseZonedDateTime(ultimosSismosChile.localTime)
        .withZoneSameInstant(Seismic.ZONE_ID);

    // ID
    final Long id = LongHashFunction.xx().hashChars(ultimosSismosChile.id);

    Seismic theSeismic = new Seismic(
        ultimosSismosChile.state,
        utcTime,
        localTime,
        chileanTime,
        ultimosSismosChile.reference,
        ultimosSismosChile.magnitude,
        ultimosSismosChile.scale,
        ultimosSismosChile.latitude,
        ultimosSismosChile.longitude,
        ultimosSismosChile.depth,
        id,
        ultimosSismosChile.url,
        ultimosSismosChile.source
    );

    return theSeismic;
  }

  /**
   * Exceptions.
   * @param ultimosSismosChile - The Seismic.
   */
  private static void throwingException(UltimosSismosChile ultimosSismosChile) {

    // If its null
    if (ultimosSismosChile == null) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Host
    String host = getHost(ultimosSismosChile.url);

    // State
    if (ultimosSismosChile.state == null) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Reference
    if (ultimosSismosChile.reference == null) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Magnitude
    if(ultimosSismosChile.magnitude == 0.0d) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Scale
    if (ultimosSismosChile.scale == null) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Latitude
    if (ultimosSismosChile.latitude == 0.0d) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Longitude
    if(ultimosSismosChile.longitude == 0.0d) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Depth
    if (ultimosSismosChile.depth == null) {
      throw new AlertApiService.AlertAPIException("NULL");
    }

    // Source
    if (ultimosSismosChile.source == null) {

      if (host != null) {
        ultimosSismosChile.source = host;

      } else {
        ultimosSismosChile.source = "*NO-SOURCE";
        log.warn("Seismic without source: {}", toString(ultimosSismosChile));
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
