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
import java.util.ArrayList;
import java.util.List;

public class AlertApiResult {

  /**
   * Metadata.
   */
  public Metadata metadata;

  /**
   * Seismic list to get.
   */
  public List<Seismic> ultimos_sismos = new ArrayList<Seismic>();
}