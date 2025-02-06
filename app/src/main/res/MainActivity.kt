import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnExit: Button = findViewById(R.id.btnExit)

        btnExit.setOnClickListener {
            // Tampilkan dialog konfirmasi sebelum keluar
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Keluar")
                .setMessage("Ingin keluar dari aplikasi?")
                .setPositiveButton("Ya") { _, _ ->
                    finishAffinity() // Tutup aplikasi
                }
                .setNegativeButton("Tidak", null)
                .show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView3: TextView = findViewById(R.id.textView3)

        // Terapkan font kustom ke TextView
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.poppins_regular)
        textView3.typeface = typeface

        textView3.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("About Me!")
                .setMessage("hello, I am a student majoring in software engineering")
                .setPositiveButton("OK", null)
                .show()
        }

        val layout = findViewById<ConstraintLayout>(R.id.main)
        val animationDrawable = layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(2000)
        animationDrawable.start()

    }
}
