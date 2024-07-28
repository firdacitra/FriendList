package com.afivadnan.y
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Friend(
    var name : String,
    var school : String,
    var hobby : String
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

}
