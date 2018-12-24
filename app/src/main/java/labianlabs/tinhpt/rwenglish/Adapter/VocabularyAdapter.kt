package labianlabs.tinhpt.rwenglish.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import labianlabs.tinhpt.rwenglish.Model.Vocabulary
import labianlabs.tinhpt.rwenglish.R

class VocabularyAdapter(var vocabularies: List<Vocabulary>, var context: Context) : BaseAdapter() {

    //region VARS
    private var viewHolder: ViewHolder? = null
    private val inflater: LayoutInflater
    //endregion

    //region CONSTRUCTOR
    init {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    //endregion

    //region SYSTEM EVENTS
    override fun getCount(): Int {
        return this.vocabularies.size
    }

    override fun getItem(position: Int): Any {
        return this.vocabularies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        viewHolder = ViewHolder()
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.vocabulary_item, null)
            viewHolder!!.imageVocabulary = convertView!!.findViewById(R.id.image_vocabulary)
            viewHolder!!.textVocabulary = convertView.findViewById(R.id.text_vocabulary)
            convertView.tag = viewHolder
        }
        viewHolder = convertView.tag as ViewHolder
        //data
        viewHolder!!.textVocabulary!!.text = vocabularies[position].originWord
        viewHolder!!.imageVocabulary!!.setImageResource(vocabularies[position].imageResourceStandForWord)
        updateIsDisplayImage(viewHolder!!, vocabularies[position].isDisplayImage)
        //
        return convertView
    }

    private fun updateIsDisplayImage(holder: ViewHolder, isDisplayImage: Boolean) {
        if (isDisplayImage) {
            holder.textVocabulary!!.visibility = View.INVISIBLE
            holder.imageVocabulary!!.visibility = View.VISIBLE
        } else {
            holder.textVocabulary!!.visibility = View.VISIBLE
            holder.imageVocabulary!!.visibility = View.INVISIBLE
        }
    }

}

internal class ViewHolder {
    var textVocabulary: TextView? = null
    var imageVocabulary: ImageView? = null
}
