package com.example.asus.todayhead.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.utils.NetWorkUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by asus on 2017/3/24.
 */
public class SystemSetActivity extends Activity implements View.OnClickListener {

    private ImageView imageView;
    private TextView check_release, off_line, clear_cache,clear_cache_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_set);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.image_back);
        check_release = (TextView) findViewById(R.id.check_release);
        off_line = (TextView) findViewById(R.id.off_line);
        clear_cache= (TextView) findViewById(R.id.clear_cache);
        clear_cache_size= (TextView) findViewById(R.id.clear_cache_size);
        imageView.setOnClickListener(this);
        check_release.setOnClickListener(this);
        off_line.setOnClickListener(this);
        clear_cache.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                Intent intent = new Intent(SystemSetActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.check_release:
                boolean isAvailable = NetWorkUtils.isAvailable(SystemSetActivity.this);
                if (!isAvailable) {
                    Toast.makeText(SystemSetActivity.this, "网络未连接，请及时连接", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(intent2);
                } else {
                    //连接成功
                    Toast.makeText(SystemSetActivity.this, "网络连接成功", Toast.LENGTH_SHORT).show();
                    downLoad();
                }
                break;
            case R.id.off_line:
                break;
            case R.id.clear_cache:
                clearCache();
                break;
        }
    }

    private void clearCache() {
        DataCleanManager data=new DataCleanManager();
        data.cleanApplicationData(this);
    }

    private void downLoad() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SystemSetActivity.this);
        builder.setTitle("版本更新");
        builder.setMessage("现在检测到新版本，是否更新?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloadApk();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    public void downloadApk() {
        String[] items = {"wifi", "手机流量"};
        new AlertDialog.Builder(this).setTitle("网络选择").setIcon(R.mipmap.ic_launcher)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                updateApk();
                                break;
                            case 1:
                                boolean mobile = NetWorkUtils.isMobile(SystemSetActivity.this);
                                if (mobile) {
                                    Toast.makeText(SystemSetActivity.this, "现在未使用wifi，将使用流量下载", Toast.LENGTH_SHORT).show();
                                    Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                                    startActivity(wifiSettingsIntent);
                                }
                                break;
                        }
                        dialog.dismiss();
                    }
                }).show();
    }

    private void updateApk() {
        String url = "http://imtt.dd.qq.com/16891/3B9164274F34F47DF2BEFF1FF4E3F064.apk?fsname=com.tencent.mobileqq_6.7.0_496.apk&csr=97c2";
        RequestParams params = new RequestParams(url);
        //保存到sd卡
        params.setSaveFilePath(Environment.getExternalStorageDirectory() + "/update/");
        //自动文件命令
        params.setAutoRename(true);
        //下载
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }
        });
    }


    class DataCleanManager {
        /**
         * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context
         */
        public void cleanInternalCache(Context context) {
            deleteFilesByDirectory(context.getCacheDir());
        }

        /**
         * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context
         */
        public void cleanDatabases(Context context) {
            deleteFilesByDirectory(new File("/data/data/"
                    + context.getPackageName() + "/databases"));
        }

        /**
         * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) * * @param
         * context
         */
        public void cleanSharedPreference(Context context) {
            deleteFilesByDirectory(new File("/data/data/"
                    + context.getPackageName() + "/shared_prefs"));
        }

        /**
         * 按名字清除本应用数据库 * * @param context * @param dbName
         */
        public void cleanDatabaseByName(Context context, String dbName) {
            context.deleteDatabase(dbName);
        }

        /**
         * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context
         */
        public void cleanFiles(Context context) {
            deleteFilesByDirectory(context.getFilesDir());
        }

        /**
         * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
         * context
         */
        public void cleanExternalCache(Context context) {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                deleteFilesByDirectory(context.getExternalCacheDir());
            }
        }

        /**
         * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath
         */
        public void cleanCustomCache(String filePath) {
            deleteFilesByDirectory(new File(filePath));
        }


        /**
         * 清除本应用所有的数据 * * @param context * @param filepath
         */
        public void cleanApplicationData(Context context) {
            cleanInternalCache(context);
            cleanExternalCache(context);
            cleanDatabases(context);
            cleanSharedPreference(context);
            cleanFiles(context);
        }

        /**
         * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
         */
        private void deleteFilesByDirectory(File directory) {
            if (directory != null && directory.exists() && directory.isDirectory()) {
                for (File item : directory.listFiles()) {
                    item.delete();
                }
                Toast.makeText(SystemSetActivity.this, "已经清除缓存", Toast.LENGTH_SHORT).show();
                clear_cache_size.setText("0.00M");
            }

        }
    }
}