package com.gb.kotlin_1728_2_1.lesson9

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.room.App.Companion.getHistoryWeatherDao
import com.gb.kotlin_1728_2_1.room.HistoryWeatherEntity
import com.gb.kotlin_1728_2_1.room.ID
import com.gb.kotlin_1728_2_1.room.NAME
import com.gb.kotlin_1728_2_1.room.TEMPERATURE


private const val URI_ALL = 1 // URI для всех записей
private const val URI_ID = 2 // URI для конкретной записи
private const val ENTITY_PATH =
    "HistoryWeatherEntity" // Часть пути (будем определять путь до HistoryEntity


class EducationContentProvider : ContentProvider() {


    private var authorities: String? = null
    private lateinit var uriMatcher: UriMatcher

    private var entityContentType: String? = null
    private var entityContentItemType: String? = null

    private lateinit var contentUri: Uri
    override fun onCreate(): Boolean {
        authorities = context?.resources?.getString(R.string.authorities)
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher.addURI(authorities, ENTITY_PATH, URI_ALL)
        uriMatcher.addURI(authorities, "${ENTITY_PATH}/#", URI_ID)
        entityContentType = "vnd.android.cursor.dir/vnd.$authorities.$ENTITY_PATH"
        entityContentItemType = "vnd.android.cursor.item/vnd.$authorities.$ENTITY_PATH"
        contentUri = Uri.parse("content://${authorities}/${ENTITY_PATH}")
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {

        val historyWeatherDao = getHistoryWeatherDao()
        val entity = mapper(values)
        historyWeatherDao.insert(entity)
        val resultUri = ContentUris.withAppendedId(contentUri, entity.id)
        context?.contentResolver?.notifyChange(resultUri, null)
        return resultUri

    }

    fun mapper(values: ContentValues?): HistoryWeatherEntity {
        values?.let {
            val id = values[ID] as Long
            val name = values[NAME] as String
            val temperature = values[TEMPERATURE] as Int
            return HistoryWeatherEntity(id, name, temperature)
        }
        return HistoryWeatherEntity()
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}