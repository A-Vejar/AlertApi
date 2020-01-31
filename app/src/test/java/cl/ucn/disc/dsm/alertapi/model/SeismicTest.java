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

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public class SeismicTest {

  /**
   * Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(SeismicTest.class);

  /**
   * The Test of Constructor.
   */
  @Test
  public void testConstructor() {

    log.debug("Testing the Constructor ..");

    // The values.
    final Integer state = 1;
    final ZonedDateTime utcTime = ZonedDateTime.now(Seismic.ZONE_ID);
    final ZonedDateTime localTime = ZonedDateTime.now(Seismic.ZONE_ID);
    final ZonedDateTime date = ZonedDateTime.now(Seismic.ZONE_ID);
    final String reference = "251 km al SE de Antofagasta - Chile";
    final double magnitude = 3.8;
    final String scale = "Mb";
    final double latitude = -24.258;
    final double longitude = -68.021;
    final double depth = 132.15;
    final Long id = Long.valueOf(56620244);
    final String url = "http:/sismologia.net/?user=demo&p=detalles&id=56620244";
    final String source = "INSIMU";


    // The Constructor
    final Seismic seismic =
        new Seismic(state, utcTime, localTime, date, reference, magnitude,
            scale, latitude, longitude, depth, id, url, source);

    // Testing
    Assertions.assertEquals(state, seismic.getState());
    Assertions.assertEquals(utcTime, seismic.getDateUtc());
    Assertions.assertEquals(localTime, seismic.getDateLocal());
    Assertions.assertEquals(date, seismic.getDateChile());
    Assertions.assertEquals(reference, seismic.getReference());
    Assertions.assertEquals(magnitude, seismic.getMagnitude());
    Assertions.assertEquals(scale, seismic.getScale());
    Assertions.assertEquals(latitude, seismic.getLatitude());
    Assertions.assertEquals(longitude, seismic.getLongitude());
    Assertions.assertEquals(depth, seismic.getDepth());
    Assertions.assertEquals(id, seismic.getId());
    Assertions.assertEquals(url, seismic.getUrl());
    Assertions.assertEquals(source, seismic.getSource());

    log.debug("Done.");
  }
}
