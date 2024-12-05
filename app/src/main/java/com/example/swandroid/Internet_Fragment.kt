package com.example.swandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import android.widget.EditText
import android.widget.Button
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class InternetFragment : Fragment() {

    private lateinit var webView: WebView  // WebView를 멤버 변수로 선언

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_internet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 툴바 설정
        val toolbar: Toolbar = view.findViewById(R.id.toolbar_internet)
        toolbar.setNavigationIcon(R.drawable.ic_back) // 뒤로가기 아이콘 설정
        toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()  // 뒤로가기 버튼 클릭 시 이전 Fragment로 이동
        }

        // URL 입력창, 이동 버튼, WebView 초기화
        val urlInput: EditText = view.findViewById(R.id.url_input)
        val goButton: Button = view.findViewById(R.id.go_button)
        webView = view.findViewById(R.id.webview)  // WebView 초기화

        // WebView 설정
        webView.webViewClient = WebViewClient()  // WebView에서 링크 클릭 시 외부 브라우저가 아닌 WebView에서 열림
        webView.settings.javaScriptEnabled = true  // JavaScript 사용 허용

        // 이동 버튼 클릭 시 URL 로드
        goButton.setOnClickListener {
            val url = urlInput.text.toString()
            if (url.isNotEmpty()) {
                val formattedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    "https://$url"
                } else {
                    url
                }
                webView.loadUrl(formattedUrl)  // WebView에 URL 로드
            } else {
                Toast.makeText(context, "URL을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
