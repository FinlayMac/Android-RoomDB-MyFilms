package finlay.moray.uhi.myfilms.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyFilmsDao {

    @Insert
    fun insertNewCinema(NewCinema: Cinema)

    @Delete
    fun deleteSingleCinema(CinemaToDelete: Cinema)

    //deletes the contents of a whole table
    @Query("DELETE FROM cinemas_table")
    fun clearCinemaTable()


    @Insert
    fun insertNewFilm(NewFilm: Films)

    @Delete
    fun deleteSingleFilm(FilmToDelete: Films)

    //deletes the contents of a whole table
    @Query("DELETE FROM films_table")
    fun clearFilmsTable()



    @Query("SELECT * from cinemas_table ORDER BY cinema_name ASC")
    fun getAllCinemas(): List<Cinema>
}

