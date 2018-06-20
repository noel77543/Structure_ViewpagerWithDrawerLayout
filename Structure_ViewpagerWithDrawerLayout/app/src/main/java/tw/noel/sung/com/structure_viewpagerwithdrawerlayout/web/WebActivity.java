package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.webview.CustomWebView;

public class WebActivity extends FragmentActivity {


    @BindView(R.id.button_back)
    Button buttonBack;
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.web_view)
    CustomWebView webView;

    private String url;
    private String page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        init();
    }

    //-------

    /***
     *  init
     */
    private void init() {
        Intent data = getIntent();
        url = data.getStringExtra("webURL");
        page = data.getStringExtra("pageTitle");

        textView.setText(page);
        webView.setLoadingMessage(page);
        webView.open(url);
    }

    //----------

    @OnClick({R.id.button_back})
    public void onViewClicked(View view) {
        back();
    }

    //---------
    @Override
    public void onBackPressed() {
        back();
    }

    //-----------

    /***
     *  返回
     */
    private void back() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}
