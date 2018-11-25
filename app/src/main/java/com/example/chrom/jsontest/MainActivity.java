package com.example.chrom.jsontest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RVAdapter.RVClickListener {
    String url="https://rawgit.com/startandroid/data/master/messages/";
    RecyclerView recyclerView;
    RVAdapter rvAdapter;
    private List<Message> jsonData=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView=(TextView)findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessagesAPI messagesApi = retrofit.create(MessagesAPI.class);

        Call<List<Message>> messages = messagesApi.messagesCall();


        messages.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    textView.setText("response " + response.body().size());
                    jsonData=response.body();
                    swap();
                } else {
                    textView.setText("response code " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                textView.setText("failure " + t);
            }
        });


    }

    private void swap(){
        if (open()){
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            findViewById(R.id.rId).setVisibility(View.VISIBLE);
        }
    }

/*public void addPhone(View view){
        String id = idText.getText().toString();
        String time = timeText.getText().toString();
        String text= textText.getText().toString();
        Phone phone = new Phone(id, time,text);
        phones.add(phone);
        adapter.notifyDataSetChanged();
    }

    public void save(View view){

        boolean result = JSONHelper.exportToJSON(this, phones);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }*/

    public boolean open(){

        if(jsonData!=null){
            recyclerView=(RecyclerView)findViewById(R.id.rId);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            rvAdapter=new RVAdapter(getApplicationContext(),jsonData);
            recyclerView.setAdapter(rvAdapter);
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(int position, View v) {

    }

    @Override
    public void onItemLongClick(int position, View v) {

    }

    /*class Async extends AsyncTask<Void,Void,Boolean> {

        private final Context context;
        private boolean status=false;

        public Async(Context context) {
            this.context=context;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
            HttpPost httppost = new HttpPost(http://someJSONUrl/jsonWebService);

            FTPClient ftp = null;
            try {
                ftp = new FTPClient();
                ftp.connect(server);ftp.login(user, password);

                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                ftp.enterLocalPassiveMode();
                OutputStream outputStream = null;

                boolean success = false;
                try {
                    outputStream = context.openFileOutput("data.json",MODE_PRIVATE);
                    success = ftp.retrieveFile(filename, outputStream);
                } finally {
                    if (outputStream != null) {
                        outputStream.close();
                        status=true;
                    }
                }

                return success;
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (ftp != null) {
                    try {
                        ftp.logout();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        ftp.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            swap();
        }
    }*/
}
