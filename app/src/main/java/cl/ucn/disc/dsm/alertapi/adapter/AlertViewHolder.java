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
   * @param rowBinding to use.
   */
  public AlertViewHolder(final RowBinding rowBinding) {
    super(rowBinding.getRoot());
    binding = rowBinding;
  }

  /**
   * Bind to the ViewHolder.
   *
   * @param seismic - To bind.
   */
  public void bind(final Seismic seismic) {

    binding.tvDateUtc.setText(String.valueOf(seismic.getDateUtc()));
    binding.tvDateChile.setText(String.valueOf(seismic.getDateChile()));
    /*
    // ZonedDateTime to Date.
    final Date date = DateTimeUtils.toDate(seismic.getDateChile().toInstant());
    binding.tvDateChile.setText(PRETTY_TIME.format(date));
    */
    binding.tvReference.setText(seismic.getReference());
    binding.tvMagnitude.setText(String.valueOf(seismic.getMagnitude()));
    binding.tvScale.setText(seismic.getScale());
    binding.tvLatitude.setText(String.valueOf(seismic.getLatitude()));
    binding.tvLongitude.setText(String.valueOf(seismic.getLongitude()));
    binding.tvDepth.setText(String.valueOf(seismic.getDepth()));
    binding.tvUrl.setText(seismic.getUrl());
    binding.tvSource.setText(seismic.getSource());
  }
}
