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
import cl.ucn.disc.dsm.alertapi.services.mockup.MockupAlertService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertServiceTest {

  /**
   * Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(AlertServiceTest.class);

  /**
   * Test {@link AlertService#getSelect(String)}.
   */
  @Test
  public void testGetAlertMockup() {

    log.debug("Testing the AlertService ...");

    // The Seismic service
    final AlertService alertService = new MockupAlertService();

    // The List of Seismic.
    final List<Seismic> seismic = alertService.getSelect("ultimos_sismos");

    Assertions.assertNotNull(seismic);
    //Assertions.assertEquals(seismic.size(), 2, "Error de tamanio");

    for (final Seismic aSeismic : seismic) {
      log.debug("Seismic: {}.", aSeismic.getSource());
    }

    log.debug("Done.");
  }

  /**
   * Test {@link AlertService#getSelect(String)}
   */
  @Test
  public void testGetAlertApi() {

    // ultimos_sismos_chile | ultimos_sismos
    final String select = "ultimos_sismos";

    log.debug("Testing the AlertApiService, requesting = {}", select);

    // The Seismic service
    final AlertService alertService = new AlertApiService();

    // The List of Seismic.
    final List<Seismic> seismic = alertService.getSelect(select);

    Assertions.assertNotNull(seismic);

    log.debug("SIZE = {}", seismic.size());
    log.debug("SEISMIC = {}", seismic);

    for(int i = 0; i < seismic.size(); i++) {
      log.debug("SEISMIC: {}.", seismic.get(i));
    }

    log.debug("Done.");
  }
}
