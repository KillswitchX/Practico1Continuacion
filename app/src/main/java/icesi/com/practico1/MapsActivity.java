package icesi.com.practico1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE = 11 ;

    private GoogleMap mMap;

    public MarkerOptions actual;

    private LocationManager manager;

    public Double lat;

    public Double lon;

    private Marker marc;

    public Polygon polygonBiblio;

    public Polygon polygonA;

    public Polygon polygonM;

    public LatLng ln;


    public LatLng oneB = new LatLng(3.341650, -76.530121);
    public LatLng twoB = new LatLng(3.341932, -76.530110);
    public LatLng threeB = new LatLng(3.341923, -76.529812);
    public LatLng fourB = new LatLng(3.341639, -76.529812);
   // public LatLng fiveB = new LatLng(3.341650, -76.529783);

    public LatLng oneS = new LatLng(3.342125, -76.530372);
    public LatLng twoS = new LatLng(3.342255, -76.530369);
    public LatLng threeS = new LatLng(3.342243, -76.530064);
    public LatLng fourS = new LatLng(3.342123, -76.530068);
    //public LatLng fiveS = new LatLng(3.342062, -76.530609);

    public LatLng oneT = new LatLng(3.342801, -76.530733);
    public LatLng twoT = new LatLng(3.342791, -76.530105);
    public LatLng threeT = new LatLng(3.342496, -76.530138);
    public LatLng fourT = new LatLng(3.342518, -76.530701);
    //public LatLng fiveT  = new LatLng(3.342801, -76.530733);

    public FloatingActionButton boton_preguntas;

    public  FloatingActionButton boton_canje;

    public FloatingActionButton boton_faciles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        boton_preguntas = findViewById(R.id.btn_preguntasDificles);
        boton_faciles = findViewById(R.id.btn_preguntasFaciles);
        boton_canje = findViewById(R.id.btn_canje);

        //boton_preguntas.setVisibility(0);
        boton_preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iniciarPreguntas();
            }
        });

        boton_faciles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        boton_canje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarCanje();
            }
        });


        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_CODE);

        inicializarPolys();


        boton_preguntas.hide();
        boton_faciles.hide();
        boton_canje.hide();

        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, new LocationListener() {

            private boolean first=false;

            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                ln = new LatLng(lat,lon);


                if(first==false){
                    actual = new MarkerOptions().position(ln).title("Mi ubicación").icon(BitmapDescriptorFactory.fromResource(R.drawable.manx));
                    marc = mMap.addMarker(actual);
                    //mMap.addMarker(actual);

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ln, 18));
                    first=true;


                    if(PolyUtil.containsLocation(ln, polygonBiblio.getPoints(), true)){
                        Toast.makeText(getApplicationContext(), "Estás dentro del poligono Biblio", Toast.LENGTH_LONG).show();
                        boton_canje.show();

                    }
                    else{
                        boton_canje.hide();
                    }


                    if(PolyUtil.containsLocation(ln, polygonA.getPoints(), true)){
                        Toast.makeText(getApplicationContext(), "Estás dentro del poligono Edificio A", Toast.LENGTH_LONG).show();
                        boton_faciles.show();
                    }
                    else{
                        boton_faciles.hide();
                    }

                    if(PolyUtil.containsLocation(ln, polygonM.getPoints(), true)){
                        Toast.makeText(getApplicationContext(), "Estás dentro del poligono Edificio M", Toast.LENGTH_LONG).show();

                        boton_preguntas.show();
                    }

                    else{
                        boton_preguntas.hide();
                    }
                }
                else{

                    marc.remove();

                    actual = new MarkerOptions().position(ln).title("Mi ubicación").icon(BitmapDescriptorFactory.fromResource(R.drawable.manx));
                    marc = mMap.addMarker(actual);

                    //mMap.addMarker(actual);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(ln));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ln, 18));

                    Toast.makeText(getApplicationContext(), "Ubicación actual actualizada", Toast.LENGTH_LONG).show();



                    if(PolyUtil.containsLocation(ln, polygonBiblio.getPoints(), true)){
                        Toast.makeText(getApplicationContext(), "Estás dentro del poligono Biblio", Toast.LENGTH_LONG).show();
                        boton_canje.show();
                    }
                    else{
                        boton_canje.hide();
                    }

                    if(PolyUtil.containsLocation(ln, polygonA.getPoints(), true)){
                        Toast.makeText(getApplicationContext(), "Estás dentro del poligono Edificio A", Toast.LENGTH_LONG).show();

                        boton_faciles.show();
                    }
                    else{
                        boton_faciles.hide();
                    }


                    if(PolyUtil.containsLocation(ln, polygonM.getPoints(), true)){
                        Toast.makeText(getApplicationContext(), "Estás dentro del poligono Edificio M", Toast.LENGTH_LONG).show();
                        boton_preguntas.show();
                    }
                    else{


                        boton_preguntas.hide();



                    }

                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });


    }


    public void iniciarCanje(){
        Intent i = new Intent(MapsActivity.this, Canje.class);
        startActivity(i);
    }

    public void iniciarPreguntas(){
        Intent i = new Intent(MapsActivity.this, Preguntas.class);
        startActivity(i);
    }

    public void iniciarFaciles(){
        Intent i = new Intent(MapsActivity.this, Preguntas.class);
        startActivity(i);
    }


    public void inicializarPolys(){

        polygonBiblio = mMap.addPolygon(new PolygonOptions()
                .add(oneB, twoB, threeB, fourB));
        polygonBiblio.setStrokeColor(Color.YELLOW);

        polygonA = mMap.addPolygon(new PolygonOptions()
                .add(oneS, twoS, threeS, fourS));
        polygonA.setStrokeColor(Color.GREEN);

        polygonM = mMap.addPolygon(new PolygonOptions()
                .add(oneT, twoT, threeT, fourT));
        polygonM.setStrokeColor(Color.RED);

    }


}
