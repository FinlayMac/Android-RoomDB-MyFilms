package finlay.moray.uhi.myfilms.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cinemas_table")
class Cinema {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "cinema_id")
    var CinemaID: Long = 0L

    @ColumnInfo(name = "cinema_name")
    var CinemaName: String = ""

    @ColumnInfo(name = "cinema_location")
    var CinemaLocation: String = ""

}

