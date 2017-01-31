package games.lancelot.reto1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GoogleMap map;
    Button bZoomIn;
    Button bZoomOut;
    ImageButton bCasa;
    TextView tvVar;
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMapa)).getMap();
        tvVar = (TextView)findViewById(R.id.tvVariable);
        bZoomIn = (Button)findViewById(R.id.bZoomIn);
        bZoomOut = (Button)findViewById(R.id.bZoomOut);
        bCasa = (ImageButton)findViewById(R.id.bCasa);



        bZoomIn.setOnClickListener(this);
        bZoomOut.setOnClickListener(this);
        bCasa.setOnClickListener(this);

        mapillaMuseo(-12.060468, -77.037016, "MALI", "Museo de Arte");
        mapilla(-12.078055, -77.062716, "Municipalidad", "Pueblo Libre");
        mapillaCasa(-12.063071, -77.058807, "Mi casa", "CheckPoint");
        mapillaIglesia(-12.083465, -77.041481, "MCI Lima", "Best Church ever!");
        mapillaUniv(-12.076848, -77.093659, "UPC San Miguel", "La mejor universidad");

    }

    public void mapilla(double altitud, double latitud, String titulo, String snippet)
    {
        LatLng zona = new LatLng(altitud, latitud);
        if (map != null)
        {
            map.addMarker(new MarkerOptions()
                    .position(zona)
                    .title(titulo)
                    .snippet(snippet));
            map.moveCamera(CameraUpdateFactory.newLatLng(zona));
        }

    }

    public void mapillaCasa(double altitud, double latitud, String titulo, String snippet)
    {
        LatLng zona = new LatLng(altitud, latitud);
        if (map != null)
        {
            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.casa3))
                    .position(zona)
                    .title(titulo)
                    .snippet(snippet));
            map.moveCamera(CameraUpdateFactory.newLatLng(zona));
        }


    }

    public void mapillaUniv(double altitud, double latitud, String titulo, String snippet)
    {
        LatLng zona = new LatLng(altitud, latitud);
        if (map != null)
        {
            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.uni4))
                    .position(zona)
                    .title(titulo)
                    .snippet(snippet));
            map.moveCamera(CameraUpdateFactory.newLatLng(zona));
        }

    }

    public void mapillaIglesia(double altitud, double latitud, String titulo, String snippet)
    {
        LatLng zona = new LatLng(altitud, latitud);
        if (map != null)
        {
            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.iglesia))
                    .position(zona)
                    .title(titulo)
                    .snippet(snippet));
            map.moveCamera(CameraUpdateFactory.newLatLng(zona));
        }


    }

    public void mapillaMuseo(double altitud, double latitud, String titulo, String snippet)
    {
        LatLng zona = new LatLng(altitud, latitud);
        if (map != null)
        {
            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bancos))
                    .position(zona)
                    .title(titulo)
                    .snippet(snippet));
            map.moveCamera(CameraUpdateFactory.newLatLng(zona));
        }


    }

    public void zoomIn(GoogleMap map)
    {
        map.moveCamera(CameraUpdateFactory.zoomIn());
    }

    public void zoomOut(GoogleMap map)
    {
        map.moveCamera(CameraUpdateFactory.zoomOut());
    }

    public void moverCamara(GoogleMap map, double altitud, double latitud)
    {
        LatLng zona = new LatLng(altitud, latitud);

        CameraPosition casaCP = new CameraPosition.Builder()
                .target(zona)
                .zoom(20)
                .bearing(300)
                .tilt(30)
                .build();

        map.moveCamera(CameraUpdateFactory.newCameraPosition(casaCP));
    }

    @Override
    public void onClick(View v)
    {
       switch (v.getId())
       {
           case R.id.bZoomIn:
           {
               zoomIn(map);
               cont++;
               if(cont>20)
                   cont = 20;
           }
               break;

           case R.id.bZoomOut:
           {
               zoomOut(map);
               cont--;
               if(cont<0)
                   cont = 0;
           }
               break;

           case R.id.bCasa:
           {
               moverCamara(map,-12.063071, -77.058807);
               cont = -1;
           }
               break;
       }


        if(cont>=5)
            tvVar.setText("Cerca a casa");
        else
            tvVar.setText("Lejos de casa");

            if(cont ==-1)
            {
                tvVar.setText("En casa");
                cont = 8;
            }



    }



    /* @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;
        LatLng sydney = new LatLng(-34,151);

        map.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Sydney")
                .snippet("Ciudad de Nemo"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/
}
