package at.technikum_wien.if20b049.step1.data.download

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.PackageManagerCompat.LOG_TAG
import at.technikum_wien.if20b049.step1.R
import at.technikum_wien.if20b049.step1.data.WeatherItem
import at.technikum_wien.if20b049.step1.data.parser.JsonParser
import at.technikum_wien.if20b049.step1.view.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class WeatherDownloader {
    private fun getResponseFromHttpUrl(urlString: String): String? {
        var con: HttpURLConnection? = null
        try {
            val url = URL(urlString)
            //return url.readText()
            con = url.openConnection() as HttpURLConnection
            con.requestMethod = "GET"
            /*return Scanner(con.inputStream).use { scanner ->
                scanner.useDelimiter("\\A")
                if (scanner.hasNext()) scanner.next() else null
            }*/
            val scanner = Scanner(con.inputStream)
            scanner.useDelimiter("\\A")
            if (scanner.hasNext())
                return scanner.next()
            else
                return null
        } catch (ex: MalformedURLException) {
            Log.e(MainActivity.LOG_TAG, "Malformed URL.", ex)
            return null
        } catch (ex: IOException) {
            Log.e(MainActivity.LOG_TAG, "I/O exception", ex)
            return null
        } finally {
            con?.disconnect()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi")
    suspend fun loadWebResult(urlString: String): String? {
        val result = withContext(Dispatchers.IO) {
            getResponseFromHttpUrl(urlString)
        }
        if (result == null) {
            Log.d(LOG_TAG, "Error")
            /*Toast.makeText(this, getString(R.string.general_error),
                Toast.LENGTH_LONG).show()
           // resultTextView?.text = ""*/
        } else {
            return withContext(Dispatchers.Default) {
                val parser = JsonParser()
                val idk = parser.parseResponse(urlString).toString()
                idk
            }
        }
        return null
    }
}