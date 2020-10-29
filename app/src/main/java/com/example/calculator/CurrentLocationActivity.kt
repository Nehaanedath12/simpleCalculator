package com.example.calculator

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CurrentLocationActivity : AppCompatActivity() {

    private lateinit  var supportMapFragment: SupportMapFragment
    private lateinit var client:FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_location)
        supportActionBar?.hide()

        supportMapFragment= (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        client = LocationServices.getFusedLocationProviderClient(this)


        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }
        loadMap()

    }


        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadMap()
            } else {
                Toast.makeText(this, "want permission", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun loadMap() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101)
        } else {

            val task=client.lastLocation
            task.addOnSuccessListener { location: Location? ->
                if (location!=null){
                    supportMapFragment.getMapAsync(OnMapReadyCallback { googleMap ->
                        val latLng=LatLng(location.latitude,location.longitude)
                        val markerOptions=MarkerOptions().position(latLng)
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10f))
                        googleMap.addMarker(markerOptions)
                    })
                }
            }

        }


    }

    }
