package com.example.elementosvisualestarea.Sensor

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.elementosvisualestarea.MenuExpandible.DrawerBaseActivity
import com.example.elementosvisualestarea.R

class SensorActivity : DrawerBaseActivity() {

    private lateinit var sensorManager: SensorManager
    private var gyroscopeSensor: Sensor? = null
    private lateinit var rootLayout: View


    private var nfcAdapter: NfcAdapter? = null
    private lateinit var statusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        // Inicializar SensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        // Referencia al layout principal
        rootLayout = findViewById(R.id.root_layout)

        // Verificar si el giroscopio está disponible
        if (gyroscopeSensor == null) {
            Toast.makeText(this, "Sensor de giroscopio no disponible", Toast.LENGTH_SHORT).show()
        }

        // Inicializar nfcInfoTextView
        statusTextView = findViewById(R.id.statusTextView)

        // Configurar el adaptador NFC
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null) {
            Toast.makeText(this, "Este dispositivo no soporta NFC", Toast.LENGTH_LONG).show()
            finish()
        } else {
            // Imprime las tecnologías NFC soportadas
            val supportedTechs = arrayOf(
                "android.nfc.tech.NfcA",
                "android.nfc.tech.NfcB",
                "android.nfc.tech.IsoDep",
                "android.nfc.tech.MifareClassic",
                "android.nfc.tech.MifareUltralight",
                "android.nfc.tech.NfcF",
                "android.nfc.tech.NfcV",
                "android.nfc.tech.Ndef"
            )

            val detectedTechs = mutableListOf<String>()

            for (tech in supportedTechs) {
                try {
                    // Intenta crear una instancia de la tecnología para verificar compatibilidad
                    val clazz = Class.forName(tech)
                    if (clazz != null) {
                        detectedTechs.add(tech)
                    }
                } catch (e: Exception) {
                    // Tecnología no soportada
                }
            }

            println("Tecnologías NFC soportadas por este dispositivo:")
            detectedTechs.forEach { println(it) }
        }
    }

    override fun onResume() {
        super.onResume()

        // Registrar Listener para el Giroscopio
        gyroscopeSensor?.let {
            sensorManager.registerListener(
                gyroscopeListener,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_IMMUTABLE
        )

        val intentFilters = arrayOf(
            IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED),
            IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED),
            IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED)
        )

        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, intentFilters, null)
    }

    override fun onPause() {
        super.onPause()

        // Desregistrar Listener del Giroscopio
        sensorManager.unregisterListener(gyroscopeListener)

        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleNfcIntent(intent)
        }
    }

    private fun handleNfcIntent(intent: Intent) {
        val action = intent.action
        if (action == NfcAdapter.ACTION_TAG_DISCOVERED || action == NfcAdapter.ACTION_TECH_DISCOVERED || action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
            val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            if (tag != null) {
                val tagId = tag.id.joinToString(separator = "") { String.format("%02X", it) }
                statusTextView.text = "Etiqueta NFC detectada: $tagId"
            } else {
                statusTextView.text = "No se pudo leer la etiqueta NFC"
            }
        } else {
            statusTextView.text = "Acción NFC no reconocida: $action"
        }
    }

    private val gyroscopeListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null) {
                val rotationX = event.values[0]
                val rotationY = event.values[1]

                when {
                    rotationX > 1.0f || rotationY > 1.0f -> {
                        rootLayout.setBackgroundColor(Color.RED)
                    }
                    rotationX < -1.0f || rotationY < -1.0f -> {
                        rootLayout.setBackgroundColor(Color.BLUE)
                    }
                    else -> {
                        rootLayout.setBackgroundColor(Color.WHITE)
                    }
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }
}
