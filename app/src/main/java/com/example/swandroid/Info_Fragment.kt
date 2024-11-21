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

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        // 안드로이드 버전 표시
        val androidVersionText: TextView = view.findViewById(R.id.androidVersionText)
        val androidVersion = android.os.Build.VERSION.RELEASE
        androidVersionText.text = "안드로이드 버전: $androidVersion"

        // Wi-Fi 상태 표시
        val wifiStatusText: TextView = view.findViewById(R.id.wifiStatusText)
        val wifiManager = context?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val isWifiEnabled = wifiManager.isWifiEnabled
        wifiStatusText.text = "WIFI: ${if (isWifiEnabled) "ON" else "OFF"}"

        // SD 카드 용량 표시
        val sdCardStatusText: TextView = view.findViewById(R.id.sdCardStatusText)
        val statFs = StatFs(Environment.getExternalStorageDirectory().absolutePath)

        val totalSpace = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            statFs.totalBytes / (1024 * 1024 * 1024) // GB 단위
        } else {
            0 // Android 18 이하에서는 처리 방법 변경 필요
        }
        val availableSpace = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            statFs.availableBytes / (1024 * 1024 * 1024) // GB 단위
        } else {
            0
        }
        sdCardStatusText.text = "SDCard 용량: $totalSpace GB / $availableSpace GB 남음"

        // 설치된 앱 수 표시
        val installedAppsText: TextView = view.findViewById(R.id.installedAppsText)
        val pm = context?.packageManager
        val installedApps = pm?.getInstalledApplications(0)?.size ?: 0
        installedAppsText.text = "설치된 앱 수: $installedApps"

        // 동작 중인 앱 수 표시
        val runningAppsText: TextView = view.findViewById(R.id.runningAppsText)
        val activityManager = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activityManager.appTasks.size
        } else {
            activityManager.runningAppProcesses.size
        }
        runningAppsText.text = "동작중인 앱 수: $runningApps"

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 툴바 설정
        val toolbar: Toolbar = view.findViewById(R.id.toolbar_info)
        toolbar.setNavigationIcon(R.drawable.ic_back) // 뒤로가기 아이콘 설정
        toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()  // 뒤로가기 버튼 클릭 시 이전 Fragment로 이동
        }
    }
}
