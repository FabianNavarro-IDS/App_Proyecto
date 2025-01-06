package com.example.elementosvisualestarea.Camara

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.widget.MediaController
import com.example.elementosvisualestarea.MenuExpandible.DrawerBaseActivity
import com.example.elementosvisualestarea.R
import java.io.IOException
import java.io.OutputStream

class CameraActivity : DrawerBaseActivity() {
    private lateinit var btnTomarFoto: Button
    private lateinit var btnGrabarVideo: Button
    private lateinit var imgFoto: ImageView
    private lateinit var videoView: VideoView

    private val REQUEST_PERMISSIONS = 1

    private val photoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val extras = activityResult.data?.extras
                val imgBitmap = extras?.get("data") as? Bitmap
                if (imgBitmap != null) {
                    imgFoto.setImageBitmap(imgBitmap)
                    saveImageToStorage(imgBitmap)
                } else {
                    Toast.makeText(this, "No se pudo obtener la foto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Captura cancelada", Toast.LENGTH_SHORT).show()
            }
        }

    private val videoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val videoUri = activityResult.data?.data
                if (videoUri != null) {
                    playVideo(videoUri)
                    saveVideoToStorage(videoUri)
                } else {
                    Toast.makeText(this, "No se pudo obtener el video", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Grabación cancelada", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btnTomarFoto = findViewById(R.id.btnTomarFoto)
        btnGrabarVideo = findViewById(R.id.btnGrabarVideo)
        imgFoto = findViewById(R.id.imgFoto)
        videoView = findViewById(R.id.videoView)

        checkAndRequestPermissions()

        btnTomarFoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoLauncher.launch(intent)
        }

        btnGrabarVideo.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            videoLauncher.launch(intent)
        }
    }

    private fun checkAndRequestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissions.any { ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }) {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS)
            } else {
                Toast.makeText(this, "Permisos ya otorgados", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveImageToStorage(bitmap: Bitmap) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "photo_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/Camera")
        }
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            try {
                val outputStream: OutputStream? = contentResolver.openOutputStream(it)
                outputStream?.use { stream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    Toast.makeText(this, "Imagen guardada", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveVideoToStorage(videoUri: Uri) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, "video_${System.currentTimeMillis()}.mp4")
            put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
            put(MediaStore.Video.Media.RELATIVE_PATH, "DCIM/Camera")
        }
        val uri = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            try {
                val outputStream: OutputStream? = contentResolver.openOutputStream(it)
                outputStream?.use { stream ->
                    contentResolver.openInputStream(videoUri)?.copyTo(stream)
                    Toast.makeText(this, "Video guardado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar el video", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun playVideo(videoUri: Uri) {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        videoView.setMediaController(mediaController)
        videoView.setVideoURI(videoUri)

        videoView.setOnPreparedListener {
            videoView.start()
        }

        videoView.setOnCompletionListener {
            Toast.makeText(this, "Video finalizado", Toast.LENGTH_SHORT).show()
        }

        videoView.setOnErrorListener { _, _, _ ->
            Toast.makeText(this, "Error al reproducir el video", Toast.LENGTH_SHORT).show()
            true
        }
    }
}
