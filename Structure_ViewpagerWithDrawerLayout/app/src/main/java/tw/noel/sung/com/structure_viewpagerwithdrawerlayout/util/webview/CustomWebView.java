package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.webview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;


/**
 * Created by noel on 2018/6/19.
 */

public class CustomWebView extends WebView  {
    private Context context;
    protected WebSettings webSettings;

    public CustomWebView(Context context) {
        super(context);
        this.context = context;
        init();
    }
    //----------

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    //----------

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    //----------
    private void init() {
        webSettings = getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);

    }
    //---------

    /**
     * 載入訊息
     * @param pageName 前往的頁面名稱
     *                 todo  step1:
     */
    public void setLoadingMessage(String pageName) {
        setWebViewClient(new CustomWebClient(context, String.format(context.getString(R.string.web_loading_message_go_to), pageName)));
    }
    //---------
    /**
     * load url
     * todo  step2:
     */
    public void open(String url) {
        loadUrl(url);
    }
}
