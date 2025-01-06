package com.example.elementosvisualestarea.Mapa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elementosvisualestarea.MenuExpandible.DrawerBaseActivity
import com.example.elementosvisualestarea.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : DrawerBaseActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Configurar el fragmento de mapa
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Establecer una ubicación inicial
        val Ceti = LatLng(20.70315, -103.38925) // Ciudad de México
        map.addMarker(MarkerOptions().position(Ceti).title("CETI Colomos"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Ceti, 16f))
    }
}
