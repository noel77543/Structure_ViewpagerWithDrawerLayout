package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.web;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.toolbox.ToolBoxPopupWindow;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.webview.CustomWebChromeClient;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.webview.CustomWebClient;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.webview.CustomWebView;

public class WebActivity extends FragmentActivity implements ToolBoxPopupWindow.OnToolBoxSelectListener, CustomWebView.OnProgressChangeListener {

    @BindView(R.id.image_view_tool)
    ImageView imageViewTool;
    @BindView(R.id.button_back)
    Button buttonBack;
    @BindView(R.id.text_view_title)
    TextView textViewTitle;
    @BindView(R.id.web_view)
    CustomWebView webView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private ToolBoxPopupWindow toolBoxPopupWindow;
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
        toolBoxPopupWindow = new ToolBoxPopupWindow(this);
        toolBoxPopupWindow.setOnToolBoxSelectedListener(this);
        imageViewTool.setVisibility(View.VISIBLE);
        textViewTitle.setText(page);

        webView.setOnProgressChangeListener(this);
        webView.setLoadingMessage(page);
        webView.open(url);
    }

    //-------------

    /***
     *  當進度更新
     * @param newProgress
     */
    @Override
    public void onProgressChanged(final int newProgress) {
        if (newProgress >= 100) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setProgress(newProgress);
        }
    }
    //----------

    @OnClick({R.id.button_back, R.id.image_view_tool})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                back();
                break;
            case R.id.image_view_tool:
                toolBoxPopupWindow.showAtLocation(imageViewTool, Gravity.TOP | Gravity.END, 0, 0);
                break;
        }

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


    //-----------

    @Override
    public void onToolBoxSelected(View view, int position) {

        switch (position) {
            //重新整理
            case 0:
                webView.open(url);
                break;
            //複製連結
            case 1:
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text label", webView.getUrl());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, getString(R.string.web_tool_toast_copy), Toast.LENGTH_SHORT).show();
                break;
            //分享連結
            case 2:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, page);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, String.format(getString(R.string.web_sharing_text), getString(R.string.app_name), webView.getUrl()));
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.web_tool_box_share)));
                break;
            //以其他應用開啟
            case 3:
                Intent openIntent = new Intent();
                openIntent.setAction(Intent.ACTION_VIEW);
                openIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                if (!url.substring(0, 4).equalsIgnoreCase("http")) {
                    url = "https:" + url;
                }
                openIntent.setData(Uri.parse(url));
                startActivity(openIntent);
                break;
            //關閉網頁
            case 4:
                finish();
                break;
        }
    }

}
