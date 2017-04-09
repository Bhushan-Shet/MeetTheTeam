package com.cmb.bhushan.meettheteam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    List<TeamMember> memberList = new ArrayList<>();
    List<Map<String,String>> membersList = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        listView = (ListView) findViewById(R.id.listView1);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, memberList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TeamMember listItem = (TeamMember)listView.getItemAtPosition(position);
                Intent in = new Intent(MainActivity.this, DetailActivity.class);
                in.putExtra("firstName", listItem.getFirstName());
                in.putExtra("lastName", listItem.getLastName());
                in.putExtra("id", listItem.getId());
                in.putExtra("title", listItem.getTitle());
                in.putExtra("bio", listItem.getBio());
                in.putExtra("image", listItem.getImageURL());
                startActivity(in);
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("team.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void initList(){
        String jsonString = loadJSONFromAsset();
        try{
            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("team");

            for(int i = 0; i<jsonMainNode.length();i++){
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String firstName = jsonChildNode.optString("firstName");
                String lastName = jsonChildNode.optString("lastName");
                String title = jsonChildNode.optString("title");
                String bio = jsonChildNode.optString("bio");
                String id = jsonChildNode.optString("id");
                String imageURL = jsonChildNode.optString("avatar");
                memberList.add(createTeamMember(firstName, lastName, title, bio, id, imageURL));
            }
        }
        catch(JSONException e){
            Toast.makeText(getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private TeamMember createTeamMember(String firstName, String lastName, String title, String bio, String id, String imageURL){
        TeamMember member = new TeamMember();
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setTitle(title);
        member.setBio(bio);
        member.setId(id);
        member.setImageURL(imageURL);
        return member;
    }
}
