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

import java.util.ArrayList;
import java.util.List;

/**
 * Metadata class.
 */
public class Metadata {

  /**
   * Metadata Request.
   */
  public String request;

  /**
   * Metadata Submitted.
   */
  public String submitted;

  /**
   * Metadata Database.
   */
  public List<String> database = new ArrayList<String>();
  /**
   * Metadata Version.
   */
  public String version;

  /**
   * Metadata User.
   */
  public String user;

  /**
   * Metadata Err.
   */
  public String err;

  /**
   * Metadata Select - Type of search.
   */
  public List<String> select = new ArrayList<String>();

  /**
   * Metadata Country.
   */
  public String country;

  /**
   * Metadata Limit.
   */
  public Integer limit;

  /**
   * Metadata Min-Magnitude.
   */
  public Integer minMagnitude;

  /**
   * Metadata Status.
   */
  public String status;
}
