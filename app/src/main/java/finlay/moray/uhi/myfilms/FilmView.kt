package finlay.moray.uhi.myfilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import finlay.moray.uhi.myfilms.database.MyFilmsDao
import finlay.moray.uhi.myfilms.database.MyFilmsDatabaseConnection
import finlay.moray.uhi.myfilms.databinding.ActivityFilmViewBinding
import finlay.moray.uhi.myfilms.databinding.ActivityListOfFilmsBinding


class FilmView : AppCompatActivity() {

    private lateinit var binding: ActivityFilmViewBinding
    lateinit var dataSource: MyFilmsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = requireNotNull(this).application
        dataSource = MyFilmsDatabaseConnection.getInstance(application).databaseDAO

        LoadData()
    }

    fun LoadData() {
        val mIntent = intent
        val longValue = mIntent.getLongExtra("longFilmID", -1L)

        val SelectedFilm = dataSource.getFilmByID(longValue)

        if (SelectedFilm != null) {
            binding.filmNameView.text = SelectedFilm.FilmName
            binding.filmRatingView.rating = SelectedFilm.FilmRating

            val FilmCinema = dataSource.getCinemaByID(SelectedFilm.CinemaID)
            binding.cinemaNameView.text = FilmCinema.CinemaName
            binding.cinemaLocationView.text = FilmCinema.CinemaLocation

        }
    }

}