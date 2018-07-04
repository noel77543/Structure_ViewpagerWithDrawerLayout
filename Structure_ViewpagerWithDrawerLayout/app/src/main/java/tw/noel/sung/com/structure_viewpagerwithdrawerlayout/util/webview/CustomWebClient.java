package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.webview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.dialog.LoadingDialog;


/**
 * Created by noel on 2018/6/19.
 */
public class CustomWebClient extends WebViewClient {

    private Context context;
    private LoadingDialog loadingDialog;

    public CustomWebClient(Context context) {
        this.context = context;
        loadingDialog = new LoadingDialog(context);
    }

    //--------------

    public void setLoadingMessage( String loadingMessage){
        loadingDialog.setLoadingMessage(loadingMessage);

    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        loadingDialog.dismiss();
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    //todo 需另加上這句 才支援https 且需 補上判斷 否則上架會遭拒 _noel
    @Override
    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(context.getString(R.string.web_title));
        builder.setMessage(context.getString(R.string.web_message));
        builder.setPositiveButton(context.getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.proceed();
            }
        });
        builder.setNegativeButton(context.getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.cancel();
                Toast.makeText(context, context.getString(R.string.web_toast), Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
