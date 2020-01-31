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

package cl.ucn.disc.dsm.alertapi.adapter;

import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.alertapi.databinding.RowBinding;
import cl.ucn.disc.dsm.alertapi.model.Seismic;
import java.util.Date;
import org.ocpsoft.prettytime.PrettyTime;
import org.threeten.bp.DateTimeUtils;

public class AlertViewHolder extends RecyclerView.ViewHolder {

  /**
   * The Date formatter.
   */
  private static final PrettyTime PRETTY_TIME = new PrettyTime();

  /**
   * Bindings.
   */
  private final RowBinding binding;

  /**
   * Constructor.
   *
   * @param rowNewsBinding to use.
   */
  public AlertViewHolder(final RowBinding rowNewsBinding) {
    super(rowNewsBinding.getRoot());
    binding = rowNewsBinding;
  }

  /**
   * Bind to the ViewHolder.
   *
   * @param seismic - To bind.
   */
  public void bind(final Seismic seismic) {

    binding.tvDepth.setText((int) seismic.getDepth());
    binding.tvLatitude.setText((int) seismic.getLatitude());
    binding.tvLongitude.setText((int) seismic.getLongitude());
    binding.tvSource.setText(seismic.getSource());
    binding.tvUrl.setText(seismic.getUrl());
    binding.tvReference.setText(seismic.getReference());
    binding.tvSource.setText(seismic.getSource());

    // ZonedDateTime to Date.
    final Date date = DateTimeUtils.toDate(seismic.getDateChile().toInstant());
    binding.tvDate.setText(PRETTY_TIME.format(date));
  }
}