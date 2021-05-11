package com.wmm.wanandroid.features.home

import android.content.Context
import android.content.Intent
import android.view.View
import android.webkit.*
import com.wmm.wanandroid.R
import com.wmm.wanandroid.core.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*


/**
 *  文章、项目详情页
 * @author wangmeng
 * @date 2021-04-26
 */
class BrowseActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_URL = "param_url"

        fun callingIntent(context: Context, url: String?) {
            val intent = Intent(context, BrowseActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_URL, url)
            context.startActivity(intent)
        }
    }

    private var mWebUrl: String? = null

    override fun layoutId() = R.layout.activity_webview

    override fun initializeView() {
        mWebUrl = intent.getStringExtra(INTENT_EXTRA_PARAM_URL)
        titleBar.setNavigationOnClickListener { finish() }
        setupWebView(webView)
        setupWebClient(webView)
        loadUrl(mWebUrl)
    }


    /**
     * 加载url
     */
    private fun loadUrl(url: String?) {
        webView.loadUrl(url)
    }

    /**
     * 设置webview的基本属性
     *
     * @param webView 容器
     */
    private fun setupWebView(webView: WebView) {
        with(webView.settings) {
            allowFileAccess = false
            allowFileAccessFromFileURLs = false
            allowUniversalAccessFromFileURLs = false
            loadWithOverviewMode = true
            domStorageEnabled = true
            allowContentAccess = true
            databaseEnabled = true
            useWideViewPort = true
            setAppCacheEnabled(true)
            setGeolocationEnabled(true);
            userAgentString = userAgentString.plus("wanAndorid")
        }
    }

    /**
     * 设置webview的加载监听
     *
     * @param webview  容器
     */
    private fun setupWebClient(webview: WebView) {
        webview.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                titleBar.title = title
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    progressBar.visibility = View.GONE
                } else {
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                }
            }
        }
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}