package com.example.calculator

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class CurrentLocationActivity : AppCompatActivity() {

    private lateinit  var supportMapFragment: SupportMapFragment
    private lateinit var client:FusedLocationProviderClient
    private lateinit var go: TextView
    private lateinit var search:EditText
    var  gMap: GoogleMap? = null
    private lateinit var list: List<Address>
    private lateinit var animLinear:LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_location)
        supportActionBar?.hide()
        go = findViewById(R.id.goButton)
        search=findViewById(R.id.search)
        val zoomIn:ImageView=findViewById(R.id.zoomIn)
        val zoomOut:ImageView=findViewById(R.id.zoomOut)
        animLinear=findViewById(R.id.animLinear)

        list = ArrayList()

        supportMapFragment= (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        client = LocationServices.getFusedLocationProviderClient(this)
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }else {

            loadMap()

            zoomIn.setOnClickListener {
                val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
                animLinear.startAnimation(animation)
            }
            zoomOut.setOnClickListener {
                val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
                animLinear.startAnimation(animation)
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadMap()
            } else {
                Toast.makeText(this, "want permission", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun loadMap() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101)
        }
        else {
            val task=client.lastLocation
            task.addOnSuccessListener { location: Location? ->

                if (location!=null){
                    supportMapFragment.getMapAsync(OnMapReadyCallback { googleMap ->
                        gMap = googleMap
                        val latLng=LatLng(location.latitude,location.longitude)
                        val markerOptions=MarkerOptions().position(latLng)
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10f))
                        googleMap.addMarker(markerOptions)
                    })
                }
            }
                go.setOnClickListener {
                    val geocode: Geocoder? = Geocoder(this)
                    val location: String? = search.text.toString()
                    if (location != null) {
                        try {
                            if (geocode != null) {
                                list = geocode.getFromLocationName(location, 1)
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }


                    }
                    if (list!=null && location!=null && list!!.size>0) {
                        val address: Address = list!!.get(0)
                        val latLng: LatLng? = LatLng(address.latitude, address.longitude)
                        val markerOptions = MarkerOptions().position(latLng!!)
                        gMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                        gMap?.addMarker(markerOptions)
                    } else {
                        Toast.makeText(this, "Enter valid place!!!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
    }
