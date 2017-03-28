package com.capps.question.Question;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.capps.question.Answer;
import com.capps.question.MainActivity;
import com.capps.question.R;

import java.io.Serializable;

/**
 * Created by varun on 24/3/17.
 */

public class QuestionAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {

    private int mCount;
    private Context mContext;
    private Answer [] mAnswers;


    //Use this Constrictor when you dont have any pervaio answers(ex: new Question with new Answers)
    public QuestionAdapter(int mCount, Context mContext) {
        this.mCount = mCount;
        this.mContext = mContext;
        mAnswers = new Answer[mCount];

    }




    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public Object getItem(int position) {

        return mAnswers[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("posision",position+"");
        final int local_position = position;

        Holder holder;
        if (convertView == null) {
//            convertView = parent.inflate(mContext, R.layout.row_listview_questions, parent); //TODO::Why Error...
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.row_listview_questions,parent);//TODO Why Error
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_listview_questions,parent,false);

            holder = new Holder();
            holder.editText = (EditText) convertView.findViewById(R.id.rowListEditTExtQuestion);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.rowListCheckBoxtQuestion);


            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (mAnswers[local_position] == null)
                        mAnswers[local_position] = new Answer();
                    mAnswers[local_position].setCurrect(isChecked);
                }
            });

            holder.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        if (mAnswers[local_position] == null){
                            mAnswers[local_position]=new Answer();
                        }
                        mAnswers[local_position].setAnswer( ((EditText)v).getText().toString() );
                    }
                }
            });

            convertView.setTag(holder);
        }else
        {
            holder = (Holder) convertView.getTag();
        }
        if (mAnswers[position]!= null){
            holder.editText.setText(mAnswers[position].getAnswer());
            holder.checkBox.setChecked(mAnswers[position].isCurrect());
        }



        return convertView;
    }







    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        int position = (int) buttonView.getTag(); //TODO:: How can I access posision from here..or i should declare & use  new variable
//        if (mAnswers[position] == null)
//            mAnswers[position] = new Answer();
//        mAnswers[position].setCurrect(isChecked);
    }




    class Holder {
        EditText editText;
        CheckBox checkBox;

        }



    public Answer[] getData(){
        return mAnswers;
    }

}
