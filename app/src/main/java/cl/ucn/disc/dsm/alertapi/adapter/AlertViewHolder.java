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
import cl.ucn.disc.dsm.alertapi.R;
import cl.ucn.disc.dsm.alertapi.databinding.RowBinding;
import cl.ucn.disc.dsm.alertapi.model.Seismic;
import cl.ucn.disc.dsm.alertapi.services.alertapi.AlertApiService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Instant;
import org.threeten.bp.ZonedDateTime;

public class AlertViewHolder extends RecyclerView.ViewHolder {

  /**
   * Bindings.
   */
  private final RowBinding binding;

  /**
   * Logger.
   */
  private static final Logger log =
      LoggerFactory.getLogger(AlertViewHolder.class);

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

    // UTC Date
    binding.tvDateUtc.setText("UTC - " + String.valueOf(seismic.getUtc_time()));

    // Magnitude
    binding.tvMagnitude.setText(String.valueOf(seismic.getMagnitude()) + " " + seismic.getScale());

    // Coordinates
    binding.tvDepth.setText("Depth: " + String.valueOf(seismic.getDepth()) + " Km.");
    binding.tvLatitude.setText("Latitude: " + String.valueOf(seismic.getLatitude()));
    binding.tvLongitude.setText("Longitude: " + String.valueOf(seismic.getLongitude()));

    // Reference
    binding.tvReference.setText(seismic.getReference());

    // Chile date
    binding.tvDateChile.setText(String.valueOf(seismic.getChilean_time().substring(11)));

    // Source
    binding.tvSource.setText(seismic.getSource());

    // URL Picture
    if (seismic.getUrl() != null) {

      String url = "http://chilealerta.tbmsp.net/api/seismogram/?la=LA&lo=LO&da=DA&he=140&n=C1,C,IU,II,GE,MX";

      String la = String.valueOf(seismic.getLatitude());
      String lo = String.valueOf(seismic.getLongitude());
      String da = seismic.getUtc_time().replace("/", "-").replace(" ", "%20");

      String urlPic = url.replace("LA", la).replace("LO",lo).replace("DA",da);

      try {
        URI uri = new URI(urlPic);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
            .setUri(String.valueOf(uri))
            .build();

        seismic.setUrlPic(urlPic);
      } catch (final URISyntaxException | NullPointerException ex) {
        return;
      }
      log.debug("URL GET = {}", seismic.getUrlPic());

      binding.sdvPicture.setImageURI(seismic.getUrlPic());
    } else {

      this.binding.sdvPicture.setImageResource(R.drawable.ic_launcher_background);
    }
  }
}
