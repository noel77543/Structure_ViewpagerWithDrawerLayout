package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class CustomWebChromeClient extends WebChromeClient {
    private OnProgressChangeListener onProgressChangeListener;

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        onProgressChangeListener.onProgressChanged(newProgress);
    }
    //----------

    public interface OnProgressChangeListener {
        void onProgressChanged(int newProgress);
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.onProgressChangeListener = onProgressChangeListener;
    }
}
