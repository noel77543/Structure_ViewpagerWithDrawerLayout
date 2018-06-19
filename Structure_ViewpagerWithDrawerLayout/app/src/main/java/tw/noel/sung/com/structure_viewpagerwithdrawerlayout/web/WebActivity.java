package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.web;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.connect.ConnectInfo;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.webview.CustomWebView;

/**
 * Created by noel on 2018/6/19.
 */

public class WebActivity extends FragmentActivity {

    @BindView(R.id.button_back)
    Button buttonBack;
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.web_view)
    CustomWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        init();
    }

    //-------
    private void init() {
        webView.setLoadingMessage(getString(R.string.navigation_item_yahoo));
        webView.open(ConnectInfo.URL_YAHOO);
    }

    //----------

    @OnClick(R.id.button_back)
    public void onViewClicked() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}
