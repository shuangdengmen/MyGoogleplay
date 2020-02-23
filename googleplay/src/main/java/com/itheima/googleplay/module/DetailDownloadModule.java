package com.itheima.googleplay.module;

import android.widget.Button;
import android.widget.ProgressBar;

import com.itheima.googleplay.R;
import com.itheima.googleplay.bean.AppInfo;
import com.itheima.googleplay.http.download.DownloadInfo;
import com.itheima.googleplay.http.download.DownloadManager;
import com.itheima.googleplay.util.ApkUtils;

import butterknife.InjectView;
import butterknife.OnClick;

public class DetailDownloadModule extends BaseModule<AppInfo> implements DownloadManager.DownloadObserver {
    @InjectView(R.id.pb_progress)
    ProgressBar pbProgress;
    @InjectView(R.id.btn_download)
    Button btnDownload;


    @Override
    protected int getLayoutId() {
        return R.layout.layout_detail_download;
    }

    private AppInfo appInfo;

    @Override
    public void loadData(AppInfo appInfo) {
        this.appInfo = appInfo;
        DownloadManager.create().registerDownloadObserver(this);
        DownloadInfo downloadInfo = DownloadManager.create().getDownloadInfo(appInfo);
        if (downloadInfo!=null){
            onDownloadUpdate(downloadInfo);
        }
    }

    @OnClick(R.id.btn_download)
    public void onClick() {
        DownloadManager downloadManager = DownloadManager.create();
        DownloadInfo downloadInfo = downloadManager.getDownloadInfo(appInfo);
        if (downloadInfo == null) {
            downloadManager.download(appInfo);
        } else {
            if (downloadInfo.state == DownloadManager.STATE_DOWNLOADING
                    || downloadInfo.state == DownloadManager.STATE_WAITING) {
                downloadManager.pause(appInfo);
            } else if (downloadInfo.state == DownloadManager.STATE_PAUSE
                    || downloadInfo.state == DownloadManager.STATE_ERROR) {
                downloadManager.download(appInfo);
            } else if (downloadInfo.state == DownloadManager.STATE_FINISH) {
                ApkUtils.install(downloadInfo.path);
            }
        }
    }

    private int calculateProgress(DownloadInfo downloadInfo) {
        int progress = (int) (downloadInfo.currentLength*100f/downloadInfo.size);
        btnDownload.setBackgroundResource(0);
        pbProgress.setProgress(progress);
        return progress;
    }

    @Override
    public void onDownloadUpdate(DownloadInfo downloadInfo) {
        if(appInfo==null||downloadInfo.id!=appInfo.id){
            return;
        }

        switch (downloadInfo.state) {
            case DownloadManager.STATE_NONE:
                btnDownload.setText("下载");
                break;
            case (DownloadManager.STATE_DOWNLOADING):
                int progress = calculateProgress(downloadInfo);
                btnDownload.setText(progress+"");
                break;
            case (DownloadManager.STATE_PAUSE):
                btnDownload.setText("继续下载");
                calculateProgress(downloadInfo);
                break;
            case (DownloadManager.STATE_FINISH):
                btnDownload.setText("安装");
                break;
            case (DownloadManager.STATE_ERROR):
                btnDownload.setText("下载失败重新下载");
                break;
            case (DownloadManager.STATE_WAITING):
                btnDownload.setText("等待中");
                break;
        }
    }
}
