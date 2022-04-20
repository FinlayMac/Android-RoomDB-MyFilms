package finlay.moray.uhi.myfilms

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import finlay.moray.uhi.myfilms.database.Cinema
import finlay.moray.uhi.myfilms.database.Films
import finlay.moray.uhi.myfilms.database.MyFilmsDao
import finlay.moray.uhi.myfilms.database.MyFilmsDatabaseConnection
import finlay.moray.uhi.myfilms.databinding.ActivityPage2Binding


class Page2 : AppCompatActivity() {

    private lateinit var binding: ActivityPage2Binding
    lateinit var dataSource: MyFilmsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPage2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = requireNotNull(this).application
        dataSource = MyFilmsDatabaseConnection.getInstance(application).databaseDAO

        binding.CinemaBtn.setOnClickListener { GotoNewCinema() }
        //binding.FilmsBtn.setOnClickListener { GotoNewFilm() }
        binding.ListBtn.setOnClickListener { GotoList() }

        binding.saveButton.setOnClickListener { AddNewFilm() }

        SetupSpinner()
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


    fun AddNewFilm() {

        var NewFilm = Films()
        NewFilm.FilmName = binding.filmNameEdit.text.toString()
        NewFilm.FilmRating = binding.filmRating.rating

        val selectedCinema = binding.cinemaSelector.selectedItem.toString()
        val loadedID = dataSource.getCinemaIDByName(selectedCinema)
        NewFilm.CinemaID = loadedID

        dataSource.insertNewFilm(NewFilm)

        binding.filmNameEdit.setText("")
        binding.filmRating.rating = 0f

        Toast.makeText(this, "Saved Film", Toast.LENGTH_SHORT).show()
    }


    fun SetupSpinner() {

        val ListOfCinemas = dataSource.getAllCinemas()

        if (ListOfCinemas.count() > 0) {
            var cinemaNames: MutableList<String> = arrayListOf()

            for (cinema in ListOfCinemas) {
                cinemaNames.add(cinema.CinemaName)
            }

            // Create an ArrayAdapter using a simple spinner layout and languages array
            val aa = ArrayAdapter(this, R.layout.simple_spinner_item, cinemaNames)
            // Set layout to use when the list of choices appear
            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            // Set Adapter to Spinner
            binding.cinemaSelector.setAdapter(aa)

        } else {
            Toast.makeText(this, "Please add a cinema first", Toast.LENGTH_SHORT).show()
            GotoNewCinema()
        }
    }


}