package labianlabs.tinhpt.rwenglish.Components

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.GridView
import labianlabs.tinhpt.rwenglish.Adapter.VocabularyAdapter
import labianlabs.tinhpt.rwenglish.Model.Vocabulary
import labianlabs.tinhpt.rwenglish.R

class FlipComponent(context: Context) : View(context) {

    fun createView(): View {
        val inflater = LayoutInflater.from(context)
        _flipView = inflater.inflate(R.layout.flip_component, null) as GridView
        commonSetupUI()
        setOnItemClick()
        return _flipView
    }

    fun updateView(vocabularys: List<Vocabulary>) {
        this.datas = vocabularys as ArrayList<Vocabulary>
        val vocabularyAdapter = VocabularyAdapter(vocabularys, context)
        _flipView!!.adapter = vocabularyAdapter
    }

    private fun setOnItemClick() {
        _flipView.setOnItemClickListener { parent, view, position, id ->
            if (currentPosition == position) {
                currentPosition = -1
            } else {
                currentPosition = position
                if (currentId != datas.get(position).idWord) {
                    currentId = datas.get(position).idWord
                } else {
                    onSelectedCorrect!!.invoke(currentId)
                }
            }

        }
    }

    private fun commonSetupUI() {
        val width = resources.displayMetrics.densityDpi
        _flipView!!.columnWidth = width / 4
        _flipView!!.gravity = Gravity.CENTER
        _flipView!!.numColumns = 4
        _flipView!!.horizontalSpacing = 2
    }

    //region VARS
    private lateinit var _flipView: GridView
    var onSelectedCorrect: ((Int) -> Unit)? = null

    private var currentId = -1
    private var currentPosition = -1
    private lateinit var datas: ArrayList<Vocabulary>
    //endregion
}