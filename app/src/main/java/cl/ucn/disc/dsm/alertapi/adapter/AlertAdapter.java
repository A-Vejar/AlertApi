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

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.alertapi.databinding.RowBinding;
import cl.ucn.disc.dsm.alertapi.model.Seismic;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertAdapter extends RecyclerView.Adapter<AlertViewHolder> {

  /**
   * Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(AlertAdapter.class);

  /**
   * News list.
   */
  private List<Seismic> seismic;

  /**
   * Constructor.
   */
  public AlertAdapter() {

    // Empty list of news.
    seismic = new ArrayList<>();

    // Each News has an unique ID.
    setHasStableIds(true);
  }

  /**
   * Change the current List.
   *
   * @param theSeismic - To use.
   */
  public void setSeismic(final List<Seismic> theSeismic) {

    // Update the news.
    seismic = theSeismic;

    // Notify to re-layout.
    this.notifyDataSetChanged();
  }

  /**
   * Called when RecyclerView needs a newViewHolder ...
   * of the given type to represent an item.
   */
  @Override
  public AlertViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
      final int viewType) {

    // The inflater.
    final LayoutInflater layoutInflater =
        LayoutInflater.from(parent.getContext());

    // The row
    final RowBinding rowBinding = RowBinding.inflate(layoutInflater, parent, false);

    final AlertViewHolder viewHolder = new AlertViewHolder(rowBinding);

    /*
     * Click Listener ...
     */
    rowBinding.getRoot().setOnClickListener(view -> {

      // The position.
      final int position = viewHolder.getAdapterPosition();

      // The id.
      final long id = viewHolder.getItemId();
      log.debug("Click! position: {}, id: {}.", position, Long.toHexString(id));

      // News to show.
      final Seismic seismic = this.seismic.get(position);

      log.debug("Link: {}.", seismic.getUrl());
      if (seismic.getUrl() != null) {

        // Open the browser.
        parent.getContext().startActivity(
            new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(seismic.getUrl())
            )
        );
      }
    });

    return viewHolder;
  }

  /**
   * Called by RecyclerView to display the data at the specified position.
   * This method should update the contents of the ViewHolder to reflect the
   * item at the given position.
   */
  @Override
  public void onBindViewHolder(@NonNull final AlertViewHolder holder,
      final int position) {
    holder.bind(seismic.get(position));
  }

  /**
   * Returns the total number of items in the data set held by the adapter.
   */
  @Override
  public int getItemCount() {
    return seismic.size();
  }

  /**
   * Return the stable ID for the item at position.
   */
  @Override
  public long getItemId(final int position) {
    return seismic.get(position).getId();
  }
}
