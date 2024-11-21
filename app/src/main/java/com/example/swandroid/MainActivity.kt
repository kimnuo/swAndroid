package com.example.swandroid

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar 설정
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // 액션 바로 사용할 Toolbar 설정

        // 각 버튼에 대한 클릭 리스너 설정
        findViewById<Button>(R.id.contactManagementButton).setOnClickListener {
            Toast.makeText(this, "연락처 관리", Toast.LENGTH_SHORT).show()
            replaceFragment(ContactFragment()) // ContactFragment로 전환
        }
        findViewById<Button>(R.id.internetSearchButton).setOnClickListener {
            Toast.makeText(this, "인터넷 검색", Toast.LENGTH_SHORT).show()
            replaceFragment(InternetFragment()) // InternetFragment로 전환
        }
        findViewById<Button>(R.id.phoneStatusButton).setOnClickListener {
            Toast.makeText(this, "폰 상태 검색", Toast.LENGTH_SHORT).show()
            replaceFragment(InfoFragment()) // InfoFragment로 전환
            // 폰 상태 검색 기능
        }
        findViewById<Button>(R.id.authorInfoButton).setOnClickListener {
            Toast.makeText(this, "저자 소개", Toast.LENGTH_SHORT).show()
            replaceFragment(AuthorFragment()) // AuthorFragment로 전환
            // 저자 소개 기능
        }
    }

    /**
     * Fragment 교체를 위한 메서드
     */
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment) // fragment_container에 프래그먼트를 교체
        fragmentTransaction.addToBackStack(null) // 뒤로 가기 지원
        fragmentTransaction.commit()
    }
}
