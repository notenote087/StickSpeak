package com.example.admin.stickspeak;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.net.HttpURLConnection;
import java.util.Locale;


public class Main2Activity extends AppCompatActivity {
    private static final String API_KEY = "AIzaSyBpZWnQ_1GULZ2hp7qVY1SSmqd6xYSDoB8";
    String targetLanguage = "en";
    Handler textViewHandler;
    TextView textView;
    ProgressDialog progressDialog;

    TextToSpeech sp ; //""
    int result ;
    String speak = "";

   //Spinner  spinnerTran;

    @Override
    protected void onPause() {
        if(sp !=null){
            sp.stop();
            sp.shutdown();

        }
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        final EditText editText = (EditText) findViewById(R.id.editText);
        final Button button = (Button) findViewById(R.id.button);
        final Button button2 = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        textViewHandler = new Handler();



        //spinnerTran = (Spinner) findViewById(R.id.spinnerTran);

        final Spinner spinnerLanguage = (Spinner) findViewById(R.id.spinnerTran);
        final String[] LanguageTarget = getResources().getStringArray(R.array.lang1);

        ArrayAdapter<String> adapterLanguage = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,LanguageTarget);

        spinnerLanguage.setAdapter(adapterLanguage);
/*
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    targetLanguage = "th";
                } else {
                    targetLanguage = "en";
                }
            }
        });*/

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String Language = parent.getItemAtPosition(position).toString();


                switch (Language){
                    case "English" : targetLanguage = "en" ;
                        break;
                    case "Japan" : targetLanguage = "ja" ;
                        break;
                    case "China" : targetLanguage = "zh-CN" ;
                        break;
                    case "Thailand" : targetLanguage = "th" ;
                        break;
                }



                sp = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            sp.setLanguage(Locale.UK);
                            switch (Language){
                                case "English" : result = sp.setLanguage(Locale.UK);
                                    break;
                                case "Japan" : result = sp.setLanguage(Locale.JAPAN);
                                    break;
                                case "China" : result = sp.setLanguage(Locale.CHINA);
                                    break;
                                case "Thailand" : result = sp.setLanguage(new Locale("th"));  // Not support Locale.THAI
                                    break;
                            }
                        }
                    }
                });


            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (speak == null || speak == "")  Toast.makeText(Main2Activity.this,"ยังไม่กดแปลเลย", Toast.LENGTH_SHORT).show();
               else sp.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TranslateText().execute(editText.getText().toString());
            }
        });


    }

    private class TranslateText extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Main2Activity.this);
            progressDialog.setTitle("Translating...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            TranslateOptions options = TranslateOptions.newBuilder()
                    .setApiKey(API_KEY)
                    .build();
            Translate translate = options.getService();
            final Translation translation =
                    translate.translate(params[0],
                            Translate.TranslateOption.targetLanguage(targetLanguage));
            textViewHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (textView != null) {
                        textView.setText(translation.getTranslatedText());
                        speak = translation.getTranslatedText().toString();
                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void arg) {
            super.onPostExecute(arg);
            if(progressDialog.isShowing()) { progressDialog.dismiss(); }
        }
    }


}

/*
public class Main2Activity extends AppCompatActivity {
*/



  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
*/
      //  final TextView editText = (TextView) findViewById(R.id.textView3);
      //  final Handler textViewHandler = new Handler();
     //  final String textTran = "CAT" ;

/*
        TranslateOptions options = TranslateOptions.newBuilder()
                .setApiKey("AIzaSyBpZWnQ_1GULZ2hp7qVY1SSmqd6xYSDoB8")
                .build();
        Translate translate = options.getService();
        //Translate translate = TranslateOptions.getDefaultInstance().getService();
        final Translation translation =
                translate.translate("cat",
                        Translate.TranslateOption.sourceLanguage("en"),
                        Translate.TranslateOption.targetLanguage("th"));

*/

        //editText.setText(translation.getTranslatedText());
/*
    new AsyncTask<Void, Void, Void>() {
        @Override
            protected Void doInBackground(Void... params) {
                TranslateOptions options = TranslateOptions.newBuilder()
                        .setApiKey("AIzaSyBpZWnQ_1GULZ2hp7qVY1SSmqd6xYSDoB8")
                        .build();
                Translate translate = options.getService();
                //Translate translate = TranslateOptions.getDefaultInstance().getService();
                final Translation translation =
                        translate.translate(textTran,
                                Translate.TranslateOption.sourceLanguage("en"),
                                Translate.TranslateOption.targetLanguage("th"));
                textViewHandler.post(new

                                             Runnable() {
                                                 @Override
                                                 public void run () {
                                                     if (editText != null) {
                                                         editText.setText(translation.getTranslatedText());
                                                     }
                                                 }
                                             });
                return null;
            }



        }.execute();*/
    //}


/*
    public void tran(){
        setContentView(R.layout.activity_main2);
         final TextView showText = (TextView) findViewById(R.id.textView3);
         final Handler textViewHandler = new Handler();

        EditText text  = (EditText) findViewById(R.id.editText) ;
          final String tranText = text.getText().toString();



        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                TranslateOptions options = TranslateOptions.newBuilder()
                        .setApiKey("AIzaSyBpZWnQ_1GULZ2hp7qVY1SSmqd6xYSDoB8")
                        .build();
                Translate translate = options.getService();
                //Translate translate = TranslateOptions.getDefaultInstance().getService();
                final Translation translation =
                        translate.translate(tranText,
                                Translate.TranslateOption.sourceLanguage("en"),
                                Translate.TranslateOption.targetLanguage("th"));
                textViewHandler.post(new

                                             Runnable() {
                                                 @Override
                                                 public void run () {
                                                     if (showText != null) {
                                                         showText.setText(translation.getTranslatedText());
                                                     }
                                                 }
                                             });
                return null;
            }



        }.execute();

    }*/

   // }











