package finlay.moray.uhi.myfilms.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_table")
class Films {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "film_id")
    var FilmID: Long = 0L

    @ColumnInfo(name = "film_name")
    var FilmName: String = ""

    @ColumnInfo(name = "film_rating")
    var FilmRating: Float = 0f

    @ColumnInfo(name = "cinema_id")
    var CinemaID: Long = 0L

}

