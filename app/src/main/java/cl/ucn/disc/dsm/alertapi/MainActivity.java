package cl.ucn.disc.dsm.alertapi;

import android.os.AsyncTask;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import cl.ucn.disc.dsm.alertapi.adapter.AlertAdapter;
import cl.ucn.disc.dsm.alertapi.adapter.AlertViewHolder;
import cl.ucn.disc.dsm.alertapi.databinding.ActivityMainBinding;
import es.dmoral.toasty.Toasty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends AppCompatActivity {

  /**
   * Logger
   */
  private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

  /**
   * Bindings.
   */
  private ActivityMainBinding binding;

  /**
   * NewsAdapter.
   */
  private AlertAdapter adapter;

  /**
   * ViewModel of News.
   */
  private AlertViewModel model;

  /**
   * @param savedInstanceState to use.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Inflate the layout.
    binding = ActivityMainBinding.inflate(getLayoutInflater());

    // Assign to the main view.
    setContentView(binding.getRoot());

    // Set the toolbar.
    {
      this.setSupportActionBar(binding.toolbar);
    }

    // Adapter + RecyclerView.
    {
      // Adapter.
      adapter = new AlertAdapter();
      binding.rvSeismic.setAdapter(adapter);

      // Layout (ListView).
      binding.rvSeismic.setLayoutManager(new LinearLayoutManager(this));

      // Separator (line).
      binding.rvSeismic.addItemDecoration(
          new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
      );
    }

    // The ViewModel.
    {
      // Build the NewsViewModel.
      model = new ViewModelProvider(this)
          .get(AlertViewModel.class);

      // Observe the list of news.
      model.getSelect()
          .observe(this, seismics -> adapter.setSeismics(seismics));

      // Observe the exception.
      model.getException().observe(this, this::showException);
    }

    // The refresh.
    {
      binding.swlRefresh.setOnRefreshListener(() -> {
        log.debug("Refreshing ..");

        // Run in background.
        AsyncTask.execute(() -> {

          // All ok.
          final int size = model.refresh();
          if (size != -1) {

            // In the UI.
            runOnUiThread(() -> {

              // Hide the loading.
              binding.swlRefresh.setRefreshing(false);

              // Show a message.
              Toasty.success(
                  this, "Alerts fetched: " + size, Toast.LENGTH_SHORT, true
              ).show();
            });
          }
        });
      });
    }
  }

  /**
   * Show the exception.
   *
   * @param exception - Exception to use.
   */
  private void showException(final Exception exception) {

    // Hide the loading.
    binding.swlRefresh.setRefreshing(false);

    // Build the message.
    final StringBuilder sb = new StringBuilder("Error: ");
    sb.append(exception.getMessage());

    if (exception.getCause() != null) {
      sb.append(", ");
      sb.append(exception.getCause().getMessage());
    }

    Toasty.error(this, sb.toString(), Toast.LENGTH_LONG, true).show();
  }
}