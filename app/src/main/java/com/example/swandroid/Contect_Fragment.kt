package com.example.swandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactFragment : Fragment() {

    // Adapter를 클래스 수준에서 선언
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        // RecyclerView 설정
        val contactList: RecyclerView = view.findViewById(R.id.contactList)
        contactList.layoutManager = LinearLayoutManager(requireContext()) // LinearLayoutManager 설정

        // 연락처 데이터 설정
        val contactData = listOf("홍길동", "이순신", "김유신", "박지성")  // 예시 데이터
        contactAdapter = ContactAdapter(contactData)  // 어댑터를 클래스 변수로 초기화
        contactList.adapter = contactAdapter  // RecyclerView에 어댑터 설정

        // 연락처 추가 버튼 클릭 리스너 설정
        val addContactButton: Button = view.findViewById(R.id.addContactButton)
        addContactButton.setOnClickListener {
            Toast.makeText(requireContext(), "연락처 추가", Toast.LENGTH_SHORT).show()  // 토스트 메시지 표시
            replaceFragment(ContactAddFragment())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 툴바 설정
        val toolbar: Toolbar = view.findViewById(R.id.toolbar_contact)
        toolbar.setNavigationIcon(R.drawable.ic_back) // 뒤로가기 아이콘 설정
        toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()  // 뒤로가기 버튼 클릭 시 이전 Fragment로 이동
        }
    }
    // Fragment 전환을 위한 함수
    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // 적절한 container ID를 사용하여 교체
            .addToBackStack(null) // 백스택에 추가하여 뒤로가기 기능 활성화
            .commit()
    }
}
