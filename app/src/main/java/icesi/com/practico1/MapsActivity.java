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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE = 11 ;

    private GoogleMap mMap;

    public MarkerOptions actual;

    private LocationManager manager;

    public Double lat;

    public Double lon;

    private Marker marc;


//    public PolylineOptions polyBiblio;
//
//    public PolylineOptions polySecond;
//
//    public PolylineOptions polyThird;


    public Polygon polygonBiblio;

    public Polygon polygonSecond;

    public Polygon polygonThird;


    public LatLng oneB = new LatLng(3.341661, -76.529788);
    public LatLng twoB = new LatLng(3.341666, -76.530072);
    public LatLng threeB = new LatLng(3.341923, -76.530062);
    public LatLng fourB = new LatLng(3.341902, -76.529783);
    public LatLng fiveB = new LatLng(3.341650, -76.529783);

    public LatLng oneS = new LatLng(3.342062, -76.530609);
    public LatLng twoS = new LatLng(3.342175, -76.530620);
    public LatLng threeS = new LatLng(3.342121, -76.530255);
    public LatLng fourS = new LatLng(3.342030, -76.530250);
    public LatLng fiveS = new LatLng(3.342062, -76.530609);

    public LatLng oneT = new LatLng(3.342801, -76.530733);
    public LatLng twoT = new LatLng(3.342791, -76.530105);
    public LatLng threeT = new LatLng(3.342496, -76.530138);
    public LatLng fourT = new LatLng(3.342518, -76.530701);
    public LatLng fiveT  = new LatLng(3.342801, -76.530733);

    public FloatingActionButton boton_preguntas;


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
        boton_preguntas = findViewById(R.id.btn_preguntas);


        //boton_preguntas.setVisibility(0);
        boton_preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                

                iniciarPreguntas();
            }
        });


        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_CODE);

        inicializarPolys();



        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, new LocationListener() {

            private boolean first=false;



            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                LatLng ln = new LatLng(lat,lon);


                if(first==false){
                    actual = new MarkerOptions().position(ln).title("Mi ubicación").icon(BitmapDescriptorFactory.fromResource(R.drawable.manx));
                    marc = mMap.addMarker(actual);
                    //mMap.addMarker(actual);
                    first=true;
                }
                else{

                    marc.remove();

                    actual = new MarkerOptions().position(ln).title("Mi ubicación").icon(BitmapDescriptorFactory.fromResource(R.drawable.manx));
                    marc = mMap.addMarker(actual);

                    //mMap.addMarker(actual);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(ln));

                    Toast.makeText(getApplicationContext(), "Ubicación actual actualizada", Toast.LENGTH_LONG).show();

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


    public void inicializarPolys(){

//        polyBiblio = new PolylineOptions();
//        polySecond = new PolylineOptions();
//        polyThird = new PolylineOptions();


        polygonBiblio = mMap.addPolygon(new PolygonOptions()
                .add(oneB, twoB, threeB, fourB));
        polygonBiblio.setStrokeColor(Color.RED);



        polygonSecond = mMap.addPolygon(new PolygonOptions()
                .add(oneS, twoS, threeS, fourS));
        polygonSecond.setStrokeColor(Color.BLUE);

        polygonThird = mMap.addPolygon(new PolygonOptions()
                .add(oneT, twoT, threeT, fourT));
        polygonBiblio.setStrokeColor(Color.GREEN);

        boolean inside =


//        polyBiblio.add(new LatLng[] {
//                oneB,
//                twoB,
//                threeB,
//                fourB,
//                fiveB
//        });
//        mMap.addPolyline(polyBiblio);
//
//
//
//        polySecond.add(new LatLng[] {
//                oneS,
//                twoS,
//                threeS,
//                fourS,
//                fiveS
//        });
//        mMap.addPolyline(polySecond);
//
//
//
//        polyThird.add(new LatLng[] {
//                oneT,
//                twoT,
//                threeT,
//                fourT,
//                fiveT
//        });
//        mMap.addPolyline(polyThird);




    }


    boolean enmarcada(double top, double left,
                      double bottom, double right,
                      double latitude, double longitude){
        /* Check latitude bounds first. */
        if(top >= latitude && latitude >= bottom){
                /* If your bounding box doesn't wrap
                   the date line the value
                   must be between the bounds.
                   If your bounding box does wrap the
                   date line it only needs to be
                   higher than the left bound or
                   lower than the right bound. */
            if(left <= right && left <= longitude && longitude <= right){
                return true;
            } else if(left > right && (left <= longitude || longitude <= right)) {
                return true;
            }
        }
        return false;
    }

}
