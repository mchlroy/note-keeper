package com.mchlroy.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class NoteListActivity extends AppCompatActivity {

    private BasicAdapter<NoteInfo> adapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });
        
        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterNotes.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        RecyclerView listNotes = findViewById(R.id.list_note);

        adapterNotes = new BasicAdapter<>(DataManager.getInstance().getNotes());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        listNotes.setAdapter(adapterNotes);
        listNotes.setLayoutManager(layoutManager);
        listNotes.addItemDecoration(new DividerItemDecoration(listNotes.getContext(), layoutManager.getOrientation()));

        adapterNotes.setOnClickListener(new OnRecyclerViewItemClickListener<NoteInfo>() {
            @Override
            public void OnClick(View view, int position, NoteInfo note) {
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
                intent.putExtra(NoteActivity.NOTE_POSITION, position);
                startActivity(intent);
            }
        });

    }
}
