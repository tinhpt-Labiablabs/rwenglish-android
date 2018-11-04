package labianlabs.tinhpt.rwenglish.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import labianlabs.tinhpt.rwenglish.Adapter.VocabularyAdapter;
import labianlabs.tinhpt.rwenglish.Model.Vocabulary;
import labianlabs.tinhpt.rwenglish.R;

public class MainActivity extends AppCompatActivity {


    //region SYSTEM
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setupFlip();
        vrData();
    }

    //endregion

    //region UTILS
    private  void initWidget(){
        flipView = findViewById(R.id.flipView);
    }
    private  void setupFlip(){
        int width = getResources().getDisplayMetrics().densityDpi;
        flipView.setColumnWidth(width/4);
        flipView.setGravity(Gravity.CENTER);
        flipView.setNumColumns(4);
        flipView.setHorizontalSpacing(2);
    }

    private void vrData(){
        Vocabulary vocabulary = new Vocabulary("Flip","Lật ngược",1,R.drawable.ic_insignia);
        List<Vocabulary> vocabularies = new ArrayList<>();
        vocabularies.add(vocabulary);
        vocabularies.add(vocabulary);
        vocabularies.add(vocabulary);
        vocabularies.add(vocabulary);
        vocabularies.add(vocabulary);
        vocabularies.add(vocabulary);
        vocabularies.add(vocabulary);
        vocabularies.add(vocabulary);

        VocabularyAdapter vocabularyAdapter = new VocabularyAdapter(vocabularies,this);
        flipView.setAdapter(vocabularyAdapter);
    }

    //endregion


    //region VARS
    private GridView flipView;

    //endregion

}
