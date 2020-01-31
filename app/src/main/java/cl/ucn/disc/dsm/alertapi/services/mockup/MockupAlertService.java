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

package cl.ucn.disc.dsm.alertapi.services.mockup;

import cl.ucn.disc.dsm.alertapi.model.Seismic;
import cl.ucn.disc.dsm.alertapi.services.AlertService;
import cl.ucn.disc.dsm.alertapi.services.alertapi.Metadata;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.ZonedDateTime;

public class MockupAlertService implements AlertService {

  /**
   * Constructor.
   */
  public MockupAlertService(){}

  /**
   * Testing
   *
   * @param select - Filter.
   * @return
   */
  @Override
  public List<Seismic> getSelect(String select) {

    final List<Seismic> seismic = new ArrayList<>();

    seismic.add(new Seismic(
        1,
        ZonedDateTime.now(Seismic.ZONE_ID),
        ZonedDateTime.now(Seismic.ZONE_ID),
        ZonedDateTime.now(Seismic.ZONE_ID),
        "251 km al SE de Antofagasta - Chile",
        3.8,
        "Mb",
        -24.258,
        -68.021,
        132.15,
        (long) 56620244,
        "http:/sismologia.net/?user=demo&p=detalles&id=56620244",
        "INSIMU"
    ));

    return seismic;
  }

  @Override
  public List<Seismic> getExample() {
    return null;
  }
}
