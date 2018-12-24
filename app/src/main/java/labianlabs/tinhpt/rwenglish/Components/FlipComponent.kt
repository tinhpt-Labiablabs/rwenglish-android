package labianlabs.tinhpt.rwenglish.Components

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.GridView
import labianlabs.tinhpt.rwenglish.Adapter.VocabularyAdapter
import labianlabs.tinhpt.rwenglish.Model.Vocabulary
import labianlabs.tinhpt.rwenglish.R

class FlipComponent(context: Context) : View(context) {

    //region UTILS
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
                Log.e("POSITION",position.toString())
            } else {
                currentPosition = position
                if (currentId != datas.get(position).idWord) {
                    currentId = datas.get(position).idWord
                    onSelected!!.invoke()
                } else {
                    onSelectedCorrect!!.invoke(currentId)
                }
            }

        }
    }

    private fun commonSetupUI() {
        val width = resources.displayMetrics.densityDpi
        _flipView.columnWidth = width / 4
        _flipView.gravity = Gravity.CENTER
        _flipView.numColumns = 4
        _flipView.horizontalSpacing = 2
        _flipView.horizontalSpacing = 16
        _flipView.verticalSpacing = 16
    }

    //endregion

    //region VARS
    var onSelectedCorrect: ((Int) -> Unit)? = null
    var onSelected: (()->Unit)? = null

    private lateinit var _flipView: GridView
    private var currentId = -1
    private var currentPosition = -1
    private lateinit var datas: ArrayList<Vocabulary>
    //endregion
}