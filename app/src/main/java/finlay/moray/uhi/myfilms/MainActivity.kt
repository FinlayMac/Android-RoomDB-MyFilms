package finlay.moray.uhi.myfilms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import finlay.moray.uhi.myfilms.database.Cinema
import finlay.moray.uhi.myfilms.database.MyFilmsDao
import finlay.moray.uhi.myfilms.database.MyFilmsDatabaseConnection
import finlay.moray.uhi.myfilms.databinding.ActivityMainBinding
import kotlin.math.log

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

        //navigation
       // binding.CinemaBtn.setOnClickListener {GotoNewCinema() }
        binding.FilmsBtn.setOnClickListener {GotoNewFilm() }
        binding.ListBtn.setOnClickListener {GotoList() }
        binding.dropCinemaTable.setOnClickListener {DropCinemaTable() }
    }

    fun GotoNewCinema() {
        startActivity(Intent(this, MainActivity::class.java))
    }
    fun GotoNewFilm() {
        startActivity(Intent(this, Page2::class.java))
    }
    fun GotoList() {
        startActivity(Intent(this, ListOfFilms::class.java))
    }

    fun DropCinemaTable(){
        dataSource.clearCinemaTable()
        Toast.makeText(this, "Deleted all cinemas", Toast.LENGTH_SHORT).show()
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


    }
}