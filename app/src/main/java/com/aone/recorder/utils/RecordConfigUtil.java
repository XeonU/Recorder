package com.aone.recorder.utils;

import android.content.Context;
import android.database.Cursor;

import com.aone.recorder.DAO.RecordConfigDAO;
import com.aone.recorder.model.RecordConfig;

/**
 * @ProjectName: Recorder
 * @Package: com.aone.recorder.utils
 * @ClassName: RecordConfigUtil
 * @Author: Xeon
 * @University: Gansu Agricultural University
 * @Department: Information Science and Technology
 * @Major: Computer Science and Technology
 * @Date: 2020/6/13 5:00
 * @Desc:
 */
public class RecordConfigUtil {
    public static RecordConfig getRecordConfig(Context context) {
        // 初始化数据库
        RecordConfigDAO recordConfigDAO = new RecordConfigDAO(context);
        // 获取配置数据
        Cursor ConfigCursor = recordConfigDAO.queryConfig();

        RecordConfig recordConfig = new RecordConfig(ConfigCursor.getInt(ConfigCursor.getColumnIndex("AudioSource")),
                ConfigCursor.getInt(ConfigCursor.getColumnIndex("AudioSamplingRate")),
                ConfigCursor.getInt(ConfigCursor.getColumnIndex("OutputFormat")),
                ConfigCursor.getInt(ConfigCursor.getColumnIndex("AudioChannels")),
                ConfigCursor.getInt(ConfigCursor.getColumnIndex("AudioEncoder")),
                ConfigCursor.getInt(ConfigCursor.getColumnIndex("AudioEncodingBitRate")),
                ConfigCursor.getString(ConfigCursor.getColumnIndex("DefaultFilePath")),
                ConfigCursor.getString(ConfigCursor.getColumnIndex("OutputFileFormat")));
        recordConfigDAO.close();
        return recordConfig;
    }

    public static String decodeOutputFileFormat(String outputFileFormat) {
        switch (outputFileFormat) {
            case "MP3":
                return "MP3";
            case "WAV":
                return "WAVE";
            case "AAC":
                return "AAC";
            case "AMR":
                return "AMR";
            default:
                return "Default";
        }
    }

    public static String encodeOutputFileFormat(String outputFileFormat) {
        switch (outputFileFormat) {
            case "MP3":
                return "MP3";
            case "WAVE":
                return "WAV";
            case "AAC":
                return "AAC";
            case "AMR":
                return "AMR";
            default:
                return "Default";
        }
    }

    public static String decodeAudioChannels(int audioChannelsCode) {
        switch (audioChannelsCode) {
            case 1:
                return "Single";
            case 2:
                return "Double";
            default:
                return "Default";
        }
    }

    public static int encodeAudioChannels(String audioChannels) {
        switch (audioChannels) {
            case "Single":
                return 1;
            case "Double":
                return 2;
            default:
                return 3;
        }
    }

    public static String decodeAudioSamplingRate(int audioSamplingRateCode) {
        return audioSamplingRateCode + "Hz";
    }

    public static String encodeAudioSamplingRate(String audioSamplingRate) {
        return audioSamplingRate.split("Hz")[0];
    }
}
