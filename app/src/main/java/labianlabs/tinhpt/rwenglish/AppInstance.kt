package labianlabs.tinhpt.rwenglish

import android.app.Application
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class AppInstance : Application() {

    init {
        mInstance = this
        languageMap = mutableMapOf()
    }

    companion object {
        lateinit var mInstance: AppInstance
        lateinit var languageMap: MutableMap<String, String>
        fun getInstance(): AppInstance {
            return mInstance
        }
    }

    fun getLanguageMap(): MutableMap<String, String> {
        if (!languageMap.isEmpty()) {
            return languageMap
        }
        return detachLocalize()
    }

    fun detachLocalize(): MutableMap<String, String> {
        val detachLine = readLanguageInAssets().split(";")
        detachLine.forEachIndexed { index, s ->
            val detachData = s.trim().split("=")
            if (detachData[0] != "") {
                languageMap.put(detachData[0].trim(), detachData[1].trim())
            }
        }
        return languageMap
    }

    private fun checkLanguage(): String {
        if (Locale.getDefault().displayLanguage.equals("English")) {
            return "languageMapEN.txt"
        } else if (Locale.getDefault().displayLanguage.equals("Tiếng Việt")) {
            return "languageMapVN.txt"
        } else {
            return "languageMapEN.txt"
        }
    }

    private fun readLanguageInAssets(): String {
        val stringBuffer = StringBuffer()
        try {
            val inputStream = mInstance.assets.open(checkLanguage())
            val inputStreamReader: InputStreamReader = InputStreamReader(inputStream)
            var dataLine: String?
            val reader = BufferedReader(inputStreamReader)
            dataLine = reader.readLine()
            while (dataLine != null) {
                stringBuffer.append(dataLine)
                dataLine = reader.readLine()
            }
            reader.close()
        } catch (e: Exception) {
            return " "
        }
        return stringBuffer.toString()
    }
}

inline fun String.localize(): String {
    return if (AppInstance.getInstance().getLanguageMap()[this] != null) {
        AppInstance.getInstance().getLanguageMap()[this].toString()
    } else {
        this
    }
}