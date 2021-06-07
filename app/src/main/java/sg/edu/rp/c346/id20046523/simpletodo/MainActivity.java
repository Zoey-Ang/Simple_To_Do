package sg.edu.rp.c346.id20046523.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etDo;
    Button btnAdd, btnClear,btnDel;
    ListView lvList;
    Spinner spAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDo=findViewById(R.id.editTextToDo);
        btnAdd=findViewById(R.id.buttonAdd);
        btnClear=findViewById(R.id.buttonClear);
        lvList=findViewById(R.id.listViewToDoList);
        spAction=findViewById(R.id.spinnerActions);
        btnDel=findViewById(R.id.buttonDel);

        //creating an ArrayList:
        ArrayList<String> toDoList = new ArrayList<String>();

        //binding the data to the listView
        ArrayAdapter alList = new ArrayAdapter(this, android.R.layout.simple_list_item_1,toDoList);
        lvList.setAdapter(alList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String things=etDo.getText().toString();
                toDoList.add(things);
                alList.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoList.clear();
                alList.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etDo.getText().toString());
                if(pos <= toDoList.size())
                {
                    toDoList.remove(pos);
                    alList.notifyDataSetChanged();
                }
                else if(pos >= toDoList.size())
                {
                    Toast.makeText(MainActivity.this, "Wrong index Number", Toast.LENGTH_LONG).show();
                }
                else if (toDoList.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"You don't have any task to remove", Toast.LENGTH_LONG).show();
                }
            }
        });

        spAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        etDo.setHint("Type a new task here");
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;

                    case 1:
                        etDo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}