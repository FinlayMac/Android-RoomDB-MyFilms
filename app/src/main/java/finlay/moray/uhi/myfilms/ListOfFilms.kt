package finlay.moray.uhi.myfilms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import finlay.moray.uhi.myfilms.database.MyFilmsDao
import finlay.moray.uhi.myfilms.databinding.ActivityListOfFilmsBinding


class ListOfFilms : AppCompatActivity() {

    private lateinit var binding: ActivityListOfFilmsBinding
    lateinit var dataSource: MyFilmsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListOfFilmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.CinemaBtn.setOnClickListener { GotoNewCinema() }
        binding.FilmsBtn.setOnClickListener {GotoNewFilm() }
        //binding.ListBtn.setOnClickListener { GotoList() }
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