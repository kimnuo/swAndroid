package com.example.swandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
class ContactAddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_add, container, false)

        // 툴바 설정
        val toolbar: Toolbar = view.findViewById(R.id.toolbar_contact)
        toolbar.setNavigationIcon(R.drawable.ic_back) // 뒤로가기 아이콘 설정
        toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()  // 뒤로가기 버튼 클릭 시 이전 Fragment로 이동
        }

        // EditText 설정
        val companyEditText: EditText = view.findViewById(R.id.add_company_input)
        val nameEditText: EditText = view.findViewById(R.id.add_usr_name_input)
        val emailEditText: EditText = view.findViewById(R.id.add_email_input)
        val phoneNumEditText: EditText = view.findViewById(R.id.add_phonenum_input)
        val jobEditText: EditText = view.findViewById(R.id.add_job_input)

        // 저장 버튼 클릭 리스너
        val saveButton: Button = view.findViewById(R.id.addSaveButton)
        saveButton.setOnClickListener {
            val company = companyEditText.text.toString()
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneNumEditText.text.toString()
            val job = jobEditText.text.toString()

            if (company.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && job.isNotEmpty()) {
                // 데이터를 저장하는 로직 추가
                Toast.makeText(requireContext(), "연락처 저장됨: $name", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 취소 버튼 클릭 리스너
        val cancelButton: Button = view.findViewById(R.id.addCancelButton)
        cancelButton.setOnClickListener {
            // 모든 입력 필드를 비우고 취소 처리
            companyEditText.text.clear()
            nameEditText.text.clear()
            emailEditText.text.clear()
            phoneNumEditText.text.clear()
            jobEditText.text.clear()

            requireActivity().supportFragmentManager.popBackStack()  // 뒤로가기 버튼 클릭 시 이전 Fragment로 이동
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
}