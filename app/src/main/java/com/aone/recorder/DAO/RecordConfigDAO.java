package com.aone.recorder.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaRecorder;

import java.util.Objects;

/**
 * @ProjectName: Recorder
 * @Package: com.aone.recorder.DAO
 * @ClassName: ConfigDAO
 * @Author: Xeon
 * @University: Gansu Agricultural University
 * @Department: Information Science and Technology
 * @Major: Computer Science and Technology
 * @Date: 2020/6/13 3:42
 * @Desc: 配置数据表相关操作，该表仅能修改与查询，有且仅有一行数据，只允许在第一次运行时写入。
 */
public class RecordConfigDAO {
    public static final String CONFIG_TABLE_NAME = "RecordConfig";
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public RecordConfigDAO(Context context) {
        dbHelper = new DBHelper(context, "recorder", null, 1);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 初始化配置数据表
     *
     * @param context context
     */
    public void initConfig(Context context) {
        ContentValues values = new ContentValues();
        values.put("AudioSource", MediaRecorder.AudioSource.DEFAULT);
        values.put("AudioSamplingRate", 44100);
        values.put("OutputFormat", MediaRecorder.OutputFormat.DEFAULT);
        values.put("AudioChannels", 2);
        values.put("AudioEncoder", MediaRecorder.AudioEncoder.DEFAULT);
        values.put("AudioEncodingBitRate", 192000);
        values.put("DefaultFilePath", Objects.requireNonNull(context.getExternalCacheDir()).getAbsolutePath() + "/");
        values.put("OutputFileFormat", "MP3");

        db.insert(CONFIG_TABLE_NAME, null, values);
    }

    /**
     * 更新配置数据表
     *
     * @param key   键
     * @param value 值
     */
    public void updateConfig(String key, String value) {
        ContentValues values = new ContentValues();
        values.put(key, value);

        db.update(CONFIG_TABLE_NAME, values, "id = ?", new String[]{"1"});
        close();
    }

    /**
     * 获取配置数据表数据集
     *
     * @return Cursor数据集
     */
    public Cursor queryConfig() {
        cursor = db.query(CONFIG_TABLE_NAME, new String[]{"AudioSource", "AudioSamplingRate", "OutputFormat", "AudioChannels", "AudioEncoder", "AudioEncodingBitRate", "DefaultFilePath", "OutputFileFormat"}, null, null, null, null, null);
        cursor.moveToNext();
        return cursor;
    }

    public void close() {
        if (cursor != null)
            cursor.close();
        if (db != null)
            db.close();
        if (dbHelper != null)
            dbHelper.close();
    }

}
