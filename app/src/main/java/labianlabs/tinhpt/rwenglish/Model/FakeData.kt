package labianlabs.tinhpt.rwenglish.Model

import android.util.Log
import labianlabs.tinhpt.rwenglish.R
import java.util.*

class FakeData() {
    fun createDatas(): List<Vocabulary> {
        val v1 = Vocabulary("Tree", "Cây", 1, R.drawable.ic_tree)
        val v2 = Vocabulary("Laptop", "Máy tính", 2, R.drawable.ic_laptop)
        val v3 = Vocabulary("Mouse", "Con chuột", 3, R.drawable.ic_mouse)
        val v4 = Vocabulary("Mobile", "Di động", 4, R.drawable.ic_smartphone)
        val v5 = Vocabulary("Moon", "Mặt Trăng", 5, R.drawable.ic_moon)
        val v6 = Vocabulary("Lake", "Hồ", 6, R.drawable.ic_lake)
        val v7 = Vocabulary("Pen", "Cây viết", 7, R.drawable.ic_pencil)
        val v8 = Vocabulary("Rulers", "Những cây thước", 8, R.drawable.ic_rules)
        return initializeDuplicateVocabulary(arrayListOf(v1,v2,v3,v4,v5,v6,v7,v8))
    }

    private fun initializeDuplicateVocabulary(data: List<Vocabulary>): MutableList<Vocabulary> {
        val dataFinal: MutableList<Vocabulary> = arrayListOf()
        data.forEachIndexed { index, vocabulary ->
            dataFinal.add(vocabulary)
            dataFinal.add(cloneVocabulary(vocabulary))
        }
        Collections.shuffle(dataFinal)
        return dataFinal
    }

    private fun cloneVocabulary(vo: Vocabulary): Vocabulary {
        val data = Vocabulary(vo.originWord, vo.localizedWord, vo.idWord, vo.imageResourceStandForWord)
        data.isDisplayImage = true
        return data
    }
}