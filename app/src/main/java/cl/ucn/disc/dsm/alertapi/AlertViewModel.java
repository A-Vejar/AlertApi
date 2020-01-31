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

package cl.ucn.disc.dsm.alertapi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cl.ucn.disc.dsm.alertapi.model.Seismic;
import cl.ucn.disc.dsm.alertapi.services.AlertService;
import cl.ucn.disc.dsm.alertapi.services.alertapi.AlertApiService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertViewModel extends ViewModel {

  /**
   * Logger.
   */
  private static final Logger log =
      LoggerFactory.getLogger(AlertViewModel.class);

  /**
   * Size of news.
   */
  private static final String SELECT_TYPE = "ultimos_sismos_chile";

  /**
   * MutableLiveData
   */
  private final MutableLiveData<List<Seismic>> seismic = new MutableLiveData<>();

  /**
   * The Exception in case of error.
   */
  private final MutableLiveData<Exception> exception = new MutableLiveData<>();

  /**
   * The provider.
   */
  private AlertService service = new AlertApiService();

  /**
   * LiveData of News to use in the view.
   *
   * @return - The List of News inside a LiveData.
   */
  public LiveData<List<Seismic>> getSelect() {
    return seismic;
  }

  /**
   * LiveData of Exception to use in the view.
   *
   * @return - The Exception in case of error.
   */
  public LiveData<Exception> getException() {
    return exception;
  }

  /**
   * Update the internal list of News.
   *
   * <p>NOTE: Need to run in background.</p>.
   *
   * @return - The number of news loaded.
   */
  public int refresh() {
    try {
      // 1. Get the list of news from NewsApi.
      final List<Seismic> seismics = service.getSelect(SELECT_TYPE);

      // 2. Set the values (NEED to be in background).
      seismic.postValue(seismics);

      // 3. All ok.
      return seismics.size();

    } catch (final Exception e) {
      log.error("Error", e);

      // 2. Set the exception.
      exception.postValue(e);

      // 3. All error.
      return -1;
    }
  }
}
