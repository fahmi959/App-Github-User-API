package com.fahmi.preferensi_dan_provider_fahmi

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.fahmi.semua_data_dan_model.FahmiUserDatabaseFahmi
import com.fahmi.semua_data_dan_model.FavUserDaonyaDisiniCuy

class UserKontenProvider : ContentProvider() {

    companion object{
        const val AUTHORITY = "com.fahmi"
        const val TABLE_NAME_FAHMI = "favs_user_by_fahmi"
                const val ID_FAVS_DATAUSER_FAHMI = 1
        val urlMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init{
            urlMatcher.addURI(AUTHORITY, TABLE_NAME_FAHMI , ID_FAVS_DATAUSER_FAHMI)
        }
    }

    private lateinit var userDao: FavUserDaonyaDisiniCuy

    override fun onCreate(): Boolean {
        userDao=context?.let {ctx -> FahmiUserDatabaseFahmi.getDatabase(ctx)?.favsFahmiDao()
        }!!
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?,
    ): Cursor? {
        var cursor: Cursor?
        when (urlMatcher.match(uri)) {
            ID_FAVS_DATAUSER_FAHMI -> {
                cursor = userDao.findAll()
                if (context != null){
                    cursor.setNotificationUri(context?.contentResolver, uri)

            }
        }
        else -> {
            cursor = null
        }
    }
        return cursor
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
  return  0
    }

    override fun getType(uri: Uri): String? {
      return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
    return null
    }


    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?,
    ): Int {
   return  0
    }
}