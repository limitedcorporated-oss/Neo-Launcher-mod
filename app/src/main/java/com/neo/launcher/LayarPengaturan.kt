package com.neo.launcher

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LayarPengaturan(onClose: () -> Unit) {
    val context = LocalContext.current
    
    // State sementara untuk visual sakelar (nantinya dihubungkan ke SharedPreferences)
    var format24Jam by remember { mutableStateOf(true) }
    var sembunyikanStatus by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize().background(Color(0xFF080A0F))) {
        Column(Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 14.dp)) {
            
            // Header Terminal
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp, top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "< cd ..", 
                    color = Color(0xFFE5C07B), 
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable { onClose() }.padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "~/settings", 
                    color = Color(0xFF63E6BE), 
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Text("────────────────────────", color = Color(0xFF39414D), fontFamily = FontFamily.Monospace)
            Spacer(Modifier.height(16.dp))

            // Daftar Menu Pengaturan
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                
                item { JudulKategori("[ TAMPILAN ]") }
                item { 
                    SakelarPengaturan("Format 24 Jam", format24Jam) { format24Jam = it }
                }
                item { 
                    SakelarPengaturan("Sembunyikan Status Bar", sembunyikanStatus) { sembunyikanStatus = it }
                }
                
                item { Spacer(Modifier.height(16.dp)) }
                
                item { JudulKategori("[ APLIKASI ]") }
                item { 
                    MenuKlik("Batas Ikon Beranda", "8 terpilih") { /* TODO: Buka dialog */ }
                }
                item { 
                    MenuKlik("Aplikasi Tersembunyi", "0 aplikasi") { /* TODO: Buka daftar */ }
                }

                item { Spacer(Modifier.height(16.dp)) }

                item { JudulKategori("[ SISTEM ]") }
                item { 
                    MenuKlik("Buka Pengaturan Bawaan HP", "sys") { 
                        context.startActivity(Intent(Settings.ACTION_SETTINGS))
                    }
                }
                item { 
                    MenuKlik("Tentang Neo Launcher", "v0.1") { }
                }
            }
        }
    }
}

// Komponen Pembantu agar kode rapi

@Composable
fun JudulKategori(judul: String) {
    Text(
        text = judul,
        color = Color(0xFFC678DD), // Warna ungu terminal
        fontFamily = FontFamily.Monospace,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun SakelarPengaturan(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onCheckedChange(!checked) }.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color(0xFFE6E6E6), fontFamily = FontFamily.Monospace, fontSize = 15.sp)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFF080A0F),
                checkedTrackColor = Color(0xFF63E6BE), // Hijau saat aktif
                uncheckedThumbColor = Color(0xFF7F8793),
                uncheckedTrackColor = Color(0xFF39414D)
            )
        )
    }
}

@Composable
fun MenuKlik(label: String, nilaiInfo: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color(0xFFE6E6E6), fontFamily = FontFamily.Monospace, fontSize = 15.sp)
        if (nilaiInfo.isNotEmpty()) {
            Text(nilaiInfo, color = Color(0xFF7F8793), fontFamily = FontFamily.Monospace, fontSize = 13.sp)
        }
    }
}
