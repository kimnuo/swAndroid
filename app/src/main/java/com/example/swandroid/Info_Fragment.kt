package com.example.swandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.widget.TextView
import android.net.wifi.WifiManager
import android.content.pm.PackageManager


class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        // 안드로이드 버전 표시
        view.findViewById<TextView>(R.id.androidVersionText).text =
            "안드로이드 버전: ${Build.VERSION.RELEASE}"

        // Wi-Fi 상태 표시
        val wifiStatusText: TextView = view.findViewById(R.id.wifiStatusText)
        if (requireContext().checkSelfPermission(android.Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            wifiStatusText.text = "Wi-Fi 상태 확인 권한 없음"
        } else {
            val wifiManager = requireContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
            wifiStatusText.text = "WIFI: ${if (wifiManager.isWifiEnabled) "ON" else "OFF"}"
        }

        // SD 카드 용량 표시
        val sdCardStatusText: TextView = view.findViewById(R.id.sdCardStatusText)
        val externalStorageDir = requireContext().getExternalFilesDir(null)
        externalStorageDir?.let {
            val statFs = StatFs(it.path)
            val totalSpace = statFs.totalBytes / (1024 * 1024 * 1024)
            val availableSpace = statFs.availableBytes / (1024 * 1024 * 1024)
            sdCardStatusText.text = "SDCard 용량: $totalSpace GB / $availableSpace GB 남음"
        } ?: run {
            sdCardStatusText.text = "SDCard 정보를 가져올 수 없습니다."
        }

        // 설치된 앱 수 표시
        val pm = requireContext().packageManager
        val installedApps = pm.getInstalledApplications(0).size
        view.findViewById<TextView>(R.id.installedAppsText).text = "설치된 앱 수: $installedApps"

        // 동작 중인 앱 수 표시
        val activityManager = requireContext().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activityManager.appTasks.size
        } else {
            activityManager.runningAppProcesses.size
        }
        view.findViewById<TextView>(R.id.runningAppsText).text = "동작중인 앱 수: $runningApps"

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 툴바 설정
        val toolbar: Toolbar = view.findViewById(R.id.toolbar_info)
        toolbar.setNavigationIcon(R.drawable.ic_back) // 뒤로가기 아이콘 설정
        toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
