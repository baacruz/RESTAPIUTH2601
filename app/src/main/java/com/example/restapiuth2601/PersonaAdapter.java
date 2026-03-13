package com.example.restapiuth2601;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.restapiuth2601.Configuracion.Personas;

import java.util.List;

public class PersonaAdapter extends ArrayAdapter<Personas> {

    public PersonaAdapter(@NonNull Context context, @NonNull List<Personas> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_persona, parent, false);
        }

        Personas persona = getItem(position);

        ImageView imgPersona = convertView.findViewById(R.id.imgPersona);
        TextView nombres = convertView.findViewById(R.id.txtNombres);
        TextView telefono = convertView.findViewById(R.id.txtTelefono);
        TextView direccion = convertView.findViewById(R.id.txtDireccion);

        if (persona != null) {
            nombres.setText(persona.getNombres() + " " + persona.getApellidos());
            telefono.setText("Tel: " + persona.getTelefono());
            direccion.setText("Dir: " + persona.getDireccion());

            // Decodificar Base64 y mostrar la imagen
            if (persona.getFoto() != null && !persona.getFoto().isEmpty()) {
                try {
                    byte[] decodedString = Base64.decode(persona.getFoto(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imgPersona.setImageBitmap(decodedByte);
                } catch (Exception e) {
                    imgPersona.setImageResource(android.R.drawable.ic_menu_report_image);
                }
            } else {
                imgPersona.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        }

        return convertView;
    }
}
