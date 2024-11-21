package com.example.swandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contactList: List<String>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    // ViewHolder를 생성하는 부분
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ContactViewHolder(view)
    }

    // ViewHolder에 데이터를 바인딩하는 부분
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contactName = contactList[position]
        holder.bind(contactName)
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int {
        return contactList.size
    }

    // ViewHolder 클래스
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(android.R.id.text1)

        // 연락처 이름을 텍스트뷰에 설정하고, 클릭 리스너 설정
        fun bind(contactName: String) {
            textView.text = contactName

            itemView.setOnClickListener {
                // 연락처 상세 화면으로 이동하는 토스트 메시지
                Toast.makeText(itemView.context, "연락처 상세", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
