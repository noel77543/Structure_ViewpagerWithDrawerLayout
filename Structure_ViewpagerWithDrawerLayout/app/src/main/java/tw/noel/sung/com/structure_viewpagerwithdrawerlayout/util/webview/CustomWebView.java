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

public class CustomWebView extends WebView implements CustomWebChromeClient.OnProgressChangeListener {
    private Context context;
    private WebSettings webSettings;
    private CustomWebClient webClient;
    private CustomWebChromeClient webChromeClient;
    private OnProgressChangeListener onProgressChangeListener;


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
        webClient = new CustomWebClient(context);
        webChromeClient = new CustomWebChromeClient();
        webChromeClient.setOnProgressChangeListener(this);
        setWebChromeClient(webChromeClient);
        setWebViewClient(webClient);

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
    @Override
    public void onProgressChanged(int newProgress) {
        onProgressChangeListener.onProgressChanged(newProgress);
    }
    //---------

    public interface OnProgressChangeListener {
        void onProgressChanged(int newProgress);
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.onProgressChangeListener = onProgressChangeListener;
    }


    //---------

    /**
     * 載入訊息
     *
     * @param pageName 前往的頁面名稱
     *                 todo  step1:
     */
    public void setLoadingMessage(String pageName) {
        webClient.setLoadingMessage(String.format(context.getString(R.string.web_loading_message_go_to), pageName));
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
