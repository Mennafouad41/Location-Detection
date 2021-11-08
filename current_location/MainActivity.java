package com.example.current_location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import android.util.Log;
public class MainActivity extends AppCompatActivity {
    SupportMapFragment SupportMapFragment;
    FusedLocationProviderClient client;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        SupportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
        super.onBackPressed();}
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    SupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {

                            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                            String [] Staions={" Helwan "," Ain Helwan "," Helwan University  "," Wadi Hof "," Hadayek Helwan "," El-Maasara "," Tora El-Asmant"," Kozzika "," Tora El-Balad "," Sakanat El-Maadi "," Maadi "," Hadayek El-Maadi "," Dar El-Salam "," El-Zahraa' "," Mar Girgis"," El-Malek El-Saleh "," Al-Sayeda Zeinab "," Saad Zaghloul "," Sadat "," Nasser "," Orabi "," Al-Shohadaa"," Ghamra 	"," El-Demerdash "," Manshiet El-Sadr "," Kobri El-Qobba "," Hammamat El-Qobba "," Saray El-Qobba "," Hadayeq El-Zaitoun "," Helmeyet El-Zaitoun "," El-Matareyya "," Ain Shams "," Ezbet El-Nakhl "," El-Marg "," New El-Marg 	"," El-Mounib "," Sakiat Mekky 	"," Omm El-Masryeen"," Giza "," Faisal "," Cairo University 	"," El Bohoth 	"," Dokki "," Opera "," Sadat "," Mohamed Naguib "," Attaba "," Al-Shohadaa"," Masarra "," Road El-Farag "," St. Teresa "," Khalafawy "," Mezallat "," Kolleyyet El-Zeraa "," Shubra El-Kheima "," Airport "," Ahmed Galal "," Adly Mansour "," El Haykestep "," Omar Ibn El-Khattab "," Qobaa "," Hesham Barakat "," El-Nozha "," Nadi El-Shams "," Alf Maskan "," Heliopolis Square "," Haroun "," Al-Ahram "," Koleyet El-Banat "," Stadium "," Fair Zone "," Abbassia "," Abdou Pasha "," El Geish "," Bab El Shaaria"," Attaba "," Nasser "," Maspero "," Zamalek "," Kit Kat "," Sudan Street "," Imbaba "," El-Bohy "," El-Kawmeya Al-Arabiya "," Ring Road "," Rod El-Farag Axis "," El-Tawfikeya "," Wadi El-Nil "," Gamaat El Dowal Al-Arabiya "," Bulaq El-Dakroor "," Cairo University 	"};
                            double[] latitude={29.848983683706336,29.86260523730296,29.869473509941233,29.879071294642582 ,29.897123695995713,29.906081424376882,29.925979724443614,29.936253802269896, 29.946762467762362,29.95330284260969,29.960293739026795,29.97010811410712,29.98205855583052,29.99548086180639,30.00610251390484,30.01771078353213,30.029285351937652,30.037031012759474, 30.044115826335712, 30.05342937007976,30.056693473621298,30.061063935236497,30.069032419434784,30.077312993544947, 30.08198430941789,30.08720127082806,30.09123238778473, 30.0976454229688,30.10588646910953,30.113261026849024, 30.121338040011842,30.131031697688268,30.139315712381347,30.152080531717257,30.163646953482864,29.981122897638127,29.99548569449503,30.01070403730045, 30.017329001390628,30.026017928546622, 30.03576268485614,30.03842877793185, 30.044138442112356,30.045309126513093, 30.052332587372522,30.061072015027563,30.07089431450896, 30.08058463161369,30.087947350102137, 30.097884875440286,30.113690749802124, 30.12230951285198,30.115630845969616 ,0,  30.147063450640015,30.14373664252316,30.14037645627881,30.134818911328914,30.130832281840416,30.12799021536805,30.12584061352785,30.11901473567838,30.108419903964652,30.101376439554596,30.09171502326423,30.08343894657538,30.07319409777788,30.07325592725284,30.071990309265427,30.064787019319336,30.06175277325762,30.05414458039824,30.052345381157135,30.05341886108706,30.055696243053525,30.06257571952568,30.06650903246232,30.070186415030282,30.07206738624411,0,0,30.096425288301937,0,0,0,0,30.04197467785316,30.026006509020828,0,0,0};;
                            double[] longitude={31.334233112538353, 31.324877650322993, 31.320063909411335, 31.313578468027824, 31.303963435952195, 31.299522461276506,  31.28754170601499,31.281814432712398, 31.272978948165786, 31.26295354841207, 31.25763386971763,31.250441657913026,31.242167945452803, 31.231175534367996, 31.22958665615008, 31.231207379868497,31.235416849189633,31.238357305275155,31.23441634533044, 31.238715188014094, 31.24605288717192, 31.264624143179343,31.27779586470513, 31.28753365018038,31.29410685987382,31.298917135604484,31.304563374063314, 31.31047876051943, 31.313963278538598, 31.31372705136573, 31.319084300069147, 31.3244241959413, 31.335680348915737,31.338372179631232, 31.212403186122884, 31.20866692891845 ,31.207239740549515, 31.20376605286893 ,31.201181055088284,  31.200151956177855,31.212233930605553,31.234428926977092,31.24415776535683,31.246785953789075,31.246060142993347,31.245095825382922, 31.245411905731736,31.245495255348384, 31.245391897381985,31.24866697303879,31.24435284953465, 31.400168694711287  ,0, 31.42120967182529, 31.40474616264416, 31.39434099850016, 31.38375174225645, 31.37293478029283, 31.360173139658006, 31.348956295863115, 31.34018894121617, 31.338304844875402, 31.332965411386294, 31.326318689357674, 31.32884909545791, 31.318093705877192, 31.300972719448733, 31.283375743599635, 31.274753203049308, 31.266881090070715, 31.255877000010365, 31.24680293836155, 31.238722499968944, 31.232124269259774, 31.222247597480763, 31.213023466676745, 31.20423132851298, 31.200111864625235,0,0,31.199472602004015,0,0,0,0, 31.192955428341797, 31.201155470896584,0,0,0,0};;
                            double[] diff;
                            Integer id=0;
                            double Difference=Math.sqrt((((latLng.latitude-latitude[0])*(latLng.latitude-latitude[0]))+((latLng.longitude-longitude[0])*(latLng.longitude-longitude[0]))));
                            double New_Difference=0;
                            for(Integer i=0;i<latitude.length;i++){
                                New_Difference=Math.sqrt((((latLng.latitude-latitude[i])*(latLng.latitude-latitude[i]))+((latLng.longitude-longitude[i])*(latLng.longitude-longitude[i]))));

                               //Toast.makeText(MainActivity.this, String.valueOf(New_Difference), Toast.LENGTH_LONG).show();
                                if(New_Difference<Difference)
                                {
                                    Difference=New_Difference;
                                    id=i;


                                }
                                else if(New_Difference>Difference){
                                    New_Difference=Difference;

                                }
                                //Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_LONG).show();
                           // Toast.makeText(MainActivity.this, String.valueOf(Staions.length), Toast.LENGTH_LONG).show();
                            Toast.makeText(MainActivity.this, Staions[id], Toast.LENGTH_LONG).show();
                           /* Intent intent = new Intent(HomePageActivity.this, TaskActivity.class);
                            intent.putExtra("station",Staions[id]);


                            startActivity(intent);*/

                            MarkerOptions options=new MarkerOptions().position(latLng)
                                    .title("I am there");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                            googleMap.addMarker(options);
                        }
                    });


                }
            }
        });}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==44)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();

    }
}

    }}