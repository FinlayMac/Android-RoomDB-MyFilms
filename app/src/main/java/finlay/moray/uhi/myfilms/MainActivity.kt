package finlay.moray.uhi.myfilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import finlay.moray.uhi.myfilms.database.Cinema
import finlay.moray.uhi.myfilms.database.MyFilmsDao
import finlay.moray.uhi.myfilms.database.MyFilmsDatabaseConnection
import finlay.moray.uhi.myfilms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var dataSource: MyFilmsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = requireNotNull(this).application
        dataSource = MyFilmsDatabaseConnection.getInstance(application).databaseDAO

        binding.saveButton.setOnClickListener { ctx -> SaveAndShowSnackBar(ctx) }
    }


    fun SaveAndShowSnackBar(v: View) {
        Log.i("Save", "Save Button Pressed")

        //Adding to database will go in here
        var NewCinema = Cinema()
        NewCinema.CinemaName = binding.cinemaNameEdit.text.toString()
        NewCinema.CinemaLocation = binding.cinemaLocationEdit.text.toString()

        dataSource.insertNewCinema(NewCinema)

        binding.cinemaNameEdit.setText("")
        binding.cinemaLocationEdit.setText("")


        Log.i("Save", "Save Complete")
        Snackbar.make(v, "Cinema Saved ", Snackbar.LENGTH_SHORT).show()


    }
}