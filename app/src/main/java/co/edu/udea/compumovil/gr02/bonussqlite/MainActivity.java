package co.edu.udea.compumovil.gr02.bonussqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DataBaseManager manager;
    private Cursor cursor;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    private EditText editText;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DataBaseManager(this);
        listView = (ListView) findViewById(R.id.listView);

        manager.insertar("Jaime", "11111111");
        manager.insertar2("Juan", "22222222");
        manager.insertar("Ana", "33333333");
        manager.eliminar("juan");
        manager.modificarTelefono("Ana", "555555555");

        cursor = manager.cargarCursorContactos();
        adapter = new SimpleCursorAdapter(this, R.layout.two_line_list_item, cursor, new String[] {DataBaseManager.CN_NAME}, new int[] {R.id.textViewItem}, 0);
        listView.setAdapter(adapter);

        imageButton = (ImageButton) findViewById(R.id.imageBtn_buscar);
        editText = (EditText) findViewById(R.id.editText_buscar);

        imageButton.setOnClickListener(this);


    }

    @Override
    protected void onStop() {
        super.onStop();
        manager.eliminarTodo();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.imageBtn_buscar){
            Cursor c =manager.buscarContacto(editText.getText().toString());
            adapter.changeCursor(c);
        }
    }
}
