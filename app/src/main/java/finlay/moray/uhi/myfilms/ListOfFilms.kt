package finlay.moray.uhi.myfilms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import finlay.moray.uhi.myfilms.database.MyFilmsDao
import finlay.moray.uhi.myfilms.database.MyFilmsDatabaseConnection
import finlay.moray.uhi.myfilms.databinding.ActivityListOfFilmsBinding


class ListOfFilms : AppCompatActivity() {

    private lateinit var binding: ActivityListOfFilmsBinding
    lateinit var dataSource: MyFilmsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListOfFilmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = requireNotNull(this).application
        dataSource = MyFilmsDatabaseConnection.getInstance(application).databaseDAO

        binding.CinemaBtn.setOnClickListener { GotoNewCinema() }
        binding.FilmsBtn.setOnClickListener { GotoNewFilm() }
        //binding.ListBtn.setOnClickListener { GotoList() }
        LoadItems()
    }

    fun LoadItems() {

        val ListOfFilms = dataSource.getAllFilms()

        if (ListOfFilms.count() > 0) {

            for (film in ListOfFilms) {
                val item1 = TextView(this)
                item1.text = film.FilmName
                item1.setTextAppearance(R.style.TitleStyle)
                binding.listOfFilmsLinearLayout.addView(item1)

                val item2 = TextView(this)
                item2.text = "Rating: ${film.FilmRating}"
                item2.setTextAppearance(R.style.RatingStyle)
                binding.listOfFilmsLinearLayout.addView(item2)

                //TODO I could not get the stying to change for buttons when done programmatically.
                val item3 = Button(this)
                item3.text = "More information"
                item3.setTextAppearance(R.style.SmallButton)
                item3.setOnClickListener { GetMoreInformation(film.FilmID) }
                binding.listOfFilmsLinearLayout.addView(item3)

                val item4 = TextView(this)
                item4.setTextAppearance(R.style.SpacerStyle)
                binding.listOfFilmsLinearLayout.addView(item4)
            }
        } else {
            Toast.makeText(this, "Please add some films into the app", Toast.LENGTH_SHORT).show()
            GotoNewFilm()
        }
    }

    fun GetMoreInformation(filmID: Long){
      //  Toast.makeText(this, "$filmID", Toast.LENGTH_SHORT).show()
        val myIntent = Intent(this, FilmView::class.java)
        myIntent.putExtra("longFilmID", filmID)
        startActivity(myIntent)
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
}