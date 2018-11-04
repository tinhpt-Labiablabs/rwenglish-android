package labianlabs.tinhpt.rwenglish.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import labianlabs.tinhpt.rwenglish.Model.Vocabulary;
import labianlabs.tinhpt.rwenglish.R;

public class VocabularyAdapter extends BaseAdapter {

    //region CONSTRUCTOR
    public VocabularyAdapter(List<Vocabulary> vocabularies, Context context) {
        this.vocabularies = vocabularies;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //endregion

    //region SYSTEM
    @Override
    public int getCount() {
        return this.vocabularies.size();
    }

    @Override
    public Object getItem(int position) {
        return this.vocabularies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = new ViewHolder();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.vocabulary_item,null);
            viewHolder.imageVocabulary = convertView.findViewById(R.id.image_vocabulary);
            viewHolder.textVocabulary = convertView.findViewById(R.id.text_vocabulary);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        //data
        viewHolder.textVocabulary.setText(vocabularies.get(position).getOriginWord());
        viewHolder.imageVocabulary.setVisibility(View.INVISIBLE);
        //
        return  convertView;
    }
    //region

    //region VARS
    private List<Vocabulary> vocabularies;
    private Context context;

    private ViewHolder viewHolder;
    private LayoutInflater inflater;

}

class ViewHolder{
    TextView textVocabulary;
    ImageView imageVocabulary;
}
